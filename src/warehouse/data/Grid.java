package warehouse.data;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class Grid.
 */
public class Grid {
	
	/** The max X. */
	private final int maxX;
    
    /** The max Y. */
    private final int maxY;
    
    /** The obstacles. */
    private final Set<Coordinate> obstacles;

    /**
     * Instantiates a new grid.
     *
     * @param maxX the max X
     * @param maxY the max Y
     * @param obstacles the obstacles
     */
    public Grid (int maxX, int maxY, int obstaclesNum){
        this.maxX=maxX;
        this.maxY=maxY;
        this.obstacles= new HashSet<>(obstaclesNum); //creo el conjunto con el tamano dependiendo de la cantidad de obstaculos
    }

    public void addObstacle(Coordinate obstacle) {
    	obstacles.add(obstacle);
    }
    
    /**
     * Checks for obstacle.
     *
     * @param coord the coord
     * @return true, if successful
     */
    public boolean hasObstacle(Coordinate coord){ 
        return obstacles.contains(coord);
    }

    /**
     * Checks if is inside.
     *
     * @param coord the coord
     * @return true, if is inside
     */
    public boolean isInside(Coordinate coord){
        int x = coord.getX();
        int y = coord.getY();
        return (x >= 0 && x <= maxX) && (y >= 0 && y <= maxY);
    }
}
