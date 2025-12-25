package plateau.equipment.tool;

import plateau.gamecharacter.Survivor;
import plateau.action.OpenDoorAction;


public class MasterKey extends Tool {

    public MasterKey(int noiseLevel) {
        super(0);
    }


    /**opens a door
    *
    *@param user the survivor using the Master Key
    */
    @Override
    public void useBy(Survivor user) {
        OpenDoorAction o=new OpenDoorAction();
        o.execute(user);

    }

    @Override
    public String descriptionEquipement() {
        return "MasterKey";
    }

    //@Override
    public boolean isType(String type) {
        return type.equals("MasterKey");
    }

    @Override
    public boolean canOpenDoor() {
        return true;
    }

}