package loft;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Vector;
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
    
    protected Plateau plateau;
    
    protected int energie;
    protected int fatigueDeplacement; // cout energetique du deplacement unitaire
    protected int fatigueCoit; // cout energetique de la reproductin
    protected int fatigueTemps; // impact du temps qui passe (par tour du jeu)
    
    
    // coordonnees dans le GUI (Affichage)
    protected Integer guiX = null;
    protected Integer guiY = null;
    
    // TODO rajouter la gestion des besoins reproductifs pour tous les Neuneus
    // TODO rajouter une fatigue liee au temps qui passe, meme sans action
    
    
    public int getEnergie() {
        return this.energie;
    }
    
    /**
     * Deplace le Neuneu dans une direction aleatoire.
     * @throws LoftException 
     */
    public void deplacer() throws LoftException {
        fatiguer();
        deplacerHasard();
    }
    
    
    public void fatiguer() throws LoftException {
        energie -= fatigueTemps;
        if (energie <= 0) {
            exclure();
            throw new LoftException(
                    LoftException.FailureContext.GETTING_OLDER_NEUNEU,
                    LoftException.FailureType.NEUNEU_IS_DEAD);
        }
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
     * Deplace le Neuneu vers un Comestible cible sur le plateau
     * @param cible Comestible vers lequel deplacer le Neuneu
     * @return Boolean True si le Neuneu est sur la meme case que sa cible apres deplacement
     * @throws LoftException 
     */
    public Boolean allerVers(Comestible cible) throws LoftException {
        
        // si aucun Comestible trouve, se deplacer au hasard
        if (cible == null) {
            deplacerHasard();
            return false;
        }
        
        // se deplacer vers le Comestible choisi
        int dX = (int) Math.signum((float) (cible.getX() - getX()));
        int dY = (int) Math.signum((float) (cible.getY() - getY()));
        Case destination = plateau.getCase(getX() + dX, getY() + dY);
        allerA(destination);
        
        return (cible._case == _case);
    }
    
    
    /**
     * Deplace effectivement le Neuneu vers la Case specifiee
     * @param destination Case sur laquelle deplacer le Neuneu
     * @throws LoftException 
     */
    public void allerA(Case destination) throws LoftException {
        if (_case.equals(destination)) return;
        _case.enleverNeuneu(this);
        _case = destination;
        destination.ajouterNeuneu(this);
        energie -= fatigueDeplacement;
        if (energie <= 0) {
            exclure();
            throw new loft.exception.LoftException(
                    LoftException.FailureContext.MOVING_NEUNEU,
                    LoftException.FailureType.NEUNEU_IS_DEAD,
                    destination);
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
    public void accoupler(Neuneu neu) throws LoftException {
        // TODO: gerer les besoins reproductifs des Neuneus autres que Lapins
        
        plateau.inclurePetitNeuneu(_case);
        
        // TODO : seul ce Neuneu se fatigue : gerer la fatigue du partenaire
        energie -= fatigueCoit;
        if (energie <= 0) {
            exclure();
            throw new loft.exception.LoftException(
                    LoftException.FailureContext.REPRODUCING_NEUNEU,
                    LoftException.FailureType.NEUNEU_IS_DEAD);
        }
    }
    
    
    /**
     * Mange les Nourritures disponibles sur la Case en vue d'atteindre le max d'energie
     * 
     * @throws LoftException 
     */
    public void manger() throws LoftException {
        
        if (energie >= Config.MAX_ENERGY) return;
        
        // se nourrir avec les Nourritures presentes sur la Case
        Comestible repas;
        while (energie < Config.MAX_ENERGY) {
            
            if (this instanceof Cannibale) repas = _case.getMeilleurComestible(this);
            else repas = _case.getMeilleureDenree();
            
            if (repas == null) return;
            
            // consommer en entier, sans depasser le max d'energie
            energie += repas.getValeurEnerg();
            if (energie > Config.MAX_ENERGY) energie = Config.MAX_ENERGY;
            plateau.supprimerComestible(repas, _case);
            
            if (repas instanceof Neuneu)
                System.out.println(this + " a devore " + repas + " sur la case [" + getX() + "][" + getY() + "] !");
            else
                System.out.println(this + " a mange " + repas + " sur la case [" + getX() + "][" + getY() + "].");
        }
    }
    
    
    /**
     * Recupere le Comestible le plus proche et (par ordre de priorite) le plus
     * attractif autour de ce Neuneu.
     * 
     * @param comestibles Objet de la classe ArrayList<Comestible>
     * @return Comestible
     */
    public Comestible chercher(Object comestibles) {
        
        // TODO : implementer une methode qui parcoure le plateau par vagues concentriques a partir d'une case donee
        // afin de reduire le cout de la recherche
        
        // chercher les Comestibles les plus proches
        Comestible comProche = null;
        int distance,
            distMin = Math.max(Config.BOARD_WIDTH, Config.BOARD_HEIGHT) + 1;
        
        for (Comestible com:((ArrayList<Comestible>)comestibles)) {
            
            // le Neuneu ne doit pas se choisir lui-meme
            if (com.equals(this)) continue;
            
            // distance du Comestible courant
            distance = getDistance(com);
            
            // cas d'un Comestible plus proche que l'actuel plus proche
            if (distance < distMin) {
                distMin = distance;
                comProche = com;
            }
            
            // cas d'un Comestible aussi proche que l'actuel plus proche:
            // choisir le plus "attractif" (gout ou sex appeal)
            else if (distance == distMin && comProche != null)
                if (com.valeurGustative > comProche.valeurGustative)
                    comProche = com;
            
        }
        
        return comProche;
    }
    
    
    /**
     * Genere un Neuneu dont le type est determine aleatoirement : Lapin, Erratique,
     * Vorace ou Cannibale.
     * 
     * @return Neuneu
     */
    public static Neuneu genererNeuneu(Case _case) {
        
        int choix1 = (int) Math.floor(Math.random()*4) + 1,
                choix2 = (int) Math.floor(Math.random()*3) + 1;
        
        switch (choix1) {
            case 1: return new Lapin(_case);
            case 2: return new Erratique(_case);
            case 3: return new Vorace(_case);
            case 4:
                switch (choix2) {
                    case 1: return new Cannibale(_case);
                    case 2: return new Lapin(_case);
                    case 3: return new Vorace(_case);
                    default: return new Erratique(_case);
                }
            default: return new Erratique(_case);
        }
    }
    
    
    /**
     * Retourne la distance entre ce Neuneu et le Comestible specifie.
     * On considere que toutes les Cases adjacentes a une Case donnee sont a
     * egale distance de cette derniere. Donc les distances en diagonale sont
     * egales aux distances en ligne droite.
     * 
     * @param com Le Comestible dont il faut evaluer la distance
     * @return La distance
     */
    public int getDistance(Comestible com) {
        int dX = com.getX() - getX();
        int dY = com.getY() - getY();
        return Math.max(Math.abs(dX), Math.abs(dY)); 
    }
    
    
    /**
     * Tracer ce Neuneu a gauche de sa Case, aligne verticalement avec les autres
     * Neuneus presents sur la meme Case.
     * @param g 
     */
    @Override
    public void tracer(Graphics g) {
        int newGuiX = Config.GUI_MARGIN_SIDE + (Config.GUI_SQUARE_SIZE + 1) * getX() + 1;
        int newGuiY = Config.GUI_MARGIN_TOP + (Config.GUI_SQUARE_SIZE + 1) * getY() + 1 + positionRelative;
        try {
            this.rectangle(g,
                newGuiX, newGuiY,
                Config.GUI_NEUNEU_SIZE, Config.GUI_NEUNEU_SIZE);
            // si le Neuneu se deplace alors tracer sa trajectoire
            if (guiX != null) {
                if (newGuiX != guiX || newGuiY != guiY) {
                    this.trajet(g, guiX + Config.GUI_NEUNEU_SIZE/2,
                            guiY + Config.GUI_NEUNEU_SIZE/2,
                            newGuiX + Config.GUI_NEUNEU_SIZE/2,
                            newGuiY + Config.GUI_NEUNEU_SIZE/2);
                }
            }
            guiX = newGuiX;
            guiY = newGuiY;
        } catch (Exception e) {
            // TODO Gerer cette exception : pas vraiment un probleme,
            // signifie que ce Neuneu a ete exclu du plateau
            // NullPointerException dans getX() (this._case est NULL)
        }
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat du Neuneu
     * 
     * @param mode Mode d'affichage.
     */
    public String afficherDebug(int mode) {
        String str = "";
        
        if      (this instanceof Lapin)     str = "L";
        else if (this instanceof Erratique) str = "E";
        else if (this instanceof Cannibale) str = "C";
        else if (this instanceof Vorace)    str = "V";
        else                                str = "N";
        
        return str;
    }
}
