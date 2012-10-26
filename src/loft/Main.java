package loft;


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
        
        // lancer en mode execution automatique
        if (!Config.WAIT_FOR_USER) {
            plateau.init();
            for (int i = 0; i < Config.NB_TURNS || i==1; i++) {
                plateau.jouerTour();
            }
        }
        // lancer en mode interactif
        else {
            plateau.init();
        }
    }
    
}
