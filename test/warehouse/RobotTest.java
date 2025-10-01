package warehouse;

import org.junit.jupiter.api.Test;

import warehouse.data.Coordinate;
import warehouse.data.Grid;
import warehouse.data.Robot;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void testInitialState() {
        Grid grid = new Grid(5, 5, 0);
        Robot robot = new Robot(new Coordinate(0, 0), grid);

        assertEquals(new Coordinate(0, 0), robot.getCurrPos());
        assertFalse(robot.isBusy());
        assertFalse(robot.isLost());
        assertEquals("0 0 NO-ITEM-PICKED", robot.getReport());
    }

    @Test
    void testMoveInsideGrid() {
        Grid grid = new Grid(5, 5, 0);
        Robot robot = new Robot(new Coordinate(1, 1), grid);

        robot.move('N');
        assertEquals(new Coordinate(1, 2), robot.getCurrPos());
        assertFalse(robot.isLost());
        assertEquals("1 2 NO-ITEM-PICKED", robot.getReport());
    }

    @Test
    void testMoveIntoObstacle() {
        Grid grid = new Grid(5, 5, 1);
        grid.addObstacle(new Coordinate(2, 2));
        Robot robot = new Robot(new Coordinate(2, 1), grid);

        robot.move('N'); // intenta ir a (2,2)
        assertEquals(new Coordinate(2, 1), robot.getCurrPos()); // no se mueve
        assertFalse(robot.isLost());
    }

    @Test
    void testMoveOutsideGridSetsLost() {
        Grid grid = new Grid(2, 2, 0);
        Robot robot = new Robot(new Coordinate(2, 2), grid);

        robot.move('N'); // se sale del grid
        assertTrue(robot.isLost());
        assertEquals("2 2 LOST", robot.getReport());
    }

    @Test
    void testPickItem() {
        Grid grid = new Grid(5, 5, 0);
        Robot robot = new Robot(new Coordinate(1, 1), grid);

        robot.move('P'); 
        assertTrue(robot.isBusy());
        assertEquals("1 1 ITEM-PICKED", robot.getReport());
    }

    @Test
    void testLostRobotIgnoresMoves() {
        Grid grid = new Grid(1, 1, 0);
        Robot robot = new Robot(new Coordinate(1, 1), grid);

        robot.move('N'); // queda perdido
        assertTrue(robot.isLost());

        robot.move('E'); // debería ignorar
        assertEquals(new Coordinate(1, 1), robot.getCurrPos());
        assertEquals("1 1 LOST", robot.getReport());
    }
}


