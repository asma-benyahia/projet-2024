package plateau.gamecharacter.zombie;

import plateau.Board;
import plateau.Cell;
import plateau.gamecharacter.Zombie;

public class Abomination extends Zombie {

	public Abomination(Board board) {
		super(3, 6, board); // Damage and lifePoints for an abomination zombie
	}

	
	@Override
	public String getType() {
		return "Abomination";
	}


	@Override
	public void changeCell(Cell newCell) {
		super.changeCell(newCell);
	}

	@Override
	public String descriptionZombies() {
		return "A";
	}

	public String descriptionGameCharacter() {
		return " Za ";
	}

	@Override
	public int getDamage() {
		return 3;
	}

}
