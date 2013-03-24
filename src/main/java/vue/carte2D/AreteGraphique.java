package vue.carte2D;


import geometrie.Arete;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import main.Controleur;


public class AreteGraphique extends ObjetGraphique{

	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	
	public AreteGraphique(Arete a, Color couleur, int epaisseur, Controleur c) {
		super();
		this.x1 = (int) ((a.getP1().getX() - c.getMinimumXCarte())/c.getZoom()) + 50;
		this.y1 = (int) ((a.getP1().getY() - c.getMinimumYCarte())/c.getZoom()) + 160;
		this.x2 = (int) ((a.getP2().getX() - c.getMinimumXCarte())/c.getZoom()) + 50;
		this.y2 = (int) ((a.getP2().getY() - c.getMinimumYCarte())/c.getZoom()) + 160;
		this.couleur = couleur;
		this.epaisseur = epaisseur;
	}

	@Override
	public void dessinerObjet(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke s = g2.getStroke();
		// trait epais
		g2.setStroke(new BasicStroke(epaisseur));
		g.drawLine(x1, y1, x2, y2);
		// retour au trait "normal" yes
		g2.setStroke(s);	
	}

	public int getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = (int) x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = (int) y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = (int) x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = (int) y2;
	}
}
