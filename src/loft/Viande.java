package loft;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe Viande. Une Viande est un type de Nourriture.
 * Les Viandes se trouvent sur les Cases du Plateau.
 * 
 * @author Marlene, Gaetan
 */
public class Viande extends Nourriture {
    
    protected static int count = 0;
    
    
    public Viande(Case _case) {
        this._case = _case;
        this.id = ++Viande.count;
        this.valeurGustative = 50;
        this.valeurEnerg = 50;
    }
    
    
    @Override
    public void colorerObjet(Graphics g){
        g.setColor(Color.RED);
    }
    
}
