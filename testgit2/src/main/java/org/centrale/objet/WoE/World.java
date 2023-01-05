/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *CLasse permettant de gérer la création du monde ainsi, que l'emplacement des différentes entités
 * @author leo20
 */
public class World {
	/*Archer robin;
	Paysan peon;
	Lapin bugs1;
	Lapin bugs2;
	Guerrier grosBill;
	Loup wolfie;*/

    /**
     *
     */

    private ArrayList<Creature> crea;

    /**
     *
     */
    public ArrayList<Nourriture> consommable;
 
    /**
     *
     */
    public ArrayList<NuageToxique> danger ; 
    
    private Joueur player;

    /**
     *
     */
    public static final int TailleMonde = 5;
    
    /**
     *Constructeur par défaut de World
     */
    public World() {
    	/*this.robin = new Archer();
    	this.peon = new Paysan();
    	this.bugs1 = new Lapin();
    	this.bugs2 = new Lapin();
    	this.grosBill = new Guerrier();
    	this.wolfie = new Loup();*/
        crea = new ArrayList<>();
        danger = new ArrayList<>();
        consommable = new ArrayList<>();
        Archer robin = new Archer();
    	Paysan peon = new Paysan();
    	Lapin bugs1 = new Lapin();
    	Guerrier grosBill = new Guerrier();
    	Loup wolfie = new Loup();
        crea.add(robin);
    	crea.add(peon);
    	crea.add(bugs1);
    	crea.add(grosBill);
    	crea.add(wolfie);
        Nourriture nour = new Nourriture();
        NuageToxique nt = new NuageToxique();
        consommable.add(nour);
        danger.add(nt);
        player = new Joueur();
	}

    /**
     *Constructeur de World
     * @param crea Liste des créatures présentes
     * @param consommable Liste des consommables existants
     * @param danger Listes des dangers existants
     */
    public World(ArrayList<Creature> crea, ArrayList<Nourriture> consommable, ArrayList<NuageToxique> danger) {
        this.crea = crea;
        this.consommable = consommable;
        this.danger = danger;
        player = new Joueur();
    }
    
    /**
     *Constructeur de World, à partir du nombre pour chaque type d'entité
     * @param nbrPerso Nombre de Personnages présents
     * @param nbrMonstre Nombre de monstres présents
     * @param nbrObjet Nombre de consommables et de dangers présents
     */
    public World(int nbrPerso, int nbrMonstre, int nbrObjet) {
        this.crea = new ArrayList<>();
        this.danger = new ArrayList<>();
        this.consommable = new ArrayList<>();
        Random alea = new Random();
        int nbrPaysan =  alea.nextInt(nbrPerso+1);
        nbrPerso = nbrPerso - nbrPaysan;
        int nbrArcher = alea.nextInt(nbrPerso + 1);
        nbrPerso = nbrPerso - nbrArcher;
        int nbrGuerrier = alea.nextInt(nbrPerso + 1);
        nbrPerso = nbrPerso - nbrGuerrier;
        int nbrMage = nbrPerso;
        int nbrLapin = alea.nextInt(nbrMonstre+1);
        nbrMonstre = nbrMonstre - nbrLapin;
        int nbrLoup = nbrMonstre;
        int nbrSoin = alea.nextInt(nbrObjet+1);
        nbrObjet = nbrObjet - nbrSoin;
        int nbrEpee = nbrObjet;
        for (int i=0; i<=nbrGuerrier;i++) {
            this.crea.add(new Guerrier());
        }
        for (int i=0; i<=nbrArcher;i++) {
            this.crea.add(new Archer());
        }
        for (int i=0; i<=nbrPaysan;i++) {
            this.crea.add(new Paysan());
        }
        for (int i=0; i<=nbrMage;i++) {
            this.crea.add(new Mage());
        }
        for (int i=0; i<=nbrLapin;i++) {
            this.crea.add(new Lapin());
        }
        for (int i=0; i<=nbrLoup;i++) {
            this.crea.add(new Loup());
        }
        for (int i=0; i<=nbrSoin;i++) {
            this.consommable.add(new Nourriture());
        }
        for (int i=0; i<=nbrEpee;i++) {
            this.danger.add(new NuageToxique());
        }
        player = new Joueur();
    }

    /**
     *Getter de l'attribut crea
     * @return La liste des créatures
     */
    public ArrayList<Creature> getCrea() {
        return crea;
    }

    /**
     *Getter de l'attribut consommable
     * @return La liste des consommables
     */
    public ArrayList<Nourriture> getConsommable() {
        return consommable;
    }

