package loft;

import java.awt.Graphics;
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
    // TODO rajouter la gestion des besoins reproductifs pour tous les Neuneus
    // TODO rajouter une fatigue liee au temps qui passe, meme sans action
    
    
    public int getEnergie() {
        return this.energie;
    }
    
    public int getX() {
        if (_case == null) return -1;
        return _case.getX();
    }
    
    public int getY() {
        if (_case == null) return -1;
        return _case.getY();
    }
    
    /**
     * Deplace le Neuneu dans une direction aleatoire.
     * @throws LoftException 
     */
    public void deplacer() throws LoftException {
        deplacerHasard();
    }
    
    
    /**
     * Deplace le Neuneu avec un comportement par defaut : erratique.
     * 
     * @throws LoftException 
     */
    protected void deplacerHasard() throws LoftException {
        
        int dX, dY;
        ArrayList<Case> casesAdjacentes = new ArrayList<Case>();
        Case adjacente;
        
        // construire la liste des cases adjacentes
        for (dX = -1; dX < 2; dX++) {
        for (dY = -1; dY < 2; dY++) {
            if (dX != 0 || dY != 0) {
                try {
                    adjacente = plateau.getCase(getX() + dX, getY() + dY);
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
        
        // se deplacer effectivement si une case libre a ete trouvee
        if (destination != null) allerA(destination);
        // TODO: si destination==null, chercher a se deplacer pour se nourrir ou se reproduire
        
        manger();
    }
    
    
    /**
     * Deplace effectivement le Neuneu vers la Case specifiee
     * @param destination Case sur laquelle deplacer le Neuneu
     * @throws LoftException 
     */
    public void allerA(Case destination) throws LoftException {
        _case.enleverNeuneu(this);
        _case = destination;
        destination.ajouterNeuneu(this);
        energie -= fatigueDeplacement;
        if (energie <= 0) {
            exclure();
            throw new loft.exception.LoftException(
                    LoftException.FailureContext.MOVING_NEUNEU,
                    LoftException.FailureType.NEUNEU_IS_DEAD);
        }
    }
    
    
    /**
     * Ajoute le Neuneu a la liste des Neuneus a exclure a la fin du tour de jeu.
     * @throws LoftException 
     */
    public void exclure() throws LoftException {
        plateau.exclure(this);
    }
    
    
    /**
     * Indique si le Neuneu doit etre exclu a la fin du tour de jeu.
     * 
     * @return Vrai si le Neuneu doit etre exclu, faux sinon.
     */
    public boolean estExclu() {
        return (this.energie <= 0);
    }
    
    
    /**
     * Donne naissance a un nouveau Neuneu sur la Case ou a lieu la reproduction.
     * 
     * @param neu Le Neuneu partenaire dans l'acte de reproduction.
     */
    public void accoupler(Neuneu neu) {
        plateau.inclurePetitNeuneu(_case);
        // TODO: gerer les besoins reproductifs des Neuneus autres que Lapins
    }
    
    
    /**
     * Mange les Nourritures disponibles sur la Case en vue d'atteindre le max d'energie
     * 
     * @throws LoftException 
     */
    public void manger() throws LoftException {
        
        if (energie >= Config.MAX_ENERGY) return;
        
        // se nourrir avec les Nourritures presentes sur la Case
        Nourriture repas = null;
        while (energie < Config.MAX_ENERGY) {
            
            repas = _case.getMeilleureDenree();
            if (repas == null) return;
            
            // consommer en entier, sans depasser le max d'energie
            energie += repas.getValeurEnerg();
            if (energie > Config.MAX_ENERGY) energie = Config.MAX_ENERGY;
            plateau.supprimerNourriture(repas, _case);
            
            if (Config.DEBUG_MODE) System.out.println(this.toString() + " a mange " + repas.toString() + " sur la case [" + getX() + "][" + getY() + "].");
        }
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
    
    
    /**
     * Retourne la distance entre ce Neuneu et le Neuneu specifie.
     * On considere que toutes les Cases adjacentes a une Case donnee sont a
     * egale distance de cette derniere. Donc les distances en diagonale sont
     * egales aux distances en ligne droite.
     * 
     * @param neu Le Neuneu dont il faut evaluer la distance
     * @return 
     */
    public int getDistance(Neuneu neu) {
        int dX = neu.getX() - getX();
        int dY = neu.getY() - getY();
        return Math.max(Math.abs(dX), Math.abs(dY)); 
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat du Neuneu
     * 
     * @param mode Mode d'affichage.
     */
    public String afficherNeuneu(int mode) {
        String str = "";
        
        if      (this instanceof Lapin)     str = "L";
        else if (this instanceof Erratique) str = "E";
        else if (this instanceof Cannibale) str = "C";
        else if (this instanceof Vorace)    str = "V";
        else                                str = "N";
        
        return str;
    }
    
    
    @Override
    public void dessinerObjet(Graphics g) {
        try {
            this.rectangle(g,
                getX()*Config.ZOOM_FACTOR,
                getY()*Config.ZOOM_FACTOR,
                Config.NEUNEU_SIZE,
                Config.NEUNEU_SIZE);
        } catch (Exception e) {
            // TODO Gerer cette exception : pas vraiment un probleme,
            // signifie que ce Neuneu a ete exclu du plateau
            // NullPointerException dans getX() (this._case est NULL)
        }
    }

}
