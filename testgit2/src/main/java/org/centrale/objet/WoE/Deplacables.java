/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *Interface contenant une méthode de déplacement pour les entités pouvant se déplacer
 * @author coco
 */
public interface Deplacables {
    
    /**
     * Méthode de déplacement
     * @param monde Le monde dans lequel l'entité se déplace
     * @return true si la créature peut se déplacer | false sinon
     */
    public abstract boolean deplace(World monde);
    
}
