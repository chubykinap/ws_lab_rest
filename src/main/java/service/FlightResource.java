package service;

import service.Exceptions.AuthenticationException;
import service.Exceptions.SQLTransactionException;
import sun.misc.BASE64Decoder;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@RequestScoped
@Path("/flights")
public class FlightResource {
    private final String USER = "user";
    private final String PASSWORD = "pass";

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

    @POST
    @Path("/addFlight")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String addFlight(@QueryParam("request") String request,
                            @HeaderParam("Authentication") String auth)
            throws SQLTransactionException, AuthenticationException {
        authenticate(auth);
        return Integer.toString(new DbMethods().addFlight(request));
    }

    @POST
    @Path("/changeFlight")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String changeFlight(@QueryParam("request") String request,
                               @HeaderParam("Authentication") String auth)
            throws SQLTransactionException, AuthenticationException {
        authenticate(auth);
        return new DbMethods().changeFlight(request);
    }

    @DELETE
    @Path("/deleteFlight/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteFlight(@PathParam("id") int request,
                               @HeaderParam("Authentication") String auth)
            throws SQLTransactionException, AuthenticationException {
        authenticate(auth);
        return new DbMethods().deleteFlight(request);
    }

    private void authenticate(String auth) throws AuthenticationException {
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(auth.split(" ")[1]);
        } catch (Exception e) {
            throw AuthenticationException.DEFAULT;
        }
        String[] split = new String(bytes).split(":");
        if (!split[0].equals(USER) || !split[1].equals(PASSWORD)) {
            throw AuthenticationException.DEFAULT;
        }
    }
}
