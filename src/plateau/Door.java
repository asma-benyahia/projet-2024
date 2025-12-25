package plateau;
/**
 * Represents a door in the game , which can be open or closed 
 */

public class Door {
    private boolean isOpen ;
    private boolean isOuter ;

    /**
     * Initializes a new closed door 
     */
    public Door(){
        this.isOpen = false ;
        this.isOuter = false;
    }
    
    /**
     * Checks if the door is currently open
     * @return true if the door is open , false if closed 
     */
    public boolean isOpen(){
        return this.isOpen ;
    }
    /**
     * Opens the door
     */
    public void openDoor(){
        if (! this.isOuter)
            isOpen = true ;
    }
    /**
     * Closes the door 
     */
    public void closeDoor(){
        isOpen = false ;
    }
    
    public void setOuter() {
        this.isOuter = true;
    }
}
