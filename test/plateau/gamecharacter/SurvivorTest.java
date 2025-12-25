package plateau.gamecharacter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import plateau.equipment.weapon.Rifle;
import plateau.equipment.weapon.Weapon;
import plateau.gamecharacter.exception.FullWeaponsBackException;
import plateau.role.Lucky;
import plateau.role.Role;

class SurvivorTest {
	private Survivor survivor;

	@BeforeEach
	public void setUp()  {
		//survivor = new Survivor();
	}

	

	@Test
	public void testCreationSurvivor(){
		assertNotNull(survivor);
		assertEquals(0, survivor.getExpertLevel());
		assertEquals(3, survivor.getActionPoint());
		assertEquals(5,survivor.getLifePoints());
	}


	@Test
	public void testAddLifePoint(){
		int initLifePoints = survivor.getLifePoints();
		int pointsToAdd = 2;
		survivor.addLifePoint(pointsToAdd);
		assertEquals(initLifePoints + pointsToAdd, survivor.getLifePoints());
	}
	
	@Test
	public void testSubLifePoint(){
		int initLifePoints = survivor.getLifePoints();
		int pointToSubtract = 2;
		survivor.subLifePoint(pointToSubtract);
		assertEquals(initLifePoints-pointToSubtract, survivor.getLifePoints() );
	}

	@Test 
	public void testAddActionPoint(){
		int initActionPoints = survivor.getActionPoint();
		int pointsToAdd = 2;
		survivor.addaActionpoints(pointsToAdd);
		assertEquals(initActionPoints+ pointsToAdd, survivor.actionPoints);
	}
	@Test
	public void testSubActionPoints(){
		int initActionPoints = survivor.getActionPoint();
		int pointsToSubtract = 2;
		survivor.subActionPoints(pointsToSubtract);
		assertEquals(initActionPoints-pointsToSubtract, survivor.getActionPoint());
	}

	@Test
	public void testGetRole(){
		Role r = new Lucky();
		survivor.addOneRole(r);
		assertEquals(r, survivor.getRole());

	}

}
