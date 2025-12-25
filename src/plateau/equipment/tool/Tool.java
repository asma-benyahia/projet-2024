package plateau.equipment.tool;

import plateau.gamecharacter.Survivor;
import plateau.Cell;
//import plateau.*;
import plateau.equipment.Equipment;

public abstract class Tool extends Equipment {
    public Tool(int noiseLevel) {
        super(noiseLevel);
    }

    public abstract void useBy(Survivor user);

    public abstract String descriptionEquipement();

    public Cell getCurrentCell() {
        return this.getCurrentCell();
    }

    @Override
    public boolean isTool() {
        return true;
    }

}