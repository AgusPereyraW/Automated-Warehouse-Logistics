package warehouse;

import org.junit.jupiter.api.Test;
import warehouse.data.Direction;
import warehouse.data.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void testMoveNorth() {
        Coordinate c = new Coordinate(1, 1);
        Coordinate moved = Direction.N.move(c);
        assertEquals(new Coordinate(1, 2), moved);
    }

    @Test
    void testMoveSouth() {
        Coordinate c = new Coordinate(1, 1);
        Coordinate moved = Direction.S.move(c);
        assertEquals(new Coordinate(1, 0), moved);
    }

    @Test
    void testMoveEast() {
        Coordinate c = new Coordinate(1, 1);
        Coordinate moved = Direction.E.move(c);
        assertEquals(new Coordinate(2, 1), moved);
    }

    @Test
    void testMoveWest() {
        Coordinate c = new Coordinate(1, 1);
        Coordinate moved = Direction.W.move(c);
        assertEquals(new Coordinate(0, 1), moved);
    }

    @Test
    void testMovePick() {
        Coordinate c = new Coordinate(1, 1);
        assertNull(Direction.P.move(c));
    }

    @Test
    void testFromCharValid() {
        assertEquals(Direction.N, Direction.fromChar('N'));
        assertEquals(Direction.S, Direction.fromChar('S'));
        assertEquals(Direction.E, Direction.fromChar('E'));
        assertEquals(Direction.W, Direction.fromChar('W'));
        assertEquals(Direction.P, Direction.fromChar('P'));
    }

    @Test
    void testFromCharInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Direction.fromChar('X'));
    }
}
