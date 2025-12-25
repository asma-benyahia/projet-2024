package plateau.action;

import java.util.Random;

//import plateau.Board;
import plateau.Cell;
import plateau.Direction;
import plateau.Door;
import plateau.gamecharacter.Survivor;
import plateau.listchooser.InteractiveListChooser;
import plateau.listchooser.ListChooser;

/**
 * Représente une action permettant au survivant de se déplacer dans une direction spécifiée.
 */
public class MoveAction2 extends Action{

    //list of directions 
	private ListChooser<Direction> directionList = new InteractiveListChooser<Direction>();
	
	public MoveAction2() {
		super();
	}


    /**
     * Exécute l'action de déplacement du survivant.
     *
     * @param s Le survivant effectuant le déplacement.
     * @exception 
     */
	public void execute(Survivor s)throws IllegalStateException  {
		Cell currentCell = s.getCurrentCell();
        if(currentCell ==null){
            throw new IllegalStateException("Current cell is null for the survivor.");
        }
		
		int currentPositionX = currentCell.getX();
		int currentPositionY = currentCell.getY();
        System.out.println("Current cell coordinates: X = " + currentPositionX + ", Y = " + currentPositionY);
		Direction[] directions = Direction.values();
        Random random = new Random();
        Direction direction = directions[random.nextInt(directions.length)];

		
		//Direction direction = directionList.choose( Direction.valuesAsList());
		Door door = currentCell.getDoor(direction);
		if (door.isOpen()) {
			if (direction == Direction.Up) {
				currentPositionX--;
			}else if (direction == Direction.Down) {
				currentPositionX++;
			}else if (direction == Direction.Right) {
				currentPositionY++;
            }else if (direction== Direction.Left){
                currentPositionY--;

            }
			else {
				currentPositionX--;
			}
			if (currentPositionX >= 0 && currentPositionX < s.getCell().length && currentPositionY >= 0 && currentPositionY < s.getCell()[0].length) {
				Cell arrivalCell = s.getCell()[currentPositionX][currentPositionY];
				s.setCurrentCell2(arrivalCell);
			} else {
				System.out.println("Invalid movement: position out of bounds.");
			}
			//Cell ArrivalCell = s.getCell()[currentPositionX][currentPositionY];
			//s.setCurrentCell(ArrivalCell);
			

            System.out.println("NEW Current cell coordinates: X = " + currentPositionX + ", Y = " + currentPositionY);
		}
		else {
			System.out.println("The door is close. Please open the door");
		}	
	}
	
	@Override
	public String toString() {
	    return "move2";
	}
}

   



