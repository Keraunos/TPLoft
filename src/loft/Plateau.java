package loft;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JButton;
import loft.exception.LoftException;

/**
 * Classe Plateau.
 * Definit les Cases et leur contenu (Nourritures, Neuneus...).
 * 
 * @author Marlene, Gaetan
 */
public class Plateau extends ObjetGraphique {
    
    // unique instance de Plateau
    private static Plateau plateau = null;
    
    // metriques
    private static int w = Config.BOARD_WIDTH;
    private static int h = Config.BOARD_HEIGHT;
    
    // donnes initiales
    private int initPopulation;
    private int initNourriture;
    private boolean estInitialise = false;
    
    // tableaux de cases et de population
    private Case[][] cases; // Case[width][height]
    private ArrayList<Neuneu> population;
    private int nbDenrees;
    
    // GUI
    private static Affichage dessin;
    private JButton button;
    
    
    /**
     * Recupere (ou cree) l'unique instance de Plateau
     */
    public static Plateau getInstance() {
        if (plateau == null) plateau = new Plateau();
        return plateau;
    }
    
    
    /**
     * Constructeur prive pour empecher l'instantiation de plusieurs Plateaux
     */
    private Plateau() {
        
        // nombre initial de Neuneus et de Nourritures
        this.initPopulation = (int) ((float)(w*h)*Config.POPULATION_RATIO);
        this.initNourriture = (int) ((float)(w*h)*Config.FOOD_RATIO);
        this.nbDenrees = 0;
        
        dessin = new Affichage();
        dessin.ajoutObjet(this);
        dessin.setVisible(true);
        
        if (!Config.WAIT_FOR_USER) return;
        
        this.button = new JButton();
        button.setActionCommand("nextTurn");
        button.addActionListener(dessin);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setText("Suivant");
        //button.setSize(Config.GUI_BUTTON_WIDTH, Config.GUI_BUTTON_HEIGHT);
        button.setBounds(
                (Config.GUI_BOARD_WIDTH - Config.GUI_BUTTON_WIDTH)/2,
                Config.GUI_BOARD_HEIGHT + Config.GUI_MARGIN_STD + Config.GUI_BUTTON_HEIGHT/2,
                Config.GUI_BUTTON_WIDTH,
                Config.GUI_BUTTON_HEIGHT);
        button.setVisible(true);
        dessin.add(button);
        
    }
    
    
    public void init() {
        
        estInitialise = true;
        
        // construction des Cases vides
        cases = new Case[w][h];
        for(int i = 0; i < w; i++ )
            for(int j = 0; j < h; j++)
                cases[i][j] = new Case(i, j);
        
        // creation de la population initiale
        population = new ArrayList<Neuneu>();
        for (int k = 0 ; k < initPopulation; k++)
            inclureNeuneu();
        
        // creation des denrees initiales
        for (int k = 0; k < initNourriture; k++)
            inclureNourriture();
        
        if (Config.DEBUG_MODE) this.afficherDebug(0);
        
        dessin.repaint();
    }
    
    /**
     * Retourne l'ensemble des Neuneus sur le plateau.
     * @return 
     */
    public ArrayList<Neuneu> getPopulation() {
        return this.population;
    }
    
