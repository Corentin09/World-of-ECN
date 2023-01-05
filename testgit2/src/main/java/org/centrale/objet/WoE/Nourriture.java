/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WorldofECN.DatabaseTools;

/**
 * Sous-classe d'objet gérant les objets consommables
 * @author leo20
 */
public class Nourriture extends Objet implements Utilisable{
    private int modifPtVie;
    private int modifDegAtt;
    private int modifPageAtt;
    private int modifPagePar;
    private int modifPtPar;
    private int nbToursEffet;

    /**
     * Getter de l'attribut modifPtVie
     * @return Les points de vie gagné ou perdu grâce au consommable
     */
    public int getModifPtVie() {
        return modifPtVie;
    }

    /**
     * Getter de l'attribut modifDegAtt
     * @return Le bonus (ou malus) de dégats du consommable
     */
    public int getModifDegAtt() {
        return modifDegAtt;
    }

    /**
     * Setter de l'attribut modifDegAtt
     * @param modifDegAtt Le nouveau bonus (ou malus) de dégats du consommable
     */
    public void setModifDegAtt(int modifDegAtt) {
        this.modifDegAtt = modifDegAtt;
    }

    /**
     * Getter de l'attribut modifPageAtt
     * @return Le bonus (ou malus) au pourcentage d'attaque du consommable
     */
    public int getModifPageAtt() {
        return modifPageAtt;
    }

    /**
     * Setter de l'attribut modifPageAtt
     * @param modifPageAtt Le nouveau bonus (ou malus) au pourcentage d'attaque du consommable
     */
    public void setModifPageAtt(int modifPageAtt) {
        this.modifPageAtt = modifPageAtt;
    }

    /**
     * Getter de l'attribut modifPagePar
     * @return Le bonus (ou malus) au pourcentage de parade du consommable
     */
    public int getModifPagePar() {
        return modifPagePar;
    }

    /**
     * Setter de l'attribut modifPagePar
     * @param modifPagePar  Le nouveau bonus (ou malus) au pourcentage de parade du consommable
     */
    public void setModifPagePar(int modifPagePar) {
        this.modifPagePar = modifPagePar;
    }

    /**
     * Getter de l'attribut modifPtPar
     * @return Le bonus (ou malus) au points de parade du consommable
     */
    public int getModifPtPar() {
        return modifPtPar;
    }

    /**
     * Stter de l'attribut modifPtPar
     * @param modifPtPar Le nouveau bonus (ou malus) au points de parade du consommable
     */
    public void setModifPtPar(int modifPtPar) {
        this.modifPtPar = modifPtPar;
    }

    /**
     * Getter de l'attribut nbTourEffet
     * @return Le nombre de tours restant pour l'effet
     */
    public int getNbToursEffet() {
        return nbToursEffet;
    }

    /**
     * Setter de l'attribut nbToursEffet
     * @param nbToursEffet Le nouveau nombre de tours restants pour l'effet
     */
    public void setNbToursEffet(int nbToursEffet) {
        this.nbToursEffet = nbToursEffet;
    }

    /**
     * Setter de l'attribut modifPtVie
     * @param modifPtVie Le nouveau nombre de points de vie gagné (ou perdu) grâce à l'effet
     */
    public void setModifPtVie(int modifPtVie) {
        this.modifPtVie = modifPtVie;
    }
  
 
    public static final String POULET = "Poulet";

  
    public static final int POULET_MODIF_PTVIE = 0;

    public static final int POULET_MODIF_DEGATT = 2;

    public static final int POULET_MODIF_PAGEATT = 0;

  
    public static final int POULET_MODIF_PAGEPAR = 0;

  
    public static final int POULET_MODIF_PTPAR = 0;

 
    public static final int POULET_NBTOURS = 3;
    
  
    public static final String CHAMPIGNON_VENENEUX = "Champignon vénéneux";

    
    public static final int CHAMPIGNON_VENENEUX_PTVIE = 0;

   
    public static final int CHAMPIGNON_VENENEUX_DEGATT = 0;

  
    public static final int CHAMPIGNON_VENENEUX_PAGEATT = 0;

 
    public static final int CHAMPIGNON_VENENEUX_PAGEPAR = 0;

