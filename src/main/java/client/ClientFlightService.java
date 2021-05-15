package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class ClientFlightService {
    private static final String URI = "http://localhost:8080/flights";
    private final Client client;

    public ClientFlightService(Client client) {
        this.client = client;
    }

    public void getFlights(String request) throws Exception {
        WebResource resource = client.resource(URI + "/getFlights");
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

    public void addFlight(String request) throws Exception {
        WebResource resource = client.resource(URI + "/addFlight");
        ClientResponse response = resource
                .queryParam("request", request)
                .get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new Exception(String.valueOf(response.getStatus()));
        }
        System.out.println("Id новой записи - " + response.getEntity(String.class));
    }

    public void changeFlight(String request) throws Exception {
        WebResource resource = client.resource(URI + "/changeFlight");
        ClientResponse response = resource
                .queryParam("request", request)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new Exception(String.valueOf(response.getStatus()));
        }
        System.out.println("Статус выполнения: " + response.getEntity(String.class));
    }

    public void deleteFlight(int id) throws Exception {
        WebResource resource = client.resource(URI + "/deleteFlight/" + id);
        ClientResponse response = resource
                .get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new Exception(String.valueOf(response.getStatus()));
        }
        System.out.println("Статус выполнения: " + response.getEntity(String.class));
    }

    private void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
    }
}
