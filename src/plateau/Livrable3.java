package plateau;

import plateau.gamecharacter.Survivor;
import plateau.gamecharacter.Zombie;
import plateau.gamecharacter.exception.FullWeaponsBackException;
import plateau.gamecharacter.exception.FullToolsBackException;
import plateau.action.*;
import plateau.gamecharacter.zombie.*;
import plateau.role.Fighter;
import plateau.role.Healer;
import plateau.role.Lucky;
import plateau.role.Snooper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import plateau.role.Role;
import plateau.role.*;

import plateau.equipment.Equipment;
import plateau.equipment.tool.*;
import plateau.equipment.weapon.*;

public class Livrable3 {
    public static void main(String[] args) {

        int largeur = 5;
        int hauteur = 5;
        int xd = 0;
        int yd = 0;

        boolean isFirstIteration = true;
        Board board = new Board(largeur, hauteur);

        System.out.println("Ville d'entraînement  :");

        System.out.println("Plateau de jeu  :");
        board.perceRue(xd, yd, largeur, hauteur, isFirstIteration);
        board.specialRooms();
        board.openStreetDoors();
        board.placerZombiesAuxEgouts();
        board.Noise0();

        // liste contenant les quatre rôles différents
        List<Role> roles = Arrays.asList(new Fighter(), new Healer(), new Lucky(), new Snooper());

        // Trouver la cellule au nord du carrefour principal
        Cell northCrossroadCell = board.cellAtNorthCrossroadRandom();
        if (northCrossroadCell != null) {
            // Parcourir la liste des rôles
            for (int i = 0; i < 4; i++) {
                // Obtenir le rôle à l'index actuel
                Role role = roles.get(i);
                // Créer un survivant
                Survivor survivor = new Survivor(board);
                // Ajouter le rôle à la liste des rôles du survivant
                survivor.addOneRole(role);

                // Ajouter le survivant à la cellule au nord du carrefour principal
                northCrossroadCell.addSurvivor(survivor);
            }
        } else {
            System.out.println("Aucune cellule au nord du carrefour.");
        }

        // placer une hache dans la main du second survivant
        Survivor secondSurvivor = northCrossroadCell.getSurvivors().get(1);
        Equipment equipment = new Axe();
        TakeInHandAction takeInHandAction = new TakeInHandAction();
        takeInHandAction.execute(secondSurvivor);

        // placer une fiole dans la main du troisième survivant + exécuter une action
        // par survivant
        Survivor thirdSurvivor = northCrossroadCell.getSurvivors().get(2);
        Equipment equipment2 = new HealingVial(0);
        TakeInHandAction takeInHandAction2 = new TakeInHandAction();
        takeInHandAction2.execute(thirdSurvivor);

        Zombie z = new Abomination(board);
        board.getCell()[0][0].addZombie(z);

        Survivor Fighter = new Survivor(board);
        Survivor Healer = new Survivor(board);
        Survivor Lucky = new Survivor(board);
        Survivor Snooper = new Survivor(board);

        board.getCell()[0][3].addSurvivor(Fighter);
        board.getCell()[0][4].addSurvivor(Healer);
        board.getCell()[1][4].addSurvivor(Lucky);
        board.getCell()[1][3].addSurvivor(Snooper);

        System.out.println(board.toString());

        System.out.println(board.description());
        // board.getCell()[3][2].getDoor(Direction.Down).openDoor();
        board.descriptionDoors();

        System.out.println();

        Role role = secondSurvivor.getRole();
        Role role2 = thirdSurvivor.getRole();

        System.out.println("bruit de niveau : " + board.getCell()[3][2].getNoiseLevel() + ".");

        System.out.println(
                "Le deuxième survivant de Role " + role.getTypeRole() + " a " + equipment.getName() + " en main.");
        System.out.println(
                "Le troisième survivant de Role " + role2.getTypeRole() + " a " + equipment2.getName() + " en main.");

        //System.out.println(Fighter.getRoles());

        System.out.println("Le zombie présent dans la case 1 est : " + z.getType());
        z.setBoard(board);
        z.move();

        System.out.println("--------------------------------------------------");
        System.out.println("Le survivant dans la cellule [0][3] est un : Fighter");
        System.out.println("Le survivant dans la cellule [0][4] est un : Healer");
        System.out.println("Le survivant dans la cellule [1][4] est un : Lucky");
        System.out.println("Le survivant dans la cellule [1][3] est un : Snooper");

    }
}