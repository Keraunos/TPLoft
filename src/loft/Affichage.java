package loft;import java.util.*;import java.awt.* ;import javax.swing.* ;import java.awt.event.* ;/** * Classe permettant le Trace de divers objets graphiques donnes sous forme  * d'une liste d'objets graphiques *  * @author Gaetan, Marlene */public class Affichage extends JFrame {        private Vector<ObjetGraphique> objetsGraphiques; // les objets a tracer        public Affichage() {        super("TP Loft");        this.objetsGraphiques = new Vector<ObjetGraphique>();        setSize(Config.GUI_WINDOW_WIDTH, Config.GUI_WINDOW_HEIGHT);    }            @Override    public void paint(Graphics g) {        super.paint(g);        Vector<ObjetGraphique> oGV = new Vector<ObjetGraphique>(objetsGraphiques);        for(ObjetGraphique oG:oGV) {            //System.out.println("----> trace de " + oG);            oG.dessiner(g);        }    }            public void ajoutObjet(ObjetGraphique o) {        objetsGraphiques.add(o);    }        public void suppressionObjet(ObjetGraphique o) {        //System.out.println("---->  suppression de " + o);        objetsGraphiques.remove(o);    }   }