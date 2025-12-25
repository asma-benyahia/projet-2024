package plateau.gamecharacter;

import java.util.List;
import java.util.Random;
import plateau.action.*;
import plateau.*;
import plateau.action.TakeInHandAction;
import plateau.listchooser.InteractiveListChooser;
import plateau.listchooser.ListChooser;
import plateau.equipment.Equipment;
import plateau.equipment.Equipment.*;

public abstract class Zombie extends GameCharacter {
	protected int damage;
	protected Board board;
	private Direction curDirection;

	/**
	 * Constructor to create a zombie with specified damage, and life points.
	 *
	 * @param damage      the damage inflicted by the zombie
	 * @param lifePoints  the life points of the zombie
	 * @param currentCell
	 */

	public Zombie(int damage, int lifePoints, Board board) {
		this.lifePoints = lifePoints;
		this.damage = damage;
		this.board = board;
	}

	/**
	 * Returns the board associated with this Zombie.
	 *
	 * @return The board associated with this Zombie.
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Sets the board for the Zombie.
 	* 
 	* @param board the board to set
 	*/
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Returns the damage inflicted by this zombie.
	 * Override this method in subclasses to define different damage values for
	 * different zombie types.
	 * 
	 * @return The damage inflicted by this zombie.
	 */
	public int getDamage(){
		return this.damage;
	} 

	/**
	 * Returns the current direction of the Zombie
	 */
	public Direction getCurrentDirection() {
		return curDirection;
	}

	/**
 	* Sets the damage for the Zombie.
 	*
 	* @param damage the damage to set.
 	*/
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
 	* Gets the life points for the Zombie.
 	*
 	* @return the life points
	 */
	public int getLifePoints() {
		return this.lifePoints;
	}

	/**
	 * Method to inflict damage to the zombie.
	 *
	 * @param damage the amount of damage to inflict to the zombie
	 */
	public void takeDamage(int damage) {
		lifePoints -= damage;
		if (lifePoints <= 0) {
			System.out.println("Zombie is eliminated ");
		} else {
			System.out.println("Zombie now has " + lifePoints + " life points.");
		}
	}
	
	/**
 	* Checks if the next cell in the current direction is a room or an invalid position, meaning the zombie is blocked and cannot enter.
 	*
 	* @return true if the next cell is a room or an invalid position, false otherwise
 	*/
	private boolean isBlocked() {
		Cell currentCell = getCurrentCell();
		int currentX = currentCell.getX();
		int currentY = currentCell.getY();
		int nextX = currentX;
		int nextY = currentY;
		switch (getCurrentDirection()) {
			case Right:
				nextX++;
				break;
			case Left:
				nextX--;
				break;
			case Up:
				nextY++;
				break;
			case Down:
				nextY--;
				break;
		}
		if (board.isValidPosition(nextX, nextY)) {
			Cell nextCell = board.getCell()[nextX][nextY];
			return nextCell.isRoom();
		} else {
			return true;
		}
	}

	/**
 	* The zombie attacks a survivor in the same zone.
 	*
 	* If there is a survivor in the same zone as the zombie, the method randomly selects one survivor and inflicts damage to them based on the zombie's damage value.
 	* If there is no survivor in the same zone as the zombie, the method prints a message indicating that there is no survivor in the same zone.
 	*/
	public void attack() {
		if(this.currentCell.isContinental()){
			System.out.println("Zombie in the continental , he can't attack");
			this.removeActionPoints();
		}
		if (this.currentCell.containsSurvivor()) {
			List<Survivor> survivorsInZone = this.currentCell.getSurvivors();

			Random random = new Random();
			Survivor s = survivorsInZone.get(random.nextInt(survivorsInZone.size()));

			//s.subLifePoint(this.getDamage());
			s.removeLifePoints();
			this.removeActionPoints();

			System.out.println ("the zombie inflicted " + this.getDamage() + " points of damage on the survivor");
		} else {
			System.out.println("No survivors in the same area as the zombie.");
		}
	}

	/**
 	* The zombie attacks a survivor in the same zone.
 	*
 	* If there is a survivor in the same zone as the zombie, the method randomly selects one survivor and inflicts damage to them based on the zombie's damage value.
 	* If there is no survivor in the same zone as the zombie, the method prints a message indicating that there is no survivor in the same zone.
 	*/
	public void attack2() {
		if (this.currentCell.isContinental()) {
			System.out.println("Zombie in the continental, he can't attack");
			this.removeActionPoints();
			return; 
		}
	
		if (this.currentCell.containsSurvivor()) {
			List<Survivor> survivorsInZone = this.currentCell.getSurvivors();
			Random random = new Random();
			Survivor s = survivorsInZone.get(random.nextInt(survivorsInZone.size()));
	
			s.removeLifePoints(); 
			this.removeActionPoints(); 
	
			System.out.println("The zombie inflicted " + this.getDamage() + " points of damage on the survivor");
			
			if (s.isDead()) {
				System.out.println(s.getName() + " is dead");
				survivorsInZone.remove(s); 
				s.getCurrentCell().removeSurvivor(s);
				if (s.getCurrentCell().isRoom()) {
					List<Equipment> survivorEquipment = s.getInventory();
					for (Equipment equipment : survivorEquipment) {
						s.getCurrentCell().addEquipment(equipment);
					}
				}
				else {
					s.removeAllEquipment();
				}
			}
		} else {
			System.out.println("No survivors in the same area as the zombie.");
		}
	}

