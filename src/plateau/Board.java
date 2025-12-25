package plateau;

import java.util.*;

import plateau.action.*;
import plateau.equipment.Equipment;
import plateau.equipment.tool.*;
import plateau.equipment.weapon.*;

import plateau.gamecharacter.*;
import plateau.gamecharacter.zombie.*;
import plateau.listchooser.InteractiveListChooser;
import plateau.room.*;
import plateau.street.*;

/**
 * Represents a game board with cells , streets , and rooms
 */
public class Board {

    private Cell[][] cells;
    private static final Random random = new Random();
    protected List<Zombie> theZombies;
    protected List<Survivor> theSurvivors;
  

    /**
     * Initialize a new game board with the specified width and height
     * 
     * @param larg the width of the board
     * @param haut the height of the board
     */
    public Board(int larg, int haut) {
        this.cells = new Cell[larg][haut];
        this.theZombies = new ArrayList<>(); // Initialise la liste des zombies !
        this.theSurvivors = new ArrayList<>(); // Initialise la liste des survivants !
        for (int i = 0; i < larg; i++) {
            for (int j = 0; j < haut; j++) {
                this.cells[i][j] = new Room(i, j);
            }
        }
    }

    /**
     * Gets the representation of the board as an array of cells
     * 
     * @return the array of cells representing the board
     */
    public Cell[][] getCell() {
        return this.cells;
    }

    public Cell getCell1(int x, int y){
        return this.cells[x][y];
    }
    /**
     * Obtains the width of the board
     * 
     * @return The width of the board
     */
    public int getLarg() {
        return this.cells.length;
    }

    /**
     * Obtains the height of the board
     * 
     * @return The height of the board
     */

    public int getHaut() {
        return this.cells[0].length;

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
    }
    
    /**
     * add a survivor to the list of survivors
     * 
     * @param s the survivor to add
     */
    public void addSurvivor(Survivor s) {
        if (theSurvivors.contains(s)) {
            System.out.println("le survivor existe deja");
        }
        this.theSurvivors.add(s);
    }
    
    public void removeDescription() {
        for (int i = 0; i < getLarg(); i++) {
            for (int j = 0; j < getHaut(); j++) {
                Cell cellule = cells[i][j];
                if (cellule.containsSurvivor()) {
                    cellule.setDescription(""); // Supprime la description du survivant de la cellule
                }
            }
        }
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
        this.removeDescription();
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
        this.removeDescription();
    }
    
