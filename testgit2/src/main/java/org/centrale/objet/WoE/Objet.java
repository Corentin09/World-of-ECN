/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Arrays;

/**
 *Classe gérant les objets
 * @author leo20
 */
public abstract class Objet extends ElementDeJeu {
	String nom;
	String description;

    /**
     *Constructeur d'objet
     * @return
     */


    /**
     * Getter de l'attribut nom
     * @return Le nom de l'objet
     */
    public String getNom() {
    	return nom;
	}

    /**
     * Setter de l'attribut nom
     * @param nom Le nouveau nom de l'objet
     */
    public void setNom(String nom) {
    	this.nom = nom;
	}

    /**
     *Getter de l'attribut description
     * @return La descirption de l'objet
     */
    public String getDescription() {
    	return description;
	}

    /**
     *Setter de l'attribut description
     * @param description La nouvelle description de l'objet
     */
    public void setDescription(String description) {
    	this.description = description;
	}

    
    

    /**
     *Constructeur par défaut d'objet
     */
    public Objet() {
        super();
   	 this.nom = "Objet Random";
         this.description = "Ceci est un objet random, et par conséquent, possède une utilité nulle";
	}

    /**
     *Constructeur de la classe Objet
     * @param nom Nom de l'objet
     * @param description Description de l'objet
     * @param pos Position de l'objet
     */
    public Objet(String nom, String description, Point2D pos) {
        super(pos);
    	this.nom = nom;
    	this.description = description;
	}
    
    /**
     *Constructeur de recopie
     * @param o Objet à recopier
     */
    public Objet(Objet o) {
        super((ElementDeJeu)o);
    	this.nom = o.nom;
    	this.description = o.description;
	}
    
    
    /**
     * Permet d'afficher les attributs correspondant à la créature
     */
    public void affiche() {
        Class nclasse = this.getClass();
        System.out.println("La classe est : "+ nclasse.getName());
        System.out.println("Nom "+ nom);
        System.out.println("Description "+ description);
        System.out.println("Position " + Arrays.toString(this.getPos().GetPosition()));
    }
}
