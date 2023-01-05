/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;

/**
 * Classe implémentant l'ensemble des éléments présent dans le jeu
 * @author coco
 */
public abstract class ElementDeJeu {
    
    private Point2D pos;

    /**
     * Constructeur de la classe ElementDeJeu
     * @param pos Position de l'élément de jeu
     */
    public ElementDeJeu(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    
    /**
     * Constructeur par défaut de la classe ElementDeJeu
     */
    public ElementDeJeu() {
        this.pos = new Point2D(0,0);
    }
    
    /**
     * Constructeur de recopie de la classe ElementDeJeu
     * @param m Élement de jeu à recopier
     */
    public ElementDeJeu(ElementDeJeu m) {
        this.pos = new Point2D(m.pos);
    }

    /**
     * Getter de l'attribut Pos
     * @return La position de l'élément de jeu
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     * Setter de l'attribut position
     * @param pos La nouvelle position de l'élément de jeu
     */
    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    
    
    
    /**
     * Méthode de sauvegarde d'un élément de jeu
     * @param connection Connection à la base de donnée
     * @param ID_sauvegarde Identifiant de la sauvegarde dans laquelle stocker l'élément de jeu
     */
    public abstract void saveToDatabase(Connection connection,int ID_sauvegarde);
    

    
}
