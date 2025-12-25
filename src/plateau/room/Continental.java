package plateau.room;

/**
 * Represents a continental room on the game board.
 * Extends the Room class.
 */
public class Continental extends Room {

    /**
     * Constructs a continental room with the specified coordinates.
     *
     * @param x The X coordinate of the continental room.
     * @param y The Y coordinate of the continental room.
     */
    public Continental(int x, int y) {
        super(x, y);
    }

    /**
     * Gets the description of the continental room.
     *
     * @return The description of the continental room.
     */
    @Override
    public String description() {
        return " C ";
        
    }

    @Override
    public boolean isContinental(){
        return true;
    }    
    
}
