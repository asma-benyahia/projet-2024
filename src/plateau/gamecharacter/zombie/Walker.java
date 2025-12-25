package plateau.gamecharacter.zombie;

import plateau.Board;
import plateau.Cell;
import plateau.gamecharacter.Zombie;

public class Walker extends Zombie {

	public Walker(Board board) {
		super(1, 1, board); // Damage and lifePoints for a walker zombie
	}

	@Override
	public String getType() {
		return "Walker";
	}

	@Override
	public void changeCell(Cell newCell) {
		super.changeCell(newCell);
	}

	@Override
	public String descriptionZombies() {
		return "W";
	}

	public String descriptionGameCharacter() {
		return " Zw ";
	}

	@Override
	public int getDamage() {
		return 1;
	}

}
