package service;

import service.Exceptions.SQLTransactionException;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("/flights")
public class FlightResource {
    @GET
    @Path("/getFlights")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Flight> getFlights(@QueryParam("request") String request)
            throws SQLTransactionException {
        return new DbMethods().getFlights(request);
    }

    @GET
    @Path("/getFlight/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Flight getFlight(@PathParam("id") int id)
            throws SQLTransactionException {
        return new DbMethods().getFlight(id);
    }

    @GET
    @Path("/addFlight")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String addFlight(@QueryParam("request") String request)
            throws SQLTransactionException {
        return Integer.toString(new DbMethods().addFlight(request));
    }

    @GET
    @Path("/changeFlight")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String changeFlight(@QueryParam("request") String request)
            throws SQLTransactionException {
        return new DbMethods().changeFlight(request);
    }

    @GET
    @Path("/deleteFlight/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteFlight(@PathParam("id") int request)
            throws SQLTransactionException {
        return new DbMethods().deleteFlight(request);
    }
}
