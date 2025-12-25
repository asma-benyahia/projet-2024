package plateau.action;

import java.util.List;

import plateau.Cell;
import plateau.equipment.Equipment;
import plateau.equipment.weapon.Weapon;
import plateau.gamecharacter.Survivor;
import plateau.gamecharacter.Zombie;
import plateau.listchooser.InteractiveListChooser;
import plateau.listchooser.ListChooser;

/**
 * Represents an action where a survivor attacks a zombie.
 */
public class AttackAction extends Action {
    
  //list of zombies 
  	private ListChooser<Zombie> zombiesListChooser;



    /**
     * Constructs an AttackAction object.
     *
     */
    public AttackAction(){
        super();

    }

    
    /**
     * Executes the attack action.
     *
     * @param s The survivor performing the attack.
     */
    @Override
    public void execute(Survivor s) {
    	Cell cellOfSurvivor = s.getCurrentCell();
        List<Zombie> zombies = cellOfSurvivor.getZombies();

        if (!zombies.isEmpty()) {
            Zombie zombietoattack = zombies.get(0);
            for (Zombie zombie : zombies) {
                if (zombie.getLifePoints() <= zombietoattack.getLifePoints()) {
                    zombietoattack = zombie; // Choisir celui qui a le moins de linPoints
                }
            }

            Equipment currentEquipment = s.getCurrentEquipment();
            if (currentEquipment.isWeapon()) {
                Weapon weapon = (Weapon) currentEquipment;
                int totalDamage = weapon.getDamage();
                zombietoattack.takeDamage(totalDamage);
                System.out.println("Survivor attacked the zombie " + zombietoattack.getType() + " with " + weapon.descriptionEquipement() + ". Zombie's remaining lifePoints " + zombietoattack.getLifePoints());
                if (zombietoattack.isDead()) {
                    zombietoattack.getCurrentCell().removeZombie(zombietoattack);
                    System.out.println("Zombie dead");
                    s.isExpert();

                    int expertLevel = s.getExpertLevel();
                    if (expertLevel == 3 || expertLevel == 7 || expertLevel == 11) {
                        // Autorisez une action supplémentaire pour ce tour de survivant
                        s.addaActionpoints(1);
                    }
                }
            } else {
                // Gérer le cas où l'équipement n'est pas une arme
                System.out.println("Cannot attack because the current equipment is not a weapon.");
            }
        } else {
            System.out.println("Invalid zombie for attack!");
        }
    
    }
    
    @Override
    public String toString() {
    	return "attack";
    }
    
}
