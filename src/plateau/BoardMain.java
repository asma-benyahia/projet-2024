package plateau;

import plateau.gamecharacter.Survivor;
import plateau.gamecharacter.Zombie;
import plateau.gamecharacter.zombie.*;

import java.util.Random;


import plateau.equipment.tool.*;

public class BoardMain {
    public static void main(String[] args) {
        int largeur ;
        int hauteur ;
        int xd = 0;
        int yd = 0;
        if (args.length >= 2) {
            try {
                largeur = Integer.parseInt(args[0]);
                hauteur = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Les arguments doivent être des entiers ");
                return;
            }

        } 
        else{
            Random random = new Random();
            int t = random.nextInt(11-5)+6;
            largeur = t;
            hauteur=t;
        }
        
        boolean isFirstIteration = true;
        Board board = new Board(largeur, hauteur);

        System.out.println("Plateau de jeu  :");
        board.perceRue(xd, yd, largeur, hauteur, isFirstIteration);
        board.specialRooms();
        board.openStreetDoors();
        board.getCell()[0][0].getDoor(Direction.Down).openDoor();

        Zombie z = new Abomination(board);
        Zombie z1 = new Balaise(board);

        Survivor s = new Survivor(board);

        Tool t = new Kit(9);
        Tool t1 = new MasterKey(10);

        board.getCell()[0][0].addZombie(z);
        board.getCell()[0][0].addZombie(z1);
        board.getCell()[1][1].addSurvivor(s);

        board.getCell()[4][2].addTool(t1);
        board.getCell()[2][4].addTool(t);

        System.out.println(board.toString());

        System.out.println(board.description());
        
        board.descriptionDoors();

        System.out.println("la porte en bas de la cell [4][2] est ouverte "
                + board.getCell()[3][2].getDoor(Direction.Down).isOpen());
                

        
        System.out.println("Les zombies présents dans la case 1 sont : " + z.getType() + " et " + z1.getType());
        System.out.println("Le tool a été ajouté à la cellule [4][2]: " + board.getCell()[4][2].cellHasTool());

    }
}
