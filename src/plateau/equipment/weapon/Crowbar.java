package plateau.equipment.weapon;

public class Crowbar extends Weapon {
    public Crowbar() {
        super(1, 4, 1, 0, 0, 0); // pied de biche : 1 lancer de dé, seuil 4, valeur de dégât 1, portée 0
        // pied de biche est silencieux donc 0
    }

    

    @Override
    public String descriptionEquipement() {
        return "Crowbar";
    }



    @Override
    public boolean canOpenDoor() {
        return false;
    }

}