package api;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import model.Config;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        Config.init();
        HttpServer server = HttpServerFactory.create("http://localhost:" + Config.getServerPort() + "/");
        server.start();
        System.out.println("Server running");
        System.out.println("Visit: http://localhost:" + Config.getServerPort() + "/api");
        System.out.println("Hit return to confirm to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
