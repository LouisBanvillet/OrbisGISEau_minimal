package vue.carteJOGL;

import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JPanel;

import main.Controleur;

public class PanneauCarteJOGL extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Controleur controleurChoix;
	protected PgePanel pane;
	protected GLCapabilities caps;

	public PanneauCarteJOGL(Controleur controleur){
		super();
		this.controleurChoix = controleur;

		//Initialisation JOGL
		GLProfile glp = GLProfile.get(GLProfile.GL2); // profil Opengl Desktop 1.x à 3.0
		GLProfile.initSingleton();
		caps = new GLCapabilities(glp);

		setPreferredSize(new Dimension(Controleur.largeurFenetreCarte-50, Controleur.hauteurFenetreCarte-120));
	}

	public void majFenetre(){
		this.removeAll();
		pane = new PgePanel(caps, controleurChoix.getCarte(), controleurChoix.getListeFinaleArete(), controleurChoix);
		pane.setPreferredSize(new Dimension(Controleur.largeurFenetreCarte-50, Controleur.hauteurFenetreCarte-120));
		this.add(pane);
	}

}
