/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 *Sous-classe de Créature gérant les monstres
 * @author coco
 */
public abstract class Monstre extends Creature {

    /**
     *Constructeur de monstre
      * @param ptVie Point de vie du monstre
     * @param degAtt Dégâts d'attaque du monstre
     * @param ptPar Points de parade du monstre
     * @param pageAtt Pourcentage de chance de toucher avec une attaque
     * @param pagePar Pourcentage de chance de parer une attaque
     * @param pos Position du monstre
     * @param effets Liste des effets affectant le monstre
     */
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos, Map<String, Nourriture> effets) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos, effets);
    }

    /**
     *Constructeur par défaut de Monstre
     */
    public Monstre() {
        super();
    }

    /**
     *Constructeur de recopie de monstre
     * @param m Monstre à recopier
     */
    public Monstre(Monstre m) {
        super((Creature)m);
    }

   
}

