package plateau.equipment.weapon;

public class Pistol extends Weapon {
    public Pistol() {
        super(1, 4, 1, 0, 1, 1); // pistolet : 1 lancer de dé, seuil 4, valeur de dégât 1, portée 0 à 1
    }

    @Override
    public String descriptionEquipement() {
        return "Pistol";
    }

    @Override
    public boolean canOpenDoor() {
        return true;
    }

}