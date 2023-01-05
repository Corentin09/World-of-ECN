/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package org.centrale.objet.WorldofECN;

import java.sql.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WoE.Archer;
import org.centrale.objet.WoE.Guerrier;
import org.centrale.objet.WoE.Lapin;
import org.centrale.objet.WoE.Loup;
import org.centrale.objet.WoE.Paysan;
import org.centrale.objet.WoE.World;

import org.centrale.objet.WorldofECN.*;

/**
 *Classe gérant la communication à la base de donnée
 * @author ECN
 */
public class DatabaseTools {

    private String login;
    private String password;
    private String url;
    private Connection connection;

    /**
     * Load infos
     */
    public DatabaseTools() {
        try {
            // Get Properties file
            ResourceBundle properties = ResourceBundle.getBundle(DatabaseTools.class.getPackage().getName() + ".database");

            // USE config parameters
            login = properties.getString("login");
            password = properties.getString("password");
            String server = properties.getString("server");
            String database = properties.getString("database");
            url = "jdbc:postgresql://" + server + "/" + database;

            // Mount driver
            Driver driver = DriverManager.getDriver(url);
            if (driver == null) {
                Class.forName("org.postgresql.Driver");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection = null;
    }

    /**
     * Get connection to the database
     */
    public void connect() {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Disconnect from database
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * get Player ID
     * @param nomJoueur Nom du jouueur
     * @param password Mot de passe du joueur
     * @return L'identifiant du joueur
     */
    public Integer getPlayerID(String nomJoueur, String password) {
        try {
            String query = "SELECT Player_ID FROM Joueur WHERE Nom_d_utilisateur = ? AND mot_de_passe = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,nomJoueur);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (rs.wasNull()) {
                return null;
            } else {
                return (rs.getInt("Player_ID"));
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * save world to database
     * @param idJoueur Identifiant du joueur
     * @param nomPartie Nom de la partie
     * @param nomSauvegarde Nom de la sauvegarde
     * @param monde Le mond eà sauvegarder
     */
    /*public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        try {
            String q1 = "SELECT ID_partie FROM Partie WHERE Nom_Partie = ?";
            PreparedStatement stmt1 = connection.prepareStatement(q1);
            stmt1.setString(1, nomPartie);
            ResultSet rs1 = stmt1.executeQuery();
            rs1.next();
            if (rs1.wasNull()) {
                return;
            } 
            int IDpartie = rs1.getInt("ID_partie");
            String q2 = "INSERT INTO Sauvegarde(Nom_sauvegarde, ID_partie) VALUES(?,?)";
            PreparedStatement stmt2 = connection.prepareStatement(q2);
            stmt2.setString(1, nomSauvegarde);
            stmt2.setInt(2,IDpartie);
            stmt2.executeUpdate();
            String q15 = "SELECT MAX(ID_sauvegarde) as m_ID FROM Sauvegarde";
            PreparedStatement stmt15 = connection.prepareStatement(q15);
            ResultSet rs = stmt15.executeQuery();
            rs.next();
            int ID_sauvegarde = rs.getInt("m_ID");
            
            for (ElementDeJeu c : monde.getListElements()) {
                c.saveToDatabase(connection, ID_sauvegarde );

            }

            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }*/

    /**
     * get world from database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @param monde
     */
    /*public void readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        try {
            ArrayList<Integer> listIDCrea = new ArrayList<>();
            String query = "SELECT ID_sauvegarde FROM Sauvegarde JOIN Partie ON Sauvegarde.ID_partie = Partie.ID_partie WHERE (Nom_sauvegarde = ? AND Nom_partie = ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,nomSauvegarde);
            stmt.setString(2, nomPartie);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (rs.wasNull()) {
                return;
            } 
            int ID_Sauvegarde = rs.getInt("ID_sauvegarde");
            String query2 = "SELECT ID_créa FROM Sauvegarde_créa WHERE ID_sauvegarde = ?";
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, ID_Sauvegarde);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                listIDCrea.add(rs2.getInt("ID_créa"));

            }
            String nomType = "Archer";
            for (int i: listIDCrea) {
                query = "SELECT Nom_classe FROM Humanoide WHERE ID_créa = ?";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, i);
                rs = stmt.executeQuery();
                rs.next();
                if (rs.wasNull()) {
                    query2 = "SELECT Nom_type_monstre FROM Monstre WHERE ID_créa = ?";
                    stmt2 = connection.prepareStatement(query2);
                    stmt2.setInt(1, i);
                    rs2 = stmt2.executeQuery();
                    rs2.next();
                    nomType = rs2.getString("Nom_type_monstre");
                    
                } else {
                    nomType = rs.getString("Nom_classe");
                }
                switch (nomType) {
                    case "Guerrier":
                        Guerrier gue = new Guerrier(monde);
                        gue.getFromDatabase(connection, i);
                        monde.getListElements().add(gue);
                        System.out.println("test");
                        break;
                    case "Paysan":
                        Paysan pay = new Paysan(monde);
                        pay.getFromDatabase(connection, i);
                        monde.getListElements().add(pay);
                        break;
                    case "Loup":
                        Loup lou = new Loup(monde);
                        lou.getFromDatabase(connection, i);
                        monde.getListElements().add(lou);
                        break;
                    case "Lapin":
                        Lapin lap = new Lapin(monde);
                        lap.getFromDatabase(connection, i);
                        monde.getListElements().add(lap);
                        break;
                    default:
                        Archer arc = new Archer(monde);
                        arc.getFromDatabase(connection, i);
                        monde.getListElements().add(arc);
                        break;
                }
                
            }
            String query3 = "SELECT ID_est_dans_loot FROM Sauvegarde_loot WHERE ID_sauvegarde = ?";
            PreparedStatement stmt3 = connection.prepareStatement(query3);
            stmt3.setInt(1, ID_Sauvegarde);
            ResultSet rs3 = stmt3.executeQuery();
            while (rs3.next()) {
                int j = rs3.getInt("ID_est_dans_loot");
                PotionSoin pSoin = new PotionSoin(monde);
                pSoin.getFromDatabase(connection, j);
                monde.getListElements().add(pSoin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }*/
}
