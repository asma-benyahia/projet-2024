package plateau.gamecharacter.zombie;

import plateau.Board;
import plateau.Cell;
import plateau.gamecharacter.Zombie;

public class Runner extends Zombie {

	public Runner(Board board) {
		super(1, 2, board); // Damage and lifePoints for a runner zombie
	}//2

	@Override
	public String getType() {
		return "Runner";
	}


	@Override
	public void changeCell(Cell newCell) {
		super.changeCell(newCell);
	}

	@Override
	public String descriptionZombies() {
		return "Ru";
	}

	public String descriptionGameCharacter() {
		return " Zr ";
	}

	@Override
	public int getDamage() {
		return 1;
	}

}
