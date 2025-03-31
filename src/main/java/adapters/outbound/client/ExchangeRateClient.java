package adapters.outbound.client;

import adapters.outbound.client.dto.ExchangeRateResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://v6.exchangerate-api.com/v6")
public interface ExchangeRateClient {

    @GET
    @Path("/{apiKey}/latest/{base}")
    @Produces(MediaType.APPLICATION_JSON)
    ExchangeRateResponse getExchangeRate(
            @PathParam("apiKey") String apiKey,
            @PathParam("base") String base
    );
}
