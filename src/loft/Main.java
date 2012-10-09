package loft;

import loft.exception.LoftException;

import java.util.ArrayList;

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

        Plateau plateau = Plateau.getInstance();
        plateau.initPlateau();
        
        // TODO lancer partie
        
        for (int i = 0; i < 100; i++) {
            plateau.jouerTour();
        }
        
        // TODO lancer partie
        
    }

}
