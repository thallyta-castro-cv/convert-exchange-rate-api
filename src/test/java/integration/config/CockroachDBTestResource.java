package integration.config;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.CockroachContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

public class CockroachDBTestResource implements QuarkusTestResourceLifecycleManager {

    private CockroachContainer cockroachdb;

    @Override
    public Map<String, String> start() {
        cockroachdb = new CockroachContainer("cockroachdb/cockroach:v22.2.0");
        cockroachdb.start();

        await().atMost(10, TimeUnit.SECONDS).until(() -> {
            try (Connection conn = DriverManager.getConnection(cockroachdb.getJdbcUrl(),
                    cockroachdb.getUsername(),
                    cockroachdb.getPassword())) {
                return conn.isValid(2);
            } catch (SQLException e) {
                return false;
            }
        });

        Map<String, String> conf = new HashMap<>();
        conf.put("quarkus.datasource.jdbc.url", cockroachdb.getJdbcUrl());
        conf.put("quarkus.datasource.username", cockroachdb.getUsername());
        conf.put("quarkus.datasource.password", cockroachdb.getPassword());

        return conf;
    }

    @Override
    public void stop() {
        if (cockroachdb != null) {
            cockroachdb.stop();
        }
    }
}

