package loft;

/**
 * Classe Neuneu. Un Neuneu est un etre vivant sur les Cases du Plateau.
 * Les Neuneus peuvent se deplacer, interagir entre eux (reproduction, parfois
 * cannibalisme) et manger des Nourritures. Quand un Neuneu n'a plus d'energie,
 * il est exclu du Plateau.
 * 
 * @author Marlene, Gaetan
 */
public abstract class Neuneu extends Comestible {
	
    private int energie;
    private int fatigueDeplacement;
    private int fatigueCoit;
    
    
    public int getEnergie(){
        return this.energie;
    }
    
    
    public void exclure(){
        
    }
    
    public void deplacer(){
        
    }
    
    public Neuneu accoupler(Neuneu neu){
        // TODO code
        return genererNeuneu();
    }

    public void manger(){
        // TODO code
    }

    /**
     * Genere un Neuneu dont le type est determine aleatoirement : Lapin, Erratique,
     * Vorace ou Cannibale.
     * 
     * @return Neuneu
     */
    public static Neuneu genererNeuneu() {
        
        int choix = (int) Math.floor(Math.random()*4) + 1;
        
        switch (choix) {
            case 1: return new Lapin();
            case 2: return new Erratique();
            case 3: return new Vorace();
            case 4: return new Cannibale();
            default: return new Erratique();
        }
    }

}
