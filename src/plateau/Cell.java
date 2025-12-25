package plateau;

import java.util.*;

import plateau.equipment.Equipment;
import plateau.equipment.tool.Tool;
import plateau.equipment.weapon.Weapon;
import plateau.gamecharacter.*;
import plateau.role.Role;
import plateau.Board;

//import plateau.equipment.*;
/**
 * An abstract class representing a cell on the game board
 */
public abstract class Cell {

    protected int x;
    protected int y;
    protected int nbDoors;
    protected Map<Direction, Door> doors;
    protected List<Survivor> theSurvivors;
    protected List<Door> theDoors;
    protected List<Zombie> theZombies;
    protected List<GameCharacter> theCharacters;
    protected List<Role> theRoles;
    protected List<Tool> theTools;
    protected List<Weapon> theWeapons;
    protected List<Equipment> theEquipments;
    protected int noiseLevel;
    private Board board;
    private String description;
    

    private final static char ZOMBIE = 'Z';
    private final static char SURVIVOR = 'S';

    /**
     * Initializes a new cell with the specified coordinates
     * 
     * @param x The X coordinate of the cell
     * @param y The Y coordinate of the cell
     * 
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.nbDoors = 4;
        this.doors = new HashMap<>();
        this.theSurvivors = new ArrayList<>();
        this.theZombies = new ArrayList<>();
        this.theRoles = new ArrayList<>();
        this.theTools = new ArrayList<>();
        this.theCharacters = new ArrayList<>();
        this.theWeapons = new ArrayList<>();
        this.theEquipments = new ArrayList<>();
        this.noiseLevel = 0;
        this.setDoorCell();
        this.board = board;
        this.description = "";

    }

    /**
     * Gets the X coordinate of the cell
     * 
     * @return The X coordinate of the cell
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the Y-coordinate of the cell
     * 
     * @return The Y coordinate of the cell
     */

    public int getY() {
        return this.y;
    }
    
    
    
    
    /**
     * Gets the description of the cell
     * @return the description of this cell
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Sets the description of the cell
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gests the number of doors in the cell
     * 
     * @return The number of doors in the cell
     */
    public int getNbDoors() {
        return this.nbDoors;
    }

    /**
     * get the cell's' noiseLevel
     * 
     * @return the cell'S noiseLevel
     */
    public int getNoiseLevel() {
        return this.noiseLevel;
    }

    /**
     * Increases Noise Level
     */
    public void increaseNoiseLevel() {
        this.noiseLevel += 1;
    }

    /**
     * Gets the door in the specified direction
     * 
     * @param direction The direction of the door
     * @return The door in the specified direction
     */
    public Door getDoor(Direction direction) {
        return this.doors.get(direction);
    }

    /**
     * Gets all doors of the cell
     * 
     * @return The map of doors associated with their direction
     */
    public Map<Direction, Door> getDoors() {
        return this.doors;
    }

    /**
     * 
     */
    public void setDoorCell() {
        for (Direction d : Direction.values()) {
            this.doors.put(d, new Door());
        }
    }

    /**
     * Adds a door to the cell in the specified direction
     * 
     * @param direction The direction in the which to add the door
     * @param door      The door to be added
     */
    public void addDoor(Direction direction, Door door) {
        doors.put(direction, door);
    }

    /**
     * add a zombie to the list of zombies
     * 
     * @param z the zombie to add
     */
    public void addZombie(Zombie z) {
        if (theZombies.contains(z)) {
            System.out.println("le zombie existe deja");
        }
        this.theZombies.add(z);
        z.setCurrentCell(this);
    }

    /**
     * add a survivor to the list of survivors
     * 
     * @param sthe survivor to add
     */
    public void addSurvivor(Survivor s) {
        //if (theSurvivors.contains(s)) {
          //  System.out.println("le survivor existe deja");
        //}
        this.theSurvivors.add(s);
        s.setCurrentCell(this);
    }

