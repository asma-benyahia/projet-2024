package plateau.equipment.tool;

import java.util.ArrayList;
import java.util.List;

import plateau.*;
import plateau.gamecharacter.Survivor;
import plateau.gamecharacter.Zombie;

/**
 * Class representing a Card tool that allows a survivor to see all survivors and zombies on the board.
 */
public class Card extends Tool {

    private ArrayList<Zombie> allZombies;
    private ArrayList<Survivor> allSurvivor;

    public Card(int noiseLevel) {
        super(noiseLevel);
        this.allZombies = new ArrayList<>();
        this.allSurvivor = new ArrayList<>();
    }

    @Override
    public String descriptionEquipement() {
        return "Card";
    }

    /**
    * Method used to see all survivors and zombies present on the board.
    *
    * @param user The survivor initiating the action.
    */
    @Override
    public void useBy(Survivor user) {
        Board board = user.getBoard();
        // Récupère la liste des survivants et zombies du plateau
        List<Survivor> survivors = board.getSurvivors();
        List<Zombie> zombies = board.getZombies();

        // Parcours la liste des survivants et ajoute chaque survivant à la liste
        // allSurvivor
        for (Survivor survivor : survivors) {
            this.allSurvivor.add(survivor);
        }

        // Parcours la liste des zombies et ajoute chaque zombie à la liste allZombie
        for (Zombie zombie : zombies) {
            this.allZombies.add(zombie);
        }
    }

    public boolean isType(String type) {
        return type.equals("Card");
    }

    @Override
    public boolean canOpenDoor() {
        return false;
    }

}