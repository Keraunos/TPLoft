package loft;

import java.awt.Color;
import java.awt.Graphics;
import loft.exception.LoftException;

/**
 * Classe Caniibale.
 * Un Cannibale est un Vorace qui, en plus d'etre un gros mangeur, a l'habitude
 * de consommer ses semblables (ie. les autres Neuneus).
 * 
 * @author Marlene, Gaetan
 */
public class Cannibale extends Vorace {
    
    protected static int count = 0;
    
    
    /**
     * Constructeur
     * @param _case La Case ou ce Cannibale sera inclu 
     */
    public Cannibale(Case _case) {
        
        super(_case);
        
        --Vorace.count;
        this.id = ++Cannibale.count;
        
        this.valeurEnerg = 45;
        this.fatigueCoit = 24;
        this.valeurGustative = 50;
        
    }
    
    
    @Override
    public void colorer(Graphics g){
        g.setColor(Color.GREEN);
    }
    
}
