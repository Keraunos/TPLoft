package loft;

/**
 * Classe Comestible.
 * Decrit tout ce qui peut se manger, notamment les Neuneus et les Nourritures.
 * 
 * @author Marlene, Gaetan
 */
public abstract class Comestible {
    
    protected Case _case;
    protected int valeurEnerg;
    protected int id = 0;
    protected int valeurGustative;
    
    
    public int getValeurEnerg(){
        return this.valeurEnerg;
    }

    public Case getCase(){
        return this._case;
    }
    
    public void setCase(Case _case){
        this._case = _case;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "_" + id;
    }
    
}
