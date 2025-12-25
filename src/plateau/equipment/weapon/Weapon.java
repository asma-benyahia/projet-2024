package plateau.equipment.weapon;

import plateau.Cell;
import plateau.equipment.Equipment;
import plateau.gamecharacter.Zombie;

public abstract class Weapon extends Equipment {

    private int nbDice;
    private int threshold;// seuil, à modif pour chainsaw (5)
    private int damage;
    private int minRange; // portée maximale
    private int maxRange; // portée minimale
    private int noiseLevel;

    public Weapon(int nbDice, int threshold, int damage, int minRange, int maxRange, int noiseLevel) {
        super(noiseLevel);
        this.nbDice = nbDice;
        this.threshold = threshold;
        this.damage = damage;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    // POUR NOISE LEVEL : je vais mettre 1 pour bruyants et 0 pour silencieux

    

    public int getNbDice() {
        return nbDice;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getDamage() {
        return damage;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getNoiseLevel() {
        return noiseLevel;
    }

    public Cell getCurrentCell() {
        return this.getCurrentCell();
    }

    public abstract String descriptionEquipement();

    public void useWeap(Zombie z){
        z.takeDamage(this.damage);
    }

    @Override
    public boolean isWeapon() {
        return true;
    }



}