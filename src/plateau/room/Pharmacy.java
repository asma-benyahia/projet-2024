package plateau.room;

/**
 * Represents a pharmacy room on the game board.
 * Extends the Room class.
 */
public class Pharmacy  extends Room{

    /**
     * Constructs a pharmacy room with the specified coordinates.
     *
     * @param x The X coordinate of the pharmacy room.
     * @param y The Y coordinate of the pharmacy room.
     */
    public Pharmacy(int x, int y) {
        super(x, y);
    }

    /**
     * Gets the description of the pharmacy room.
     *
     * @return The description of the pharmacy room.
     */
    @Override
    public String description() {
        return " P ";
        
    }
    
    @Override
    public boolean isPharmacy(){
        return true;
    }

}
