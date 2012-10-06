package loft;

/**
 * Class Comestible.
 * Decrit tout ce qui peut se manger, notamment les Neuneus et les Nourritures.
 * 
 * @author Marlene, Gaetan
 */
public abstract class Comestible {
    
    private Case _case;
    private int valeurEnerg;
    private static int initValeurEnerg;
    private static int valeurGustative;
    
    public int getValeurEnerg(){
        return this.valeurEnerg;
    }

    public Case getCase(){
        return this._case;
    }
    
    public void setCase(Case _case){
        this._case = _case;
    }
    
}
