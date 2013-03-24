package vue.carte2D;


import geometrie.Point;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import main.Controleur;

public class PointGraphique extends ObjetGraphique{

	protected int x;
	protected int y;	

	public PointGraphique(Point p, Color couleur, int epaisseur, Controleur c) {
		super();
		this.x = (int) ((p.getX() - c.getMinimumXCarte())/c.getZoom()) + 50;
		this.y = (int) ((p.getY() - c.getMinimumYCarte())/c.getZoom()) + 160;
		this.couleur = couleur;
		this.epaisseur = epaisseur;
	}

	@Override
	public void dessinerObjet(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke s = g2.getStroke();
		// trait epais
		g2.setStroke(new BasicStroke(epaisseur));
		g.drawLine(x, y, x, y); // effet d'epaisseur avec ligne nulle=point!
		// retour au trait "normal"
		g2.setStroke(s);
	}
	
	public int getX() {
		return x;
	}

	public void setX(double x) {
		this.x = (int) x;
	}

	public int getY() {
		return y;
	}

	public void setY(double y) {
		this.y = (int) y;
	}

}
