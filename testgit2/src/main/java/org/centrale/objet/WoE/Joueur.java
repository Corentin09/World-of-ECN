/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WorldofECN.DatabaseTools;

/**
 * Classe permettant d'implémenter et de gérer le joueur humain 
 * @author coco
 */
public class Joueur  {
    private String nom;
    private String login;
    private String password;
    private Personnage personnage;
    private ArrayList<Nourriture> inventaire;

    /**
     * Constructeur de Joueur
     * @param nom Nom du joueur
     * @param login Login du joueur
     * @param password Mot de passe du joueur
     * @param personnage Personnage contrôlé par le joueur
     * @param inventaire Inventaire du personnage controlé par le joueur
     */
    public Joueur(String nom, String login, String password, Personnage personnage, ArrayList<Nourriture> inventaire) {
        this.nom = nom;
        this.login = login;
        this.password = password;
        this.personnage = personnage;
        this.inventaire = inventaire;
    }

    /**
     * Constructeur de Joueur avec en paramètre seulement le nom et le personnage à contrôler
     * @param nom Nom du joueur
     * @param personnage Personnage contrôlé par le joueur
     */
    public Joueur(String nom, Personnage personnage) {
        this.nom = nom;
        this.personnage = personnage;
        this.login = "ProXGamerdu31";
        this.password = "12345";
        this.inventaire = new ArrayList<>();
    }

    /**
     * Constructeur de Joueur avec en paramètre seulement le nom
     * @param nom Nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.personnage = new Guerrier();
        this.login = "ProXGamerdu31";
        this.password = "12345";
        this.inventaire = new ArrayList<>();
    }

    /**
     * Constructeur par défaut de Joueur
     */
    public Joueur() {
        this.nom = "Bob";
        this.personnage = new Guerrier();
        this.login = "ProXGamerdu31";
        this.password = "12345";
        this.inventaire = new ArrayList<>();
    }
    
    /**
     *  Getter de l'attribut Nom
     * @return Le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter de l'attribut login
     * @return Le login du joueur
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter de l'attribut password
     * @return Le mot de passe du joueur
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter de l'attribut personnage 
     * @return Le personnage du joueur
     */
    public Personnage getPersonnage() {
        return personnage;
    }

    /**
     * Getter de l'attribut inventaire
     * @return L'inventaire du joueur
     */
    public ArrayList<Nourriture> getInventaire() {
        return inventaire;
    }
    
    

    /**
     * Setter de l'attribut nom
     * @param nom Le nouveau nom du joueur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter de l'attribut login
     * @param login Le nouveau login du joueur
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Setter de l'attribut password
     * @param password Le nouveau mot de passe du joueur
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter de l'attribut personnage 
     * @param personnage Le nouveau personnage du joueur
     */
    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    /**
     * Setter de l'attribut inventaire
     * @param inventaire Le nouveau inventaire du joueur
     */
    public void setInventaire(ArrayList<Nourriture> inventaire) {
        this.inventaire = inventaire;
    }
    
    
    
    /**
     * Méthode de choix du personnage 
     */
    public void choixPerso() {
        Scanner sc;
        sc = new Scanner(System.in);
        Random alea = new Random();
        System.out.println("Choisissez votre classe de personnage");
        System.out.println("Guerrier | Archer | Mage");
        String cPerso = sc.nextLine();
        System.out.println("Choisissez votre nom de personnage");
        String cNom = sc.nextLine();
        Map<String,Nourriture> effets = new HashMap<>();
        switch(cPerso) {
            case "Guerrier":
                int pv = alea.nextInt(100,151);
                int degAtt = alea.nextInt(20,31);
                int ptPar = alea.nextInt(15,26);
                int pageAtt = alea.nextInt(50,81);
                int pagePar = alea.nextInt(30,51);
                int distAttMax = 1;
                Point2D pos = new Point2D(0,0);
                this.personnage = new Guerrier(cNom,pv,degAtt,ptPar,pageAtt,pagePar,distAttMax,pos, effets);
                break;
            case "Archer":
                int pvArcher = alea.nextInt(5,101);
                int degAttArcher = alea.nextInt(5,16);
                int ptParArcher = alea.nextInt(10,20);
                int pageAttArcher = alea.nextInt(60,91);
                int pageParArcher = alea.nextInt(40,61);
                int distAttMaxArcher = alea.nextInt(3,8);
                Point2D posArcher = new Point2D(0,0);
                int fleche = alea.nextInt(10,21);
                this.personnage = new Archer(fleche, cNom,pvArcher,degAttArcher,ptParArcher,pageAttArcher,pageParArcher,distAttMaxArcher,posArcher, effets);
                break;
            case "Mage":
                int pvMage = alea.nextInt(40,76);
                int degAttMage = alea.nextInt(20,51);
                int ptParMage = alea.nextInt(5,11);
                int pageAttMage = alea.nextInt(50,76);
                int pageParMage = alea.nextInt(10,21);
                int distAttMaxMage = alea.nextInt(1,6);
                Point2D posMage = new Point2D(0,0);
                int mana = alea.nextInt(75,126);
                this.personnage = new Mage(mana, cNom,pvMage,degAttMage,ptParMage,pageAttMage,pageParMage,distAttMaxMage,posMage, effets);
                break;
            default :
                int pvg = alea.nextInt(100,151);
                int degAttg = alea.nextInt(20,31);
                int ptParg = alea.nextInt(15,26);
                int pageAttg = alea.nextInt(50,81);
                int pageParg = alea.nextInt(30,51);
                int distAttMaxg = 1;
                Point2D posg = new Point2D(0,0);
                this.personnage = new Guerrier(cNom,pvg,degAttg,ptParg,pageAttg,pageParg,distAttMaxg,posg, effets);
                break;
  
        }
        
    }
    
