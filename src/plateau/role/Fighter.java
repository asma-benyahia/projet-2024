package plateau.role;

import java.util.Random;

import plateau.gamecharacter.Survivor;

/**
 * The Fighter role adds 1 to each die roll when attacking a zombie.
 */
public class Fighter extends Role {

 
    /**
     * Constructs a new Fighter role with the given pseudo.
     *
     */
    public Fighter() {
        super("Fighter");
      
    }

    // ce que j'ai supprimer on doit le faire dans FighterAction 
    // ici on doit juste ecrire ce que fait chaque role 
    // a verifier avec le prof 


     /**
     * Gets the description of the Fighter role.
     *
     * @return The description of the Fighter role.
     */
    @Override
    public String descriptionRole() {
        return " Le combattant ajoute 1 à chaque lancer de dé lors qu’il attaque un zombie. ";
    }

     /**
     * Gets the type of the role.
     *
     * @return The type of the role.
     */
    @Override
    public String getTypeRole() {
        return "Fighter";
    }

     /**
     * Simulates an attack with a bonus die roll.
     *
     * @return The result of the attack with the bonus die roll.
     */
    public int attackWithBonus(){
        Random random = new Random();
        int res = random.nextInt(6) + 1;
        return res ;
    }

   /**
     * Adds specific actions to the given survivor.
     * This method is not implemented for the Lucky role, as it does not have any specific actions.
     *
     * @param s The survivor to add actions to.
	 * 
     * @throws UnsupportedOperationException The Lucky role does not have any specific actions.
     */
	@Override
	public void addSpecificActions(Survivor s) {
		throw new UnsupportedOperationException("The Lucky role does not have any specific actions");
	}


   

}
