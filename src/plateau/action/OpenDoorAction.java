package plateau.action;

import java.util.Random;

import plateau.Cell;
import plateau.Direction;
import plateau.Door;
import plateau.equipment.Equipment;
import plateau.gamecharacter.Survivor;
import plateau.listchooser.InteractiveListChooser;
import plateau.listchooser.ListChooser;

/**
 * Represents an action where a survivor attempts to open a door in a specified direction.
 * To open a door, the survivor must have appropriate equipment that allows them to open doors.
 */
public class OpenDoorAction extends Action {
	
	private static ListChooser<Direction> directionList = new InteractiveListChooser<Direction>();
	
	/**
     * Initializes a new instance of OpenDoorAction with the associated survivor.
     *
     * @param survivor The survivor associated with this action.
     */
	public OpenDoorAction() {
		super();
		
	}

	/**
     * Executes the action of opening a door for the associated survivor.
     *
     * @param s The survivor performing the action.
     */
	@Override
	public void execute(Survivor s) {
		
		
		Cell currentCell = s.getCurrentCell();
		//if (currentCell == null) {
            //System.out.println("Player's cell is null. Cannot play this round.");
            //return;
        //}
		Equipment e = s.getCurrentEquipment();
		if(e.canOpenDoor()){
			//Direction direction = directionList.choose( Direction.valuesAsList());
			Direction[] directions = Direction.values();
        	Random random = new Random();
        	Direction direction = directions[random.nextInt(directions.length)];
			Door door = currentCell.getDoor(direction);
			s.openDoor(door);
			if(currentCell.cellHasZombie(currentCell.getX(), currentCell.getY())){
				System.out.println(zombiesInCell());
			}

			
			//Le fait d’ouvrir une porte fait apparaître systématiquement entre 1 et 3 zombies, et au plus une Abomination ou un Balaise

		}
	}



	private String zombiesInCell() {
		int numZombie = (int)(Math.random()*3) + 1;
        
        return "" + numZombie;
    }

	@Override
	public String toString() {
		return "open a door";
	}


}
