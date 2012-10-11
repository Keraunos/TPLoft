package loft;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe Erratique.
 * Un Erratique est un Neuneu un peu basique, qui erre sans but precis.
 * 
 * @author Marlene, Gaetan
 */
public class Erratique extends Neuneu {
    
    protected static int count = 0;
    
    
    /**
     * Constructeur
     */
    public Erratique(Case _case) {
        
        this.id = ++Erratique.count;
        
        this.plateau = Plateau.getInstance();
        this._case = _case;
        
        this.valeurEnerg = 35;
        this.energie = Config.MAX_ENERGY;
        this.fatigueDeplacement = 8;
        this.fatigueCoit = 22;
        this.valeurGustative = 55;
        
    }
    
    
    @Override
    public void colorerObjet(Graphics g){
        g.setColor(Color.DARK_GRAY);
    }
    
}
