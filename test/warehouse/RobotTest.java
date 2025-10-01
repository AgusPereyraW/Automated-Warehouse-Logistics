package warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import warehouse.data.Coordinate;
import warehouse.data.Grid;
import warehouse.data.Robot;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        // Grilla de 5x5 sin obstáculos iniciales
        grid = new Grid(5, 5, 0);
    }

    @Test
    void testInitialState() {
        Robot robot = new Robot(new Coordinate(2, 2), grid);

        assertEquals(new Coordinate(2, 2), robot.getCurrPos());
        assertFalse(robot.isBusy());
        assertFalse(robot.isLost());
        assertEquals("2 2 NO-ITEM-PICKED", robot.getReport());
    }

    @Test
    void testMoveNorth() {
        Robot robot = new Robot(new Coordinate(2, 2), grid);

        robot.move('N');

        assertEquals(new Coordinate(2, 3), robot.getCurrPos());
        assertFalse(robot.isLost());
    }

    @Test
    void testMoveSouth() {
        Robot robot = new Robot(new Coordinate(2, 2), grid);

        robot.move('S');

        assertEquals(new Coordinate(2, 1), robot.getCurrPos());
    }

    @Test
    void testMoveEast() {
        Robot robot = new Robot(new Coordinate(2, 2), grid);

        robot.move('E');

        assertEquals(new Coordinate(3, 2), robot.getCurrPos());
    }

    @Test
    void testMoveWest() {
        Robot robot = new Robot(new Coordinate(2, 2), grid);

        robot.move('W');

        assertEquals(new Coordinate(1, 2), robot.getCurrPos());
    }

    @Test
    void testMoveOutsideGrid_Lost() {
        Robot robot = new Robot(new Coordinate(0, 0), grid);

        robot.move('S'); // sale fuera de la grilla

        assertTrue(robot.isLost());
        assertEquals("0 0 LOST", robot.getReport());
    }

    @Test
    void testMoveWithObstacle() {
        grid.addObstacle(new Coordinate(2, 3));
        Robot robot = new Robot(new Coordinate(2, 2), grid);

        robot.move('N'); // hay obstáculo en (2,3)

        assertEquals(new Coordinate(2, 2), robot.getCurrPos()); // se queda donde estaba
        assertFalse(robot.isLost());
    }

    @Test
    void testPickItem() {
        Robot robot = new Robot(new Coordinate(1, 1), grid);

        robot.move('P');

        assertTrue(robot.isBusy());
        assertEquals("1 1 ITEM-PICKED", robot.getReport());
    }

    @Test
    void testInvalidInstruction() {
        Robot robot = new Robot(new Coordinate(1, 1), grid);

        assertThrows(IllegalArgumentException.class, () -> robot.move('X'));
    }
}
