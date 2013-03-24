package vue.panneaux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Controleur;

import vue.AbstractVue;

/**
 * Panel qui permet de choisir une carte à traiter.
 * @author Louis
 */
public class ChargementCarteVue extends AbstractVue {

	private static final long serialVersionUID = 1L;
	protected JTextField choixCarteTextField;

	//Le controleur
	private Controleur controleurChoix;

	/**
	 * Création de la fenêtre
	 */
	public ChargementCarteVue(Controleur controleur) {
		super();
		this.controleurChoix = controleur;

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));

		// Affichage du titre
		JPanel panelTitre = new JPanel();
		JLabel titre = new JLabel("OrbisGISEau");
		titre.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 24));
		panelTitre.setBorder(new EmptyBorder(10, 0, 80, 0));
		panelTitre.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix, 100));
		panelTitre.add(titre);

		//Affichage du panel de choix de fichier
		JPanel panelChoix = new JPanel();
		choixCarteTextField = new JTextField();
		choixCarteTextField.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix-200, 20));
		JButton choixCarteButton = new JButton("Charger un fichier");
		choixCarteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				choisirFichierCarte();
			}
		});
		panelChoix.setBorder(new EmptyBorder(40, 10, 40, 10));
		panelChoix.setLayout(new BorderLayout(0, 0));
		panelChoix.add(choixCarteTextField, BorderLayout.WEST);
		panelChoix.add(choixCarteButton, BorderLayout.EAST);

		// Affichage des informations
		JPanel panelInfo = new JPanel();
		JLabel info = new JLabel("<html>Info : ce plugin génère une carte de la zone inondée à partir des entrées suivantes : <br>" +
				"une carte (compatible avec GDMS), un point d'entrée et une hauteur d'eau.<html>");
		info.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 9));
		panelInfo.setBorder(new EmptyBorder(50, 0, 10, 0));
		panelInfo.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix, 100));
		panelInfo.add(info);

		this.add(panelTitre, BorderLayout.NORTH);
		this.add(panelChoix, BorderLayout.CENTER);
		this.add(panelInfo, BorderLayout.SOUTH);
	}
	
	/**
	 * Affiche une boite de dialogue permettant de choisir un fichier.
	 */
	public void choisirFichierCarte() {
		File repertoireCourant = null;
        try {
            repertoireCourant = new File(".").getCanonicalFile();
        } catch(IOException e) {}

        JFileChooser dialogue = new JFileChooser(repertoireCourant);
         
        // affichage
        dialogue.showOpenDialog(null);
         
        // récupération du fichier sélectionné
        controleurChoix.setFichierCarte(dialogue.getSelectedFile());
        try {
			choixCarteTextField.setText(dialogue.getSelectedFile().getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}