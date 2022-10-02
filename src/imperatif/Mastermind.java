package imperatif;

import java.util.Scanner;

public class Mastermind {
		
	/** l'arrangement des 4 pion **/
	public static int[] arrangement (int nbElements, int min, int max) {
		int[] tab = new int[nbElements];
		for (int i = 0 ; i < tab.length ; i++) {
			tab[i] = (int) (Math.random() * (max - min) + min); 
		}
		return tab;
	}
	
	/** Afficher l'arragement **/
	public static String afficherColor (int[] tab) {
		String monTab = "{";
		for (int i = 0 ; i < tab.length -1; i++) {
			switch (tab[i]) {
			case 1 : monTab += "bleu" + ","; break;
			case 2 : monTab += "rouge" + ","; break;
			case 3 : monTab += "vert" + ","; break;
			case 4 : monTab += "jaune" + ","; break;
			case 5 : monTab += "orange" + ","; break;
			case 6 : monTab += "rose" + ","; break;
			case 7 : monTab += "violet" + ","; break;
			default : monTab += "noir" + ","; break;
			}
		}
		switch (tab[3]) {
		case 1 : monTab += "bleu" + "}"; break;
		case 2 : monTab += "rouge" + "}"; break;
		case 3 : monTab += "vert" + "}"; break;
		case 4 : monTab += "jaune" + "}"; break;
		case 5 : monTab += "orange" + "}"; break;
		case 6 : monTab += "rose" + "}"; break;
		case 7 : monTab += "violet" + "}"; break;
		default : monTab += "noir" + "}"; break;
		}
		return monTab;
	}
	
	
	/*----- joueur choisis un assortiment de 4 couleurs parmi les 8 disponible -----*/
	
	public static String[] CouleurJoueur () {
		Scanner sc = new Scanner(System.in);
		String[] CouleurJoueur = new String[4];
		for (int i = 0; i < 4; i++) {  
			System.out.println("Entrez la couleur " + (i+1) + " (options : bleu/rouge/vert/jaune/orange/rose/violet/noir)" );
			CouleurJoueur[i] = sc.nextLine().toLowerCase();
		}
		return CouleurJoueur;
	}
	
	/*----- transformer la proposition en int tab -----*/
	public static int[] transformerProposition (String[] tab) {
		int[] propo = new int[4];
		for (int i = 0 ; i < tab.length; i++) {
			switch (tab[i]) {
			case "bleu" : propo[i] = 1; break;
			case "rouge" : propo[i] = 2; break;
			case "vert" : propo[i] = 3; break;
			case "jaune" : propo[i] = 4; break;
			case "orange" : propo[i] = 5; break;
			case "rose" : propo[i] = 6; break;
			case "violet" : propo[i] = 7; break;
			case "noir" : propo[i] = 8; break;
			default : propo[i] = 0; break;
			}
		}
		return propo;
	}
	
	public static String afficherTableau (String[] strings) { 
		// Algo nous donnant la description du tableau crée précedemment
		String monTab = "{" +strings[0];
		for(int i = 1; i< strings.length; i++) {
			monTab = monTab + ", " + strings[i];
		}
		monTab += "}";
		return monTab;
	}
	
	
	/*
	 * deroulement de jeux
	 */
	
	
	/*----- reponse : indique le nombre de pions de bonne couleur et bien placés -----*/
	public static int[] verifier (int[] arr, int[] propo) {
		int[] verifier = new int[2];		
		int sommeBien = 0;
		int sommeMal = 0;
		for (int i = 0 ; i < propo.length; i++) {
			if (propo[i] == arr[i]) {
				propo[i] = 0;
				arr[i] = 0;
				sommeBien ++;
			}
		}
		
	/*----- indique le nombre de pions de bonne couleur mais mal placés -----*/
		for(int i = 0; i < propo.length; i++){
            if(propo[i] != 0){
                for(int j = 0; j < arr.length; j++){
                    if(arr[j] == propo[i]){
                        arr[j] = 0;
                        propo[i] = 0;
                        sommeMal ++;
                        break;
                    }
                }
            }
        }
		verifier[0] = sommeBien;
		verifier[1] = sommeMal;
		return verifier;
	}

