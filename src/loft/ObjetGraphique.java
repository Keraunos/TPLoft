package loft;import java.awt.*;import java.awt.event.*;import javax.swing.*;/** * Classe definissant un objet graphique a tracer par la classe Affichage. *  * @author Gaetan, Marlene */abstract class ObjetGraphique {            public ObjetGraphique() {        // do nothing    }            abstract public void dessinerObjet(Graphics g);            public void colorerObjet(Graphics g){        g.setColor(Color.BLACK);    }        public void ligne(Graphics g,int x1,int y1,int x2,int y2) {        g.drawLine(x1 ,y1 ,x2 ,y2 );    }        public void rectangle(Graphics g, int x, int y, int width, int height) {        g.fillRect(x + Config.LEFT_MARGIN, y + Config.TOP_MARGIN, width, height);    }        public void cercle(Graphics g, int x, int y, int diameter) {        g.fillOval(x + Config.LEFT_MARGIN, y + Config.TOP_MARGIN, diameter, diameter);    }}