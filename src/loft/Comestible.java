package loft;

/**
 * Classe Comestible.
 * Decrit tout ce qui peut se manger, notamment les Neuneus et les Nourritures.
 * 
 * @author Marlene, Gaetan
 */
public abstract class Comestible extends ObjetGraphique {
    
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
    
    public int getX() {
        return _case.getX();
    }
    
    public int getY() {
        return _case.getY();
    }
    
    public void setCase(Case _case){
        this._case = _case;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "_" + id;
    }
    
}
