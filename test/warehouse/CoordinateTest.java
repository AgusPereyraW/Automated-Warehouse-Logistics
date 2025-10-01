package warehouse;

import org.junit.jupiter.api.Test;

import warehouse.data.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testGetters() {
        Coordinate c = new Coordinate(3, 4);
        assertEquals(3, c.getX());
        assertEquals(4, c.getY());
    }

    @Test
    void testEqualsSameObject() {
        Coordinate c = new Coordinate(1, 1);
        assertEquals(c, c);
    }

    @Test
    void testEqualsAndHashCode() {
        Coordinate c1 = new Coordinate(2, 5);
        Coordinate c2 = new Coordinate(2, 5);
        Coordinate c3 = new Coordinate(3, 5);

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testEqualsWithDifferentTypeAndNull() {
        Coordinate c = new Coordinate(1, 1);
        assertNotEquals(c, "string");
        assertNotEquals(c, null);
    }
}

