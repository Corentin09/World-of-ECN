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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WorldofECN.DatabaseTools;

/**
 *Sous-classe de Personnage gérant les paysans
 * @author coco
 */
public class Paysan extends Personnage {

    /**
     *Constructeur de Paysan
     * @param nom Nom du paysan
     * @param ptVie Points de vie du paysan
     * @param degAtt Dégâts infligés par une attaque
     * @param ptPar Dégâts parés par une attaque
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param distAttMax Distance d'attaque maximale
     * @param pos Position du paysan
     * @param effets Liste des effets affectant le paysan
     */
    public Paysan(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos, Map<String, Nourriture> effets) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos, effets);
    }

    /**
     *Constructeur de recopie de Paysan
     * @param perso Paysan à recopier
     */
    public Paysan(Paysan perso) {
        
        super((Personnage)perso);
    }

    /**
     *Constructeur par défaut de paysan
     */
    public Paysan() {
        super();
    }
    
    
     /**
     *Méthode de sauvega
     * Méthode de sauvegarde du paysan
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant de la sauvegarde dans laquelle stocker le paysan
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
            stmt2.setString(4, "Archer");
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
