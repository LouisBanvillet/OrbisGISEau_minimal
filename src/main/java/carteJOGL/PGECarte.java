package vue.carteJOGL;

import geometrie.*;
import javax.media.opengl.GL2;

import main.Controleur;

public class PGECarte {

	private Carte c;
	protected Controleur controleur;

	public PGECarte(Carte c, Controleur controleur){
		super();
		this.c = c;
		this.controleur = controleur;
	}

	public void draw(GL2 gl) {
		//On dessine les triangles
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glColor4d(0.5, 0.5, 0.5, 1.0);
		for(Triangle t : c.getEnsembleTriangle()){
			gl.glVertex3d((t.getP1().getX() - controleur.getMinimumXCarte())/controleur.getZoom3D() - 1.5, 
					(t.getP1().getY() - controleur.getMinimumYCarte())/controleur.getZoom3D() - 0.5,
					(t.getP1().getZ() - controleur.getMinimumZCarte())/controleur.getZoom3D() - 0.5);
			gl.glVertex3d((t.getP2().getX() - controleur.getMinimumXCarte())/controleur.getZoom3D() - 1.5, 
					(t.getP2().getY() - controleur.getMinimumYCarte())/controleur.getZoom3D() - 0.5, 
					(t.getP2().getZ() - controleur.getMinimumZCarte())/controleur.getZoom3D() - 0.5);
			gl.glVertex3d((t.getP3().getX() - controleur.getMinimumXCarte())/controleur.getZoom3D() - 1.5, 
					(t.getP3().getY() - controleur.getMinimumYCarte())/controleur.getZoom3D() - 0.5, 
					(t.getP3().getZ() - controleur.getMinimumZCarte())/controleur.getZoom3D() - 0.5);
		}
		gl.glEnd();
		
		
		//Dessine les aretes.
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3d(0.2, 0.2, 0.2);
		for(Arete a : c.getEnsembleArete()){
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
