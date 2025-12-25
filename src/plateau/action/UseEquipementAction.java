package plateau.action;

import plateau.equipment.Equipment;
import plateau.equipment.tool.Tool;
import plateau.equipment.weapon.Weapon;
import plateau.gamecharacter.Survivor;

public class UseEquipementAction extends Action {

    public UseEquipementAction() {
        super();
    }

    @Override
    public void execute(Survivor s) {
        Equipment equipementInHand = s.getCurrentEquipment();

        if (equipementInHand != null) {

            if (equipementInHand.isTool()) {
                Tool tool = (Tool) equipementInHand;
                tool.useBy(s);
                System.out.println("L'equipement " + tool.getName() + "à été utilisé et disparait du jeu ");
                s.removeEquipment(equipementInHand);
            } else if (equipementInHand.isWeapon()) {
                Weapon weapon = (Weapon) equipementInHand;
                if (s.getTarget() != null) {
                    weapon.useWeap(s.getTarget());
                } else {
                    System.out.println("Vous n'avez pas de cible");
                }

                // a verifier
                // attacker un zombie
                // ou ouvrir une porte
                // Parmi les équipements il y a les armes qui peuvent infliger des blessures aux
                // zombies.
                // Avoir une arme en main ne
                // permet pas d’attaquer, mais est nécessaire pour réaliser l’action d’attaquer
                // un zombie ou bien l’action d’ouverture de
                // porte
            }
        }
    }

    @Override
    public String toString() {
        return "use";
    }

}