    /**
     *Getter de l'attribut danger
     * @return La liste des dangers
     */
    public ArrayList<NuageToxique> getDanger() {
        return danger;
    }

    /**
     *Getter de l'attribut player
     * @return Le joueur présent dans le monde
     */
    public Joueur getPlayer() {
        return player;
    }
    
    
       
    
    /**
     *Méthode permettant d'initialiser les positions des objets dans le monde
     */
    public void creerMondeAlea() {
    	Random alea = new Random();
    	int nombre_crea = crea.size();
    	ArrayList<Point2D> vu = new ArrayList<>();
    	for (int i = 0 ; i < nombre_crea ; i++){
        	crea.get(i).getPos().setPosition(alea.nextInt(2*TailleMonde+1)-TailleMonde,alea.nextInt(2*TailleMonde+1)-TailleMonde);
        	while (vu.contains(crea.get(i).getPos())) {
                    crea.get(i).getPos().setPosition(alea.nextInt(2*TailleMonde+1)-TailleMonde,alea.nextInt(2*TailleMonde+1)-TailleMonde);
        	}
        	vu.add(crea.get(i).getPos());
    	}
        int nombre_potion = consommable.size();
    	ArrayList<Point2D> vu_consommable = new ArrayList<>();
    	for (int i = 0 ; i < nombre_potion ; i++){
        	consommable.get(i).getPos().setPosition(alea.nextInt(2*TailleMonde+1)-TailleMonde,alea.nextInt(2*TailleMonde+1)-TailleMonde);
        	while (vu_consommable.contains(consommable.get(i).getPos())) {
                    consommable.get(i).getPos().setPosition(alea.nextInt(2*TailleMonde+1)-TailleMonde,alea.nextInt(2*TailleMonde+1)-TailleMonde);
        	}
        	vu_consommable.add(consommable.get(i).getPos());
    	}
        int nombre_sword = danger.size();
    	ArrayList<Point2D> vu_danger = new ArrayList<>();
    	for (int i = 0 ; i < nombre_sword ; i++){
        	danger.get(i).getPos().setPosition(alea.nextInt(2*TailleMonde+1)-TailleMonde,alea.nextInt(2*TailleMonde+1)-TailleMonde);
        	while (vu_danger.contains(danger.get(i).getPos())) {
                    danger.get(i).getPos().setPosition(alea.nextInt(2*TailleMonde+1)-TailleMonde,alea.nextInt(2*TailleMonde+1)-TailleMonde);
        	}
        	vu_danger.add(danger.get(i).getPos());
    	}
	}
    
    /**
     *Affiche les entités présentes dans le monde
     */
    public void affiche() {
        int nombre_crea = crea.size();
        for (int i = 0 ; i < nombre_crea ; i++){
            crea.get(i).affiche();
        }
        int nombre_potion = consommable.size();
        for (int i = 0 ; i < nombre_potion ; i++){
            consommable.get(i).affiche();
        }
        int nombre_sword = consommable.size();
        for (int i = 0 ; i < nombre_sword ; i++){
            consommable.get(i).affiche();
        }
        
    }
    
    /**
     *Applique l'effet des potions de soin
     */
    /*
    public void soigne(){
    	ArrayList<Integer> a_supprimer = new ArrayList<>();
    	for (int i = 0 ; i < potion.size() ; i++){
        	for (int j = 0; j < crea.size(); j++){
                    if (crea.get(j).getPos().equals(potion.get(i).getPos())){
                	crea.get(j).setPtVie(crea.get(j).getPtVie() + potion.get(i).getValeur_soin());
                	a_supprimer.add(i);
                    }
        	}
    	}
        
            for (int k = 0; k < potion.size(); k++) {
                if (a_supprimer.contains(k)){
                    potion.set(k, null);
                }
            }
    } */
    
    /**
     * Vérifie que deux créatures ne soient pas sur la même case et les déplace dans le cas échéant
     */
    /*public void check_position() {
        ArrayList<Point2D> listPositionVu = new ArrayList<>();
        for (Creature c : crea) {
            while (listPositionVu.contains(c.getPos()) || Math.abs(c.getPos().getX()) > 50 || Math.abs(c.getPos().getY()) > 50) {
                c.deplace();
            }
            listPositionVu.add(c.getPos());
        }
        
    }*/
    
