package plateau.gamecharacter;

import java.util.*;
import plateau.*;
import plateau.action.*;
import plateau.equipment.Equipment;
import plateau.equipment.tool.Tool;
import plateau.equipment.weapon.Rifle;
import plateau.equipment.weapon.Weapon;
import plateau.gamecharacter.exception.*;
import plateau.listchooser.RandomListChooser;
import plateau.role.Role;

public class Survivor extends GameCharacter {
	protected int expertLevel;
	protected int actionPoints;
	protected ArrayList<Weapon> weaponsBackpack;
	protected ArrayList<Tool> toolsBackpack;
	protected Equipment currentEquipment;
	protected Equipment inHand;
	protected List<Direction> theDirections;
	protected List<Role> theRoles; // Les survivants peuvent avoir des rôles particuliers qui leur sont attribués
									// au début de la partie
	protected List<Zombie> theZombies;
	protected int nbZombieKilled;
	protected String pseudo; /* identify the survivor */
	private Board board;
	private Role role;
	private Zombie target;

	private String name;

	protected List<Action> actions;

	protected RandomListChooser<Action>  actions2 = new RandomListChooser<>();

	// list of neighbours
	protected List<Survivor> neighbour;

	// list of directions
	// public static ListChooser<Direction> ld; // à supprimer

	/**
	 * Constructs a new Survivor object with default values.
	 * Default values are:
	 * - Expert level: 0
	 * - Action points: 3
	 * - Life points: 5
	 */
	public Survivor(Board board) {
		this.expertLevel = 0;
		this.actionPoints = 3;
		this.lifePoints = 5;
		this.weaponsBackpack = new ArrayList<>();// y'en a 5 maximum
		this.toolsBackpack = new ArrayList<>();
		this.theRoles = new ArrayList<>();
		this.currentEquipment = (Equipment) new Rifle(); // Au départ, chaque survivant a un pistolet en main.
		this.target=null;
		this.board = board;
		actions = new ArrayList<>();
		//actions.add(new MoveAction());
		actions.add(new AttackAction());
		actions.add(new HealerAction());
		actions.add(new LookAroundAction());
		actions.add(new MakeNoiseAction());
		actions.add(new OpenDoorAction());
		actions.add(new MoveAction2());
		actions.add(new SearchARoomAction());
		actions.add(new TakeInHandAction());
		actions.add(new UseEquipementAction());
	}

	// pour éviter de mettre board en public et utiliser getCell dans Move
	public Cell[][] getCell() {
		return board.getCell();
	}
	public int getX(){
		return this.currentCell.getX();
	}
	public int getY(){
		return this.currentCell.getY();
	}

