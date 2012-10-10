package loft;

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
    public static Nourriture genererNourriture() {
        
        int choix = (int) Math.floor(Math.random()*3);
        
        switch(choix) {
            case 0: return new Fruit();
            case 1: return new Viande();
            case 2: return new Alcool();
            default: return new Fruit();
        }
        
    }
    
}
