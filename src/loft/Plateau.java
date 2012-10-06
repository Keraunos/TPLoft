package loft;

import java.util.ArrayList;

/**
 * Class Plateau.
 * Definit les Cases et leur contenu (Nourritures, Neuneus...).
 * 
 * @author Marlene, Gaetan
 */
public class Plateau {
    
    // metriques
    private int w = 20;
    private int h = 20;
    
    // donnes initiales
    private int initPopulation;
    private int initNourriture;
    
    // tableaux de cases et de population
    private Case[][] cases;
    private ArrayList<Neuneu> population;
    
    
    
    public Plateau() {

        this.initPopulation = (int) ((float)(this.w*this.h)/10f);
        this.initPopulation = (int) ((float)(this.w*this.h)/5f);

        for(int i = 0; i < w; i++ )
            for(int j = 0; j < h; j++)
                cases[i][j] = new Case(i, j);
        
        for (int k = 0 ; k<initPopulation;k++){

            int coordX = (int) Math.floor(Math.random()*w);
            int coordY = (int) Math.floor(Math.random()*h);
            
            Case c = cases[coordX][coordY];
            if (c.estLibre()) c.ajouterNeuneu();
            
        }

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
    
}
