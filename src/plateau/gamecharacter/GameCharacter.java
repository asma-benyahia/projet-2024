package plateau.gamecharacter;
import plateau.*;

public abstract class GameCharacter{

    protected int lifePoints;
    protected int actionPoints;
    protected  Cell  currentCell ;

  


    public abstract  int getLifePoints() ;

    public void setLifePoinnts(int lifePoints){
        this.lifePoints = lifePoints ;
    }

    public void setActionsPoints( int actionPoints){
        this.actionPoints = actionPoints;
    }
    public Cell getCurrentCell(){
        return this.currentCell;
    }
    public void setCurrentCell( Cell cell){
        this.currentCell = cell ;
    }

    public abstract void  setCurrentCell2(Cell cell);
    
    public boolean isDead (){
		return this.lifePoints <= 0;	
	}
    public abstract void move();

    public abstract void changeCell(Cell newCell);


	public abstract String descriptionGameCharacter();

    public void removeLifePoints(){
        this.lifePoints--;
    }
    public void removeActionPoints(){
        this.actionPoints--;
    }
     

}
    

