package warehouse.data;

/**
 * The Class Robot.
 */
public class Robot {
	
	/** The curr pos. */
	private Coordinate currPos;
    
    /** The busy. */
    private boolean busy;
    
    /** The lost. */
    private boolean lost;
    
    /** The grid. */
    private final Grid grid;

    /**
     * Instantiates a new robot.
     *
     * @param currPos the curr pos
     * @param grid the grid
     */
    public Robot(Coordinate currPos, Grid grid){
        this.currPos=currPos;
        this.busy=false;
        this.lost=false;
        this.grid=grid;
    }

    /**
     * Gets the curr pos.
     *
     * @return the curr pos
     */
    public Coordinate getCurrPos(){
        return currPos;
    }

    /**
     * Checks if is busy.
     *
     * @return true, if is busy
     */
    public boolean isBusy(){
        return busy;
    }

    /**
     * Checks if is lost.
     *
     * @return true, if is lost
     */
    public boolean isLost(){
        return lost;
    }

    /**
     * Move.
     *
     * @param instruction the instruction
     */
    public void move(char instruction) {
    	Coordinate nextPos = null;
        switch (instruction) {
            case 'N' -> nextPos = new Coordinate(currPos.getX(), currPos.getY() + 1);
            case 'S' -> nextPos = new Coordinate(currPos.getX(), currPos.getY() - 1);
            case 'E' -> nextPos = new Coordinate(currPos.getX() + 1, currPos.getY());
            case 'W' -> nextPos = new Coordinate(currPos.getX() - 1, currPos.getY());
            case 'P' -> {
                setBusy();
            }
            default -> throw new IllegalArgumentException("instrucción inválida: " + instruction);
        }
        if (nextPos != null && !grid.hasObstacle(nextPos) && !lost) {//si hay obstaculo salteo la instruccion, y no vuelvo a actualizar las coordenadas si el robot se perdio
            if (grid.isInside(nextPos)) {
            	currPos = nextPos;
            } else {
                setLost();
            }
        }
    }

    /**
     * Sets the busy.
     */
    private void setBusy(){
        this.busy=true;
    }
    
    /**
     * Sets the lost.
     */
    private void setLost(){
        this.lost=true;
    }

    /**
     * Gets the report.
     *
     * @return the report
     */
    public String getReport() {
        if (lost) {
            return currPos.getX() + " " + currPos.getY() + " LOST";
        } 
        else if (busy) {
            return currPos.getX() + " " + currPos.getY() + " ITEM-PICKED";
        } else {
        	return currPos.getX() + " " + currPos.getY() + " NO-ITEM-PICKED";
        }
    }
}
