package plateau.gamecharacter.zombie;

import plateau.Board;
import plateau.Cell;
import plateau.gamecharacter.Zombie;

public class Balaise extends Zombie {

	public Balaise(Board board) {
		super(2, 4, board); // Damage and lifePoints for a balaise zombie
	}

	@Override
	public String getType() {
		return "Balaise";
	}


	@Override
	public void changeCell(Cell newCell) {
		super.changeCell(newCell);
	}

	@Override
	public String descriptionZombies() {
		return "B";
	}

	public String descriptionGameCharacter() {
		return " Zb ";
	}

	@Override
	public int getDamage() {
		return 2;
	}

}
