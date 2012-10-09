package loft;

import loft.exception.LoftException;

/**
 * Classe Vorace.
 * Un Vorace est un Neuneu qui n'ecoute que son ventre.
 * 
 * @author Marlene, Gaetan
 */
public class Vorace extends Neuneu {
    
    
    /**
     * Constructeur
     */
    public Vorace(Case _case) {
        this.plateau = Plateau.getInstance();
        this._case = _case;
        this.valeurEnerg = 42;
        this.energie = 100;
        this.fatigueDeplacement = 2;
        this.fatigueCoit = 25;
        this.valeurGustative = 55;
    }
    
    
//    @Override
//    public void deplacer() throws LoftException {
//        // TODO code
//    }
}
