package loft;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe Alcool. Un Alcool est un type de Nourriture particulier, tres prise des
 * Neuneus. Les Alcools se trouvent sur les Cases du Plateau.
 * 
 * @author Marlene, Gaetan
 */
public class Alcool extends Nourriture {
    
    protected static int count = 0;
    
    
    public Alcool(Case _case) {
        this._case = _case;
        this.id = ++Alcool.count;
        this.valeurGustative = 90;
        this.valeurEnerg = 15;
    }
    
    
    @Override
    public void colorerObjet(Graphics g){
        g.setColor(Color.MAGENTA);
    }
    
}
