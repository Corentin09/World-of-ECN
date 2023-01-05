/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.centrale.objet.WoE.World.TailleMonde;
import org.centrale.objet.WorldofECN.DatabaseTools;

/**
 * Sous-classe d'objet gérant les dangers, comme les nuages toxiques
 * @author leo20
 */
public class NuageToxique extends Objet implements Deplacables, Combattant {
        private int toxicite;

    /**
     *Constructeur par défaut de la classe NuageToxique
     */
    public NuageToxique() {
        super();
        toxicite=1;
    }

    /**
     *Constructeur de la classe NuageToxique
     * @param nom Nom du danger
     * @param description Description du danger
     * @param pos Position du danger
     * @param toxicite Toxicité du danger
     */
    public NuageToxique(String nom, String description, Point2D pos, int toxicite) {
        super(nom, description, pos);
        this.toxicite = toxicite;
    }

    /**
     *Constructeur de recopie de la classe NuageToxique
     * @param nt Nuage Toxique à recopier
     */
    public NuageToxique(NuageToxique nt) {
        super(nt);
        this.toxicite = nt.toxicite;
    }

    /**
     *Getter de l'attribut toxicite
     * @return La toxicité du NuageToxique
     */
    public int getToxicite() {
        return toxicite;
    }

    /**
     *Setter de l'attribut toxicite
     * @param toxicite La nouvelle toxicité du Nuage toxique
     */
    public void setToxicite(int toxicite) {
        this.toxicite = toxicite;
    }
    
    /**
     *Méthode de combat du nuage toxique
     * @param c Créature affectée par le nuage toxique
     */
    @Override
    public void combattre(Creature c) {
        double d = this.getPos().distance(c.getPos());
        if (d>0){
            System.out.println("La créature n''est pas dans le nuage toxique et ne subit par conséquent aucun dégât");
        }
        else {
            c.setPtVie(c.getPtVie()-toxicite);
            System.out.println(c.getClass()+" respire le gaz et subit "+toxicite+" dégâts. Il lui reste "+c.getPtVie()+" points de vie");
        }
    }

    /**
     *Méthode de déplacement du nuage toxique
     * @param monde Monde dans lequel évolue le nuage toxique
     * @return true si le nuage peut se déplacer | false sinon
     */
    @Override
    public boolean deplace(World monde) {
        Random Alea = new Random();
        Point2D testPosition = new Point2D(this.getPos());
        int r1 = Alea.nextInt(3)-1;
        int r2 = Alea.nextInt(3)-1;
        while ((r1 == 0) && (r2==0)) {
        r1 = Alea.nextInt(3)-1;
        r2 = Alea.nextInt(3)-1;
            }
        testPosition.Translate(r1, r2);
        for (Creature c: monde.getCrea()) {
            if (c.getPos().equals(testPosition) || Math.abs(testPosition.getX()) > TailleMonde || Math.abs(testPosition.getY()) > TailleMonde) {
                return false;
            } else {
                this.getPos().Translate(r1, r2);
                return true;
                }
         
        }
        return false;
    }
    
    
    /**
     * Méthode de sauvegarde du nuage toxique
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant de la sauvegarde dans laquelle stocker le nuage toxique
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
            String query = "INSERT INTO Objet(id_element,nom, description) VALUES(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ID_element);
            stmt.setString(2,this.getNom());
            stmt.setString(3,this.getDescription());
            stmt.executeUpdate();
            String query2 = "INSERT INTO danger(id_element,degats) VALUES(?,?)" ;
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, ID_element);
            stmt2.setInt(2, this.getToxicite());
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
