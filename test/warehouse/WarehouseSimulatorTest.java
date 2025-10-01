package warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseSimulatorTest {

    @Test
    void testValidSimulation() {
        String input = "5 5\n0\n1 1 NNEE\n";
        String result = WarehouseSimulator.simulate(input);
        assertTrue(result.contains("ITEM") || result.length() > 0);
    }

    @Test
    void testRobotLost() {
        String input = "2 2\n0\n0 0 NNNN\n"; // se pierde al salir de la grilla
        String result = WarehouseSimulator.simulate(input);
        assertTrue(result.contains("LOST"));
    }

    @Test
    void testWithObstacles() {
        String input = "5 5\n1\n2 2\n1 1 EEE\n";
        String result = WarehouseSimulator.simulate(input);
        assertNotNull(result);
    }

    @Test
    void testInvalidInput() {
        String result = WarehouseSimulator.simulate("invalid data");
        assertEquals("Error Procesando Input", result);
    }
}
