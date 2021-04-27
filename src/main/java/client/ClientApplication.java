package client;


import com.sun.jersey.api.client.Client;

import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        Client client = Client.create();
        String request = new Scanner(System.in).nextLine();
        try {
            new ClientFlightService().getFlights(client, request);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
