package loft;

/**
 * Classe Caniibale.
 * Un Cannibale est un Vorace qui, en plus d'etre un gros mangeur, a l'habitude
 * de consommer ses semblables (ie. les autres Neuneus).
 * 
 * @author Marlene, Gaetan
 */
public class Cannibale extends Vorace {
    
    
    /**
     * Constructeur
     */
    public Cannibale(Case _case) {
        super(_case);
    }
    
    
    @Override
    public void deplacer() {
        
    }
}