    /**
     *Méthode effectuant les tours de jeu
     * @param tourJeu Le nombre de tours de jeu max
     */
    public void tour_de_jeu(int tourJeu) {
        int PtVieBonus = 0;
        for (int i=0; i <= tourJeu; i++) {
            this.afficheInterface();
            Scanner sc = new Scanner(System.in);
            System.out.println("Tour " + i);
            PtVieBonus = 0;
            for (String s: player.getPersonnage().getEffets().keySet()) {
                PtVieBonus += player.getPersonnage().getEffets().get(s).getModifPtVie();
                int t=player.getPersonnage().getEffets().get(s).getNbToursEffet();
                if ((t-1) > 0 ) {
                    player.getPersonnage().getEffets().get(s).setNbToursEffet(t-1);
                } else {
                    player.getPersonnage().getEffets().remove(s);
                }
            }
            PtVieBonus += player.getPersonnage().getPtVie();
            player.getPersonnage().setPtVie(PtVieBonus);
            System.out.println("Le joueur est en position : X = " + player.getPersonnage().getPos().getX() + " et Y = " + player.getPersonnage().getPos().getY());
            //affiche_world_visible(player.getPersonnage().getPos(),Math.max(player.getPersonnage().getDistAttMax(),10));
            System.out.println("Choisissez une action");
            System.out.println("deplace | " + "combattre | " + "utiliser" );
            String action = sc.nextLine();
            switch (action) {
                case "deplace":
                    boolean testPos = false;
                    while (!testPos) {
                        testPos = deplaceJoueur();
                    }
                    break;
                case "utiliser":
                    player.affiche_inventaire();
                    System.out.println("Choisissez un objet à utiliser :");
                    System.out.println("Choisissez 0 pour ne rien faire");
                    int objetConsomme = sc.nextInt();
                    if (objetConsomme > 0 || objetConsomme <= player.getInventaire().size()) {
                    player.getInventaire().get(objetConsomme - 1).est_utilise(player.getPersonnage());
                    player.getInventaire().remove(objetConsomme - 1);
                    }
                    break;
                    
                case "combattre":
                    int k = 0;
                    int j = -1;
                    ArrayList<Integer> indiceCrea = new ArrayList<>();
                    System.out.println("Les créatures à portée d'attaque sont :");
                    for (Creature c: crea) {
                        j += 1;
                        if (c.getPos().distance(player.getPersonnage().getPos()) <= player.getPersonnage().getDistAttMax()) {
                            k += 1;
                            System.out.println("Créature " + k);
                            c.affichePertinent();
                            indiceCrea.add(j);
                        }
                        
                    }
                    if (k != 0) {
                        System.out.println("Choisissez une créature à combattre");
                        int creaCombattu = sc.nextInt();
                        Personnage perso = player.getPersonnage();
                        ((Combattant)perso).combattre(crea.get(indiceCrea.get(k-1)));
                        break;
                    } else {
                        System.out.println("Pas de créature à portée");
                        break;
                    }
                    
                default :
                    player.getPersonnage().affiche();
                    break;
        }
            for (Creature c : crea) {
                //c.deplace(this);
            }
        
    }
    }
    
   
    
    /**
     *Méthode de déplacement du joueur
     * @return
     */
    public boolean deplaceJoueur() {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int y = 0;
        Point2D testPosition = new Point2D(player.getPersonnage().getPos());
        Point2D pos = player.getPersonnage().getPos();
        System.out.println("Rentrez une direction");
        System.out.println("N | NE | E | SE | S | SO | O | NO");
        String direction = sc.nextLine();
        switch (direction) {
            case "N":
                y = -1;
                break;
            case "NE":
                x = 1;
                y = -1;
                break;
            case "E":
                x = 1;
                break;
            case "SE":
                x = 1;
                y = 1;
                break;
            case "S":
                y = 1;
                break;
            case "SO":
                x = -1;
                y = 1;
                break;
            case "O":
                x = -1;
                break;
            case "NO":
                x = -1;
                y = -1;
                break;
            default:
                return false;
             
        }
        testPosition.Translate(x, y);
        for (Creature c: crea) {
            if (c.getPos().equals(testPosition) || Math.abs(testPosition.getX()) > TailleMonde || Math.abs(testPosition.getY()) > TailleMonde) {
                return false;
            } else {
                player.getPersonnage().getPos().Translate(x, y);
                player.ramasser(this);
                return true;
                }
            
        } 
        return false;
    }
    
    /**
     *Méthode d'affichage du monde jusqu'à une certaine distance
     * @param pos Position du point d'origine de l'affichage
     * @param distAffichage Distance de l'affichage
     */
    public void affiche_world_visible(Point2D pos, int distAffichage) {
        for (Creature c : crea) {
            if (c.getPos().distance(pos) <= distAffichage) {
                c.affichePertinent();
            }
        }
        for (NuageToxique c : danger) {
            if (c.getPos().distance(pos) <= distAffichage) {
                c.affiche();
            }
        }
        for (Nourriture c : consommable) {
            if (c.getPos().distance(pos) <= distAffichage) {
                c.affiche();
            }
        }
        
    }
    