	public static int choixNiveau(){
		int MaxCoup = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Choix de niveau (options : easy/medium/hard/insane/extreme)" );
		String Niveau = sc.nextLine().toLowerCase();
		while (MaxCoup == 0) {
			switch (Niveau) {
				case "easy" : {
					MaxCoup = 100;
					System.out.println("Niveau : easy");
					System.out.println("Vous aurez " + MaxCoup + " chances d'essayer."); 
					break;
				}
				case "medium" : {
					MaxCoup = 80;
					System.out.println("Niveau : medium"); 
					System.out.println("Vous aurez " + MaxCoup + " chances d'essayer."); 
					break;
				}
				case "hard" : {
					MaxCoup = 60;
					System.out.println("Niveau : hard"); 
					System.out.println("Vous aurez " + MaxCoup + " chances d'essayer."); 
					break;
				}
				case "insane" : {
					MaxCoup = 25;
					System.out.println("Niveau : insane"); 
					System.out.println("Vous aurez " + MaxCoup + " chances d'essayer."); 
					break;
				}
				case "extreme" : {
					MaxCoup = 15;
					System.out.println("Niveau : extreme"); 
					System.out.println("Vous aurez " + MaxCoup + " chances d'essayer."); 
					break;
				}
				default : {
					System.out.println("Veuillez saisir un niveau valide.");
					System.out.println();
					sc = new Scanner(System.in);
					System.out.println("Choix de niveau "+" (options : easy/medium/hard/insane/extreme)" );
					Niveau = sc.nextLine().toLowerCase();
					break;
				}
			}
		}
		return MaxCoup;
	}
	
	public static int ChoixMode() {
		int codeMode = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Choix de mode (options : joueurguess/pcguess)" );
		String Mode = sc.nextLine().toLowerCase();
		while (codeMode == 0) {
			switch (Mode) {
			case "joueurguess" : codeMode = 1; break;
			case "pcguess" : codeMode = 2; break;
			default : {
				System.out.println("Veuillez saisir une mode valide.");
				System.out.println();
				sc = new Scanner(System.in);
				System.out.println("Choix de mode (options : jouerguess/pcguess)" );
				Mode = sc.nextLine().toLowerCase();
				break;
				}
			}
		}
		return codeMode;
	}
	 
	//developperGuess
	public static int[] developper(int[] guess){
		int m = guess[0]*1000 + guess[1]*100 + guess[2]*10 + guess[3];
		int[] newguess = new int[4];
		if (m <= 8888 && guess[0] < 9 && guess[1] < 9 && guess[2] < 9 && guess[3] < 9) {
			m++;
			newguess[3] = m%10 ;
			newguess[2] = m/10%10 ;
			newguess[1] = m/100%10;
			newguess[0] = m/1000%10;
		}
		return newguess;
	}


	/*
	 * Programme de test
	 */
	
	public static void main(String[] args){
		System.out.println( "\t╔╦╗╔═╗╔═╗╔╦╗╔═╗╦═╗╔╦╗╦╔╗╔╔╦╗  ");
		System.out.println( "\t║║║╠═╣╚═╗ ║ ║╣ ╠╦╝║║║║║║║ ║║  ");
		System.out.println( "\t╩ ╩╩ ╩╚═╝ ╩ ╚═╝╩╚═╩ ╩╩╝╚╝═╩╝  ");
		System.out.println("Bienvenue dans le Mastermind créé par Yongkang et Sarah !");
		int codeMode = ChoixMode();
		switch (codeMode){
		case 1 : MasterMindJoueurGuess(); break;
		default : MasterMindPCGuess(); break;
		}
	}
	
