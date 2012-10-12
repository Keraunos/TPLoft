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
    protected static int intervalleRapports = 5; // nb de tours entre deux rapports
    protected int dernierRapport;
    
    
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
        this.fatigueDeplacement = 8;
        this.fatigueCoit = 20;
        this.valeurGustative = 60;
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
        
        // si le Lapin n'a pas besoin de se reproduire, il erre
        if (++dernierRapport < Lapin.intervalleRapports) {
            deplacerHasard();
            return;
        }
        
        // chercher les Neuneus les plus proches
        Neuneu neuProche = null;
        int distance,
            distMin = Math.max(Config.BOARD_WIDTH, Config.BOARD_HEIGHT) + 1;
        
        for (Neuneu neu:plateau.getPopulation()) {
            
            // pas de reproduction avec les exclus
            if (neu.estExclu()) continue;
            
            // distance du Neuneu courant
            distance = getDistance(neu);
            
            // ne pas aller vers les Neuneus qui sont sur la meme case
            if (distance == 0) continue;
            
            // cas d'un Neuneu plus proche que l'actuel plus proche
            if (distance < distMin) {
                distMin = distance;
                neuProche = neu;
            }
            
            // cas d'un Neuneu aussi proche que l'actuel plus proche:
            // choisir le plus "attractif" (la valeur gustative est confondue avec le sex appeal)
            else if (distance == distMin && neuProche != null)
                if (neu.valeurGustative > neuProche.valeurGustative)
                    neuProche = neu;
            
        }
        
        // si aucun Neuneu trouve, se deplacer au hasard
        if (neuProche == null) {
            deplacerHasard();
            return;
        }
        
        // se deplacer vers le Neuneu choisi et s'accoupler si possible
        int dX = (int) Math.signum((float) (neuProche.getX()-getX()));
        int dY = (int) Math.signum((float) (neuProche.getY()-getY()));
        Case destination = plateau.getCase(getX()+dX, getY()+dY);
        allerA(destination);
        if (destination.equals(neuProche._case)) {
            accoupler(neuProche);
            dernierRapport = 0;
            if (neuProche instanceof Lapin)
                ((Lapin)neuProche).setDernierRapport(0);
        }
        
        manger();
    }
    
    
    @Override
    public void colorer(Graphics g){
        g.setColor(Color.PINK);
    }
    
}
