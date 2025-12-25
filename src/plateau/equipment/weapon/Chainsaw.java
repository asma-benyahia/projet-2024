package plateau.equipment.weapon;

public class Chainsaw extends Weapon {
    public Chainsaw() {
        super(2, 5, 3, 0, 0, 1); // tronçonneuse : 2 lancers de dé, seuil 5, valeur de dégât 3, portée 0
        // tronçonneuse est bruyant , donc 1
    }

    @Override
    public String descriptionEquipement() {
        return "Chainsaw";
    }

    @Override
    public boolean canOpenDoor() {
        return false;
    }

}