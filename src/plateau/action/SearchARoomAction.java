package plateau.action;

import java.util.*;

import plateau.Cell;
import plateau.equipment.Equipment;
import plateau.equipment.tool.Tool;
import plateau.equipment.weapon.Weapon;
import plateau.gamecharacter.Survivor;
import plateau.gamecharacter.exception.*;
import plateau.listchooser.InteractiveListChooser;
import plateau.listchooser.RandomListChooser;
import plateau.room.Room;

public class SearchARoomAction extends Action {

    /**
     * Constructor for the action of searching a room.
     */
    public SearchARoomAction() {
        super();
    }

    /**
     * Method allowing the survivor to search a room.
     */
    public void fouille(Survivor s) {
        Cell cell = s.getCurrentCell();

        if (cell.isRoom() && cell.getEquipment().size() > 0) {
            Room room = (Room) cell;
            List<Equipment> roomEquipement = room.getEquipments(); // La liste des equipement qui existe dans room
            System.out.println("Il y a  " + cell.getEquipment().size() + " équipements dans la pièce ");
            List<Equipment> backPack = s.getAllEquipmentInBackpack();// Les equipement dans le sac a dos

            //InteractiveListChooser<Equipment> equipmentChooser = new InteractiveListChooser<>();
            RandomListChooser<Equipment> equipmentChooser = new RandomListChooser<>();
            //Equipment choseEquipementRoom = equipmentChooser.choose(roomEquipement);

            Random random = new Random();
            int randomIndex = random.nextInt(roomEquipement.size());
            Equipment choseEquipementRoom = roomEquipement.get(randomIndex);

            try {
                if (choseEquipementRoom.isWeapon()) {
                    if (s.weaponsBackpackIsFull()) { // Si le sac a dos est plein
                        System.out.println("Le sac à dos est plein. Choisissez un équipement à jeter :");
                        Equipment choseBackPackEquipment = equipmentChooser.choose(backPack); // choisir un
                                                                                              // equipement pamis
                                                                                              // ceux du sac a dos
                        s.removeEquipment(choseBackPackEquipment);// Jeter l'equipement
                        roomEquipement.add(choseBackPackEquipment);// ajouter l'equipement jeuter dans la piece
                        System.out.println(" " + choseBackPackEquipment + " a été jeté dans la pièce\n");

                    }
                    s.addWeaponInWeaponsBackpack((Weapon) choseEquipementRoom);
                    System.out.println(" " + choseEquipementRoom + " a été ajouté dans le sac à dos \n");

                } else if (choseEquipementRoom.isTool()) {
                    if (s.toolsBackpackIsFull()) { // Si le sac a dos est plein
                        System.out.println("Le sac à dos est plein. Choisissez un équipement à jeter :");
                        Equipment choseBackPackEquipment = equipmentChooser.choose(backPack); // choisir un
                                                                                              // equipement pamis
                                                                                              // ceux du sac a dos
                        s.removeEquipment(choseBackPackEquipment);// Jeter l'equipement
                        roomEquipement.add(choseBackPackEquipment);// ajouter l'equipement jeuter dans la piece
                        System.out.println(" " + choseBackPackEquipment + " a été jeté dans la pièce");

                    }
                    s.addToolInToolsBackpack((Tool) choseEquipementRoom);
                    System.out.println(" " + choseEquipementRoom + " a été ajouté dans le sac à dos\n");

                }
            }

            catch (FullToolsBackException | FullWeaponsBackException e) {
                System.out.println("Impossible de ramasser l'équipement. Le sac à dos est plein.\n");
            }
        } else if (cell.getEquipment().size() == 0) {
            System.out.println("Pas d'equipement dans cette cell");

        } else {
            System.out.println("is not a room");

        }
    }

    /**
     * Method to execute the action of searching a room.
     * 
     * @param s The survivor executing the action.
     */
    @Override
    public void execute(Survivor s) {
        fouille(s);
    }

    @Override
    public String toString() {
        return "search a room";
    }
}
