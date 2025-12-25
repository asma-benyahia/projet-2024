package plateau.gamecharacter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plateau.Board;

class GameCharacterTest {
	private Survivor s1;
	private Board b;
	

	@BeforeEach
	public void setUp(){
		this.s1=new Survivor(b);
	}

	@Test
	public void testIfIsDead() {
		s1.subLifePoint(5);
		assertTrue(s1.isDead());
		
	}
	
	@Test
	public void testIfMoveIsOk() {

	}
	
	@Test
	public void testIfChangeCell() {
		
	}

}