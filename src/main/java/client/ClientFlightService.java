package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class ClientFlightService {
    private static final String URI = "http://localhost:8080";

    public void getFlights(Client client, String request) throws Exception {
        WebResource resource = client.resource(URI + "/flights/getFlights");
        ClientResponse response = resource
                .queryParam("request", request)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new Exception("Request failed");
        }
        GenericType<List<Flight>> type = new GenericType<List<Flight>>() {
        };
        printFlights(response.getEntity(type));
    }

    private void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
    }
}