    /**
     * Méthode de ramassage d'un objet par le joueur
     * @param monde Le monde dans lequel évolue le joueur
     */
    public void ramasser(World monde) {
        for (Nourriture n: monde.getConsommable()) {
            if (this.getPersonnage().getPos().equals(n.getPos())) {
                n.setPos(null);
                this.getInventaire().add(n);
                monde.getConsommable().remove(n);
            }
        }
        
    }
    
    /**
     * Méthode d'affichage de l'inventaire du joueur
     */
    public void affiche_inventaire() {
        System.out.println("L'inventaire du joueur contient :");
        int k = 0;
        for (Nourriture o: this.inventaire) {
            k+=1;
            System.out.println("Objet" + k + ": " +o.nom);
        }
    }
    
    /**
     * Méthode de sauvegarde de l'inventaire
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant de la sauvegarde dans laquelle stocker l'inventaire
     * @param ID_joueur Identifiant du joueur dans la bse de donnée
     */
    public void sauvegarde_inventaire(Connection connection, int ID_sauvegarde, int ID_joueur) {
            try {
                this.getPersonnage().saveToDatabase(connection, ID_sauvegarde);
                String q152 = "SELECT MAX(id_element) as m_ID FROM element_de_jeu";
                PreparedStatement stmt152 = connection.prepareStatement(q152);
                ResultSet rs152 = stmt152.executeQuery();
                rs152.next();
                int ID_element_joueur = rs152.getInt("m_ID");
                for (Nourriture n: this.inventaire) {
                    String query0 = "INSERT INTO element_de_jeu() VALUES()";
                    PreparedStatement stmt0 = connection.prepareStatement(query0);
                    stmt0.executeUpdate();
                    String q15 = "SELECT MAX(id_element) as m_ID FROM element_de_jeu";
                    PreparedStatement stmt15 = connection.prepareStatement(q15);
                    ResultSet rs = stmt15.executeQuery();
                    rs.next();
                    int ID_element = rs.getInt("m_ID");
                    String query = "INSERT INTO Objet(id_element,nom, description) VALUES(?,?,?)";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setInt(1, ID_element);
                    stmt.setString(2,n.getNom());
                    stmt.setString(3,n.getDescription());
                    stmt.executeUpdate();
                    String query2 = "INSERT INTO consommable(id_element,modif_pt_vie,modif_deg_att,modif_page_att,modif_pt_par,modif_page_par,nb_tour_restant) VALUES(?,?,?,?,?,?,?)" ;
                    PreparedStatement stmt2 = connection.prepareStatement(query2);
                    stmt2.setInt(1, ID_element);
                    stmt2.setInt(2, n.getModifPtVie());
                    stmt2.setInt(2, n.getModifDegAtt());
                    stmt2.setInt(2, n.getModifPageAtt());
                    stmt2.setInt(2, n.getModifPtPar());
                    stmt2.setInt(2, n.getModifPagePar());
                    stmt2.setInt(2, n.getNbToursEffet());
                    stmt2.executeUpdate();
                    String query3 = "INSERT INTO est_sauvegarde(id_sauvegarde,id_element) VALUES(?,?)";
                    PreparedStatement stmt3 = connection.prepareStatement(query3);
                    stmt3.setInt(1, ID_sauvegarde);
                    stmt3.setInt(2, ID_element);
                    stmt3.executeUpdate();
                    String query20 = "SELECT id_joue_dans FROM joue_dans WHERE id_element = ? and id_joueur = ?";
                    PreparedStatement stmt20 = connection.prepareStatement(query20);
                    stmt20.setInt(1, ID_element);
                    stmt20.setInt(2, ID_joueur);
                    ResultSet rs20 = stmt20.executeQuery();
                    rs20.next();
                    int ID_joue = rs20.getInt("id_joue_dans");
                    String query4 = "INSERT INTO inventaire(id_element,id_joue_dans) VALUES(?,?)";
                    PreparedStatement stmt4 = connection.prepareStatement(query4);
                    stmt4.setInt(1, ID_element_joueur);
                    stmt4.setInt(2, ID_joue);
                    stmt4.executeUpdate();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        
    }
    
    
    
    
    
}
