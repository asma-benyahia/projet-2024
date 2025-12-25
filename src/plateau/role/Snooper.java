package plateau.role;

import plateau.action.SearchARoomAction;
import plateau.gamecharacter.Survivor;
/**
 * The Snooper role allows the survivor to perform a free search action every turn.
 */
public class Snooper extends Role {

    /**
     * Constructs a Snooper object with the given pseudo.
     *
     */
    public Snooper() {
        super("Snooper");
    }


    /**
     * Returns the type of this role.
     *
     * @return The type of this role, which is "Snooper".
     */
    @Override
    public String getTypeRole() {
        return "Snooper";
    }

     /**
     * Returns a description of the Snooper role.
     *
     * @return A description of the Snooper role.
     */
    public String descriptionRole() {
        return "Le fouineur peut réaliser une action de fouille gratuite à chaque tour. ";
    }

    /**
     * Adds specific actions to the given survivor.
     * In this case, adds a search action to perform a free search every turn.
     *
     * @param s The survivor to add actions to.
     */
    @Override
    public void addSpecificActions(Survivor s) {
        s.addAction(new SearchARoomAction());

    }
}