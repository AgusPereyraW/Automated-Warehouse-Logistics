package warehouse.data;

//
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
        Direction direccion = Direction.fromChar(instruction);
        Coordinate nextPos = direccion.move(currPos);

        if (direccion == Direction.P) {
            setBusy();
            return;
        }

        if (nextPos != null && !grid.hasObstacle(nextPos) && !lost) {
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
