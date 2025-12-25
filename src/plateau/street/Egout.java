package plateau.street;

import plateau.Board;
import plateau.gamecharacter.Zombie;

/**
 * Represents a sewer cell on the game board.
 * Extends the Street class.
 */
public class Egout extends Street {
	
	private Board board;

    /**
     * Constructs a sewer cell with the specified coordinates.
     *
     * @param x The X coordinate of the sewer cell.
     * @param y The Y coordinate of the sewer cell.
     * @param board The game board.
     */
    public Egout(int x, int y, Board board) {
        super(x, y);
        this.board = board;
    }

    /**
     * Gets the description of the sewer cell.
     *
     * @return The description of the sewer cell.
     */
    public String description() {
        return " E ";
    }

    @Override
    protected boolean estEgout() {
        return true;
    }

    @Override
    protected boolean isCarrefourP() {
        return false;
    }
    
    /**
     * appears zombies on board
     * @param numberOfZombies to spawn
     */
    public void createZombies(int nbZombies) {
        for (int i = 0; i < nbZombies; i++) {
            // Génération aléatoire d'un type de zombie
            Zombie zombie = board.genererTypeDeZombieAleatoire();
            addZombie(zombie); // Ajoute le zombie à l'égout
        }
    }

}
