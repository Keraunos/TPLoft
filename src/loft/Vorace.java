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
        this.fatigueDeplacement = 15;
        this.fatigueCoit = 25;
        this.fatigueTemps = 1;
        this.valeurGustative = Config.TASTE_VORACE;
        
    }
    
    
    /**
     * Deplace le Vorace vers la Nourriture la plus proche .
     * On considere que les deplacements en diagonale ne sont pas plus couteux en
     * energie que les deplacements en ligne droite, donc toutes les Cases
     * adjacentes a une Case donnee sont a egale distance de cette derniere.
     * 
     * @throws LoftException 
     */
    @Override
    public void deplacer() throws LoftException {
        
        fatiguer();
        
        // chercher les Nourritures les plus proches
        Nourriture nouProche = (Nourriture) chercher(plateau.getDenrees());
        allerVers(nouProche);
        
        manger();
    }
    
    
    @Override
    public void colorer(Graphics g){
        g.setColor(Color.BLUE);
    }
    
}
