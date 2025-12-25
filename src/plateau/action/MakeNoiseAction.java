package plateau.action;

import plateau.gamecharacter.Survivor;

/**
 * Represents an action where a survivor makes noise.
 */
public class MakeNoiseAction extends Action {


    /**
     * Constructs a MakeNoiseAction object with the specified noise level.
     *
     */
    public MakeNoiseAction() {
    	super();
    }


    /**
     * Executes the action of making noise by increasing the noise level in the survivor's current cell.
     * The survivor also spends action points for this action.
     *
     * @param survivor The survivor making noise.
     */
    @Override
    public void execute(Survivor survivor) {
        System.out.println("Niveau bruit actuel : " + survivor.getCurrentCell().getNoiseLevel());
        survivor.getCurrentCell().increaseNoiseLevel();
        System.out.println("Noise level : " + survivor.getCurrentCell().getNoiseLevel() + " in current cell :"
                + survivor.getCurrentCell().description());
    }


	@Override
	public String toString() {
		return "make noise";
	}
}
