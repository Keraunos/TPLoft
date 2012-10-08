package loft;

import java.util.ArrayList;
import loft.exception.LoftException;

/**
 * Classe Plateau.
 * Definit les Cases et leur contenu (Nourritures, Neuneus...).
 * 
 * @author Marlene, Gaetan
 */
public class Plateau {
    
    // metriques
    private int w = Config.BOARD_WIDTH;
    private int h = Config.BOARD_HEIGHT;
    
    // donnes initiales
    private int initPopulation;
    private int initNourriture;
    
    // tableaux de cases et de population
    private Case[][] cases; // Case[width][height]
    private ArrayList<Neuneu> population;
    
    
    
    public Plateau() {
        
        // construction des Cases vides
        this.cases = new Case[w][h];
        for(int i = 0; i < w; i++ )
            for(int j = 0; j < h; j++)
                cases[i][j] = new Case(i, j);
        
        // nombre initial de Neuneus et de Nourritures
        this.initPopulation = (int) ((float)(w*h)*Config.POPULATION_RATIO);
        this.initNourriture = (int) ((float)(w*h)*Config.FOOD_RATIO);
        
        
        // creation de la population initiale
        Case c;
        for (int k = 0 ; k < initPopulation; k++){
            c = cases[(int) (Math.random()*w)][(int) (Math.random()*h)];
            if (c.estLibre()) c.ajouterNeuneu();
        }
        
        // creation des denrees initiales
        Nourriture n;
        for (int k = 0; k < initNourriture; k++) {
            c = cases[(int) (Math.random()*w)][(int) (Math.random()*h)];
            c.ajouterNourriture();
        }
        
        if (Config.DEBUG_MODE) this.afficherPlateau();

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
    
    
    public void joueurTour() {
        
    }
    
    
    public void exclure(Neuneu neu) {
        
    }
    
    
    public void inclureNeuneu() {
        
    }
    
    
    public void inclurePetitNeuneu(Case ccase) {
        
    }
    
    
    public void inclureNourriture() {
        
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat du plateau.
     */
    public void afficherPlateau() {
        
        // dessiner la premiere ligne avec les coordonnees en abscisses
        System.out.print("   ");
        for (int i = 0; i < w; i++)
            System.out.print(" "+(i-((i/10)*10))+"  ");
        System.out.println("");
        
        // dessiner le reste du plateau
        for (int j = 0; j < h; j++) {
            // ordonnees
            System.out.print(" "+(j-((j/10)*10))+" ");
            // ligne de cases
            for (int i = 0; i < w; i++)
                System.out.print(cases[i][j].afficherCase());
            System.out.println("");
        }
    }
    
}
