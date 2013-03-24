package vue.carte2D;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ObjetGraphique {
	
	protected Color couleur;
	protected int epaisseur;

	public ObjetGraphique() {
		// do nothing
	}

	abstract public void dessinerObjet(Graphics g);
	
	public void colorerObjet(Graphics g){
		g.setColor(couleur);
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

}
