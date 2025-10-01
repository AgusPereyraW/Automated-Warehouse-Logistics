package warehouse;

import org.junit.jupiter.api.Test;

import warehouse.data.Coordinate;
import warehouse.data.Grid;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testIsInsideWithinBounds() {
        Grid grid = new Grid(5, 5, 0);

        assertTrue(grid.isInside(new Coordinate(0, 0)));
        assertTrue(grid.isInside(new Coordinate(5, 5)));
        assertTrue(grid.isInside(new Coordinate(3, 4)));
    }

    @Test
    void testIsInsideOutsideBounds() {
        Grid grid = new Grid(5, 5, 0);

        assertFalse(grid.isInside(new Coordinate(6, 5)));
        assertFalse(grid.isInside(new Coordinate(5, -1)));
        assertFalse(grid.isInside(new Coordinate(-1, 0)));
    }

    @Test
    void testAddAndCheckObstacle() {
        Grid grid = new Grid(5, 5, 1);
        Coordinate obstacle = new Coordinate(2, 2);

        assertFalse(grid.hasObstacle(obstacle)); // inicialmente vacío
        grid.addObstacle(obstacle);

        assertTrue(grid.hasObstacle(new Coordinate(2, 2)));
        assertFalse(grid.hasObstacle(new Coordinate(3, 3)));
    }
}


