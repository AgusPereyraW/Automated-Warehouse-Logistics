package warehouse;
import org.junit.jupiter.api.Test;

import warehouse.data.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testGetters() {
        Coordinate c = new Coordinate(3, 5);
        assertEquals(3, c.getX());
        assertEquals(5, c.getY());
    }

    @Test
    void testEqualsAndHashCode() {
        Coordinate c1 = new Coordinate(1, 2);
        Coordinate c2 = new Coordinate(1, 2);
        Coordinate c3 = new Coordinate(2, 3);

        // equals
        assertEquals(c1, c2);
        assertNotEquals(c1, c3);

        // hashCode
        assertEquals(c1.hashCode(), c2.hashCode());
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }

    @Test
    void testEqualsSelfAndNull() {
        Coordinate c = new Coordinate(0, 0);

        assertEquals(c, c);        // igual a sí mismo
        assertNotEquals(c, null);  // no igual a null
        assertNotEquals(c, "texto"); // no igual a otro tipo
    }
}
