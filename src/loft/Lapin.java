package loft;

import java.awt.Color;
import java.awt.Graphics;
import loft.exception.LoftException;

/**
 * Classe Lapin.
 * Un Lapin est un Neuneu particulierement porte sur la reproduction.
 * 
 * @author Marlene, Gaetan
 */
public class Lapin extends Neuneu {
    
    protected static int count = 0;
    protected static int intervalleRapports = Config.SEX_FREQ_LAPIN; 
    protected int dernierRapport; // nb de tours depuis le dernier acte de reproduction
    
    
    /**
     * Constructeur
     * @param _case La Case ou ce Lapin sera inclu 
     */
    public Lapin(Case _case) {
        
        this.id = ++Lapin.count;
        
        this.plateau = Plateau.getInstance();
        this._case = _case;
        
        this.valeurEnerg = 40;
        this.energie = Config.MAX_ENERGY;
        this.fatigueDeplacement = 5;
        this.fatigueCoit = 10;
        this.fatigueTemps = 1;
        this.valeurGustative = Config.TASTE_LAPIN;
        this.dernierRapport = Lapin.intervalleRapports;
        
    }
    
    
    /**
     * Fixe le nombre de tours de jeu depuis le dernier acte de reproduction
     * de ce Neuneu
     */
    private void setDernierRapport(int dr) {
        this.dernierRapport = dr;
    }
    
    
    /**
     * Deplace le Lapin vers le plus proche Neuneu en vue de se reproduire.
     * On considere que les deplacements en diagonale ne sont pas plus couteux en
     * energie que les deplacements en ligne droite, donc toutes les Cases
     * adjacentes a une Case donnee sont a egale distance de cette derniere.
     * 
     * @throws LoftException 
     */
    @Override
    public void deplacer() throws LoftException {
        
        fatiguer();
        
        // si le Lapin n'a pas besoin de se reproduire, il erre
        if (++dernierRapport < Lapin.intervalleRapports) {
            deplacerHasard();
            return;
        }
        
        // TODO rendre les Lapins un peu plus intelligents que les Erratiques sur la recherche de nourriture
        // chercher les Neuneus les plus proches (neuProche peut etre NULL)
        Neuneu neuProche = (Neuneu) chercher(plateau.getPopulation());
        boolean cibleAtteinte = allerVers(neuProche);
        
        manger();
        
        // TODO: fixer une limite max de population par case pour eviter divergence
        if (cibleAtteinte) {
            accoupler(neuProche);
            dernierRapport = 0;
            if (neuProche instanceof Lapin)
                ((Lapin) neuProche).setDernierRapport(0);
        }
        
    }
    
    
    @Override
    public void colorer(Graphics g){
        g.setColor(Color.PINK);
    }
    
}
