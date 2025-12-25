package plateau.role;
import plateau.action.HealerAction;
import plateau.gamecharacter.Survivor;
/**
 * The Healer role represents a survivor with the ability to heal other survivors.
 */
public class Healer extends Role {

    /**
     * Constructs a Healer with the given pseudo.
     *
     */
    public Healer (){
        super("Healer");
    }

    /**
     * Gets the type of the role.
     *
     * @return The type of the role ("Healer").
     */
    @Override
    public String getTypeRole() {
        return "Healer";
    }

    /**
     * Gets the description of the Healer role.
     *
     * @return A description of the Healer role.
     */
    @Override
    public String descriptionRole() {
        return " Le soigneur possède la capacité particulière de pouvoir exécuter l’action “soigner”. Cette action consiste à rendre1 point de vie à un des survivants dans sa zone (lui compris)";

    }
    

     /**
     * Adds specific actions to the given survivor.
     * A Healer adds a HealerAction to the survivor.
     *
     * @param s The survivor to add actions to.
     */
    @Override
    public void addSpecificActions(Survivor s) {
        s.addAction(new HealerAction());
    }

    
}