    /**
     * Retrieves the list of zombies present in the cell.
     * 
     * @return The list of zombies in the cell.
     */
    public List<Zombie> getZombies() {
        return this.theZombies;
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
     * eliminate the survivor who is dead from the board
     */
    public void eliminateSurvivorsFromGame(){ 
    	Iterator<Survivor> iterator = this.theSurvivors.iterator();
    while (iterator.hasNext()) {
        Survivor s = iterator.next();
        if (s.isDead()){ 
            iterator.remove(); // Supprime le survivant de la liste
            Cell currentCell = s.getCurrentCell();
            List<Equipment> equipmentsBackpack = s.getAllEquipmentInBackpack();
            if (currentCell.isStreet()){
                // Si le survivant est mort dans une rue, retirez tous ses outils et armes de son sac à dos
                for (Tool tool : s.getAllTools()) {
                    s.removeToolFromBackpack(tool);
                }
                for (Weapon weapon : s.getAllWeapons()) {
                    s.removeWeaponFromBackpack(weapon);
                }
            } else {
                // Si le survivant est mort ailleurs, transférez ses équipements vers sa cellule actuelle
                for (Tool tool : s.getAllTools()) {
                    currentCell.addTool(tool);
                }
                for (Weapon weapon : s.getAllWeapons()) {
                    currentCell.addWeapon(weapon);
                }
            }
        }
    }
	}


    /**
     * eliminate the survivor who is dead from the board
     */
    public void eliminateZombiesFromGame(){
    	Iterator<Zombie> iterator = this.theZombies.iterator();
        while (iterator.hasNext()) {
        	Zombie z = iterator.next();
            if (z.isDead()){ 
            	iterator.remove();
            }
        }
	}

    /**
     * Creats streets on the board based on the specified parameters
     * 
     * @param xd               X coordinate of the top left corner
     * @param yd               Y coordinate of the top left corner
     * @param larg             width of the area to be pierced
     * @param haut             height of the area to be pierced
     * @param isFirstIteration Indicate whether it is the firste iteration
     */

     public void perceRue(int xd, int yd, int larg, int haut, boolean isFirstIteration) {
        if (larg >= 5 && haut >= 5) {
            int x = random.nextInt(larg - 4) + (xd + 2);
            int y = random.nextInt(haut - 4) + (yd + 2);
    
            initEgout(xd, yd, larg, haut, isFirstIteration, x, y);
            initCarrefourP(xd, yd, larg, haut, isFirstIteration, x, y);
    
            perceRue(xd, yd, x - xd, y - yd, false);
            perceRue(x + 1, yd, larg - (x - xd + 1), y - yd, false);
            perceRue(xd, y + 1, x - xd, haut - (y - yd + 1), false);
            perceRue(x + 1, y + 1, larg - (x - xd + 1), haut - (y - yd + 1), false);
        } else if (larg >= 5 && haut < 5) {
            int x = random.nextInt(larg - 4) + (xd + 2);
            for (int j = yd; j < yd + haut; j++) {
                this.cells[x][j] = new Street(x, j);
            }
            perceRue(xd, yd, x - xd, haut, false);
            perceRue(x + 1, yd, larg - (x - xd + 1), haut, false);
        } else if (larg < 5 && haut >= 5) {
            int y = random.nextInt(haut - 4) + (yd + 2);
            for (int i = xd; i < xd + larg; i++) {
                this.cells[i][y] = new Street(i, y);
            }
            perceRue(xd, yd, larg, y - yd, false);
            perceRue(xd, y + 1, larg, haut - (y - yd + 1), false);
        }
    }
    

    /**
     * Initializes and places a CarrefourP (crossroad) in the specified area on the
     * board.
     * This method is used during the creation of streets on the board.
     * 
     * @param xd               X coordinate of the top left corner of the area
     * @param yd               Y coordinate of the top left corner of the area
     * @param larg             Width of the area to be pierced
     * @param haut             Height of the area to be pierced
     * @param isFirstIteration Indicates whether it is the first iteration
     * @param x                X coordinate of the CarrefourP within the area
     * @param y                Y coordinate of the CarrefourP within the area
     */
    private void initCarrefourP(int xd, int yd,int haut, int larg, boolean isFirstIteration, int x, int y) {
        for (int j = yd; j <= yd + larg - 1; j++) {
            for (int i = xd; i <= xd + haut - 1; i++) {
                if (isFirstIteration == true && cells[x][j] == cells[i][y]) {
                    this.cells[x][y] = new CarrefourP(x, y);

                }
            }
        }
    }

    /**
     * Completes the initialization of Egout (sewer) or Street cells in the
     * specified area on the board.
     * This method is used during the creation of streets on the board.
     * 
     * @param xd               X coordinate of the top left corner of the area
     * @param yd               Y coordinate of the top left corner of the area
     * @param larg             Width of the area to be pierced
     * @param haut             Height of the area to be pierced
     * @param isFirstIteration Indicates whether it is the first iteration
     * @param x                X coordinate of the Egout or Street within the area
     * @param y                Y coordinate of the Egout or Street within the area
     */
    private void initEgout(int xd, int yd, int larg, int haut, boolean isFirstIteration, int x, int y) {
        for (int j = yd; j < yd + haut; j++) {
            if (isFirstIteration && (xd == 0 || xd + larg - 1 == cells.length - 1) && (j == yd || j == yd + haut - 1)) {
                if (x >= 0 && x < cells.length && j >= 0 && j < cells[0].length) {
                    this.cells[x][j] = new Egout(x, j, this);
                }
            } else {
                if (x >= 0 && x < cells.length && j >= 0 && j < cells[0].length) {
                    this.cells[x][j] = new Street(x, j);
                }
            }
        }
    
        for (int i = xd; i < xd + larg; i++) {
            if (isFirstIteration && (yd == 0 || yd + haut - 1 == cells[0].length - 1) && (i == xd || i == xd + larg - 1)) {
                if (i >= 0 && i < cells.length && y >= 0 && y < cells[0].length) {
                    this.cells[i][y] = new Egout(i, y, this);
                }
            } else {
                if (i >= 0 && i < cells.length && y >= 0 && y < cells[0].length) {
                    this.cells[i][y] = new Street(i, y);
                }
            }
        }
    }
    

    /**
     * Initializes the doors for each room on the board , closing them by default
     * 
     * @param rooms The list of rooms on the board
     */
    public void initDoors(List<Room> rooms) {
        createDoors();
        for (Room r : rooms) {
            for (Direction d : Direction.values()){
                r.getDoor(d).closeDoor();
            }
        }

        // Fermeture des portes supérieures pour la première rangée de salles
        for (int i = 0; i < this.getHaut(); i++) {
            this.getCell()[0][i].getDoor(Direction.Up).closeDoor();
        }

        // Fermeture des portes de gauche pour la première colonne de salles
        for(int i = 0; i < this.getLarg(); i++) {
            this.getCell()[i][0].getDoor(Direction.Left).closeDoor();
        }

        
    } 
    // faut revoir la logique de cette methode : faut qu'on ferme les porte de
      // chaque piece
      // et on ferme par defaut les portes en bordur et ne pas les ouvrir

  

    /**
     * Creates doors betwen areas . each room cell has doors connecting it
     * to neighboring cells ( up , down, left , right )
     */
    public void createDoors() {
        for (int i = 0; i < this.getHaut(); i++) {
            for (int j = 0; j < this.getLarg(); j++) {
                Cell cell = this.cells[i][j];
                // Vérifie si la cellule a une cellule voisine à droite
                if (j + 1 < this.getLarg()) {
                    Cell rightCell = this.cells[i][j + 1];
                    createCommonDoor(cell, rightCell, Direction.Right, Direction.Left);
                }
                // Vérifie si la cellule a une cellule voisine en bas
                if (i + 1 < this.getHaut()) {
                    Cell downCell = this.cells[i + 1][j];
                    createCommonDoor(cell, downCell, Direction.Down, Direction.Up);
                }
            }
        }
    }

    /**
     * Opens doors between street cells in the game board.
     * Iterates over each cell in the board, and if the current cell and its
     * neighboring cells are all street cells,
     * opens the corresponding doors between them.
     */

    
    public void openStreetDoors() {
        for (int i = 1; i < this.getLarg(); i++) { // i parcourt les colonnes 
            for (int j = 1; j < this.getHaut(); j++) {// et j les lignes 
                Cell currentCell = this.cells[i][j];

                if ( currentCell.isStreet()) {
                     
                    // Ouvrir les portes vers le haut
                    Door upDoor = currentCell.getDoor(Direction.Up);
                    if (j+1 < this.getHaut() ) {
                        Cell cellHaut = this.cells[i-1][j];
                        if (cellHaut.isStreet()){
                            upDoor.openDoor();

                        }
                        
                    }
                    // Ouvrir les portes vers la Gauche 
                    if (j - 1 >= 0) { 
                        Cell cellGauche = this.cells[i][j - 1]; 
                        if (cellGauche.isStreet()) { 
                            Door leftDoor = currentCell.getDoor(Direction.Left); 
                            leftDoor.openDoor(); 
                        }
                    }
                }
                if (j + 1 < this.getHaut()) {
                    Cell cellDroite = this.cells[i ][j+1];
                    if (cellDroite.isStreet()) {
                        Door rightDoor = cellDroite.getDoor(Direction.Right);
                        rightDoor.openDoor();
                    }
                }
                if(i+1 < this.getLarg()){
                    Cell cellBas = this.cells[i+1][j];
                    if(cellBas.isStreet()){
                        Door downDoor=cellBas.getDoor(Direction.Down);
                        downDoor.openDoor();
                    }
                }
                
            }
        }
    }




    /**
     * Creates a common door between two neighboring cells
     * 
     * @param cell     The current cell.
     * @param downCell The neighboring cell.
     * @param down     The direction of the door in the current cell.
     * @param up       The direction of the door in the neighboring cell.
     */
    private void createCommonDoor(Cell cell, Cell neighborCell, Direction currentDir, Direction neighborDir) {
        if (cell.getDoor(currentDir) == null && neighborCell.getDoor(neighborDir) == null) {
            Door commonDoor = new Door(); // Créer une porte commune
            cell.addDoor(currentDir, commonDoor);
            neighborCell.addDoor(neighborDir, commonDoor); // Ajoute la même porte à la cellule voisine
        }
    }

    /**
     * Replaces two randomly selected rooms from the board with special rooms :
     * one with a Continental and the other with a Pharmacy
     */
    public void specialRooms() {
        int larg = cells.length;
        int haut = cells[0].length;
        ArrayList<Cell> rooms = new ArrayList<>();

        for (int i = 0; i < larg; i++) {
            for (int j = 0; j < haut; j++) {
                if (cells[i][j] instanceof Room) {
                    rooms.add(cells[i][j]); // On stocke les pièces dans une liste
                }
            }
        }
        if (rooms.size() >= 2) { // 2 pièces pour remplacer par pharmacy et continental

            int index1 = random.nextInt(rooms.size());
            int index2 = 0;
            while (index2 == index1) {
                index2 = random.nextInt(rooms.size());
            } // on choisit 2 pièces distinctes
            Room room1 = (Room) rooms.get(index1);
            cells[room1.getX()][room1.getY()] = new Continental(room1.getX(), room1.getY());

            Room room2 = (Room) rooms.get(index2);
            cells[room2.getX()][room2.getY()] = new Pharmacy(room2.getX(), room2.getY());
        }
    }

    /**
     * Gets a textual representation of the game board
     * 
     * @return A string representing the game board with descriptions of each cell.
     */
    public String description() {
        String result = "";
        for (int i = 0; i < getHaut(); i++) {
            for (int j = 0; j < getLarg(); j++) {
                result += cells[i][j].description() + " ";
            }
            result += "\n";
        }

        return result;
    }

    /**
     * Displays the description of doors for each cell in the game board.
     * Iterates over each cell in the board and displays the description of doors
     * based on the specified index.
     * The index determines which part of the door's description to display.
     * - For index 0, displays the upper part of the door.
     * - For indexes 1 and 2, displays the middle and lower parts of the door,
     * respectively.
     */
    public void descriptionDoors() {

        for (int i = 0; i < getLarg(); i++) {
            for (int n = 0; n < 3; n++) {
                for (int j = 0; j < getHaut(); j++) {
                    this.cells[i][j].display(n);
                }
                if (n != 0) {
                    System.out.println('|');
                } else {
                    System.out.println();
                }
            }
        }
        for (int s = 0; s < this.getHaut(); s++) {
            System.out.print("------");
        }
        System.out.println("\n");
    }
/*
    // methode pour afficher les personnages
    public String displayCharacter() {
        String result = "";
        for (int i = 0; i < getHaut(); i++) {
            for (int j = 0; j < getLarg(); j++) {
                Cell currentCell = cells[i][j];
                if (currentCell.cellHasCharacter(i, j)) { // Vérifie s'il y a un personnage dans la cellule
                    // liste des personnages dans la cellule
                    List<GameCharacter> characters = cells[i][j].getCharacter();
                    for (GameCharacter character : characters) {
                        result += character.descriptionGameCharacter() + " ";
                    }
                } else {
                    result += currentCell.description() + " "; // Sinon, utilise la description de la cellule normale
                }
            }
            result += "\n";
        }
        return result;
    }
*/
    public void placerZombiesAuxEgouts() {
        int totalZombies = getLarg() + getHaut();
        //System.out.println("On a " + totalZombies + "zombies presents");

        while(totalZombies>0){
            // Parcours des cellules du plateau pour trouver les emplacements d'égouts
            for (int i = 0; i < getLarg(); i++) {
                for (int j = 0; j < getHaut(); j++) {
                    Cell cellule = cells[i][j];
                    if (cellule.estEgout() && totalZombies >0 ) {
                        Zombie zombie = genererTypeDeZombieAleatoire();
                    
                        cellule.addZombie(zombie);
                        this.addZombie(zombie);
                        totalZombies--;
                    }

                }
                
            }
        }

    }

    public Zombie genererTypeDeZombieAleatoire() {
        // Liste des types de zombies disponibles
        List<Zombie> typesDeZombies = Arrays.asList(new Abomination(this), new Balaise(this), new Walker(this), new Runner(this));

        // Génération aléatoire d'un indice pour choisir un type de zombie dans la liste
        Random random = new Random();
        // entre 1 et 3 zombies, et au plus une Abomination ou un Balaise
        int indice = random.nextInt(typesDeZombies.size());

        // Retourne le type de zombie correspondant à l'indice généré aléatoirement
        return typesDeZombies.get(indice);
    }

   

    // cellule au nord du carrefour principal
    public Cell cellAtNorthCrossroad() {
        // Parcourir toutes les cellules pour trouver le carrefour principal
        for (int x = 0; x < getLarg(); x++) {
            for (int y = 0; y < getHaut(); y++) {
                Cell cell = getCell()[x][y];
                if (cell.isCarrefourP()) {
                    // Si la cellule est un carrefour principal
                    // Trouver la cellule au nord
                    int northX = x - 1; // nord ici pourtant x est censé être largeur
                    int northY = y; // Nord est une case au-dessus
                    return cell = getCell()[northX][northY];

                }
            }
        }
        return null;
    }

    // pour trouver aléatoirement une cellule dans la zone nord du carrefour
    // principal

    public Cell cellAtNorthCrossroadRandom() {
        // Trouver le carrefour principal
        Cell carrefourPrincipal = null;

        // Parcourir toutes les cellules pour trouver le carrefour principal
        for (int x = 0; x < getLarg(); x++) {
            for (int y = 0; y < getHaut(); y++) {
                Cell cell = getCell()[x][y];
                if (cell.isCarrefourP()) {
                    carrefourPrincipal = cell;
                    break; // Sortir de la boucle une fois que le carrefour principal est trouvé
                }
            }
        }

        // Trouver la cellule au nord du carrefour principal
        if (carrefourPrincipal != null) {
            int northX = carrefourPrincipal.getX() - 1; // Nord est une case au-dessus
            int northY = carrefourPrincipal.getY();

            // Créer un générateur de nombres aléatoires
            Random random = new Random();

            // Générer des coordonnées aléatoires dans les limites de la grille
            int randomNorthX = random.nextInt(northX);
            int randomNorthY = random.nextInt(northY);

            // Vérifier les limites de la grille
            if (randomNorthX >= 0 && randomNorthX < getLarg() && randomNorthY >= 0 && randomNorthY < getHaut()) {
                return getCell()[randomNorthX][randomNorthY];
            }
        }

        // Si le carrefour principal n'est pas trouvé ou si la cellule au nord n'est pas
        // dans les limites de la grille, retourner null
        return null;
    }
    
    public void Noise0 () {
        for (int x = 0; x < getLarg(); x++) {
            for (int y = 0; y < getHaut(); y++) {
                Cell cell = getCell()[x][y];
                cell.resetNoiseLevel();
            }
        }
    }

    public void placeEquipementsINRoom(){
        Random random = new Random();
        for(int i=0; i<getLarg(); i++){
            for(int j=0; j<getHaut(); j++){
                Cell cell = getCell()[i][j];
                if(cell.isRoom()){
                    Room room = (Room) cell;
                    int numEquipements = random.nextInt(3)+1;
                    for(int k=0; k<numEquipements; k++){
                        Equipment equipment =  generateRandomEquipment();
                        room.addEquipment(equipment);
                    }
                }
            }
        }
        
    }
    public Equipment generateRandomEquipment(){
        List<Equipment> e = new ArrayList<>();
        e.add(new Axe());
        e.add(new Chainsaw());
        e.add(new Crowbar());
        e.add(new Pistol());
        e.add(new Rifle());
        // noiseLevel a changer pour chaque Tool
        e.add(new Card(0));
        e.add(new Glasses(1));
        e.add(new HealingVial(1));
        e.add(new Kit(1));
        e.add(new MasterKey(1));

        Random random = new Random();
        int randomIndex = random.nextInt(e.size());
        return e.get(randomIndex);

    }
    
    
    public double getAverageSurvivorExperience() {
        double totalExperience = 0;
        int nbSurvivors = 0;

        for (Survivor survivor : getSurvivors()) {
            totalExperience += survivor.getExpertLevel();
            nbSurvivors++;
        }

        return totalExperience / nbSurvivors;
    }
    
    
    
    public void multiplyZombiesFromSewers() {
    	//l'expérience moyenne des survivants
        double averageExperience = this.getAverageSurvivorExperience();
        
        //on arrondit à l’entier supérieur)et on divise par 3
        int nbZombies = (int) Math.ceil(averageExperience / 3);
        
        for (int i = 0; i < getLarg(); i++) {
            for (int j = 0; j < getHaut(); j++) {
            	Cell cell = getCell()[i][j];
            	//si la cell est egout
                if (cell.estEgout()) {
                    for (int n = 0; n <= nbZombies; i++){
                        this.placerZombiesAuxEgouts();
                    }
                }
            }
        }
    } 
    
    // Methode non implementé
    /**
	 * Allow to chose an actions
	 * @param s
	 * @return
	 */
	public Action chooseAction(Survivor s) {
		List<Action> actions = new ArrayList<>();
		actions.add(new AttackAction());
		//actions.add(new LookAroundAction(this));
		actions.add(new MakeNoiseAction());
		actions.add(new SearchARoomAction());
		actions.add(new TakeInHandAction());
		actions.add(new UseEquipementAction());
		actions.add(new OpenDoorAction());
		actions.add(new MoveAction2());

		//Utiliser ListChooser pour choisir une action
		InteractiveListChooser<Action> actionChooser =new InteractiveListChooser<>();
		System.out.print("Choose an action :" );
		Action chosenAction = actionChooser.choose(actions);
		return chosenAction;
	}


    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < this.getLarg() && y >= 0 && y < this.getHaut();
    }

    public int getXNext(Direction direction) {
        switch (direction) {
            case Right:
                return 1;
            case Left:
                return -1;
            default:
                return 0;
        }
    }

    public int getYNext(Direction direction) {
        switch (direction) {
            case Up:
                return 1;
            case Down:
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Finds the loudest zone adjacent to the current cell.
     * 
     * @return The cell representing the loudest zone adjacent to the current cell.
     */
    public Cell findLoudestZone(Cell currentCell) {
        int currentNoiseLevel = currentCell.getNoiseLevel();
        Cell loudestZone = currentCell;

        // Browse adjacent cells
        for (int i = 0; i < this.getLarg(); i++) {
            for (int j = 0; j < this.getHaut(); j++) {
                Cell neighbor = this.getCell()[i][j];
                if (neighbor != currentCell && neighbor.getNoiseLevel() > currentNoiseLevel) {
                    loudestZone = neighbor;
                    currentNoiseLevel = neighbor.getNoiseLevel();
                }
            }
        }
        return loudestZone;
    }



}
