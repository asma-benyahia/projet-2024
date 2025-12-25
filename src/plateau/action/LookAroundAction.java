package plateau.action;

import java.util.*;

import plateau.*;
import plateau.gamecharacter.*;
import plateau.room.Continental;

/**
 * Represents an action for a survivor to look around in the current cell.
 * Provides descriptions of doors (open or closed) and the presence of survivors and zombies in the cell.
 */
public class LookAroundAction extends Action {

    /**
     * Constructs a new LookAroundAction object for the specified survivor.
     *
     */
    public LookAroundAction() {
    	super();
    }

    /**
     * Provides descriptions of the doors in the current cell, indicating whether they are open or closed.
     */
    public void doorsDescription(Survivor survivor){
        Cell cell = survivor.getCurrentCell();
        Map<Direction, Door> doors = cell.getDoors();

        for(Direction d : doors.keySet()){
            if(doors.get(d).isOpen()){
                System.out.println(("La porte de direction " + d + " est ouverte "));

            }
            else {
                System.out.println("La porte de direction " + d + " est fermé");
            }
        }
    }

    /**
     * Provides a description of the current cell, including the number of zombies and survivors present.
     * Excludes the survivor performing the action from the count of survivors.
     */
    public void cellDescription(Survivor survivor){
        Cell cell = survivor.getCurrentCell();

        List<Survivor> listeOfSurvivor = cell.getSurvivors();
        List<Zombie> listeOfZombie = cell.getZombies();

        //listeOfSurvivor.remove(survivor);// on exclue le survivor qui execute laction 

        int numberOfZombie = listeOfZombie.size();
        int numberOfSurvivor = listeOfSurvivor.size();

        System.out.println("Nombre de zombies présents dans cette cellule : " + numberOfZombie);
        System.out.println("Nombre de survivant présents dans cette cellule : " + numberOfSurvivor);
    }

    /**
     * Executes the look around action, providing descriptions of doors and the cell.
     *
     * @param s the survivor performing the action
     */
    @Override
    public void execute(Survivor s) {
        Cell cell = s.getCurrentCell();
        if(cell.isContinental() ){
            System.out.println("The survivor is in continental !he can't see anything ! ");
        }else {
            doorsDescription(s);
            cellDescription(s);

        }
       

    }

	@Override
	public String toString() {
		return "take a look";
	}
}