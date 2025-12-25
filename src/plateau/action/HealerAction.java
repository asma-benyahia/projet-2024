package plateau.action;


import java.util.List;

import plateau.gamecharacter.Survivor;

import plateau.role.Role;

/**
 * Represents an action where a survivor heals themselves.
 */
public class HealerAction extends Action {

	private int lifePointToAdd;

    /**
     * Constructs a HealerAction object.
     *
     */
    public HealerAction() {
        super();
    }


    /**
     * Get the number of life points to add.
     *
     * @return The number of life points to add.
     */
    public int getLifePointToAdd(){
        return this.lifePointToAdd;
    }

    /**
     * Set the number of life points to add.
     *
     * @param n The number of life points to add.
     */
    public void setLifePointToAdd(int n ){
        this.lifePointToAdd=n;
    }

   
    /**
     * Executes the healing action on the survivor.
     *
     * @param s The survivor performing the healing action.
     */
    @Override
    public void execute(Survivor s) {
        List<Survivor> survivorInZone = s.getCurrentCell().getSurvivors();

        for(Survivor survivor : survivorInZone){
            if (!survivor.isDead() && survivor.getLifePoints() < 6){
                survivor.addLifePoint(1);
            }
            
        }
        
        
    }


    @Override
    public String toString() {
        return "heal";
    }




}
