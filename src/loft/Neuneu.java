package loft;

import java.util.ArrayList;
import loft.exception.LoftException;

/**
 * Classe Neuneu. Un Neuneu est un etre vivant sur les Cases du Plateau.
 * Les Neuneus peuvent se deplacer, interagir entre eux (reproduction, parfois
 * cannibalisme) et manger des Nourritures. Quand un Neuneu n'a plus d'energie,
 * il est exclu du Plateau.
 * 
 * @author Marlene, Gaetan
 */
public abstract class Neuneu extends Comestible {
	
    protected int energie;
    protected int fatigueDeplacement;
    protected int fatigueCoit;
    protected Plateau plateau;
    
    
    public int getEnergie() {
        return this.energie;
    }
    
    
    public void exclure() {
        
    }
    
    /**
     * Deplace le Neuneu dans une direction aleatoire.
     */
    public void deplacer() throws LoftException {
        
        plateau = Plateau.getInstance();
        int x = this._case.getX(), dX,
            y = this._case.getY(), dY;
        ArrayList<Case> casesAdjacentes = new ArrayList<Case>();
        Case adjacente;
        
        // construire la liste des cases adjacentes
        for (dX = -1; dX < 2; dX++) {
        for (dY = -1; dY < 2; dY++) {
            if (dX != 0 || dY != 0) {
                try {
                    adjacente = plateau.getCase(x + dX, y + dY);
                    casesAdjacentes.add(adjacente);
                } catch (LoftException e) {
                    // ignore exception gracefully
                }
            }
        }}
        
        // determiner la case de destination parmi les adjacentes
        int i;
        Case c, destination = null;
        do {
            i = (int) (Math.random()*casesAdjacentes.size());
            c = casesAdjacentes.get(i);
            if (!c.estLibre()) casesAdjacentes.remove(i);
            else destination = c;
        } while (casesAdjacentes.size() > 0 && destination == null);
        
        // si aucune case libre pour se deplacer, alors rester sur place
        if (destination == null) return;
        
        // sinon, se deplacer effectivement
        this._case.enleverNeuneu(this);
        destination.ajouterNeuneu(this);
        energie -= fatigueDeplacement;
        if (energie <= 0) plateau.exclure(this);
    }
    
    
    public Neuneu accoupler(Neuneu neu) {
        // TODO code
        return neu;
    }

    public void manger() {
        // TODO code
    }

    /**
     * Genere un Neuneu dont le type est determine aleatoirement : Lapin, Erratique,
     * Vorace ou Cannibale.
     * 
     * @return Neuneu
     */
    public static Neuneu genererNeuneu(Case _case) {
        
        int choix = (int) Math.floor(Math.random()*4) + 1;
        
        switch (choix) {
            case 1: return new Lapin(_case);
            case 2: return new Erratique(_case);
            case 3: return new Vorace(_case);
            case 4: return new Cannibale(_case);
            default: return new Erratique(_case);
        }
    }

}
