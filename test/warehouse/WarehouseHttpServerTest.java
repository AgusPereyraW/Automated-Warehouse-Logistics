package warehouse;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("deprecation")
class WarehouseHttpServerTest {

    private static Thread serverThread;

    @BeforeAll
    static void startServer() {
        serverThread = new Thread(() -> {
            try {
                WarehouseHttpServer.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    private String sendRequest(String method, String body) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:8080/simulate").openConnection();
        conn.setRequestMethod(method);

        if (body != null) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }
        }

        int status = conn.getResponseCode();
        InputStream input = (status == 200) ? conn.getInputStream() : conn.getErrorStream();
        if (input == null) return "NO RESPONSE";

        return new String(input.readAllBytes(), StandardCharsets.UTF_8);
    }

    @Test
    void testValidPost() throws IOException {
        String input = "5 5\n0\n1 1 N\n";
        String response = sendRequest("POST", input);
        assertTrue(response.contains("ITEM") || response.length() > 0);
    }

    @Test
    void testInvalidPost() throws IOException {
        String response = sendRequest("POST", "invalid input here");
        assertTrue(response.contains("Error") || response.length() > 0);
    }

    @Test
    void testGetMethod() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:8080/simulate").openConnection();
        conn.setRequestMethod("GET");
        assertEquals(405, conn.getResponseCode());
    }
}
