package loft;

import loft.exception.LoftException;

/**
 * Classe Lapin.
 * Un Lapin est un Neuneu particulierement porte sur la reproduction.
 * 
 * @author Marlene, Gaetan
 */
public class Lapin extends Neuneu {
    
    
    /**
     * Constructeur
     */
    public Lapin(Case _case) {
        this._case = _case;
        this.valeurEnerg = 40;
        this.energie = 100;
        this.fatigueDeplacement = 10;
        this.fatigueCoit = 20;
        this.valeurGustative = 60;
    }
    
    
//    @Override
//    public void deplacer() throws LoftException {
//        // TODO code
//    }
    
}
