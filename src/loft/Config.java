package loft;

/**
 * Classe Config. Contient les constantes globales de configuration.
 * 
 * @author gaetan
 */
public class Config {
    
    // mode d'execution
    protected static final boolean DEBUG_MODE = true;
    protected static final int NB_DISP_ELTS_IN_SQUARE = 4; // en DEBUG: nb. max de Neuneus (ou de Nourritures) affiches par case dans la console
    protected static final boolean WAIT_FOR_USER = false; // true: bouton "tour suivant"; false: defilement automatique
    protected static final boolean WAIT_FOR_ACTION_IN_CONSOLE = false; // en DEBUG: appui sur touche Enter dans console pour tour suivant
    protected static final long PAUSE_DURATION = 300; // en DEBUG: (ms) pause entre 2 tours (defimement automatique)
    
    
    // deroulement d'une partie
    protected static final int NB_TURNS = 300;
    
    // dimensions du plateau de jeu
    protected static final int BOARD_WIDTH = 12;
    protected static final int BOARD_HEIGHT = 12;
    
    // ratio de remplissage initial du plateau (par rapport au nombre total de cases)
    protected static final float POPULATION_RATIO = 0.1f;
    protected static final float FOOD_RATIO = 0.2f;
    
    // limites basses : declenchent les inclusions de nouveaux Neuneus et Nourritures
    // limites relatives aux quantites initiales
    protected static final float POP_LOW_LIMIT_RATIO = 0.5f;
    protected static final float FOOD_LOW_LIMIT_RATIO = 0.8f;
    // limites absolues
    protected static final int POP_LOW_LIMIT = 2;
    protected static final int FOOD_LOW_LIMIT = 4;
    
    // energie max et initiale des Neuneus
    protected static final int MAX_ENERGY = 100;
    
    
    // GUI
    // taille des Comestibles
    protected static final int GUI_SQUARE_SIZE = 50;
    protected static final int GUI_NEUNEU_SIZE = (int) GUI_SQUARE_SIZE / 3;
    protected static final int GUI_FOOD_SIZE = (int) GUI_SQUARE_SIZE / 4;
    // marges
    protected static final int GUI_MARGIN_STD = 20;
    protected static final int GUI_MARGIN_TOP = 22 + GUI_MARGIN_STD;
    protected static final int GUI_MARGIN_SIDE = GUI_MARGIN_STD;
    // dimensions du plateau de jeu
    protected static final int GUI_BOARD_WIDTH = BOARD_WIDTH * (GUI_SQUARE_SIZE + 1) + 1; // 1 est la largeur des lignes entre les cases
    protected static final int GUI_BOARD_HEIGHT = BOARD_HEIGHT * (GUI_SQUARE_SIZE + 1) + 1;
    // dimensions du bouton pour passer les tours
    protected static final int GUI_BUTTON_WIDTH = 200;
    protected static final int GUI_BUTTON_HEIGHT = 40;
    // dimensions de la fenetre
    protected static final int GUI_WINDOW_WIDTH = Math.max(GUI_BOARD_WIDTH, GUI_BUTTON_WIDTH) + 2 * GUI_MARGIN_SIDE;
    protected static final int GUI_WINDOW_HEIGHT = GUI_BOARD_HEIGHT + GUI_BUTTON_HEIGHT + GUI_MARGIN_TOP + 2 * GUI_MARGIN_STD;
    
    
    // reglages du jeu
    protected static final int TASTE_ERRATIQUE = 35;
    protected static final int TASTE_LAPIN = 40;
    protected static final int TASTE_VORACE = 45;
    protected static final int TASTE_CANNIBALE = 50;
    
    protected static final int TASTE_ALCOOL = 90;
    protected static final int TASTE_FRUIT = 70;
    protected static final int TASTE_VIANDE = 50;
    
    protected static final int SEX_FREQ_LAPIN = 5;
    
}
