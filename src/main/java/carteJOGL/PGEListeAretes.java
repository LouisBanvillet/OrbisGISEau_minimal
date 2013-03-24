package vue.carteJOGL;

import geometrie.Arete;

import java.util.ArrayList;
import javax.media.opengl.GL2;

import main.Controleur;

public class PGEListeAretes {

	private ArrayList<Arete> listeAretes;	
	protected Controleur controleur;


	public PGEListeAretes(ArrayList<Arete> listeAretes, Controleur controleur){
		super();
		this.listeAretes = listeAretes;
		this.controleur = controleur;
	}

	public void draw(GL2 gl) {
		// rouge: axe X
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3d(0, 0, 1);

		for(Arete a : listeAretes){
			gl.glVertex3d((a.getP1().getX() - controleur.getMinimumXCarte())/controleur.getZoom3D() - 1.5, 
					(a.getP1().getY() - controleur.getMinimumYCarte())/controleur.getZoom3D() - 0.5,
					(a.getP1().getZ() - controleur.getMinimumZCarte())/controleur.getZoom3D() - 0.5);
			gl.glVertex3d((a.getP2().getX() - controleur.getMinimumXCarte())/controleur.getZoom3D() - 1.5, 
					(a.getP2().getY() - controleur.getMinimumYCarte())/controleur.getZoom3D() - 0.5, 
					(a.getP2().getZ() - controleur.getMinimumZCarte())/controleur.getZoom3D() - 0.5);
		}

		gl.glEnd();
	}

}
