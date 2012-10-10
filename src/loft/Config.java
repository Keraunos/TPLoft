package loft;

/**
 * Classe Config. Contient les constantes globales de configuration.
 * 
 * @author gaetan
 */
public class Config {
    
    // mode de deboggage
    protected static final boolean DEBUG_MODE = true;
    protected static final boolean WAIT_FOR_USER = true;
    
    // deroulement d'une partie
    protected static final int NB_TURNS = 100;
    
    // dimensions du plateau de jeu
    protected static final int BOARD_WIDTH = 5;
    protected static final int BOARD_HEIGHT = 4;
    
    // ratio de remplissage initial du plateau (par rapport au nombre total de cases)
    protected static final float POPULATION_RATIO = 0.1f;
    protected static final float FOOD_RATIO = 0.2f;
    
    // limites basses : declenchent les inclusions de nouveaux Neuneus et Nourritures
    // limites relatives aux quantites initiales
    protected static final float POP_LOW_LIMIT_RATIO = 0;//0.5f;
    protected static final float FOOD_LOW_LIMIT_RATIO = 0;//0.5f; 
    // limites absolues
    protected static final int POP_LOW_LIMIT = 0;//2;
    protected static final int FOOD_LOW_LIMIT = 0;//3;
    
    // energie max et initiale des Neuneus
    protected static final int MAX_ENERGY = 100;
    
}
