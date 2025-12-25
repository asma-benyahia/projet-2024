package plateau.equipment.weapon;

public class Axe extends Weapon {
    public Axe() {
        super(1, 4, 2, 0, 0, 0); // 1 lancer de dé, seuil 4, valeur de dégât 1, portée 0
        // 1 car il est silencieux
    }

    @Override
    public String descriptionEquipement() {
        return "Axe";
    }

    @Override
    public String getName() {
        return "Axe";
    }

    @Override
    public boolean canOpenDoor() {
        return false;
    }

}