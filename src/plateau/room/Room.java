package plateau.room;

import java.util.*;
import plateau.*;
import plateau.equipment.Equipment;
import plateau.equipment.tool.Tool;
import plateau.equipment.weapon.Weapon;

/**
 * Represents a room cell on the game board.
 * Extends the Cell class.
 */
public class Room extends Cell {
    //protected List<Tool> tools;
    //protected List<Weapon> weapons;
    //protected List<Equipment> equipments;

    /**
     * Constructs a room cell with the specified coordinates.
     *
     * @param x The X coordinate of the room cell.
     * @param y The Y coordinate of the room cell.
     */
    public Room(int x, int y) {
        super(x, y);
        //this.equipments = new ArrayList<>(); // list of equipment in the room
         
        //this.tools = new ArrayList<>();
        //this.weapons = new ArrayList<>();
    }

    /**
     * Gets the description of the room cell.
     *
     * @return The description of the room cell.
     */
    @Override
    public String description() {
        return " R ";

    }

    /**
     * Gets a string representation of the room cell.
     *
     * @return A string representing the room cell.
     */
    public String getDescription() {
        return "Room"; // ajouter case correspondante
    }

    /**
     * Checks if the cell represents a street.
     *
     * @return false since this is not a street cell.
     */
    @Override
    public boolean isStreet() {
        return false;
    }

    /**
     * Gets the list of equipment in the room.
     *
     * @return The list of equipment in the room.
     */
    /* 
    public List<Equipment> getRoomEquip() {
        return this.theEquipments;
    }*/

    /**
     * Adds an equipment to the room.
     *
     * @param e The equipment to add to the room.
     */
    public void addEquipment(Equipment e) {
        this.theEquipments.add(e);
    }

    public List<Equipment> getEquipments(){
        return this.theEquipments;
    }
    /**
     * Checks if the room has any tools.
     *
     * @return true if the room has tools, false otherwise.
     */
    public boolean hasTool() {
        return !theTools.isEmpty();
    }

    /**
     * Checks if the room has any weapons.
     *
     * @return true if the room has weapons, false otherwise.
     */
    public boolean hasWeapon() {
        return !theWeapons.isEmpty();
    }

    /**
     * Adds a tool to the room.
     *
     * @param tool The tool to add to the room.
     */
    public void addTool(Tool tool) {
        this.theTools.add(tool);
    }

    /**
     * Adds a weapon to the room.
     *
     * @param weapon The weapon to add to the room.
     */
    public void addWeapon(Weapon weapon) {
        this.theWeapons.add(weapon);
    }

    /**
     * Gets the list of all equipment in the room.
     *
     * @return The list of all equipment in the room.
     */
    /* 
    public List<Equipment> getEquipments() {
        List<Equipment> equipments = new ArrayList<>();
        equipments.addAll(theTools);
        equipments.addAll(theWeapons);
        return equipments;
    }*/

    /**
     * Sets the list of equipment in the room.
     *
     * @param equipments The list of equipment to set in the room.
     */
    public void setEquipments(List<Equipment> equipments) {
        this.theEquipments = equipments;
    }

    @Override
    protected boolean estEgout() {
        return false;
    }

    @Override
    protected boolean isCarrefourP() {
        return false;
    }

    @Override
    public boolean isRoom() {
        return true;
    }

    @Override
    public boolean isContinental() {
        return false;
    }

    @Override
    public boolean isPharmacy() {
        return false;
    }

}
