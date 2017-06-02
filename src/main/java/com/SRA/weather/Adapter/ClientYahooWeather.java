package com.SRA.weather.Adapter;


import org.json.JSONObject;
import org.springframework.context.annotation.Bean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.ws.Response;

@Path("/v1/")
@Produces("application/jason")
public interface ClientYahooWeather {

    @GET
    @Path("public/yql")
    String getWeather(@QueryParam("q") String q, @QueryParam("format") String format);


}
