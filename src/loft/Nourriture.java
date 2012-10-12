package loft;

import java.awt.Graphics;

/**
 * Classe Nourriture. Les Nourritures peuvent etre mangees par les Neuneux.
 * Les Nourritures se trouvent sur les Cases du Plateau.
 * 
 * @author Marlene, Gaetan
 */
public abstract class Nourriture extends Comestible{
    
    
    public void setValeurEnerg(int valEnerg) {
        this.valeurEnerg = valEnerg;
    }
    
    
    /**
     * Genere une Nourriture dont la classe est determinee aleatoirement.
     * 
     * @return Nourriture
     */
    public static Nourriture genererNourriture(Case _case) {
        
        int choix = (int) Math.floor(Math.random()*3);
        
        switch(choix) {
            case 0: return new Fruit(_case);
            case 1: return new Viande(_case);
            case 2: return new Alcool(_case);
            default: return new Fruit(_case);
        }
        
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat du Neuneu
     * 
     * @param mode Mode d'affichage.
     */
    public String afficherDebug(int mode) {
        String str = "";
        
        if      (this instanceof Fruit)     str = "f";
        else if (this instanceof Viande)    str = "v";
        else if (this instanceof Alcool)    str = "a";
        else                                str = "?";
        
        return str;
    }
    
    
    @Override
    public void tracer(Graphics g) {
        try {
            this.cercle(g,
                Config.GUI_SIDE_MARGIN + (Config.GUI_SQUARE_SIZE + 1) * (getX() + 1) - Config.GUI_FOOD_SIZE,
                Config.GUI_TOP_MARGIN + (Config.GUI_SQUARE_SIZE + 1) * getY() + positionRelative,
                Config.GUI_FOOD_SIZE);
        } catch (Exception e) {
            // TODO Gerer cette exception : pas vraiment un probleme,
            // signifie que cette Nourriture a ete exclue du plateau
            // NullPointerException dans getX() (this._case est NULL)
        }
    }
    
}
