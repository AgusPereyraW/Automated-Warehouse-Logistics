package warehouse;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * The Class WarehouseHttpServer.
 */
public class WarehouseHttpServer {

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/simulate", new SimulationHandler());
        server.setExecutor(null);
        System.out.println("Servidor iniciado en http://localhost:8080/simulate");
        server.start();
    }

    /**
     * The Class SimulationHandler.
     */
    static class SimulationHandler implements HttpHandler {
        /**
         * Handle.
         *
         * @param exchange the exchange
         * @throws IOException Signals that an I/O exception has occurred.
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            String input = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String output = WarehouseSimulator.simulate(input);

            byte[] responseBytes = output.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        }
    }
}
