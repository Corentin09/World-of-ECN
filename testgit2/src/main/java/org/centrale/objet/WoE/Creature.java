/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static org.centrale.objet.WoE.World.TailleMonde;

/**
 * Sous-classe d'ElementDeJeu gérant les créatures
 * @author coco
 */
public abstract class Creature extends ElementDeJeu implements Deplacables {
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Map<String, Nourriture> effets;

    /**
     * Constructeur de la classe créature
     * @param ptVie Point de vie de la créature
     * @param degAtt Dégâts d'attaque de la créature
     * @param ptPar Points de parade de la créature
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param pos Position de la créature
     * @param effets Liste des effets subis par la créature
     */
    public Creature(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos, Map<String, Nourriture> effets) {
        super(pos);
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.effets = new HashMap<>();
    }

    /**
     * Constructeur par défaut de la classe créature
     */
    public Creature() {
        super();
        this.ptVie = 100;
        this.degAtt = 1;
        this.ptPar = 10;
        this.pageAtt = 10;
        this.pagePar = 10;
        this.effets = new HashMap<>();
    }

    /**
     * Constructeur de recopie de la classe créature
     * @param m Créature à recopier
     */
    public Creature(Creature m) {
        super((ElementDeJeu)m);
        this.ptVie = m.ptVie;
        this.degAtt = m.degAtt;
        this.pagePar = m.pagePar;
        this.effets = m.effets;
    }

    /**
     * Getter de l'attribut ptVie
     * @return Le nombre de point de vie de la créature
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     * Getter de l'attribut degAtt
     * @return le nombre de dégâts infligés par une attque
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     * Getter de l'attribut ptPar
     * @return le nombre de dégâts évités par la parade
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     * Getter de l'attribut pageAtt
     * @return Le pourcentage de chance de toucher avec une attaque
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     * Getter de l'attribut pagePar
     * @return Le pourcentage de chance de parer une attaque
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     * Getter de l'attribut effets
     * @return La liste des effets affectant la créature
     */
    public Map<String, Nourriture> getEffets() {
        return effets;
    }

    /**
     * Setter de l'attribut effets
     * @param effets Liste des effets affectant la créature
     */
    public void setEffets(Map<String, Nourriture> effets) {
        this.effets = effets;
    }

    /**
     * Setter de l'attribut ptVie
     * @param ptVie Point de vie de la créature
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    /**
     * Setter de l'attribut degAtt
     * @param degAtt Dégâts infligés par une attaque
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    /**
     * Setter de l'attribut ptPar
     * @param ptPar Dégâts évités par une parade
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    /**
     * Setter de l'attribut pageAtt
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     * Setter de l'attribut pagePar
     * @param pagePar Pourcentage de chance de parer une attaque
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }


   
    /**
     * Permet de déplacer une créature
     * @return true si la créature peut se déplacer | false sinon
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
     * Permet d'afficher les attributs correspondant à la créature
     */
    public void affiche() {
        Class nclasse = this.getClass();
        System.out.println("La classe est : "+ nclasse.getName());
        System.out.println("PtVie "+ ptVie);
        System.out.println("degAtt "+ degAtt);
        System.out.println("ptPar "+ ptPar);
        System.out.println("pageAtt "+ pageAtt);
        System.out.println("pagePar "+ pagePar);
        System.out.println(Arrays.toString(this.getPos().GetPosition()));
    
    
    }

    /**
     * Permet d'afficher la position de la créature
     */
    public void affichePos() {
        Class nclasse = this.getClass();
        System.out.println("La classe est : "+ nclasse.getName());
        System.out.println(Arrays.toString(this.getPos().GetPosition()));
    
    }
    
    /**
     * Pemret d'afficher la classe, la position et les points de vie d'une créature
     */
    public void affichePertinent() {
        Class nclasse = this.getClass();
        System.out.println("La classe est : "+ nclasse.getName());
        System.out.println(Arrays.toString(this.getPos().GetPosition()));
        System.out.println("La créature possède " + ptVie );
    }
    
}