    /**
     * Affiche les différents types de protagonistes présents dans le monde
     */
    public void affiche_protagoniste() {
        int p = 0;
        int g = 0;
        int l = 0;
        int a = 0;
        int lo = 0;
        for (Creature c : crea) {
            if ("org.centrale.objet.WoE.Paysan".equals(c.getClass().getName())) {
                p += 1;
            }
            if ("org.centrale.objet.WoE.Loup".equals(c.getClass().getName())) {
                lo += 1;
            }
            if ("org.centrale.objet.WoE.Guerrier".equals(c.getClass().getName())) {
                g += 1;
            }
            if ("org.centrale.objet.WoE.Archer".equals(c.getClass().getName())) {
                a += 1;
            }
            if ("org.centrale.objet.WoE.Lapin".equals(c.getClass().getName())) {
                l += 1;
            }
        }
            System.out.println("Le monde contient " + g + " Guerriers");
            System.out.println("Le monde contient " + a + " Archers");
            System.out.println("Le monde contient " + p + " Paysans");
            System.out.println("Le monde contient " + l + " Lapins");
            System.out.println("Le monde contient " + lo + " Loups");
        
    }
    
    /**
     * Affiche les différents types de protagonistes présents dans le monde
     */

    public static final int CASE_VIDE = 0;

    /**
     *
     */
    public static final int GUERRIER = 1;

    /**
     *
     */
    public static final int ARCHER = 2;

    /**
     *
     */
    public static final int PAYSAN = 3;

    /**
     *
     */
    public static final int LAPIN = 4;

    /**
     *
     */
    public static final int LOUP = 5;

    /**
     *
     */
    public static final int OBJET = 6;
    
    /**
     *Méthode testant si une créature est présent sur une position
     * @param testPosition Position à tester
     * @return
     */
    public int testPos(Point2D testPosition){
        for (Creature c: crea) {
            if (c.getPos().equals(testPosition) ) {
                //System.out.println(crea);
                switch(c.getClass().getSimpleName()){
                    case "Guerrier":
                        return GUERRIER;
                    case "Archer":
                        return ARCHER;
                    case "Paysan":
                        return PAYSAN;
                    case "Lapin":
                        return LAPIN;
                    case "Loup":
                        return LOUP;
                }
            }
            
        } 
        return CASE_VIDE;
    };
    
    /**
     *Méthode testant si un objet est présent sur une position
     * @param testPosition Position à tester
     * @return
     */
    public int testPosObjet(Point2D testPosition){
        for (Nourriture c: consommable) {
            if (c.getPos().equals(testPosition) ) {
                //System.out.println(crea);
                return OBJET;
            }
            
        } 
        return CASE_VIDE;
    };
    
    /**
     *Méthode d'affichage de l'interface
     */
    public void afficheInterface(){
        int x = TailleMonde;
        int y = TailleMonde;
        Point2D joueur = player.getPersonnage().getPos();
        String delimit = "";
        String nv_ligne ="";
        for(int i=-x;i<x; i++){
                delimit+="____";
            };
        for(int j = -y; j<y;j++){
            nv_ligne = "";
            System.out.println(delimit);
            for(int i=-x;i<x; i++){
                nv_ligne += " ";
                Point2D pos = new Point2D(i,j);
                if(pos.equals(joueur)){
                    nv_ligne +="|J|";
                }
                else {
                switch(testPos(pos)){
                    case CASE_VIDE:
                        switch(testPosObjet(pos)){
                        case OBJET:
                            nv_ligne+="|O|";
                            break;
                        default:
                            nv_ligne+="| |";
                            break;
                        }
                        break;
                    case GUERRIER:
                        nv_ligne+="|G|";
                        break;    
                    case ARCHER:
                        nv_ligne+="|A|";
                        break;
                    case PAYSAN:
                        nv_ligne+="|P|";
                        break;
                    case LAPIN:
                        nv_ligne+="|R|";
                        break;
                    case LOUP:
                        nv_ligne+="|L|";
                };
                };
                
            };
            System.out.println(nv_ligne);
                
            };
            System.out.println("Légende : ");
            System.out.println("J: Vous     G: Guerrier");
            System.out.println("A: Archer   P: Paysan");
            System.out.println("R: Lapin    L: Loup");
            System.out.println("O: Objet");
        };
}

