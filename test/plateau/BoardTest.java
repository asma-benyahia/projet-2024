package plateau;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import plateau.room.Room;
import plateau.street.Street;
import plateau.gamecharacter.zombie.Runner;

class BoardTest {
	private Board board;

	/* méthode exécutée avant l'exécution de chacune des méthodes de test */
	@BeforeEach
	public void befor() {
		this.board = new Board(5, 5);
	}

	@Test
	public void testGetCell() {
		Cell[][] cells = board.getCell();

		assertNotNull(cells);
		assertEquals(5, cells.length);
		assertEquals(5, cells[0].length);
	}

	@Test
	public void testGetLarg() {
		assertEquals(5, board.getLarg());
	}

	@Test
	public void testGetHaut() {
		assertEquals(5, board.getHaut());
	}

	@Test
	public void testCreateDoors() {
		Cell[][] cells = board.getCell();

		// On test la création des portes pour chaque cellule
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				Cell cell = cells[i][j];
				assertNotNull(cell);

				// Vérification l'etat des portes pour chaque cellule
				for (Direction dir : Direction.values()) {
					Door door = cell.getDoor(dir);
					assertNotNull(door);

					// Vérif si portes sont initialement fermées
					assertFalse(door.isOpen());

				}

			}
		}
	}

	@Test
	public void testBoardCreation() {
		int width = 7; // Ajustement de la largeur à 7
		int height = 5; // Ajustement de la hauteur à 5

		Board board = new Board(width, height);

		assertNotNull(board);
		assertEquals(width, board.getLarg());
		assertEquals(height, board.getHaut());

		Cell[][] cells = board.getCell();
		assertNotNull(cells);
		assertEquals(width, cells.length);
		assertEquals(height, cells[0].length);

		// Vérification si toutes les cellules sont initialisées
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				assertNotNull(cells[i][j]);
			}
		}
	}

	@Test
	public void perceRueTest() {
		board.perceRue(0, 0, 5, 5, true);
		// test pour plateau d'entrainement
		assertTrue(board.description().contains(" S "));

		assertTrue(board.description().contains(" R "));

		assertEquals(" Cp", board.getCell()[2][2].description());

		assertEquals(" E ", board.getCell()[0][2].description());
		assertEquals(" E ", board.getCell()[2][0].description());
		assertEquals(" E ", board.getCell()[4][2].description());
		assertEquals(" E ", board.getCell()[2][4].description());

	}

	@Test
	public void testInitDoors() {
		// Création d'une seule pièce pour le test
		Room room = new Room(3, 2);
		// Ajout de la pièce à la liste des pièces du plateau
		board.getCell()[3][2] = room;
		// Appel de la méthode à tester
		board.initDoors(List.of(room));
		// Room est en bordure, donc les portes vers l'extérieur devraient être fermées
		assertFalse(room.getDoor(Direction.Up).isOpen());
		assertFalse(room.getDoor(Direction.Down).isOpen());
		assertFalse(room.getDoor(Direction.Left).isOpen());
		assertFalse(room.getDoor(Direction.Right).isOpen());
	}

	@Test
	public void testRemoveZombie(){
		Runner z=new Runner(board);
		Runner z2=new Runner(board);
		board.addZombie(z);
		board.addZombie(z2);
		board.removeZombie(z);
		assertFalse(board.getZombies().contains(z)); 
    	assertTrue(board.getZombies().contains(z2));

	}

}
