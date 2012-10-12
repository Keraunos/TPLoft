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
    protected static final int NB_DISP_ELTS_IN_SQUARE = 4;
    
    // deroulement d'une partie
    protected static final int NB_TURNS = 1000;
    
    // dimensions du plateau de jeu
    protected static final int BOARD_WIDTH = 12;
    protected static final int BOARD_HEIGHT = 12;
    
    // ratio de remplissage initial du plateau (par rapport au nombre total de cases)
    protected static final float POPULATION_RATIO = 0.01f;
    protected static final float FOOD_RATIO = 0.01f;
    
    // limites basses : declenchent les inclusions de nouveaux Neuneus et Nourritures
    // limites relatives aux quantites initiales
    protected static final float POP_LOW_LIMIT_RATIO = 0;//0.5f;
    protected static final float FOOD_LOW_LIMIT_RATIO = 0;//0.5f; 
    // limites absolues
    protected static final int POP_LOW_LIMIT = 0;//2;
    protected static final int FOOD_LOW_LIMIT = 0;//3;
    
    // energie max et initiale des Neuneus
    protected static final int MAX_ENERGY = 100;
    
    
    // GUI
    protected static final int TOP_MARGIN = 22+20;
    protected static final int LEFT_MARGIN = 20;
    protected static final int ZOOM_FACTOR = 40;
    protected static final int NEUNEU_SIZE = 12;
    protected static final int FOOD_SIZE = 8;
    protected static final float FOOD_SHIFT = 0.4f;
    
}
