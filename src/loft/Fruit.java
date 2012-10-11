package loft;

import java.awt.Color;
import java.awt.Graphics;


/**
 * Classe Fruit. Un Fruit est un type de Nourriture, assez prise des Neuneus.
 * Les Fruits se trouvent sur les Cases du Plateau.
 * 
 * @author Marlene, Gaetan
 */
public class Fruit extends Nourriture {
    
    protected static int count = 0;
    
    
    public Fruit(Case _case) {
        this._case = _case;
        this.id = ++Fruit.count;
        this.valeurGustative = 70;
        this.valeurEnerg = 25;
    }
    
    
    @Override
    public void colorerObjet(Graphics g){
        g.setColor(Color.ORANGE);
    }
    
}
