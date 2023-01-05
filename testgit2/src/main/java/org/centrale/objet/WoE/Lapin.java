/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WorldofECN.DatabaseTools;

/**
 * Sous-classe de Monstre gérant les lapins
 * @author coco
 */
public class Lapin extends Monstre{

    /**
     * Constructeur de Lapin
     * @param ptVie Point de vie du lapin
     * @param degAtt Dégâts d'attaque du lapin
     * @param ptPar Points de parade du lapin
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param pos Position du lapin
     * @param effets Liste des effets affectant le lapin
     */
    public Lapin(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos, Map<String, Nourriture> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos, effets);
    }

    /**
     *Constructeur par défaut de Lapin
     */
    public Lapin() {
        super();
    }

    /**
     *Constructeur de recopie de Lapin
     * @param m Lapin à recopier
     */
    public Lapin(Lapin m) {
        super(m);
    }
    
    
     /**
     * Méthode de sauvegarde du lapin
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant de la sauvegarde dans laquelle stocker le lapin
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
            String query2 = "INSERT INTO monstre(id_element,type_monstre) VALUES(?,?)" ;
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, ID_element);
            stmt2.setString(2, "Lapin");
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
