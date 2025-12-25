package plateau.gamecharacter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plateau.street.Street;
import plateau.Board;
import plateau.gamecharacter.zombie.Runner;



class ZombieTest {

	private Runner z;
	private Survivor s;
	private Board board;
	private Street street;

	@BeforeEach
	void setUp() throws Exception {
		this.z=new Runner(board);
		this.s=new Survivor(board);
		this.street=new Street(2,2);
        z.setCurrentCell(street);
        s.setCurrentCell(street);

	}

	@Test
	void attackTest() {
		int lifePnt=s.getLifePoints();
		z.attack();
		int updatedLifePoints = s.getLifePoints();
		assertTrue(lifePnt>updatedLifePoints);
	}

	//ne fonctionne pas

}
