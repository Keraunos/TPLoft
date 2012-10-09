package loft;

//import loft.exception.LoftException;

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
        
//        ArrayList<Case> tab1 = new ArrayList<Case>(),
//                tab2 = new ArrayList<Case>(),
//                tab3;
//        Case    c1 = new Case(0, 0),
//                c2 = new Case(0, 1),
//                c3 = new Case(1, 0);
//        tab1.add(c1);
//        tab1.add(c2);
//        tab1.add(c3);
//        tab2.add(c1);
//        System.out.println(tab1.toString());
//        
//        tab3 = new ArrayList<Case>(tab1);
//        tab3.removeAll(tab2);
//        tab1.get(0).ajouterNeuneu(Neuneu.genererNeuneu(c1));
//        tab3.get(0).ajouterNourriture();
//        System.out.println(tab1.get(1).afficherCase(0));
//        System.out.println(tab3.toString());
//        System.out.println(tab3.get(0).afficherCase(0));
//        System.out.println(tab1.toString());
    }

}
