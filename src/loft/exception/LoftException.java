package loft.exception;

import loft.Neuneu;

/**
 * Classe LoftException. Gere les erreurs survenant lors du jeu.
 * Une LoftException a un contexte (FailureContext) et un type (FailureType).
 * 
 * @author Marlene, Gaetan
 */
public class LoftException extends Exception {
    
    private FailureContext context;
    private FailureType type;
    private Object o;
    
    
    public LoftException(FailureContext context, FailureType type) {
        this.context = context;
        this.type = type;
    }
    
    public LoftException(FailureContext context, FailureType type, Object o) {
        this.context = context;
        this.type = type;
        this.o = o;
    }
    
    public FailureContext getContext() {
        return context;
    }
    
    public FailureType getType() {
        return type;
    }
    
    public Object getObject() {
        return o;
    }
    
    
    public void setContext(FailureContext context) {
        this.context = context;
    }
    
    public void setType(FailureType type) {
        this.type = type;
    }
    
    
    public void displayErrorMsg(Neuneu neu) {
        
        String msg = "";

        switch (getType()) {
            
            case NEUNEU_IS_DEAD:
                msg += "Le neuneu " + neu.toString() + " est mort";
                switch (getContext()) {
                    case MOVING_NEUNEU: msg += " d'epuisement en se deplacant";
                    default: msg += ".";
                }
                break;
                
            case FOOD_NOT_ON_SQUARE:
                msg += "La Nourriture " + getObject().toString() +
                        " ne se trouve pas sur la case [" + neu.getX() + "][" + neu.getY() + "].";
                break;
                
            default: msg += "Erreur de type " + getType() + " dans le contexte " + getContext();
        }

        System.out.println(msg);
    }
    
    /**
     * Classe interne FailureContext. Designe l'action ayant echoue.
     */
    public enum FailureContext {
        
        // Plateau
        CREATING_GAME_BOARD,
        
        // Case
        CREATING_CASE,
        GETTING_CASE,
        
        // Neuneu
        EATING_NEUNEU,
        EXCLUDING_NEUNEU,
        FEEDING_NEUNEU,
        GENERATING_BABY_NEUNEU,
        GENERATING_RANDOM_NEUNEU,
        PLACING_NEUNEU,
        MOVING_NEUNEU,
        REPRODUCING_NEUNEU,
        
        // Nourriture
        EATING_FOOD,
        PLACING_FOOD,
        REMOVING_FOOD,
    }
    
    /**
     * Classe interne FailureType. Decrit la cause de l'erreur.
     */
    public enum FailureType {
        NEUNEU_NOT_ON_SQUARE,
        NEUNEU_IS_DEAD,
        SEARCH_OUT_OF_BOUNDS,
        FOOD_NOT_ON_SQUARE,
    }
    
}

