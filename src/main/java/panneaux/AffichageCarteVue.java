package vue.panneaux;

import javax.swing.JTabbedPane;

import main.Controleur;
import vue.AbstractVue;
import vue.carte2D.PanneauCarte2D;
import vue.carteJOGL.PanneauCarteJOGL;

public class AffichageCarteVue extends AbstractVue {

	private static final long serialVersionUID = 1L;
	protected JTabbedPane tabbedPane = new JTabbedPane();

	PanneauCarte2D panel2D;
	PanneauCarteJOGL panelJOGL;

	//Le controleur
	private Controleur controleurChoix;

	/**
	 * Création de la fenêtre
	 */
	public AffichageCarteVue(Controleur controleur) {
		super();
		this.controleurChoix = controleur;

		panel2D = new PanneauCarte2D(controleurChoix);
		tabbedPane.addTab("Carte 2D", null, panel2D, "Does nothing");

		panelJOGL = new PanneauCarteJOGL(controleurChoix);
		tabbedPane.addTab("Carte OpenGL", null, panelJOGL, "Does twice as much nothing");

		this.add(tabbedPane);
	}
	
	public void majFenetre(){
		panel2D.majFenetre();
		panelJOGL.majFenetre();
	}
	
}