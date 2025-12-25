package plateau;

import java.util.*;


import plateau.gamecharacter.*;
import plateau.listchooser.RandomListChooser;
import plateau.role.*;
import plateau.equipment.Equipment;
import plateau.equipment.tool.*;
import plateau.equipment.weapon.*;
import plateau.room.Room;
// Pour le moment on a bien a chaque fois le tour de survivor , et ouis on a le toue de zombies puis fin de tour 
// on le verifie avec laffichage de chaque Phase et le i qui nous compte le tour combien

public class Game {
	
	private Board board;
	private Phase currentPhase ;
	private static int EXP_GLOBAL = 0;
	

	public Game(Board board , int numberOfSurvivors) {
		this.board = board;
		createSurvivors(numberOfSurvivors);
		System.out.println("\n");
        initSurvivors();
		placeEquipementsINRoom(); 
		//board.placerZombiesAuxEgouts(); Pas de zombie au debus
		this.currentPhase = Phase.SURVIVORS; // la premiere phase est survivors
		
	}
	
	
	
	/**
	 * generate a type od Role randomly
	 * @return a role
	 */
	private Role genererTypeDeRoleAleatoire() {
        // Liste des types de roles disponibles
        List<Role> typesDeRoles = Arrays.asList(new Fighter(), new Healer(), new Lucky(), new Snooper());

        // Génération aléatoire d'un indice pour choisir un type de role dans la liste
        Random random = new Random();
        
        int indice = random.nextInt(typesDeRoles.size());

        // Retourne le type de zombie correspondant à l'indice généré aléatoirement
        return typesDeRoles.get(indice);
    }

	
	
	/**
	 * create survivors
	 * @param n number of survivors for create
	 */
	private void  createSurvivors(int n){
		for(int i =0; i<n; i++){
			Survivor s = new Survivor(board);
			Role role = genererTypeDeRoleAleatoire();
			s.addRole(role);
			board.addSurvivor(s);
			System.out.print(s);
			s.setName("s" +(i+1));
		}
	}
	
	


	// Méthode pour initialiser les survivants avec leurs rôles aleatoirement dans le plateau
	/**
	 * initialise survivors with their role randomly at board
	 */
    private void initSurvivors() {
        
    	
     // Liste des cellules disponibles pour placer les survivants
        List<Cell> cellulesDisponibles = new ArrayList<>();
		placeEquipementsINRoom(); 

        for (int i = 0; i < board.getLarg(); i++) {
            for (int j = 0; j < board.getHaut(); j++) {
            	Cell cellule = board.getCell()[i][j];
                if (cellule.isCarrefourP()) {
                	for (Survivor survivor : board.getSurvivors()) {
                            //ajouter les survivants avec leurs rôles au plateau aleatoirement et retirer la cellule de la liste des cellules disponibles
                            cellule.addSurvivor(survivor);
                    }
                }       	
            }
        }
    }	
    
    
    


	/**
	 * play the game
	 */
	public void play() {
		board.placerZombiesAuxEgouts();
		//boolean jeuFini = true; // ajout de jeuFini que pour que la boucle sarrete a un moment
		int i = 1;
		//tq jeu pas fini
		while (!isGameOver()) {// la boucle tourne aà l'infini si on enlève le '!' snn elle tourne une fois et s'arrete
			if(currentPhase == Phase.SURVIVORS){
				System.out.println(" Tour " + i);
				System.out.println("Tour Survivors : " );
				
				SurvivorPlay();
				

			}
			else if (currentPhase == Phase.ZOMBIES){
				System.out.println("Tour zombies");
				//System.out.println("ON A :" + board.getZombies().size()+ " ZOMBIES");
				ZombiePlay();
			}
			else {
				EndPhasePlay();

			}
			//jeuFini=false;
			
		}
		board.descriptionDoors();
	}
	
	
	
	

	/**
	 *  end of phase
	 */
	private void EndPhasePlay() {
		System.out.println("Phase fin tour");
		currentPhase = Phase.END;
		// Nettoyer le plateau des zombies et survivants qui ont été tués
		board.eliminateSurvivorsFromGame();
		board.eliminateZombiesFromGame();
        // Remettre le bruit à zéro sur toutes les zones
		board.Noise0();
		// de nouveaux zombies apparaissent proportionnellement à l’expérience globale des survivants sur les zones des
		//rues disposant de bouches d’égouts, à condition qu’il reste encore des zombies “vivants”
		EXP_GLOBAL = 0;
		for(Survivor s : board.getSurvivors()){
			EXP_GLOBAL =+ s.getExpertLevel();
		 }
		 if(EXP_GLOBAL == 0){
			board.placerZombiesAuxEgouts();
		 }
		 //else{
			//board.multiplyZombiesFromSewers();
		 //}
		 System.out.println("GlobalExp = " + EXP_GLOBAL );
		 currentPhase= Phase.SURVIVORS;
	}
	
