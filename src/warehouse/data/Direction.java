package warehouse.data;

//
/**
 * The Enum Direction.
 */
public enum Direction {
	
	/** The n. */
	N {
        @Override
        public Coordinate move(Coordinate actual) {
            return new Coordinate(actual.getX(), actual.getY() + 1);
        }
    },
    
    /** The s. */
    S {
        @Override
        public Coordinate move(Coordinate actual) {
            return new Coordinate(actual.getX(), actual.getY() - 1);
        }
    },
    
    /** The e. */
    E {
        @Override
        public Coordinate move(Coordinate actual) {
            return new Coordinate(actual.getX() + 1, actual.getY());
        }
    },
    
    /** The w. */
    W {
        @Override
        public Coordinate move(Coordinate actual) {
            return new Coordinate(actual.getX() - 1, actual.getY());
        }
    },
    
    /** The p. */
    P { 
        @Override
        public Coordinate move(Coordinate actual) {
            return null;
        }
    };

    /**
     * Move.
     *
     * @param actual the actual
     * @return the coordinate
     */
    //cada direccion sabe como se mueve
    public abstract Coordinate move(Coordinate actual);

    /**
     * From char.
     *
     * @param c the c
     * @return the direction
     */
    public static Direction fromChar(char c) {
        return switch (c) {
            case 'N' -> N;
            case 'S' -> S;
            case 'E' -> E;
            case 'W' -> W;
            case 'P' -> P;
            default -> throw new IllegalArgumentException("Dirección inválida: " + c);
        };
    }
}
