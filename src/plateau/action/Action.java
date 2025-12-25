package plateau.action;
import plateau.gamecharacter.Survivor;

/**
 * Abstract class representing an action that can be performed by a survivor.
 */
public abstract class Action {
    
    /**
     * Executes the action on the given survivor.
     *
     * @param s The survivor on which the action is to be executed.
     */
    public abstract void execute(Survivor s);
    
    /**
     * Returns a string representation of the action.
     *
     * @return A string describing the action.
     */
    public abstract String toString();

    
}