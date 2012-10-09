package loft;

import java.io.*;
import java.util.ArrayList;
import loft.exception.LoftException;

/**
 * Classe Plateau.
 * Definit les Cases et leur contenu (Nourritures, Neuneus...).
 * 
 * @author Marlene, Gaetan
 */
public class Plateau {
    
    // unique instance de Plateau
    private static Plateau plateau = null;
    
    // metriques
    private static int w = Config.BOARD_WIDTH;
    private static int h = Config.BOARD_HEIGHT;
    
    // donnes initiales
    private int initPopulation;
    private int initNourriture;
    
    // tableaux de cases et de population
    private Case[][] cases; // Case[width][height]
    private ArrayList<Neuneu> population;
    private int nbDenrees;
    
    
    
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
        
    }
    
    
    public void initPlateau() {
        
        // construction des Cases vides
        this.cases = new Case[w][h];
        for(int i = 0; i < w; i++ )
            for(int j = 0; j < h; j++)
                cases[i][j] = new Case(i, j);
        
        // creation de la population initiale
        this.population = new ArrayList<Neuneu>();
        for (int k = 0 ; k < initPopulation; k++)
            inclureNeuneu();
        
        // creation des denrees initiales
        this.nbDenrees = initNourriture;
        for (int k = 0; k < initNourriture; k++)
            getRandCase().ajouterNourriture();
        
        if (Config.DEBUG_MODE) this.afficherPlateau(0);
        
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
                System.out.println("Erreur de type " + e.getType() + " dans le contexte " + e.getContext());
                // TODO switch sur e.getType() et e.getContext() pour gerer les erreurs elegamment
            }
        }
        
        if (Config.DEBUG_MODE) afficherPlateau(0);
        
    }
    
    
    /**
     * Exclut du Plateau le Neuneu specifie.
     * 
     * @param Neuneu Le Neuneu a exclure.
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
        
        this.population.add(c.ajouterNeuneu());
        
    }
    
    
    public void inclurePetitNeuneu(Case _case) {
        // TODO code
    }
    
    
    /**
     * Inclut une Nourriture sur une case choisie aleatoirement.
     */
    public void inclureNourriture() {
        getRandCase().ajouterNourriture();
        nbDenrees++;
    }
    
    
    /**
     * Retourne une Case aleatoirement.
     * 
     * @return Une Case choisie aleatoirement.
     */
    private Case getRandCase() {
        return cases[(int) (Math.random()*w)][(int) (Math.random()*h)];
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat du plateau.
     * 
     * @param mode Mode d'affichage.
     */
    private void afficherPlateau(int mode) {
        
        String space = "";
        switch(mode) {
            case 0: space = "  ";   break;
            case 1: space = " ";    break;
            case 2: space = " ";    break;
            default: space = " ";
        }
        
        // dessiner la premiere ligne avec les coordonnees en abscisses
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
                System.out.print(cases[i][j].afficherCase(mode));
            System.out.println("");
        }
        
        System.out.println("\nAppuyer sur la touche ENTER pour continuer...");
        Reader r = new InputStreamReader(System.in);
        try {
            r.read();
        } catch (IOException e) {}

        
    }
    
}
