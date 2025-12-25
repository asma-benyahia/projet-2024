package plateau.equipment.tool;

import plateau.gamecharacter.Survivor;

public class HealingVial extends Tool {

    public HealingVial(int noiseLevel) {
        super(0);
    }

    /**
    * Method used by a survivor to heal, granting them an additional life point.
    * Additionally, this action consumes one action point from the survivor.
    *
    * @param user The survivor who is using the healing vial
    */
    public void useBy(Survivor user) {
        user.addLifePoint(1);
        user.subActionPoints(1);
        System.out.println("Survivant a gagné 1 point de vie en utilisant une fiole de guérison.");
    }

    @Override
    public String descriptionEquipement() {
        return "HealingVial";
    }

    public boolean isType(String type) {
        return type.equals("HealingVial");
    }

    @Override
    public String getName() {
        return "HealingVial";
    }

    @Override
    public boolean canOpenDoor() {
        return false;
    }
}
