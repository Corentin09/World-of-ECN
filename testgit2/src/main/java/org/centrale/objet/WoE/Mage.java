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
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WorldofECN.DatabaseTools;

/**
 * Sous-classe de créature gérant les mages
 * @author coco
 */
public class Mage extends Personnage implements Combattant {
    private int PtMana;

    /**
     * Constructeur de Mage
     * @param PtMana Point de Mana du mage
     * @param ptVie Points de vie du mage
     * @param degAtt Dégâts infligés par une attaque
     * @param ptPar Dégâts parés par une attaque
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param distAttMax Distance d'attaque maximale
     * @param pos Position du mage
     * @param effets Liste des effets affectant le mage
     */
    public Mage(int PtMana, String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos, Map<String,Nourriture> effets) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos, effets);
        this.PtMana = PtMana;
    }

    /**
     *Constructeur par défaut du mage
     */
    public Mage() {
        super();
        this.PtMana = 100;
    }

    /**
     *Constructeur de recopie du mage
     * @param perso Mage à recopier
     */
    public Mage(Mage perso) {
        super((Personnage)perso);
        this.PtMana = perso.PtMana;
    }

    /**
     * Getter de l'attribut PtMana
     * @return Les points de mana du mage
     */
    public int getPtMana() {
        return PtMana;
    }

    /**
     * Setter de l'attribut PtMana
     * @param PtMana Les nouveaux points de mana du mage
     */
    public void setPtMana(int PtMana) {
        this.PtMana = PtMana;
    }
    
    

   
    
    /**
     * Méthode de combat du mage
     * @param c Créature à combattre
     */
    @Override
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
            System.out.println(this.getNom() + " utilise Coup de baton");
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
                    ouch =  Math.max(0,this.getDegAtt()+ptAttBonus-c.getPtPar() - ptParBonus);
                }
            }
            } else {
               int patt = alea.nextInt(101);
               System.out.println(this.getNom() + " utilise Boule de Feu");
               this.setPtMana(this.getPtMana()-10);  
            if (patt > this.getPageAtt()) {
                System.out.println("L'attaque échoue");
            } else {
                System.out.println("L'attaque réussi");
                ouch = 2*(this.getDegAtt()+ptAttBonus);
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
     *Fonction d'affichage de la classe Mage
     */
    @Override
    public void affiche() {
        super.affiche();
        System.out.println("Le nombre de points de mana est " + PtMana);
    }
    
    
     /**
     * Méthode de sauvegarde du mage
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant de la sauvegarde dans laquelle stocker le mage
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
            String query2 = "INSERT INTO personnage(id_element,nom, dist_att_max,pt_mana, nom_classe) VALUES(?,?,?,?,?)" ;
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, ID_element);
            stmt2.setString(2, this.getNom());
            stmt2.setInt(3, this.getDistAttMax());
            stmt2.setInt(4,this.getPtMana());
            stmt2.setString(5, "Mage");
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

