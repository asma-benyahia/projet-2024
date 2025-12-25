package plateau.street;

import plateau.*;

/**
 * Represents a street cell on the game board.
 * Extends the Cell class.
 */
public class Street extends Cell {

    /**
     * Constructs a Street object with the specified coordinates.
     *
     * @param x The X coordinate of the street cell.
     * @param y The Y coordinate of the street cell.
     */
    public Street(int x, int y) {
        super(x, y);

    }

    /**
     * Gets the description of the street cell.
     *
     * @return The description of the street cell.
     */
    @Override
    public String description() {
        return " S ";

    }

    /**
     * Checks if the cell represents a street.
     *
     * @return true since this is a street cell.
     */
    @Override
    public boolean isStreet() {
        return true;
    }

    @Override
    protected boolean estEgout() {
        return false;
    }

    @Override
    protected boolean isCarrefourP() {
        return false;
    }

    @Override
    public boolean isRoom() {
        return false;
    }

    @Override
    public boolean isContinental() {
        return false;
    }

    @Override
    public boolean isPharmacy() {
        return false;
    }


}
