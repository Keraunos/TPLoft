package loft;

import java.awt.Graphics;
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
     * Retourne le Comestible le plus attractif sur cette Case
     * 
     * @return Le Comestible avec la plus grande valeurGustative trouvee (premiere trouvee)
     */
    public Comestible getMeilleurComestible(Neuneu mangeur) {
        
        Comestible meilleurComestible = null;
        if (getMeilleureDenree() != null) meilleurComestible = getMeilleureDenree();
        int maxValeurGustative = 0;
        
        ArrayList<Neuneu> aManger = new ArrayList<Neuneu>(occupants);
        if (mangeur != null) aManger.remove(mangeur);
        
        for (Comestible com:aManger)
            if (com.valeurGustative > maxValeurGustative) {
                com.valeurGustative = maxValeurGustative;
                meilleurComestible = com;
            }
        
        return meilleurComestible;
    }
    
    
    /**
     * Dessine le contenu de la Case dans l'Affichage
     */
    public void dessiner(Graphics g) {
        
        int nbNeu = occupants.size();
        int nbNou = denrees.size();
        // espacement qui sert a distinguer les Comestibles presents sur la meme Case
        int pas;
        // distance d'un Comestible au bord superieur de la Case
        int ordonneeRelative;
        
        // TODO prevoir le cas ou le nb de Neuneus est tres important (ex: affichage des n premiers et du nb total)
        // dessiner les Neuneus s'il y en a
        if (nbNeu > 0) {
            if (nbNeu == 1) pas = 0;
            else pas = (int) ((float) (Config.GUI_SQUARE_SIZE - Config.GUI_NEUNEU_SIZE) / (nbNeu-1));

            for (int cNeu = 0; cNeu < nbNeu; cNeu++) {
                //System.out.println("---> case [" + i + "][" + j + "] - neuneu " + occupants.get(cCom));
                ordonneeRelative = cNeu * pas;
                try {
                    occupants.get(cNeu).setPositionRelative(ordonneeRelative);
                    occupants.get(cNeu).dessiner(g);
                } catch (IndexOutOfBoundsException ex) {
                    // ignore exception gracefully
                }
            }
        }
        
        // dessiner les Nourritures s'il y en a
        if (nbNou > 0) {
            if (nbNou == 1) pas = 0;
            else pas = (int) ((float) (Config.GUI_SQUARE_SIZE - Config.GUI_FOOD_SIZE) / (nbNou - 1));

            for (int cNou = 0; cNou < nbNou; cNou++) {
                ordonneeRelative = cNou * pas;
                try {
                    denrees.get(cNou).setPositionRelative(ordonneeRelative);
                    denrees.get(cNou).dessiner(g);
                } catch (IndexOutOfBoundsException ex) {
                    // ignore exception gracefully
                }
            }
        }
    }
    
    
    /**
     * Fonction de DEBUG. Affiche l'etat de la Case.
     * 
     * @param mode Mode d'affichage.
     */
    public String afficherDebug(int mode) {
        
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
                            contenuNeu += occupants.get(k-1).afficherDebug(0);
                    }
                    
                    if (k > nbNourritures) contenuNou += " ";
                    else {
                        if ((k == nbElts) && (nbNourritures > nbElts))
                            contenuNou += (nbNourritures - nbElts + 1);
                        else
                            contenuNou += denrees.get(k-1).afficherDebug(0);
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
