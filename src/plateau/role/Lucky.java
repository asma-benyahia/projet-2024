package plateau.role;

import java.util.Random;

import plateau.gamecharacter.Survivor;


/**
 * The Lucky role represents a survivor with the ability to roll an extra die when attacking a zombie.
 */
public class Lucky extends Role {


	/**
     * Constructs a Lucky with the given pseudo.
     *
     */
	public Lucky() {
		super("Lucky");
	}



	/**
     * Gets the type of the role.
     *
     * @return The type of the role ("Lucky").
     */
	@Override
	public String getTypeRole() {
		return "Lucky";
	}


	/**
     * Gets the description of the Lucky role.
     *
     * @return A description of the Lucky role.
     */
	public String descriptionRole() {
		return " The lucky survivor can roll an extra die when attacking a zombie";
	}

	public int attackWithExtraDie(){
		// on retourn un nombre aleatoire entre 1 et 6
		Random random = new Random();
		int res = random.nextInt(6)+1;
		return res;
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
