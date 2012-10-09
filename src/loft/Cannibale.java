package loft;

import loft.exception.LoftException;

/**
 * Classe Caniibale.
 * Un Cannibale est un Vorace qui, en plus d'etre un gros mangeur, a l'habitude
 * de consommer ses semblables (ie. les autres Neuneus).
 * 
 * @author Marlene, Gaetan
 */
public class Cannibale extends Vorace {
    
    
    /**
     * Constructeur
     */
    public Cannibale(Case _case) {
        super(_case);
        this.valeurEnerg = 45;
        this.energie = 100;
        this.fatigueCoit = 24;
        this.valeurGustative = 50;
    }
    
    
//    @Override
//    public void deplacer() throws LoftException {
//        // TODO code
//    }
}
