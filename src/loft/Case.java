package loft;

import loft.exception.LoftException;
import java.util.ArrayList;

/**
 * Classe Case. Une case est un element du plateau qui peut contenir de la
 * nourriture et des Neuneus.
 * 
 * @author Marlene, Gaetan
 */
public class Case {
    
    private int x;
    private int y;
    private ArrayList<Neuneu> occupants;
    private ArrayList<Nourriture> denrees;

    public Case(int x, int y){
        this.x = x;
        this.y = y;
        this.occupants = new ArrayList<Neuneu>();
        this.denrees = new ArrayList<Nourriture>();
    }
    
    // accesseurs pour les coordonnees de la case
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    
    // accesseurs pour le contenu de la case
    public ArrayList<Neuneu> getOccupants() {
        return this.occupants;
    }
    public ArrayList<Nourriture> getDenrees() {
        return this.denrees;
    }
    
    
    /**
     * Ajoute une Nourriture generee aleatoirement sur cette Case.
     */
    public Nourriture ajouterNourriture() {
        Nourriture nourr = Nourriture.genererNourriture(this);
        this.denrees.add(nourr);
        return nourr;
    }
    
    /**
     * Ajoute un Neuneu genere aleatoirement sur cette Case.
     */
    public Neuneu ajouterNeuneu() {
        Neuneu neu = Neuneu.genererNeuneu(this);
        this.occupants.add(neu);
        return neu;
    }
    
    /**
     * Ajoute le Neuneu specifie sur cette Case.
     * 
     * @param neu Le Neuneu a ajouter sur cette Case.
     */
    public void ajouterNeuneu(Neuneu neu) {
        if (neu==null) return;
        this.occupants.add(neu);
    }
    
    
    /**
     * Enleve le Neuneu specifie de cette Case.
     * /!\ Si le Neuneu se deplace, veiller a l'ajouter a une Case adjacente /!\
     * 
     * @param neu Le Neuneu a enlever de cette Case.
     * @throws LoftException Exception levee si le Neuneu n'est pas sur cette Case.
     */
    public void enleverNeuneu(Neuneu neu) throws LoftException {
        
        if (neu==null) return;
        
        if (this.occupants.contains(neu))
            this.occupants.remove(neu);
        else throw new LoftException(
                LoftException.FailureContext.MOVING_NEUNEU,
                LoftException.FailureType.NEUNEU_NOT_ON_SQUARE);
    }
    
    
    /**
     * Teste si cette Case est vide (aucun Neuneu).
     * 
     * @return Vrai si aucun Neuneu n'est sur cette Case, faux sinon.
     */
    public boolean estLibre() {
        return occupants.isEmpty();
    }
    
    
    /**
     * Retourne la Nourriture la plus attractive sur cette Case
     * 
     * @return La Nourriture avec la plus grande valeurGustative trouvee (premiere trouvee)
     */
    public Nourriture getMeilleureDenree() {
        
        int maxValeurGustative = 0;
        Nourriture meilleureNourriture = null;
        for (Nourriture nourr:denrees) {
            if (nourr.valeurGustative > maxValeurGustative) {
                maxValeurGustative = nourr.valeurGustative;
                meilleureNourriture = nourr;
            }
        }
        
        return meilleureNourriture;
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat de la Case.
     * 
     * @param mode Mode d'affichage.
     */
    public String afficherCase(int mode) {
        
        int nbNeuneus = occupants.size();
        int nbNourritures = denrees.size();
        
        String contenu = "", contenuNeu = "", contenuNou = "";
        
        switch(mode) {
            
            // afficher un nb max de Neuneus et de Nourritures s'il y en a
            case 0:
                int k, nbElts = Config.NB_DISP_ELTS_IN_SQUARE;
                for (k = 1; k <= nbElts; k++) {
                    
                    if (k > nbNeuneus) contenuNeu += " ";
                    else {
                        if ((k == nbElts) && (nbNeuneus > nbElts))
                            contenuNeu += (nbNeuneus - nbElts + 1);
                        else
                            contenuNeu += occupants.get(k-1).afficherNeuneu(0);
                    }
                    
                    if (k > nbNourritures) contenuNou += " ";
                    else {
                        if ((k == nbElts) && (nbNourritures > nbElts))
                            contenuNou += (nbNourritures - nbElts + 1);
                        else
                            contenuNou += denrees.get(k-1).afficherNourriture(0);
                    }
                }
                contenu += contenuNeu + contenuNou;
                break;
                
            // indiquer presence de Neuneus uniquement et donner leur nombre
            case 1:
                contenu += (nbNeuneus > 0) ? nbNeuneus : " ";
                break;
                
            // indiquer presence de Nourritures uniquement et donneur leur nombre
            case 2:
                contenu += (nbNourritures > 0) ? nbNourritures : " ";
                break;
            
            default:
                contenu += "X";
        }
        
        return "[" + contenu + "]";
    }
    
}
