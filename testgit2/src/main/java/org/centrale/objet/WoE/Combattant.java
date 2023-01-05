/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * Interface contenant une methode de combat pour les différentes créatures combattantes
 * @author coco
 */
public interface Combattant {
    
    /**
     * Méthode de combat entre la créature et une créature cible
     * @param c Créature à combattre
     */
    public abstract void combattre(Creature c);
    
}
