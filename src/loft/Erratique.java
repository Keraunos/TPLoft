package loft;

/**
 * Classe Erratique.
 * Un Erratique est un Neuneu un peu basique, qui erre sans but precis.
 * 
 * @author Marlene, Gaetan
 */
public class Erratique extends Neuneu {
    
    
    /**
     * Constructeur
     */
    public Erratique(Case _case) {
        
        this.plateau = Plateau.getInstance();
        this._case = _case;
        
        this.valeurEnerg = 35;
        this.energie = 100;
        this.fatigueDeplacement = 3;
        this.fatigueCoit = 22;
        this.valeurGustative = 55;
        
    }
}
