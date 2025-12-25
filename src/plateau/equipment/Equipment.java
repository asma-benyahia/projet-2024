package plateau.equipment;

public abstract  class Equipment {
    // on laisse classe ou on change par interface ??
    // chaque equipement a un niveau de bruit et ya ceux qui sont silencieux
    protected int noiseLevel;
    protected boolean canOpen;

    public Equipment(int noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public int getNoiseLevel() {
        return this.noiseLevel;
    }

    public abstract boolean canOpenDoor();

    public abstract String descriptionEquipement();

    public boolean isWeapon() {
        return false;
    }

    public boolean isTool() {
        return false;
    }

    public String getName() {
        return this.descriptionEquipement();
    }
    @Override
    public String toString() {
        return this.descriptionEquipement();
    }

    // public abstract boolean isType(String type);
    // Equipment à passer en classe abstraite pour pouvoir utiliser dans Action ??

}