/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *Classe gérant les ensembles de deux entiers représentant les corrdonées d'entité dans le jeu World of ECN
 * @author coco
 */
public class Point2D {
    
    private int x;
    
    private int y;
    
    /**
     *Constructeur par défaut
     */
    public Point2D() {
    
        x = 10;
        y = 10;
    }

    /**
     *Constructeur de Point2D
     * @param a Coordonnée selon x
     * @param b Coordonnée selon y
     */
    public Point2D(int a,int b) {
    
        x = a;
        y = b;
    }

    /**
     *Constructeur de recopie
     * @param p Point à recopier
     */
    public Point2D(Point2D p) {
    
        x = p.x;
        y = p.y;
    }

    /**
     *Getter de l'attribut x
     * @return La coordonnée x
     */
    public int getX() {
        return x;
    }

    /**
     *Getter de l'attribut y
     * @return La coordonnée y
     */
    public int getY() {
        return y;
    }

    /**
     *Setter de l'attribut x
     * @param x La nouvelle coordonnée x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *Setter de l'attribut y
     * @param y La nouvelle coordonnée y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *Méthode permettant de redéfinir la position d'un point
     * @param x Coordonnée selon x
     * @param y Coordonnée selon y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        
    }
    
    /**
     *Méthode permettant de calculer la distance entre deux points
     * @param p1 Point à avec lequel mesurer la distance
     * @return Distance 
     */
    public double distance(Point2D p1) {
        double d;
        d = Math.sqrt((p1.x-this.x)*(p1.x-this.x)+(p1.y-this.y)*(p1.y-this.y));
      return d;  
        
    }
    
    /**
     *Méthode permettant d'obtenir les coordonnées d'un point
     * @return Un tableau de coordonnées
     */
    public int[] GetPosition() {
        int tab[] = new int[2];
        tab[0] = x;
        tab[1] = y;
        
        return tab;
    }
    
    /**
     *Méthode permettant de translater un point 
     * @param dx Translation à effectuer selon l'axe x
     * @param dy translation à effectuer selon l'axe y
     */
    public void Translate(int dx, int dy) {
        
        
          this.setX(x + dx);
          this.setY(y + dy);
        
}

 
        
        
    
    /**
     *Méthode permettant de comparer si deux points ont les mêmes coordonnées
     * @param p Point à comparer
     * @return Vrai (true) si les points ont les mêmes coordonées ou faux (false) si les coordonnées sont différentes
     */
    public boolean equals(Point2D p) {
        return !(this.x != p.x || this.y != p.y);

    }
    
}
    
