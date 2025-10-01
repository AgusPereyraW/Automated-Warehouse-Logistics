package warehouse;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private final String resourcesPath = "resources/input.txt";

    @BeforeEach
    void setup() throws IOException {
        // Crear un archivo de prueba válido
        String content = "5 5\n0\n1 1 N\n";
        Files.createDirectories(Paths.get("resources"));
        Files.write(Paths.get(resourcesPath), content.getBytes());
    }

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(resourcesPath));
    }

    @Test
    void testMainWithValidFile() {
        assertDoesNotThrow(() -> Warehouse.main(new String[]{}));
    }

    @Test
    void testMainFileNotFound() {
        File file = new File(resourcesPath);
        file.delete(); // borrar para provocar excepción

        assertDoesNotThrow(() -> Warehouse.main(new String[]{}));
        // no falla porque se captura la excepción
    }
}
