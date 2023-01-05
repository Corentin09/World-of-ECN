/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 *Classe de test de WoE
 * @author leo20
 */
public class TestWoE {

    /**
     *Méthode main
     * @param args
     */
    public static void main(String[] args) {
            
            /*Test Génération monde avec constructeur */
            /*System.out.println("Génération du monde: Essai n°1");
            Archer Arch1 = new Archer();
            Guerrier Guer1 = new Guerrier();
            Lapin Lap1 = new Lapin();
            Loup Loup1 = new Loup();
            Paysan Pays1 = new Paysan();
            PotionSoin Ps1 = new PotionSoin();
            Epee Ep1 = new Epee();
            
            ArrayList<Creature> crea = new ArrayList<>();
            ArrayList<PotionSoin> potion = new ArrayList<>();
            ArrayList<Epee> sword = new ArrayList<>();
            
            crea.add(Arch1);
            crea.add(Guer1);
            crea.add(Lap1);
            crea.add(Pays1);
            crea.add(Loup1);
            
            potion.add(Ps1);
            sword.add(Ep1);
                   
            
            World Hello = new World(crea,potion,sword);
            Hello.creerMondeAlea();
            
            Hello.affiche();
                      
            
            System.out.println("Génération du monde: Essai n°2");
            World Hello2 = new World();
            Hello2.creerMondeAlea();
            
            Hello2.affiche();*/
	
        
            /*Création et placements des entités pour le test de déplacement*/
            /*System.out.println("Test de déplacement");
            Point2D pa = new Point2D(0,0);
            Point2D pb = new Point2D(10,10);
            Point2D pc = new Point2D(-10,-10);
            
            Lapin bugs = new Lapin(100,40,30,80,60,pa);
            Paysan peons = new Paysan("Roger",100,20,5,60,60,10,pb);
            Archer robin = new Archer(10,"Steve",100,30,10,60,30,10,pc);*/
            
            
            /*Test de déplacement*/
            /*bugs.affichePos();
            System.out.println("Déplacement de bugs");
            bugs.deplace();
            bugs.affichePos();
            bugs.deplace();
            bugs.affichePos();
            bugs.deplace();
            bugs.affichePos();
            
            peons.affichePos();
            System.out.println("Déplacement de peons");
            peons.deplace();
            peons.affichePos();
            peons.deplace();
            peons.affichePos();
            peons.deplace();
            peons.affichePos();
            
            robin.affichePos();
            System.out.println("Déplacement de robin");
            robin.deplace();
            robin.affichePos();
            robin.deplace();
            robin.affichePos();
            robin.deplace();
            robin.affichePos();*/
            
            
            /*Test de copie*/
            /*Point2D r1 = new Point2D(0,0);
            Archer robin = new Archer(10,"robin",100,30,10,60,30,10,r1);
            Archer GuillaumeT = new Archer(robin);
            
            System.out.println("La position de robin est "+ Arrays.toString(robin.getPos().GetPosition()));
            System.out.println("La position de GuillaumeT est "+ Arrays.toString(GuillaumeT.getPos().GetPosition()));
            robin.deplace();
            System.out.println("La position de robin est "+ Arrays.toString(robin.getPos().GetPosition()));
            System.out.println("La position de GuillaumeT est "+ Arrays.toString(GuillaumeT.getPos().GetPosition()));*/

            /*Création et placements des entités pour le test de combat*/
            
            /*System.out.println("Test du combat");
            Point2D p1 = new Point2D(0,0);
            Point2D p2 = new Point2D(1,0);
            Point2D p3 = new Point2D(1,1);
            Guerrier Bob = new Guerrier("Kevin",100,40,30,80,60,10,p1);
            Loup random_mob = new Loup(100,20,5,60,60,p2);
            Archer Steve = new Archer(10,"Steve",100,30,10,60,30,10,p3);*/


            /*Test de combat */
            /*Bob.affiche();
            random_mob.affiche();
            Steve.affiche();
            Bob.combattre(random_mob);
            Bob.combattre(random_mob);
            Bob.combattre(Steve);
            random_mob.combattre(Bob);
            Steve.combattre(Bob);
            Steve.combattre(Bob);
            Bob.affiche();
            random_mob.affiche();
            Steve.affiche();*/
            
            /*Test de Soin*/
            /*Point2D pa1 = new Point2D(0,0);
            Point2D pa2 = new Point2D(1,0);
            Point2D pa3 = new Point2D(-1,0);
            Point2D pa4 = new Point2D(0,1);
            Point2D pa5 = new Point2D(0,-1);
            
            Lapin Sujet_de_test = new Lapin(100,40,30,80,60,pa1);
            PotionSoin P1 = new PotionSoin(10,"Potion 1",0,0,"C'est la potion 1",pa2);
            PotionSoin P2 = new PotionSoin(20,"Potion 2",0,0,"C'est la potion 2",pa3);
            PotionSoin P3 = new PotionSoin(30,"Potion 3",0,0,"C'est la potion 3",pa4);
            PotionSoin P4 = new PotionSoin(40,"Potion 4",0,0,"C'est la potion 4",pa5);
            
            ArrayList<Creature> crea = new ArrayList<>();
            ArrayList<PotionSoin> potion = new ArrayList<>();
            ArrayList<Epee> sword = new ArrayList<>();
            
            crea.add(Sujet_de_test);
            potion.add(P1);
            potion.add(P2);
            potion.add(P3);
            potion.add(P4);
            
            
            World Test_Soin = new World(crea,potion,sword);
            Test_Soin.crea.get(0).affiche();
            Test_Soin.crea.get(0).deplace();
            System.out.println("Avant que le lapin boive la potion il a " + Test_Soin.crea.get(0).getPtVie());
            Test_Soin.soigne();
            System.out.println("Après avoir bu la potion, le lapin a " + Test_Soin.crea.get(0).getPtVie());
            if (Test_Soin.potion.get(0) != null){
                 Test_Soin.potion.get(0).affiche();
            }
            else {
                 System.out.println("La potion 1 a été consommée");
            }
            if (Test_Soin.potion.get(1) != null){
                 Test_Soin.potion.get(1).affiche();
            }
            else {
                 System.out.println("La potion 2 a été consommée");
            }
            if (Test_Soin.potion.get(2) != null){
                 Test_Soin.potion.get(2).affiche();
            }
            else {
                 System.out.println("La potion 3 a été consommée");
            }
            if (Test_Soin.potion.get(3) != null){
                 Test_Soin.potion.get(3).affiche();
            }
            else {
                 System.out.println("La potion 4 a été consommée");
            }*/
            /*
            Création Perso
            */
            
            /*
            ArrayList<Creature> LPerso = new ArrayList<>();
            ArrayList<PotionSoin> LPS = new ArrayList<>();
            ArrayList<Epee> LE = new ArrayList<>();
            Random alea = new Random();
            int RandG = alea.nextInt(50);
            int RandL = alea.nextInt(50);
            int RandA = alea.nextInt(50);
            int RandLo = alea.nextInt(50);
            int RandP = alea.nextInt(50);
            for (int i=0;i<RandG;i++) {
                LPerso.add(new Guerrier());
            }
            for (int i=0;i<RandA;i++) {
                LPerso.add(new Archer());
            }
            for (int i=0;i<RandL;i++) {
                LPerso.add(new Lapin());
            }
            for (int i=0;i<RandP;i++) {
                LPerso.add(new Paysan());
            }
            for (int i=0;i<RandLo;i++) {
                LPerso.add(new Loup());
            }
            
            World Test_LPerso = new World(LPerso,LPS,LE);
            int Psize = Test_LPerso.getCrea().size();
            int Total_PV=0;
            for (Creature c : Test_LPerso.getCrea()) {
                Total_PV += c.getPtVie();
            }
            Test_LPerso.affiche_protagoniste();
            System.out.println("Le nombre total de PV est " + Total_PV);
            
            */
            
            /*Test Conteneur */
             /*ArrayList<Creature> listCrea = new ArrayList<>();
             int tailleListCrea = 100;
             for (int i = 0; i < tailleListCrea; i++) {
                 listCrea.add(new Guerrier());
             }
             
            long debutN = System.nanoTime();
            int PVTest1=0;
            for (Creature c : listCrea) {
                PVTest1 += c.getPtVie();
            }
            long finN = System.nanoTime();
            System.out.println("Temps ecoulé en ns pour calculer les PV totaux avec un tableau avec itérateur : " + (finN-debutN));
            
            long debutN2 = System.nanoTime();
            int PVTest2=0;
            for (int i = 0; i < tailleListCrea; i++) {
                PVTest2 += listCrea.get(i).getPtVie();
            }
            long finN2 = System.nanoTime();
            System.out.println("Temps ecoulé en ns pour calculer les PV totaux avec un tableau avec parcours basé sur la taille : " + (finN2-debutN2));
            
            LinkedList<Creature> listLinkCrea = new LinkedList<>();
             for (int i = 0; i <= tailleListCrea; i++) {
                 listLinkCrea.add(new Guerrier());
             }
             
            long debutN3 = System.nanoTime();
            int PVTest3=0;
            for (Creature c : listLinkCrea) {
                PVTest3 += c.getPtVie();
            }
            long finN3 = System.nanoTime();
            System.out.println("Temps ecoulé en ns pour calculer les PV totaux avec une liste chainée avec itérateur : " + (finN3-debutN3));
            
            long debutN4 = System.nanoTime();
            int PVTest4=0;
            for (int i = 0; i < tailleListCrea; i++) {
                PVTest4 += listLinkCrea.get(i).getPtVie();
            }
            long finN4 = System.nanoTime();
            System.out.println("Temps ecoulé en ns pour calculer les PV totaux avec une liste chainée avec parcours basé sur la taille : " + (finN4-debutN4));*/
             
             World monde = new World(10,10,10);
             monde.getPlayer().choixPerso();
             monde.creerMondeAlea();
             monde.tour_de_jeu(10);


           
            

	}
    
}
