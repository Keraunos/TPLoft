package loft;

/**
 * Classe Config. Contient les constantes globales de configuration.
 * 
 * @author gaetan
 */
public class Config {
    
    // mode de deboggage
    protected static final boolean DEBUG_MODE = true;
    
    // dimensions du plateau de jeu
    protected static final int BOARD_WIDTH = 8;
    protected static final int BOARD_HEIGHT = 6;
    
    // ratio de remplissage initial du plateau (par rapport au nombre total de cases)
    protected static final float POPULATION_RATIO = 0.1f;
    protected static final float FOOD_RATIO = 0.2f;
    
    // limites basses : declenchent les inclusions de nouveaux Neuneus et Nourritures
    // limites relatives aux quantites initiales
    protected static final float POP_LOW_LIMIT_RATIO = 0.5f;
    protected static final float FOOD_LOW_LIMIT_RATIO = 0.5f; 
    // limites absolues
    protected static final int POP_LOW_LIMIT = 2;
    protected static final int FOOD_LOW_LIMIT = 3;
    
}
