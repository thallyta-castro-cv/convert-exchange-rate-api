package adapters.outbound.controller;

import application.service.ConvertCurrency;
import domain.ExchangeRate;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/convert-currency")
public class ConvertCurrencyController {

    @Inject
    private ConvertCurrency convertCurrency;

    public ConvertCurrencyController(ConvertCurrency convertCurrency) {
        this.convertCurrency = convertCurrency;
    }

    @GET
    @Path("{base}/{target}")
    public Response getExchangeRate(@PathParam("base") String base, @PathParam("target") String target){
        ExchangeRate exchangeRate = convertCurrency.getConversionRate(base, target);
        return Response.ok(exchangeRate).build();
    }

    @GET
    public Response getAllExchangeRates() {
        List<ExchangeRate> exchangeRates = convertCurrency.getAllConversionRate();
        return Response.ok(exchangeRates).build();
    }

}
