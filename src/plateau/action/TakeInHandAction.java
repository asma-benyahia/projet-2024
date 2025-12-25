package plateau.action;

import java.util.List;

import plateau.equipment.Equipment;
import plateau.gamecharacter.Survivor;
import plateau.listchooser.InteractiveListChooser;
import plateau.listchooser.ListChooser;
import plateau.listchooser.RandomListChooser;

/**
 * This class represents an action for a survivor to take an equipment from
 * their backpack
 * and place it in their hand.
 */
public class TakeInHandAction extends Action {

	/**
	 * Constructor for the TakeInHandAction class.
	 */
	public TakeInHandAction() {
		super();
	}

	/**
	 * Executes the action of taking an equipment in hand.
	 * If the survivor's backpack is not empty, the player can choose an equipment
	 * from those available in the backpack to place in their hand.
	 * 
	 * @param s The survivor executing the action.
	 */
	@Override
	public void execute(Survivor s) {
		Equipment equipmentInHand = s.getInHand();
		List<Equipment> backpackEquipments = s.getAllEquipmentInBackpack();
		if (!backpackEquipments.isEmpty()) {

			ListChooser<Equipment> equipmentListChooser = new RandomListChooser<>();

			Equipment choosEquipment = equipmentListChooser.choose(backpackEquipments); // choisir un equipement
			backpackEquipments.remove(choosEquipment);
			if (equipmentInHand != null) {
				backpackEquipments.add(equipmentInHand);
				System.out.println("Lequipement " + equipmentInHand.getName() + "a été mis dans le sac a dos ");
			}
			s.setCurrentEquipement(choosEquipment);
			backpackEquipments.remove(choosEquipment);
			if (choosEquipment != null) {
				System.out.println("Le survivant a dans la main l'equipement :" + choosEquipment.getName());
			} else {
				System.out.println("Aucun équipement selectioné.");
			}
		}
	}

	@Override
	public String toString() {
		return "take in hand";
	}

}