    /**
     * Retourne la Case aux coordonnees specifiees.
     * 
     * @param x L'abscisse de la Case.
     * @param y L'ordonnee de la Case.
     * @return La Case voulue.
     * @throws LoftException Erreur si les coordonnees sont hors limites.
     */
    public Case getCase(int x, int y) throws LoftException {
        
        if (x<0 || x>=w || y<0 || y>=h) throw new LoftException(
                LoftException.FailureContext.GETTING_CASE,
                LoftException.FailureType.SEARCH_OUT_OF_BOUNDS);
        
        return this.cases[x][y];
    }
    
    
    /**
     * Deroule un tour de jeu en faisant evoluer tous les Neuneus et en
     * reintroduisant des Neuneus ou de la Nourriture s'il en manque.
     */
    public void jouerTour() {
        
        if (!estInitialise) init();
        
        // inclure Neuneus si population trop basse
        while (population.size() <= Config.POP_LOW_LIMIT ||
                population.size() <= Config.POP_LOW_LIMIT_RATIO*initPopulation)
            inclureNeuneu();
        
        // inclure Nourritures si denrees trop rares
        while (nbDenrees <= Config.FOOD_LOW_LIMIT ||
                nbDenrees <= Config.FOOD_LOW_LIMIT_RATIO*initNourriture)
            inclureNourriture();
        
        // faire se deplacer et agir tous les Neuneus (manger, reproduction...)
        ArrayList<Neuneu> populationDebutTour = new ArrayList<Neuneu>(population);
        for (Neuneu neu:populationDebutTour) {
            
            try {
                neu.deplacer();
            } catch (LoftException e) {
                e.displayErrorMsg(neu);
            }
        }
        
        if (Config.DEBUG_MODE) afficherDebug(0);
        
        dessin.repaint();
        
    }
    
    
    /**
     * Exclut du Plateau le Neuneu specifie.
     * 
     * @param Neuneu Le Neuneu a exclure.
     * @throws LoftException 
     */
    public void exclure(Neuneu neu) throws LoftException {
        population.remove(neu);
        neu._case.enleverNeuneu(neu);
        neu._case = null;
        
    }
    
    
    /**
     * Inclut sur le Plateau un nouveau Neuneu, sur une case libre choisie aleatoirement
     */
    public final void inclureNeuneu() {
        
        Case c;
        do c = getRandCase();
        while (!c.estLibre());
        // TODO: gerer le cas ou cette boucle peut tourner indefiniment
        
        Neuneu neu = c.ajouterNeuneu();
        this.population.add(neu);
        
    }
    
    
    /**
     * Fait naitre un nouveau Neuneu sur la Case specifiee.
     * 
     * @param _case Case ou doit apparaitre le nouveau Neuneu.
     */
    public void inclurePetitNeuneu(Case _case) {
        Neuneu neu = _case.ajouterNeuneu();
        this.population.add(neu);
        
    }
    
    
    /**
     * Inclut une Nourriture sur une case choisie aleatoirement.
     */
    public void inclureNourriture() {
        Nourriture nourr = getRandCase().ajouterNourriture();
        nbDenrees++;
        
    }
    
    
    /**
     * Supprimme du jeu la Nourriture specifiee
     * 
     * @param nourr Nourriture a supprimer
     * @param _case Case sur laquelle se trouve la Nourriture specifiee
     */
    public void supprimerNourriture(Nourriture nourr, Case _case) throws LoftException {
        
        if (!_case.getDenrees().remove(nourr))
            throw new LoftException(
                    LoftException.FailureContext.REMOVING_FOOD,
                    LoftException.FailureType.FOOD_NOT_ON_SQUARE,
                    nourr);
        nourr._case = null;
        nbDenrees--;
        
    }
    
    
    /**
     * Retourne une Case aleatoirement.
     * 
     * @return Une Case choisie aleatoirement.
     */
    private Case getRandCase() {
        return cases[(int) (Math.random()*w)][(int) (Math.random()*h)];
    }
    
    
    @Override
    public void colorer(Graphics g) {
        g.setColor(Color.WHITE);
    }
    
    
    /**
     * Trace tout le Plateau de jeu : le fond, la grille, le contenu des Cases
     * 
     * @param g 
     */
    @Override
    public void tracer(Graphics g) {
        
        if (!this.estInitialise) return;
        
        // fond de la fenetre
        Color fond = new Color(220, 235, 255);
        g.setColor(fond);
        g.fillRect(0, 0, Config.GUI_WINDOW_WIDTH, Config.GUI_WINDOW_HEIGHT);
        
        // fond du plateau
        g.setColor(Color.WHITE);
        rectangle(g,
                Config.GUI_MARGIN_SIDE, Config.GUI_MARGIN_TOP,
                Config.GUI_BOARD_WIDTH, Config.GUI_BOARD_HEIGHT);
        
        // grille (lignes delimitant les cases)
        g.setColor(Color.GRAY);
        for (int i = 0; i <= w; i++)
            this.ligne(g,
                    Config.GUI_MARGIN_SIDE + (Config.GUI_SQUARE_SIZE+1) * i,
                    Config.GUI_MARGIN_TOP,
                    Config.GUI_MARGIN_SIDE + (Config.GUI_SQUARE_SIZE+1) * i,
                    Config.GUI_MARGIN_TOP + (Config.GUI_SQUARE_SIZE+1) * h);
        for(int j = 0; j <= h; j++)
            this.ligne(g,
                    Config.GUI_MARGIN_SIDE,
                    Config.GUI_MARGIN_TOP + (Config.GUI_SQUARE_SIZE+1) * j,
                    Config.GUI_MARGIN_SIDE + (Config.GUI_SQUARE_SIZE+1) * w,
                    Config.GUI_MARGIN_TOP + (Config.GUI_SQUARE_SIZE+1) * j);
        
        // contenu des cases
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j ++)
                cases[i][j].dessiner(g);
        
        button.repaint();
        
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat du plateau.
     * 
     * @param mode Mode d'affichage.
     */
    private void afficherDebug(int mode) {
        
        // definir l'espace entre les chiffres des abscisses
        String space = "";
        switch(mode) {
            case 0:
                for (int k=0; k<Config.NB_DISP_ELTS_IN_SQUARE; k++) space += "  ";
                break;
            case 1: space = " ";    break;
            case 2: space = " ";    break;
            default: space = " ";
        }
        
        // dessiner la premiere ligne avec les chiffres des abscisses
        System.out.print("   ");
        for (int i = 0; i < w; i++)
            System.out.print(" " + (i-((i/10)*10)) + space);
        System.out.println("");
        
        // dessiner le reste du plateau
        for (int j = 0; j < h; j++) {
            // ordonnees
            System.out.print(" "+(j-((j/10)*10))+" ");
            // ligne de cases
            for (int i = 0; i < w; i++)
                System.out.print(cases[i][j].afficherDebug(mode));
            System.out.println("");
        }
        
        // laisser l'utilisateur passer au tour suivant
        if (Config.WAIT_FOR_ACTION_IN_CONSOLE) {
            System.out.println("\nAppuyer sur la touche ENTER pour continuer...");
            Reader r = new InputStreamReader(System.in);
            try {
                r.read();
            } catch (IOException e) {}
        }
        
        // ou passer au tour suivant automatiquement
        else if (!Config.WAIT_FOR_USER) {
            double t1, t0 = System.currentTimeMillis();
            do {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("Une erreur s'est produite lors de la pause entre deux tours.");
                }
                t1 = System.currentTimeMillis(); 
            } while (t1-t0 < Config.PAUSE_DURATION);
        }
    }
    
}
