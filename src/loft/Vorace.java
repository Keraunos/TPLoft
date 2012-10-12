package loft;

import java.awt.Color;
import java.awt.Graphics;
import loft.exception.LoftException;

/**
 * Classe Vorace.
 * Un Vorace est un Neuneu qui n'ecoute que son ventre.
 * 
 * @author Marlene, Gaetan
 */
public class Vorace extends Neuneu {
    
    protected static int count = 0;
    
    
    /**
     * Constructeur
     * @param _case La Case ou ce Vorace sera inclu 
     */
    public Vorace(Case _case) {
        
        this.id = ++Vorace.count;
        
        this.plateau = Plateau.getInstance();
        this._case = _case;
        
        this.valeurEnerg = 42;
        this.energie = Config.MAX_ENERGY;
        this.fatigueDeplacement = 10;
        this.fatigueCoit = 25;
        this.valeurGustative = 55;
        
    }
    
    
//    @Override
//    public void deplacer() throws LoftException {
//        // TODO code
//    }
    
    
    @Override
    public void colorer(Graphics g){
        g.setColor(Color.BLUE);
    }
    
}
