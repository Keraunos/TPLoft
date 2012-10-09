package loft.exception;


/**
 * Classe LoftException. Gere les erreurs survenant lors du jeu.
 * Une LoftException a un contexte (FailureContext) et un type (FailureType).
 * 
 * @author Marlene, Gaetan
 */
public class LoftException extends Exception {
    
    private FailureContext context;
    private FailureType type;

    
    public LoftException(FailureContext context, FailureType type) {
        this.context = context;
        this.type = type;
    }
    
    
    public FailureContext getContext() {
        return context;
    }
    
    public FailureType getType() {
        return type;
    }
    
    
    public void setContext(FailureContext context) {
        this.context = context;
    }
    
    public void setType(FailureType type) {
        this.type = type;
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
    }
    
    /**
     * Classe interne FailureType. Decrit la cause de l'erreur.
     */
    public enum FailureType {
        NEUNEU_NOT_ON_SQUARE,
        NEUNEU_IS_DEAD,
        SEARCH_OUT_OF_BOUNDS,
    }
    
}

