package service;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import java.net.URI;

public class ServiceApplication {
    private static final URI SERVICE_URI = URI.create("http://localhost:8080");

    public static void main(String[] args) {
        HttpServer server = null;
        try {
            ResourceConfig config = new ClassNamesResourceConfig(FlightResource.class);
            server = GrizzlyServerFactory.createHttpServer(SERVICE_URI, config);
            server.start();
            System.in.read();
            stopServer(server);
        } catch (Exception e) {
            e.printStackTrace();
            stopServer(server);
        }
    }

    private static void stopServer(HttpServer server) {
        if (server != null)
            server.stop();
    }
}