	/**
	 * Returns the board associated with this Survivor.
	 *
	 * @return The board associated with this Survivor.
	 */
	public Board getBoard() {
		return this.board;
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	/**
	 * Increases a survivor's life points by the specified amount.
	 *
	 * @param n The amount by which to increase the survivor's life points.
	 */
	public void addLifePoint(int n) {
		this.lifePoints += n;
	}
	/**
	 * get Target of survivor
	 */
	public Zombie getTarget(){
		return this.target;
	}

	/**set a target
	 * @param z the zombie (target)
	 */
	public void setTarget(Zombie z){
		this.target=z;
	}

	/**
	 *remove target
	 */
	public void removeTarget(){
		this.target=null;
	}

	/**
	 * Decreases a survivor's life points by the specified amount.
	 *
	 * @param n The amount by which to decrease the survivor's life points.
	 */
	public void subLifePoint(int n) {
		super.lifePoints -= n;
	}

	/**
	 * Increases the action points of this survivor by the specified amount.
	 *
	 * @param n The amount by which to increase the survivor's action points.
	 */
	public void addaActionpoints(int n) {
		this.actionPoints += n;
	}

	/**
	 * Decreases the action points of this survivor by the specified amount.
	 *
	 * @param n The amount by which to decrease the survivor's action points.
	 */
	public void subActionPoints(int n) {
		if (this.actionPoints>0) {
			this.actionPoints -= n;
		}
	}

	/**
	 * Returns the action points of the survivor.
	 * 
	 * @return The action points of the survivor.
	 */
	public int getActionPoint() {
		return this.actionPoints;
	}
	
	public boolean hasActionPoints() {
		return this.actionPoints > 0;
	}

	/**
	 * Returns the number of life points of this survivor.
	 *
	 * @return The number of life points of the survivor.
	 */
	public int getLifePoints() {
		return this.lifePoints;
	}

	public Equipment getInHand() {
		return this.inHand;
	}

	/**
	 * sets the action points
	 * 
	 * @param actionPoints the live points to set
	 */
	public void setActionsPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	/**
	 * Returns the expertise level of the survivor.
	 * 
	 * @return The expertise level of the survivor.
	 */
	public int getExpertLevel() {
		return this.expertLevel;
	}

	/**
	 * get the roles
	 * 
	 * @return the list of the roles
	 */
	public List<Role> getRoles() {
		return this.theRoles;
	}

	/**
	 * get the Equipement used by the survivor
	 * 
	 * @return thecurrent equipement
	 */
	public Equipment getCurrentEquipment() {
		return this.currentEquipment;
	}

	/**
	 * Reduces the survivor's life points when injured.
	 */
	public void getHurt() {
		this.lifePoints--;
	}

	/**
	 * 
	 * @return true if the toolsbackpack is full , false otherwise
	 */
	public boolean toolsBackpackIsFull() {
		return this.toolsBackpack.size() == 5;// peut contenir au maximum 5 équipements
	}

	/**
	 * 
	 * @return true if the weaponsbackpack is full , false otherwise
	 */
	public boolean weaponsBackpackIsFull() {
		return this.weaponsBackpack.size() == 5;// peut contenir au maximum 5 équipements
	}

	/**
	 * set a new currentEquipement from the grouns
	 * the current equipement is move to the backpack if he's not full
	 * 
	 * @param e the equipement who will becom the currentEquipement
	 */
	public void setCurrentEquipement(Equipment e) {

		if (weaponsBackpack.contains(e) || toolsBackpack.contains(e)) {
			this.currentEquipment = e;
		}
	}

	/**
	 * Retrieves all equipment carried by the survivor, including the current
	 * equipment,
	 * weapons in the backpack, and tools in the backpack.
	 *
	 * @return A list containing all the equipment carried by the survivor.
	 */
	public List<Equipment> getAllEquipment() {
		List<Equipment> allEquipment = new ArrayList<>();
		allEquipment.add(currentEquipment);
		allEquipment.addAll(weaponsBackpack);
		allEquipment.addAll(toolsBackpack);
		return allEquipment;
	}

	/**
	 * Retrieves all equipment stored in the survivor's backpack, including weapons
	 * and tools.
	 *
	 * @return A list containing all the equipment stored in the survivor's
	 *         backpack.
	 */
	public List<Equipment> getAllEquipmentInBackpack() {
		List<Equipment> equipmentInBackpack = new ArrayList<>();
		equipmentInBackpack.addAll(weaponsBackpack);
		equipmentInBackpack.addAll(toolsBackpack);
		return equipmentInBackpack;
	}

	/**
	 * Retrieves all equipment carried by the survivor, excluding the current
	 * equipment held in hand.
	 *
	 * @return A list containing all the equipment carried by the survivor,
	 *         excluding the current equipment.
	 */
	public List<Equipment> getInventory() {
		List<Equipment> inventory = new ArrayList<>();
		inventory.addAll(getAllEquipment()); // Ajoute les équipements portés par le survivant
		inventory.remove(getCurrentEquipment()); // Retire l'équipement tenu en main (car déjà inclus dans
													// getAllEquipment())
		return inventory;
	}

	/**
	 * Removes all equipment from the survivor, including the current equipment and
	 * those in the backpacks.
	 * After calling this method, the survivor will have no equipment.
	 */
	public void removeAllEquipment() {
		this.currentEquipment = null;
		this.weaponsBackpack.clear();
		this.toolsBackpack.clear();
	}

	/**
	 * Checks if the survivor possesses the specified equipment.
	 *
	 * @param equipment The equipment to check for.
	 * @return true if the survivor possesses the equipment, false otherwise.
	 */
	public boolean hasEquipment(Equipment equipment) {
		return getAllEquipment().contains(equipment);
	}

	/**
	 * Removes an equipment from the survivor's inventory.
	 *
	 * @param equipment The equipment to be removed.
	 */
	public void removeEquipment(Equipment equipment) {
		if (hasEquipment(equipment)) {
			getAllEquipment().remove(equipment);
		} else {
			System.out.println("Survivor does not have this equipment.");
		}
	}

	/**
	 * get all the weapons
	 * 
	 * @return the liste of all the weapons
	 */
	public ArrayList<Weapon> getAllWeapons() {
		return this.weaponsBackpack;
	}

	/**
	 * get all the tools
	 * 
	 * @return the liste of all the tools
	 */
	public ArrayList<Tool> getAllTools() {
		return this.toolsBackpack;
	}

	/**
	 * get every Object which are in the backpack
	 * 
	 * @return all the object in backpack
	 */

	// A verifier !!
	// on dois changer le nom
	public ArrayList<Tool> getObjectsFromBackpack() {
		return this.toolsBackpack;
	}

	/**
	 * make a survivor add some tool in his Toolbackpack
	 * 
	 * @param newTool
	 *  1 if the new tool was successfully added
	 * @exception FullToolsBackException
	 */
	public void addToolInToolsBackpack(Tool newTool) throws FullToolsBackException {
		if (this.toolsBackpack.size() < 5) {
			this.toolsBackpack.add(newTool);
		} else {
			throw new FullToolsBackException("the tool back is full , please delete some equipment before ");
		}
	}

	/**
	 * make a survivor add some weapon in his weaponsBackpack
	 * 
	 * @param newWeapon
	 * @return 1 if the new weapon was successfully added
	 * @exception FullWeaponsBackException
	 */
	public void addWeaponInWeaponsBackpack(Weapon newWeapon) throws FullWeaponsBackException {
		if (this.weaponsBackpack.size() < 5) {
			this.weaponsBackpack.add(newWeapon);

		} else {
			throw new FullWeaponsBackException("the weapon back is full , please delete some equipment before ");
		}
	}

	/**
	 * allow a survivor to remove some tool from his backpack
	 * 
	 * @param removedTool the tool to be removed
	 */
	public void removeToolFromBackpack(Tool removedTool) {
		this.toolsBackpack.remove(removedTool);
	}

	/**
	 * allow a survivor to remove some weapon from his backpack
	 * 
	 * @param removedWeapon the weapon to be removed
	 */
	public void removeWeaponFromBackpack(Weapon removedWeapon) {
		this.weaponsBackpack.remove(removedWeapon); // ??
	}

	/*
	 * get the number of Zombies killed by this Survivor
	 */
	public int getNbZombieKilled() {
		return this.nbZombieKilled;
	}

	// }

	// a revoir apres
	// j'ai mis en commentaire pour pouvoir compiler
	/**
	 * public void useTool() {
	 * Tool t = getCurrentEquipment();
	 * t.useBy(this);
	 * }
	 */
	// ??

	/**
	 * Opens the specified door.
	 *
	 * @param door The door to be opened.
	 */
	public void openDoor(Door door) {
		door.openDoor();
	}

	/**
	 * Increases the expert level of the survivor.
	 */
	public void isExpert() { // a verifier
		this.expertLevel += 1;
	}


	@Override
	public void changeCell(Cell newCell) {
		Cell currentCell = this.getCurrentCell();
		if (currentCell == null) {
			System.out.println("Player's cell is null. Cannot play this round.");
			return;
		}
		this.setCurrentCell(newCell);
	}

	public String descriptionGameCharacter() {
		return " S ";
	}

	// choose a survivor from the list neighbour
	/**
	 * public Survivor neighbourChoose(){
	 * if(neighbour.isEmpty()){
	 * return null;
	 * }
	 * int choice = -1;
	 * while (choice < 0 || choice > neighbour.size()) {
	 * System.out.println("Please select an item:");
	 * System.out.println(" 0 - None");
	 * int index = 1;
	 * for (Survivor element : neighbour) {
	 * System.out.println(" " + index + " - " + element);
	 * index++;
	 * }
	 * System.out.println("Your choice?");
	 * try {
	 * choice = Input.readInt();
	 * } catch (java.io.IOException e) {
	 * System.out.println("Please enter a number between 0 and " +
	 * (neighbour.size()));
	 * }
	 * if (choice == 0) {
	 * return null;
	 * }
	 * return neighbour.get(choice - 1);
	 * }
	 * }
	 */

	/**
	 * get all actions
	 * 
	 * @return the liste of all actions
	 */
	public List<Action> getAllAction() {
		return this.actions;

	}

	/**
	 * choose an action then use it
	 */
	public void survivorAct() {
		// choisir une action A parmi this.actions avec interactivelistchooser
		// A.execute(this)

		if (actions.isEmpty()) {
			System.out.println("No actions available.");
			return;
		}
		// choose an action among theActions
		//InteractiveListChooser<Action> actionChooser = new InteractiveListChooser<>();
		//board.descriptionDoors();
		System.out.println("Choose an action :");

		//Action selectedAction = actionChooser.choose(this.actions);
		 Random random = new Random();
		 Action selectedAction =
		 this.actions.get(random.nextInt(this.actions.size()));

		//if (this.currentCell.getNbZombies() != 0) {
		//	selectedAction = this.actions.get(0);

		//}/
		System.out.println("L'action choisie est :" + selectedAction.toString());

		if (selectedAction != null) {
			if (!(selectedAction instanceof LookAroundAction)) {
				subActionPoints(1);
			}
			if (this.itsAFighter()) {
				
			}
			else if (this.itsAHealer()) {
				if (selectedAction instanceof HealerAction) {
					addaActionpoints(1);
				}
				
			}
			else if(this.itsALucky()) {
				if (selectedAction instanceof AttackAction) {
					this.addaActionpoints(1);
				}
			}
			else if(itsASnooper()) {
				if (selectedAction instanceof SearchARoomAction) {
					addaActionpoints(1);//
				}
			}else {
				selectedAction.execute(this);
			}
		}

	}

	private boolean itsASnooper() {
		Role role = this.getRole();
	    return role != null && role.getTypeRole().equals("Snooper");
	}

	private boolean itsALucky() {
		Role role = this.getRole();
	    return role != null && role.getTypeRole().equals("Lucky");
	}

	private boolean itsAHealer() {
	    Role role = this.getRole();
	    return role != null && role.getTypeRole().equals("Healer");	}


	private boolean itsAFighter() {
	    Role role = this.getRole();
	    return role != null && role.getTypeRole().equals("Fighter");	}


	public void lookAroundAction(){
		Action lookAround = new LookAroundAction();
		lookAround.execute(this);
	}

	// ajouter un role avec une action spécifique
	public void addRole(Role r) {
		this.theRoles.add(r);
		//r.addSpecificActions(this);
	}

	public Role getRole() {
		return role;
	}

	public void addOneRole(Role role) {
		// Vérifie si la liste theRoles est initialisée
		if (this.theRoles == null) {
			// Initialise la liste theRoles si elle est nulle
			this.theRoles = new ArrayList<>();
		}
		// Ajoute le rôle à la liste
		this.theRoles.add(role);
		this.role = role;
	}

	public void addRoles(List<Role> roles) {
		// Vérifie si la liste theRoles est initialisée
		if (this.theRoles == null) {
			// Initialise la liste theRoles si elle est nulle
			this.theRoles = new ArrayList<>();
		}
		// Ajoute le rôle à la liste
		this.theRoles.addAll(roles);
	}

	public void addAction(Action a) {
		actions.add(a);
	}

	public void executeActions() {
		for (Action action : actions) {
			action.execute(this); // Appel de la méthode execute de chaque action avec le survivant en paramètre
		}
	}

	public List<Action> getAvailableActions() {
		return actions;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'move'");
	}

	@Override
	public void setCurrentCell2(Cell cell) {
		this.currentCell.removeSurvivor(this);
		this.currentCell=cell;
		cell.addSurvivor(this);

	}

}
