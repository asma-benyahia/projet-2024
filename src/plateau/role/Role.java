package plateau.role;

import plateau.gamecharacter.Survivor;

public abstract class Role  { 
    protected String pseudo;

    public Role(String pseudo) {
        this.pseudo = pseudo;
    }


    public abstract String getTypeRole();

    public abstract String descriptionRole();

    public abstract void addSpecificActions(Survivor s);
}
