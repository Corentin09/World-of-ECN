/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WorldofECN.DatabaseTools;

/**
 * Sous-classe de personnage gérant les guerriers
 * @author coco
 */
public class Guerrier extends Personnage implements Combattant {

    /**
     * Constructeur de Guerrier
     * @param nom Nom du guerrier
     * @param ptVie Points de vie du guerrier
     * @param degAtt Dégâts infligés par une attaque
     * @param ptPar Dégâts parés par une attaque
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param distAttMax Distance d'attaque maximale
     * @param pos Position du guerrier
     * @param effets Liste des effets affectant le guerrier
     */
    public Guerrier(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos, Map<String,Nourriture> effets) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos, effets);
    }

    /**
     * Constructeur de recopie 
     * @param perso Guerrier à recopier
     */
    public Guerrier(Guerrier perso) {
        
        super((Personnage)perso);
    }

    /**
     * Constructeur par défaut de guerrier
     */
    public Guerrier() {
        super();
    }
    
    /**
     * Méthode de combat
     * @param c Créature à combattre
     */
    public void combattre(Creature c) {
        Random alea = new Random();
        int ouch;
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
        
        System.out.println(this.getNom() + " utilise Coup d'Épée");
        double d = this.getPos().distance(c.getPos());
        if (d !=1) {
            System.out.println("La créature est trop loin pour être attaquée"); 
        } else {
            int patt = alea.nextInt(101);
            if (patt > (this.getPageAtt() + pageAttBonus)) {
                System.out.println("L'attaque échoue");
            } else {
                System.out.println("L'attaque réussi");
                int ppar = alea.nextInt(101);
                if (ppar > (c.getPagePar() + pageParBonus)) {
                    System.out.println("La parade échoue");
                    ouch = this.getDegAtt()+ptAttBonus;
                    
                } else {
                    System.out.println("La parade réussi");
                    ouch =  Math.max(0,ptAttBonus+this.getDegAtt()-c.getPtPar() - ptParBonus);
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
            
            
            }   
    }
    
     /**
     * Méthode de sauvegarde d'un guerrier
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant de la sauvegarde dans laquelle stocker le guerrier
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
            String query2 = "INSERT INTO personnage(id_element,nom, dist_att_max, nom_classe) VALUES(?,?,?,?)" ;
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, ID_element);
            stmt2.setString(2, this.getNom());
            stmt2.setInt(3, this.getDistAttMax());
            stmt2.setString(4, "Guerrier");
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
