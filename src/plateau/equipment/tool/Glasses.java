package plateau.equipment.tool;

import plateau.Cell;
import plateau.Direction;
import plateau.gamecharacter.Survivor;
import plateau.action.LookAroundAction;


public class Glasses extends Tool {

    public Glasses(int noiseLevel) {
        super(0);
    }

    @Override
    public String descriptionEquipement() {
        return "Glasses";
    }

    /**
     * Method allowing a survivor to observe the surrounding cells.
     *
     * @param user The survivor who is using this action to observe the cells.
    */
    @Override
    public void useBy(Survivor user) {

        Cell currentCell = user.getCurrentCell();

        // Récupérer les cellules voisines
        Cell leftCell = currentCell.getNeighbor(Direction.Left);
        Cell rightCell = currentCell.getNeighbor(Direction.Right);
        Cell upCell = currentCell.getNeighbor(Direction.Up);
        Cell downCell = currentCell.getNeighbor(Direction.Down);
        
        // Afficher le contenu des cellules voisines
        System.out.println("Contenu de la cellule à gauche : " + leftCell.description());
        System.out.println("Contenu de la cellule à droite : " + rightCell.description());
        System.out.println("Contenu de la cellule au-dessus : " + upCell.description());
        System.out.println("Contenu de la cellule en dessous : " + downCell.description());

    }
    

    public boolean isGlasses() {
        return true;
    }

    @Override
    public boolean canOpenDoor() {
        return false;
    }
}