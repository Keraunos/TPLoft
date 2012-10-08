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
    protected static final int BOARD_WIDTH = 6;
    protected static final int BOARD_HEIGHT = 6;
    
    // ratio de remplissage du plateau (par rapport au nombre total de cases)
    protected static final float POPULATION_RATIO = 0.1f;
    protected static final float FOOD_RATIO = 0.2f;
}
