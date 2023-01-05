/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WorldofECN.DatabaseTools;


/**
 * Sous-classe de personnage gérant les archers
 * @author coco
 */
public class Archer extends Personnage implements Combattant {
    private int nbFleche;

    /**
     * Constructeur de la classe Archer
     * @param nbFleche Nombre de flèche possédées par l'archer
     * @param nom Nom de l'archer
     * @param ptVie Points de vie de l'archer
     * @param degAtt Dégâts infligés par une attaque
     * @param ptPar Dégâts parés par une attaque
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param distAttMax Distance d'attaque maximale
     * @param pos Position de l'archer
     * @param effets Liste des effets subis par l'archer
     */
    public Archer(int nbFleche, String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos, Map<String, Nourriture> effets) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos, effets);
        this.nbFleche = nbFleche;
    }

    /**
     * Constructeur par défaut de l'archer
     */
    public Archer() {
        super();
        nbFleche = 10;
    }

    /**
     * Constructeur de recopie d'Archer
     * @param perso Archer à recopier
     */
    public Archer(Archer perso) {
        super(perso);
        nbFleche = perso.nbFleche;
    }

    /**
     * Getter de l'attribut flèche
     * @return Le nombre de flèche de l'archer
     */
    public int getNbFleche() {
        return nbFleche;
    }

    /**
     * Setter de l'attribut flèche
     * @param nbFleche Nombre de flèche de l'archer
     */
    public void setNbFleche(int nbFleche) {
        this.nbFleche = nbFleche;
    }
    
    /**
     * Méthode de combat de l'archer
     * @param c Créature à combattre
     */
    public void combattre(Creature c) {
        Random alea = new Random();
        int ouch = 0;
        int ptAttBonus = 0;
        int ptParBonus = 0;
        int pageAttBonus = 0;
        int pageParBonus = 0;
        for (String s: this.getEffets().keySet()) {
            ptAttBonus += this.getEffets().get(s).getModifDegAtt();
            pageAttBonus +=  this.getEffets().get(s).getModifPageAtt();     
        }
        for (String s: c.getEffets().keySet()) {
            ptParBonus += c.getEffets().get(s).getModifPtPar();
            pageParBonus +=  c.getEffets().get(s).getModifPagePar();     
        }
        double d = this.getPos().distance(c.getPos());
        if (d > this.getDistAttMax()) {
            System.out.println("La créature est trop loin pour être attaquée"); 
        } else {
            if (d == 1) {
            int patt = alea.nextInt(101);
            System.out.println(this.getNom() + " utilise Coup de dague");
            if (patt > (this.getPageAtt()+pageAttBonus)) {
                System.out.println("L'attaque échoue");
            } else {
                System.out.println("L'attaque réussi");
                int ppar = alea.nextInt(101);
                if (ppar > (c.getPagePar()+pageParBonus)) {
                    System.out.println("La parade échoue");
                    ouch = this.getDegAtt()+ptAttBonus;
                    
                } else {
                    System.out.println("La parade réussi");
                    ouch =  Math.max(0,this.getDegAtt()+ptAttBonus-c.getPtPar()-ptParBonus);
                }
            }
            } else {
               int patt = alea.nextInt(101);
               System.out.println(this.getNom() + " utilise Tir à l'arc");
               this.setNbFleche(this.getNbFleche()-1);  
            if (patt > (this.getPageAtt()+pageAttBonus)) {
                System.out.println("L'attaque échoue");
            } else {
                System.out.println("L'attaque réussi");
                ouch = this.getDegAtt()+ptAttBonus;
            }
            }
        }
                System.out.println("L'attaque inflige " + ouch + "points de dégâts");
            if (ouch >= 0.5*c.getPtVie()) {
                System.out.println("C'est super efficace");
            }
            if (ouch <= 0.1*c.getPtVie()) {
                System.out.println("Ce n'est pas très efficace");
            }
            c.setPtVie(c.getPtVie()-ouch);
            System.out.println("Il reste " + c.getPtVie() + "points de vie");
             
            }  
    
    /**
     *Fonction d'affichage de la classe Archer
     */
    @Override
    public void affiche() {
        super.affiche();
        System.out.println("Le nombre de flèche est " + nbFleche);
    }

    
     /**
     * Méthode de sauvegarde de l'archer dans une base de donnée
     * @param connection La connection à la base de donnée
     * @param ID_sauvegarde L'identifiant de la sauvegarde où stocker l'archer
     */
    @Override
    public void saveToDatabase(Connection connection,int ID_sauvegarde) {
        try {
            String query0 = "INSERT INTO element_de_jeu(position_y,position_x) VALUES(?,?)";
            PreparedStatement stmt0 = connection.prepareStatement(query0);
            Point2D coord = this.getPos();
            stmt0.setInt(1,coord.getY());
            stmt0.setInt(2, coord.getX());
            stmt0.executeUpdate();
            String q15 = "SELECT MAX(id_element) as m_ID FROM element_de_jeu";
            PreparedStatement stmt15 = connection.prepareStatement(q15);
            ResultSet rs = stmt15.executeQuery();
            rs.next();
            int ID_element = rs.getInt("m_ID");
            String query = "INSERT INTO creature(id_element,pt_vie,deg_att,pt_par,page_att,page_par) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ID_element);
            stmt.setInt(2,this.getPtVie());
            stmt.setInt(3,this.getDegAtt());
            stmt.setInt(4,this.getPtPar());
            stmt.setInt(5,this.getPageAtt());
            stmt.setInt(6,this.getPagePar());
            stmt.executeUpdate();
            String query2 = "INSERT INTO personnage(id_element,nom, dist_att_max,nb_fleche, nom_classe) VALUES(?,?,?,?,?)" ;
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, ID_element);
            stmt2.setString(2, this.getNom());
            stmt2.setInt(3, this.getDistAttMax());
            stmt2.setInt(4,this.getNbFleche());
            stmt2.setString(5, "Archer");
            stmt2.executeUpdate();
            String query3 = "INSERT INTO est_sauvegarde(id_sauvegarde,id_element) VALUES(?,?)";
            PreparedStatement stmt3 = connection.prepareStatement(query3);
            stmt3.setInt(1, ID_sauvegarde);
            stmt3.setInt(2, ID_element);
            stmt3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}    


