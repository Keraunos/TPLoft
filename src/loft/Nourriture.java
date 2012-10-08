package loft;

/**
 * Classe Nourriture. Les Nourritures peuvent etre mangees par les Neuneux.
 * Les Nourritures se trouvent sur les Cases du Plateau.
 * 
 * @author Marlene, Gaetan
 */
public class Nourriture extends Comestible{
    
    
    public Nourriture() {
        
    }
    
    public void setValeurEnerg(){
        
    }
    
    
    public static Nourriture genererNourriture() {
        
        int choix = (int) Math.floor(Math.random()*2);
        
        switch(choix) {
            case 0: return new Nourriture();
            case 1: return new Alcool();
            default: return new Nourriture();
        }
        
    }
    
}
