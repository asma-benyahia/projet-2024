package plateau;

import java.util.Random;

public class Livrable4 {

    public static void main(String[] args) {
        int largeur ;
        int hauteur ;
        int numberOfSurvivors=2;
        int xd = 0;
        int yd = 0;
        boolean isFirstIteration = true;
        if (args.length >= 2) {
            try {
                largeur = Integer.parseInt(args[0]);
                hauteur = Integer.parseInt(args[1]);
                if(args.length >= 3){
                    numberOfSurvivors= Integer.parseInt(args[2]);
                }
            } catch (NumberFormatException e) {
                System.err.println("Les arguments doivent être des entiers ");
                return;
            }
        }else{
            Random random = new Random();
            int t = random.nextInt(11-5)+6;
            largeur = t;
            hauteur=t;
        }
        Board board = new Board(largeur, hauteur);
        board.perceRue(xd, yd, largeur, hauteur, isFirstIteration);
        board.specialRooms();
        board.openStreetDoors();
        

        
        
        
        Game game = new Game(board, numberOfSurvivors);
        // on crée 4 zombies Dans les 4 Bouche d'egouts
        // Si on a 4 zombies , on dois avoir 4 ou plus de survivor
        // a mettre plus tard 
        board.descriptionDoors();
        game.play();



    }
    
}
