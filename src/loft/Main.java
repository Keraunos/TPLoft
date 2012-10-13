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
        if (!Config.WAIT_FOR_USER) {
            for (int i = 0; i < Config.NB_TURNS || i==1; i++) {
                plateau.jouerTour();
            }
        }
        else {
            plateau.init();
        }
    }
    
}