    /**
     * add a role
     * 
     * @param r The role to add
     */
    public void addRole(Role r) {
        if (theRoles.contains(r)) {
            System.out.println("le rôle existe deja");
        }
        this.theRoles.add(r);
    }

    /**
     * add a tool
     * 
     * @param t The tool to add
     */
    public void addTool(Tool t) {
        if (theTools.contains(t)) {
            System.out.println("l'équipement existe deja");
        }
        this.theTools.add(t);
    }

    /**
     * add an equipement
     * 
     * @param e The equipement to add
     */
    public void addEquipment(Equipment e) {
        if (theEquipments.contains(e)) {
            System.out.println("l'équipement existe deja");
        }
        this.theEquipments.add(e);
    }

    /**
     * Adds a weapon to the list of weapons in the cell.
     * 
     * @param w The weapon to add.
     * 
     */
    public void addWeapon(Weapon w) {
        if (theWeapons.contains(w)) {
            System.out.println("l'arme existe deja");
        }
        this.theWeapons.add(w);
    }

    /**
     * Removes a zombie from the list of zombies in the cell.
     * 
     * @param z The zombie to remove.
     */
    public void removeZombie(Zombie z) {
        if (!(theZombies.contains(z))) {
            System.out.println("le zombie n'existe pas ");
        }
        this.theZombies.remove(z);
    }

    /**
     * Removes a survivor from the list of survivors in the cell.
     * 
     * @param s The survivor to remove.
     * 
     */
    public void removeSurvivor(Survivor s) {
        if (!(theSurvivors.contains(s))) {
            System.out.println("le survivor n'existe pas ");
        }
        this.theSurvivors.remove(s);
    }

    /**
     * Opens the door in the specified direction.
     * 
     * @param d The direction of the door to be opened.
     */
    public void openDoor(Direction d) {
        Door door = this.getDoor(d);
        door.openDoor();
    }

    /**
     * Retrieves the list of zombies present in the cell.
     * 
     * @return The list of zombies in the cell.
     */
    public List<Zombie> getZombies() {
        return theZombies;
    }

    /**
     * Retrieves the list of survivors present in the cell.
     * 
     * @return The list of survivors in the cell.
     */
    public List<Survivor> getSurvivors() {
        return this.theSurvivors;
    }

    /**
     * Retrieves the list of all game characters present in the cell.
     * 
     * @return The list of game characters in the cell.
     */
    public List<GameCharacter> getCharacter() {
        return theCharacters;
    }

    /**
     * Retrieves the list of tools present in the cell.
     * 
     * @return The list of tools in the cell.
     */
    public List<Tool> getTools() {
        return theTools;
    }

    /**
     * Retrieves the list of weapons present in the cell.
     * 
     * @return The list of weapons in the cell.
     */
    public List<Weapon> getWeapons() {
        return theWeapons;
    }

    /**
     * Retrieves the list of equipments present in the cell.
     * 
     * @return The list of equipments in the cell.
     */
    public List<Equipment> getEquipment() {
        return theEquipments;
    }

    /**
     * Retrieves the list of roles present in the cell.
     * 
     * @return The list of roles in the cell.
     */
    public List<Role> getRoles() {
        return theRoles;
    }