	/**
 	* Returns a 2D array of cells representing the game board.
		 *
 	* @return a 2D array of cells representing the game board
 	*/
	public Cell[][] getCell() {
		return board.getCell();
	}

	@Override
	public void setCurrentCell2(Cell cell){
		this.currentCell.removeZombie(this);
		this.currentCell= cell;
		cell.addZombie(this);
	}

	/**
	 * Moves the zombie gradually towards the noisiest area of the board
	 */
	@Override
	public void move() {

		Cell currentCell = getCurrentCell();
		Cell loudestZone = board.findLoudestZone(currentCell);

		int loudestX = loudestZone.getX();
		int loudestY = loudestZone.getY();

		while (currentCell.getX() != loudestX || currentCell.getY() != loudestY) {
			int currentX = getCurrentCell().getX();
			int currentY = getCurrentCell().getY();

			int nextX = currentX;
			int nextY = currentY;

			if (currentX < loudestX) {
				nextX++;
			} else if (currentX > loudestX) {
				nextX--;
			}

			if (currentY < loudestY) {
				nextY++;
			} else if (currentY > loudestY) {
				nextY--;
			}

			if (getBoard().isValidPosition(nextX, nextY)) {
				Cell nextCell = board.getCell()[nextX][nextY];

				if (!nextCell.isRoom()) {
					setCurrentCell2(nextCell);
					System.out.println("The zombie moved towards the cell [" + nextX + "][" + nextY + "].");

					if (loudestX == getCurrentCell().getX() && loudestY == getCurrentCell().getY()) {
						System.out.println("The zombie has reached the noisiest area");
						return;
					}


				} else if (isBlocked()) {
					System.out.println("The zombie is blocked in front of the door.");
					break;
				}
			}
		}
	}

	public void move2() {
		Cell currentCell = getCurrentCell();
		Cell loudestZone = board.findLoudestZone(currentCell);
		if (currentCell == null) {
			throw new IllegalStateException("Current cell is null for the zombie.");
		}
	
		int currentPositionX = currentCell.getX();
		int currentPositionY = currentCell.getY();
		System.out.println("Current cell coordinates: X = " + currentPositionX + ", Y = " + currentPositionY);
	
		Direction direction = Direction.values()[(int) (Math.random() * Direction.values().length)]; // Choix aléatoire d'une direction
		Door door = currentCell.getDoor(direction);
	
		if (!door.isOpen() || door.isOpen()) {
			if (direction == Direction.Up) {
				currentPositionY--;
			} else if (direction == Direction.Down) {
				currentPositionY++;
			} else if (direction == Direction.Right) {
				currentPositionX++;
			} else if (direction == Direction.Left) {
				currentPositionX--;
			}
	
			if (getBoard().isValidPosition(currentPositionX, currentPositionY)) {
				Cell nextCell = getBoard().getCell()[currentPositionX][currentPositionY];
				setCurrentCell2(nextCell);
	
				System.out.println("The zombie moved towards the cell [" + currentPositionX + "][" + currentPositionY + "].");
	
				if (loudestZone.getX() == getCurrentCell().getX() && loudestZone.getY() == getCurrentCell().getY()) {
					System.out.println("The zombie has reached the noisiest area.");
					return;
				}
			} else {
				System.out.println("Invalid destination cell.");
			}
		} else {
			System.out.println("The door is closed. Please open the door.");
		}
	
		System.out.println("NEW Current cell coordinates: X = " + getCurrentCell().getX() + ", Y = " + getCurrentCell().getY());
	}

	public abstract String getType();

	@Override
	public void changeCell(Cell newCell) {
		// TODO Auto-generated method stub

	}
	

	public abstract String descriptionZombies();

	/**
	 * @return a string representation of the zombie
 	*/
	public String descriptionGameCharacter() {
		return " Z ";
	}


	

	/**
	 * The zombie performs an action based on whether there is a survivor in its current cell.
 	* If there is a survivor, the zombie attacks them. Otherwise, the zombie moves towards the noisiest cell.
 	*/
	public void zombieAct() {
		if (currentCell.containsSurvivor()) {
			attack();
			System.out.println("Zombie attack");
		} else {
			move2(); 
			System.out.println("Zombie move");
		}
	}

}
