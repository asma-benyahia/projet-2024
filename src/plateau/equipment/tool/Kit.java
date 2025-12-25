package plateau.equipment.tool;

import plateau.gamecharacter.*;
import plateau.listchooser.ListChooser;
import plateau.listchooser.RandomListChooser;
import plateau.listchooser.InteractiveListChooser;

import java.util.List;


public class Kit extends Tool {

  private static ListChooser<Survivor> survivorsInZone = new RandomListChooser<Survivor>(); // ?? 

  public Kit(int noiseLevel) {
    super(0);
  }
  
  /**method used by th survivor to heal another survivor present in the same cell
   * sub life action to the healer and add life point to the other survivor
   * 
   * @param user the survivor using the kit to heal the other survivor
   */
  @Override
  public void useBy(Survivor user) {
    List<Survivor> survivorsInZone=user.getCurrentCell().getSurvivors();
    ListChooser<Survivor> survChoose=new RandomListChooser<Survivor>();
	
    Survivor choosedSurv=survChoose.choose(survivorsInZone);
    if (choosedSurv != null) {
      choosedSurv.addLifePoint(1);
      user.subActionPoints(1);
    }
  }
   

  @Override
  public String descriptionEquipement() {
    return "Kit";
  }

  //@Override
  public boolean isType(String type) {
    return type.equals("Kit");
  }


  @Override
  public boolean canOpenDoor() {
    return false;
  }

}