    /**
     * Checks if the cell contains a zombie at the specified coordinates.
     * 
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return true if a zombie is present at the specified coordinates, false
     *         otherwise.
     */
    public boolean cellHasZombie(int x, int y) {
        // Méthode pour vérifier si une cellule contient un zombie
        for (Zombie zombie : theZombies) {
            if (zombie.getCurrentCell().getX() == x && zombie.getCurrentCell().getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell contains a survivor at the specified coordinates.
     * 
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return true if a survivor is present at the specified coordinates, false
     *         otherwise.
     */
    public boolean cellHasSurvivor(int x, int y) {
        // Méthode pour vérifier si une cellule contient un survivor
        for (Survivor survivor : theSurvivors) {
            if (survivor.getCurrentCell().getX() == x && survivor.getCurrentCell().getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell contains any game character at the specified coordinates.
     * 
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return true if any game character is present at the specified coordinates,
     *         false otherwise.
     */
    public boolean cellHasCharacter(int x, int y) {
        // Méthode pour savoir si une personnage est present dans une cell
        for (GameCharacter gamecharacter : theCharacters) {
            if (gamecharacter.getCurrentCell().getX() == x && gamecharacter.getCurrentCell().getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell contains a tool at the specified coordinates.
     * 
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return true if a tool is present at the specified coordinates, false
     *         otherwise.
     */
    public boolean cellHasTool(int x, int y) {
        for (Tool tool : theTools) {
            if (tool.getCurrentCell().getX() == x && tool.getCurrentCell().getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell contains a weapon at the specified coordinates.
     * 
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return true if a weapon is present at the specified coordinates, false
     *         otherwise.
     */
    public boolean cellHasWeapon(int x, int y) {
        for (Weapon weapon : theWeapons) {
            if (weapon.getCurrentCell().getX() == x && weapon.getCurrentCell().getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a description of the cell.
     * 
     * @return A string description of the cell.
     */
    public abstract String description();

    /**
     * Checks if the current cell is equals to another object
     * 
     * @param o The object to compare with
     * @return true if the cells are equal , false otherwise
     */
    public boolean equals(Object o) {
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) o;
        return (this.x == other.x && this.y == other.y &&
                this.nbDoors == other.nbDoors && this.doors.equals(other.doors));
    }

    /**
     * Displays a part of the cell based on the given parameter.
     * 
     * @param n The parameter indicating which part of the cell to display:
     *          - If n equals 0, it displays the top part of the cell (border).
     *          - If n equals 1, it displays the left part of the cell.
     *          - If n is other than 0 or 1, it displays the remaining part of the
     *          left side.
     */
    public void display(int n) {
        // n=0 la partie sup de la cell (bordure)
        if (n == 0) {
            System.out.print(this.getDoor(Direction.Up).isOpen() ? "     " : "------");
        } else if (n == 1) {
            System.out.print(this.getDoor(Direction.Left).isOpen() ? openLeft1() : closeLeft1());
        } else {
            System.out.print(this.getDoor(Direction.Left).isOpen() ? openLeft2() : closeLeft2());
        }

    }

    /**
     * Gets the number of zombies present in the cell.
     * 
     * @return The number of zombies in the cell.
     */
    public int getNbZombies() {
        return this.theZombies.size();
    }

    /**
     * Gets the number of survivors present in the cell.
     * 
     * @return The number of survivors in the cell.
     */
    public int getNbSurvivors() {
        return this.theSurvivors.size();
    }

    /**
     * Checks if the cell has any zombies and returns a string representation
     * accordingly.
     * 
     * @return A string representation indicating the presence of zombies in the
     *         cell.
     */
    private String hasZombies() {
        int z = getNbZombies();
        if (z == 0) {
            return "  ";
        }
        return "" + ZOMBIE + z;
    }

    /**
     * Checks if the cell has any survivors and returns a string representation
     * accordingly.
     * 
     * @return A string representation indicating the presence of survivors in the
     *         cell.
     */
    private String hasSurvivors() {
        int s = getNbSurvivors();
        if (s == 0) {
            return "     ";
        }
        return "   " + SURVIVOR + s;
    }

    /**
     * Generates the string representation of the left side of the cell when the
     * door is open.
     * 
     * @return The string representation of the left side of the cell when the door
     *         is open.
     */
    public String openLeft1() {
        return " " + description() + hasZombies();
    }

    /**
     * Generates the string representation of the left side of the cell when the
     * door is closed.
     * 
     * @return The string representation of the left side of the cell when the door
     *         is closed.
     */
    public String closeLeft1() {
        return "|" + description() + hasZombies();
    }

    /**
     * Generates the string representation of the remaining part of the left side of
     * the cell when the door is open.
     * 
     * @return The string representation of the remaining part of the left side of
     *         the cell when the door is open.
     */
    public String openLeft2() {
        return " " + hasSurvivors();
    }

    /**
     * Generates the string representation of the remaining part of the left side of
     * the cell when the door is closed.
     * 
     * @return The string representation of the remaining part of the left side of
     *         the cell when the door is closed.
     */
    public String closeLeft2() {
        return "|" + hasSurvivors();
    }

    /**
     * Resets the noise level of the cell to zero.
     */
    public void resetNoiseLevel() {
        this.noiseLevel = 0;
    }
    

    /**
     * Finds the loudest zone adjacent to the current cell.
     * 
     * @return The cell representing the loudest zone adjacent to the current cell.
     */
    public Cell findLoudestZone() {
        int maxNoiseLevel = 0;
        Cell loudestZone = null;

        // Parcourir les cellules adjacentes
        for (Direction direction : Direction.values()) {
            Cell neighbor = getNeighbor(direction);
            if (neighbor != null && neighbor.getNoiseLevel() > maxNoiseLevel) {
                maxNoiseLevel = neighbor.getNoiseLevel();
                loudestZone = neighbor;
            }
        }

        return loudestZone;
    }

    /**
     * Retrieves the neighbor cell in the specified direction.
     * 
     * @param direction The direction in which to find the neighbor cell.
     * @return The neighbor cell in the specified direction, or null if the neighbor
     *         cell is outside the board.
     */
    public Cell getNeighbor(Direction direction) {
        int newX = this.x;
        int newY = this.y;

        // Déterminer les coordonnées de la cellule voisine en fonction de la direction
        switch (direction) {
            case Up:
                newY++;
                break;
            case Down:
                newY--;
                break;
            case Left:
                newX--;
                break;
            case Right:
                newX++;
                break;
        }

        if (isValidCell(newX, newY)) {
            return board.getCell()[newX][newY];
        } else {
            return null;
        }
    }


    /**
     * Checks if the specified coordinates represent a valid cell within the
     * boundaries of the game board.
     * 
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return true if the coordinates represent a valid cell within the game board
     *         boundaries, false otherwise.
     */
    public boolean isValidCell(int x, int y) {
        // Vérifie si les coordonnées sont à l'intérieur des limites du plateau de jeu
        return x >= 0 && x < board.getLarg() && y >= 0 && y < board.getHaut();
    }

    /**
     * Checks if the cell contains any game character.
     * 
     * @return true if the cell contains any game character, false otherwise.
     */
    public boolean cellHasCharacter() {
        return !theCharacters.isEmpty();
    }

    /**
     * Checks if the cell contains any tool.
     * 
     * @return true if the cell contains any tool, false otherwise.
     */
    public boolean cellHasTool() {
        return !theTools.isEmpty();
    }

    /**
     * Checks if the cell contains any weapon.
     * 
     * @return true if the cell contains any weapon, false otherwise.
     */
    public boolean cellHasWeapon() {
        return !theWeapons.isEmpty();
    }

    /**
     * Checks if the cell contains any role.
     * 
     * @return true if the cell contains any role, false otherwise.
     */
    public boolean cellHasRole() {
        return !theRoles.isEmpty();
    }

    /**
     * Checks if the cell represents a street.
     * 
     * @return true if the cell represents a street, false otherwise.
     */
    public abstract boolean isStreet();


    /**
     * Checks if the cell represents a Room.
     * 
     * @return true if the cell represents a Room, false otherwise.
     */
    public abstract boolean isRoom();

    /**
     * Checks if the cell contains any survivor.
     * 
     * @return true if the cell contains any survivor, false otherwise.
     */
    public boolean containsSurvivor() {
        // Vérifie s'il y a un survivant dans la cellule
        for (Survivor survivor : theSurvivors) {
            if (survivor.getCurrentCell() == this) {
                return true;
            }
        }
        return false;
    }

    protected abstract boolean estEgout();

    protected abstract boolean isCarrefourP();

    public abstract boolean isContinental();

    protected abstract boolean isPharmacy();

}
