package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/flights")
@Produces({MediaType.APPLICATION_JSON})
public class FlightResource {
    @GET
    @Path("/getFlights")
    public List<Flight> getFlights(@QueryParam("request") String request){
        return new DbMethods().getFlights(request);
    }
}
