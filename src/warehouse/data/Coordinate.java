package warehouse.data;

import java.util.Objects;

/**
 * The Class Coordinate.
 */
public class Coordinate {
	
	/** The x. */
	private final int x;
    
    /** The y. */
    private final int y;

    /**
     * Instantiates a new coordinate.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Gets the x.
     *
     * @return the x
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the y.
     *
     * @return the y
     */
    public int getY(){
        return y;
    }
    
    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) 
        	return true;
        if (o == null || getClass() != o.getClass()) 
        	return false;
        Coordinate coord2 = (Coordinate) o;
        
        return x == coord2.x && y == coord2.y;
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
}
