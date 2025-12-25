package plateau.equipment.weapon;

public class Rifle extends Weapon {
    public Rifle() {
        super(2, 4, 1, 1, 3, 1); // carabine : 2 lancers de dé, seuil 4, valeur de dégât 1, portée 1 à 3
        // 1 car bruyant
    }

    @Override
    public String descriptionEquipement() {
        return "Rifle";
    }

    @Override
    public boolean canOpenDoor() {
        return true;
    }

}