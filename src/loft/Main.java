package loft;

import java.awt.*;
import loft.exception.LoftException;

import java.util.ArrayList;
import java.util.Vector;

//import java.util.ArrayList;

/**
 * Classe Main pour le projet de TP "Loft". Lance le jeu.
 * 
 * @author Marlene, Gaetan
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        // lancer la partie
        Plateau plateau = Plateau.getInstance();
        plateau.initPlateau();
        for (int i = 0; i < Config.NB_TURNS; i++) {
            plateau.jouerTour();
        }
        
//        Vector<ObjetGraphique> mesObjetsG = new Vector<ObjetGraphique>();
//        Plateau plateau = Plateau.getInstance();
//        plateau.initPlateau();
        
//        try {
//
//            Case c00 = plateau.getCase(0, 0);
//            
//            Lapin l1 = new Lapin(c00);
//            
//            mesObjetsG.add(l1);
//            
//            // Instructions d'affichage des figures
//            Affichage monDessin;
//            monDessin = new Affichage();
//            monDessin.ajoutObjet(l1);
//            monDessin.setVisible(true);
//            
//
//        } catch (LoftException e) {
//          e.printStackTrace();
//        }
    }
    
}
