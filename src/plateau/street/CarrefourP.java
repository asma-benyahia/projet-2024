package plateau.street;

/**
 * Represents a pedestrian crossroads cell on the game board.
 * Extends the Street class.
 */
public class CarrefourP extends Street {

    /**
     * Constructs a pedestrian crossroads cell with the specified coordinates.
     *
     * @param x The X coordinate of the pedestrian crossroads cell.
     * @param y The Y coordinate of the pedestrian crossroads cell.
     */
    public CarrefourP(int x, int y) {
        super(x, y);
    }

    /**
     * Gets the description of the pedestrian crossroads cell.
     *
     * @return The description of the pedestrian crossroads cell.
     */
    public String description() {
        return " Cp";
    }

    @Override
    protected boolean estEgout() {
        return false;
    }

    @Override
    protected boolean isCarrefourP() {
        return true;
    }
}
