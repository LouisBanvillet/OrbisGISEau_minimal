package vue.panneaux;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Controleur;
import vue.AbstractVue;

public class ChoixHauteurEauVue  extends AbstractVue {

	private static final long serialVersionUID = 1L;
	protected JTextField choixHeauTextField;

	//Le controleur
	private Controleur controleurChoix;

	/**
	 * Création de la fenêtre
	 */
	public ChoixHauteurEauVue(Controleur controleur) {
		super();
		this.controleurChoix = controleur;

		this.setBorder(new EmptyBorder(50, 50, 50, 50));
		this.setLayout(new BorderLayout(0, 0));

		// Affichage de la consigne
		JPanel panelConsigne = new JPanel();
		JLabel consigne = new JLabel("Choisissez une hauteur d'eau :");
		panelConsigne.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelConsigne.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix, 100));
		panelConsigne.add(consigne);

		//Affichage de la comboBox de choix du point
		JPanel panelChoix = new JPanel();
		choixHeauTextField = new JTextField("0.0");
		choixHeauTextField.setHorizontalAlignment(JTextField.RIGHT);
		choixHeauTextField.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix-200, 20));
		panelChoix.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelChoix.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix, 100));
		panelChoix.add(choixHeauTextField);

		this.add(panelConsigne, BorderLayout.NORTH);
		this.add(panelChoix, BorderLayout.CENTER);
	}
	
	public void generer(){
		controleurChoix.setHeau(new Double(Double.parseDouble(choixHeauTextField.getText())));
		controleurChoix.generer();
	}
	
	public double heauSelection(){
		return new Double(Double.parseDouble(choixHeauTextField.getText()));
	}

	public void majFenetre(){

	}

}