	public static void MasterMindJoueurGuess() {	
		System.out.println();
		System.out.println("Regles du jeu : ");
		System.out.println("Vous avez plusieurs chances de trouver la bonne combinaison.");
		System.out.println("La combinaison comporte 4 couleurs choisis parmi 8 couleurs différentes.");
		System.out.println("Vous allez choisir un niveau pour votre jeu.");
		System.out.println("Pour chaque niveau différent, vous aurez un nombre différent des chances.");
		System.out.println();
		System.out.println("ALLEZ ! BON COURAGE !");
		System.out.println();
		
		//Choisir le niveau de jeu
		int MaxCoup = choixNiveau();
		System.out.println();
		
		/*----- L'ordinateur choisi aléatoirement un arrangement de 4 pions de couleur -----*/
		int[] t1 = arrangement(4,1,8);
		//MODE TRICHER
		//System.out.println(afficherColor(t1));
		//System.out.println();
	
		/*----- jouer inserte son proposition-----*/
		int coup = 1;
		String[] propo = CouleurJoueur();
		System.out.println("Votre proposition n°" + coup + "/" + MaxCoup +" : " + afficherTableau(propo));
		
		int[] t2 = transformerProposition(propo);
		int[] t3 = {0,0};
		t3 = verifier(t1,t2);
		
		//situtation pas trouve
		//afficher la reponse de l'ordinateur
		while (t3[0] != 4 && coup <= MaxCoup) {
			System.out.println("Raté, vous avez : ");
		if (t3[0] <= 1) 
			System.out.println(t3[0] + " bien placé"); 
		else
			System.out.println(t3[0] + " bien placés");
		if (t3[1] <= 1) 
			System.out.println(t3[1] + " mal placé");
		else
			System.out.println(t3[1] + " mal placés");
		
		//reinitialiser notre sous-programme verifier
		t3[0] = 0;
		t3[1] = 0;
		System.out.println("Faites attention ! Vous avez encore " + (MaxCoup - coup) + " chances !");
		System.out.println();
		
		System.out.println("Réessayez à nouveau :");
		coup ++;
		propo = CouleurJoueur();
		System.out.println("Votre proposition n°" + coup + " : " + afficherTableau(propo));
		t2 = transformerProposition(propo);
		t3 = verifier(t1,t2);
		}
		
		while (t3[0] != 4 && coup > MaxCoup) {
			System.out.println("Oh no! Vous avez déjà fait " + MaxCoup + " coups.");
			System.out.println("Vous avez épuisé le  nombre de tentatives pour ce  niveau.");
			System.out.println("La bonne combinaison : " + afficherColor(t1));
			break;
		}
		
		//situation gagner
		while (t3[0] == 4) {
			System.out.println("Bravo! Vous avez gagnez avec " + coup + " coup(s)!");	
			break;
			}
		}
	
	public static void MasterMindPCGuess() {
		System.out.println();
		System.out.println("Regles du jeu : ");
		System.out.println("Vous allez proposer  une combinaison.");
		System.out.println("La combinaison comporte 4 couleurs choisis, parmi 8 couleurs différentes.");	
		System.out.println("Le PC va tenter de deviner votre combinaison.");
		System.out.println();
		System.out.println("On commence !");
		System.out.println();
		
		//le joueur donne la bonne combinaison
		String[] propo = CouleurJoueur();
		int[] t1 = transformerProposition(propo);
		
		//PC guess
		int coup = 1;
		int[] t2 = {1,1,1,1};
		System.out.println("la proposition n°" + coup + " de PC : " + afficherColor(t2));
		
		int[] t3 = {0,0};
		t3 = verifier(t1,t2);
		
		//situtation pas trouve
		//afficher la reponse de l'ordinateur
		while (t3[0] != 4 ){
			System.out.println("Raté, il a : ");
		if (t3[0] <= 1) 
			System.out.println(t3[0] + " bien placé"); 
		else
			System.out.println(t3[0] + " bien placés");
		if (t3[1] <= 1) 
			System.out.println(t3[1] + " mal placé");
		else
			System.out.println(t3[1] + " mal placés");
		 
		//reinitialiser notre sous-programme verifier
		t3[0] = 0;
		t3[1] = 0;
		System.out.println();
		
		System.out.println("Réessayez à nouveau :");
		coup ++;
		t2 = developper(t2);
		System.out.println("la proposition n°" + coup + " de PC : " + afficherColor(t2));
		t3 = verifier(t1,t2);
		}
		
		//situation gagner
		while (t3[0] == 4) {
			System.out.println("Bravo! Le PC a réussi de trouver la bonne combinaison avec " + coup + " coup(s)!");	
			break;
			}
		}
	}
