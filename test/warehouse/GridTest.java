package warehouse;
import org.junit.jupiter.api.Test;

import warehouse.data.Coordinate;
import warehouse.data.Grid;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testIsInside_ValidCoordinates() {
        Grid grid = new Grid(5, 5, 0);

        assertTrue(grid.isInside(new Coordinate(0, 0)));
        assertTrue(grid.isInside(new Coordinate(5, 5)));
        assertTrue(grid.isInside(new Coordinate(3, 4)));
    }

    @Test
    void testIsInside_InvalidCoordinates() {
        Grid grid = new Grid(5, 5, 0);

        assertFalse(grid.isInside(new Coordinate(-1, 0)));
        assertFalse(grid.isInside(new Coordinate(0, -1)));
        assertFalse(grid.isInside(new Coordinate(6, 2)));
        assertFalse(grid.isInside(new Coordinate(2, 6)));
    }

    @Test
    void testHasObstacle_WhenEmpty() {
        Grid grid = new Grid(5, 5, 0);

        assertFalse(grid.hasObstacle(new Coordinate(2, 2)));
    }

    @Test
    void testAddObstacle_ThenHasObstacle() {
        Grid grid = new Grid(5, 5, 1);
        Coordinate obstacle = new Coordinate(3, 3);

        grid.addObstacle(obstacle);

        assertTrue(grid.hasObstacle(new Coordinate(3, 3)));
        assertFalse(grid.hasObstacle(new Coordinate(4, 4)));
    }

    @Test
    void testAddMultipleObstacles() {
        Grid grid = new Grid(5, 5, 3);
        Coordinate o1 = new Coordinate(1, 1);
        Coordinate o2 = new Coordinate(2, 2);
        Coordinate o3 = new Coordinate(3, 3);

        grid.addObstacle(o1);
        grid.addObstacle(o2);
        grid.addObstacle(o3);

        assertTrue(grid.hasObstacle(o1));
        assertTrue(grid.hasObstacle(o2));
        assertTrue(grid.hasObstacle(o3));
        assertFalse(grid.hasObstacle(new Coordinate(4, 4)));
    }
}