	/**
	 * check if all zombies are eliminated
	 * @return true if all zombies are eliminated false otherwise 
	 */
	private boolean zombiesEliminate() {
		boolean res =  board.getZombies().isEmpty();
		System.out.println("Zombies eliminate: " + res);
	    return res;
	}
	/**
	 * check if all survivors are eliminated
	 * @return true if all survivors are eliminated false otherwise 
	 */
	private boolean survivorsEliminate() {
		boolean res =  board.getSurvivors().isEmpty();
	    System.out.println("Survivors eliminate: " + res);
	    return res;
	}
	/**
	 * // atteindre un niveau d’expérience globale (somme des expériences des survivants) de 30
	 * @return true if enough Experience is atteint
	 */
	private boolean enoughExperience() {
		int totalExperience = 0;
		for (Survivor s : board.getSurvivors()) {
			totalExperience+=s.getExpertLevel();
		}
		boolean res =  totalExperience >= 30;
	    System.out.println("Enough experience: " + res);
	    return res;
	}
	

	/**
	 * check if it is game over
	 * @return true if it is game over, false otherwise
	 */
	private boolean isGameOver() {
	    // Le jeu est terminé si l'une des conditions est remplie
		boolean res = enoughExperience()|| survivorsEliminate() || zombiesEliminate() ;
	    System.out.println("Game over: " + res);
	    return res;
	    
	}
	
	

	private void SurvivorPlay(){
		// code a ecrire 
		System.out.println("Phase Survivor");
		//System.out.println("prblm dans isGameOver");// pourtant la logique de la methode et bon
		System.out.println("On a " + board.getSurvivors().size() + " Survivors");
		for(Survivor s : board.theSurvivors ){
			while(s.hasActionPoints()) {
				System.out.println("le tour de :" + s.getName());
				System.out.println("Le Survivant se trouve a la position X= " +  s.getX() + " Et la position Y= " + s.getY());
				System.out.println(" L'equipement present dans la main du survivant est " + s.getCurrentEquipment().descriptionEquipement());
				Cell currentCell = s.getCurrentCell();
				
				List<Equipment> roomEquipement = currentCell.getEquipment();
				System.out.print(" L'equipement présent  dans la cellule est " + roomEquipement.size()  + ", ");
				
				System.out.println("Life Points : " + s.getLifePoints() + ", Action Points : " + s.getActionPoint()+", Expertise : "+ s.getExpertLevel());
				s.survivorAct(); //Siii il faut pour tout le monde, car pour chaque survivors ds board on fait act
				
				System.out.println("Nv LifePoint : " + s.getLifePoints() + " Nv Action Points : " + s.getActionPoint()+", Expertise : "+ board.getAverageSurvivorExperience());
			}
			s.addaActionpoints(3);
				
			}
			
		// A la fin du code , on passe a la phase Zombie 
		currentPhase = Phase.ZOMBIES;
	}
	
	
	private void ZombiePlay(){
		// code 
		System.out.println("Phase zombie");
		for(Zombie z : board.getZombies()){
			z.setBoard(board);
			System.out.println("Le Zombie se trouve a la position X= " +  z.getCurrentCell().getX() + " Et la position Y= " + z.getCurrentCell().getY());

			z.zombieAct();
			System.out.println("Le Zombie se trouve a la position X= " +  z.getCurrentCell().getX() + " Et la position Y= " + z.getCurrentCell().getY());

		}
		// A la fin du code , on passe a la phase End
		currentPhase=Phase.END;
	}


	public void placeEquipementsINRoom() {
		Random random = new Random();
		for (int i = 0; i < board.getLarg(); i++) {
			for (int j = 0; j < board.getHaut(); j++) {
				Cell cell = board.getCell()[i][j];
				if (cell.isRoom()) {
					Room room = (Room) cell;
					int numEquipements = random.nextInt(3) + 1;
					for (int k = 0; k < numEquipements; k++) {
						Equipment equipment = generateRandomEquipment();
						room.addEquipment(equipment);
						System.out.println(cell.getEquipment().size() + " " + equipment.descriptionEquipement()
								+ " ajouté(s) à la case : [" + cell.getX() + "][" + cell.getY() + "]");
						System.out.println("");
					}
				}
			}
		}

	}
	
    public Equipment generateRandomEquipment(){
        List<Equipment> e = new ArrayList<>();
        e.add(new Axe());
        e.add(new Chainsaw());
        e.add(new Crowbar());
        e.add(new Pistol());
        e.add(new Rifle());
        // noiseLevel a changer pour chaque Tool
        e.add(new Card(0));
        e.add(new Glasses(1));
        e.add(new HealingVial(1));
        e.add(new Kit(1));
        e.add(new MasterKey(1));

        Random random = new Random();
        int randomIndex = random.nextInt(e.size());
        return e.get(randomIndex);

    }

	
	
	
}