    public static final int CHAMPIGNON_VENENEUX_PTPAR = -2;

  
    public static final int CHAMPIGNON_VENENEUX_NBTOURS = 5;

    /**
     * Constructeur de Nourriture
     * @param modifPtVie Les points de vie gagné ou perdu grâce au consommabl
     * @param modifDegAtt Le bonus (ou malus) de dégats du consommable
     * @param modifPageAtt Le bonus (ou malus) au pourcentage d'attaque du consommable
     * @param modifPagePar Le bonus (ou malus) au pourcentage de parade du consommable
     * @param modifPtPar Le bonus (ou malus) au points de parade du consommable
     * @param nbToursEffet Le nombre de tours restant pour l'effet du consommable
     * @param nom Nom du consommable
     * @param description Description du consommable
     * @param pos Position du consommable sur la carte
     */
    public Nourriture(int modifPtVie, int modifDegAtt, int modifPageAtt, int modifPagePar, int modifPtPar, int nbToursEffet, String nom, String description, Point2D pos) {
        super(nom, description, pos);
        this.modifPtVie = modifPtVie;
        this.modifDegAtt = modifDegAtt;
        this.modifPageAtt = modifPageAtt;
        this.modifPagePar = modifPagePar;
        this.modifPtPar = modifPtPar;
        this.nbToursEffet = nbToursEffet;
    }
    
    /**
     * Constructeur par défaut de Nourriture
     */
    public Nourriture() {
        super("poulet","La vie, bah c'est pas facile",new Point2D(0,0));
        this.modifPtVie = POULET_MODIF_PTVIE;
        this.modifDegAtt = POULET_MODIF_DEGATT;
        this.modifPageAtt = POULET_MODIF_PAGEATT;
        this.modifPagePar = POULET_MODIF_PAGEPAR;
        this.modifPtPar = POULET_MODIF_PTPAR;
        this.nbToursEffet = POULET_NBTOURS;
    }

    /**
     * Constructeur d'un consommable "Poulet"
     * @param pos Position du poulet
     * @return Le consommable
     */
    public Nourriture poulet(Point2D pos) {
        return new Nourriture(POULET_MODIF_PTVIE, POULET_MODIF_DEGATT, POULET_MODIF_PAGEATT, POULET_MODIF_PAGEPAR, POULET_MODIF_PTPAR, POULET_NBTOURS, "Poulet", "Miam", pos);
    };
    
    /**
     * Constructeur d'un consommable "Champignon Veneneux"
     * @param pos Position du champignon
     * @return Le consommable
     */
    public Nourriture champignonVeneneux(Point2D pos) {
        return new Nourriture(CHAMPIGNON_VENENEUX_PTVIE, CHAMPIGNON_VENENEUX_DEGATT, CHAMPIGNON_VENENEUX_PAGEATT, CHAMPIGNON_VENENEUX_PAGEPAR, CHAMPIGNON_VENENEUX_PTPAR, CHAMPIGNON_VENENEUX_NBTOURS,"Champignon vénéneux","Manger ceci était une mauvaise idée",pos);
    };
    
    /**
     *Méthode d'utilisation d'un consommable
     * @param p Personnage utilisant le consommable
     */
    @Override
    public void est_utilise(Personnage p){
        p.getEffets().put(this.getNom(),this);
    }
    
    
    /**
     *Méthode de sauvegarde d'un consommable
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant devla sauvegarde dans laquelle stocker le consommable
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
            String query2 = "INSERT INTO consommable(id_element,modif_pt_vie,modif_deg_att,modif_page_att,modif_pt_par,modif_page_par,nb_tour_restant) VALUES(?,?,?,?,?,?,?)" ;
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, ID_element);
            stmt2.setInt(2, this.getModifPtVie());
            stmt2.setInt(2, this.getModifDegAtt());
            stmt2.setInt(2, this.getModifPageAtt());
            stmt2.setInt(2, this.getModifPtPar());
            stmt2.setInt(2, this.getModifPagePar());
            stmt2.setInt(2, this.getNbToursEffet());
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


