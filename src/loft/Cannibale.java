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
        this.fatigueDeplacement = 22;
        this.fatigueTemps = 2;
        this.valeurGustative = Config.TASTE_CANNIBALE;
        
    }
    
    
    /**
     * Deplace le Cannibale vers le Comestible le plus proche .
     * On considere que les deplacements en diagonale ne sont pas plus couteux en
     * energie que les deplacements en ligne droite, donc toutes les Cases
     * adjacentes a une Case donnee sont a egale distance de cette derniere.
     * 
     * @throws LoftException 
     */
    @Override
    public void deplacer() throws LoftException {
        
        fatiguer();
        
        // chercher les Comestibles les plus proches
        Comestible comProche = (Comestible) chercher(plateau.getComestibles());
        allerVers(comProche);
        
        manger();
    }
    
    
    @Override
    public void colorer(Graphics g){
        g.setColor(Color.GREEN);
    }
    
}
