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
    
    
    /**
     * Constructeur
     * @param _case La Case sur laquelle cette Viande sera creee
     */
    public Viande(Case _case) {
        this._case = _case;
        this.id = ++Viande.count;
        this.valeurGustative = Config.TASTE_VIANDE;
        this.valeurEnerg = 50;
    }
    
    
    @Override
    public void colorer(Graphics g){
        g.setColor(Color.RED);
    }
    
}
