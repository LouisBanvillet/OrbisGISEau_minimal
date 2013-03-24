package vue.panneaux;

import geometrie.Point;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Controleur;
import vue.AbstractVue;

public class ChoixPointEauVue extends AbstractVue {

	private static final long serialVersionUID = 1L;
	protected JComboBox<Point> choixPointComboBox;

	//Le controleur
	private Controleur controleurChoix;

	/**
	 * Création de la fenêtre
	 */
	public ChoixPointEauVue(Controleur controleur) {
		super();
		this.controleurChoix = controleur;

		this.setBorder(new EmptyBorder(50, 50, 50, 50));
		this.setLayout(new BorderLayout(0, 0));

		// Affichage de la consigne
		JPanel panelConsigne = new JPanel();
		JLabel consigne = new JLabel("Choisissez un point d'entrée :");
		panelConsigne.setBorder(new EmptyBorder(10, 0, 80, 0));
		panelConsigne.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix, 100));
		panelConsigne.add(consigne);

		//Affichage de la comboBox de choix du point
		JPanel panelChoix = new JPanel();
		choixPointComboBox = new JComboBox<Point>();
		choixPointComboBox.setRenderer(new ComboRenderer());
		choixPointComboBox.addActionListener(new ItemAction());
		panelChoix.setBorder(new EmptyBorder(10, 0, 80, 0));
		panelChoix.setPreferredSize(new Dimension (Controleur.largeurFenetreChoix, 100));
		panelChoix.add(choixPointComboBox);

		this.add(panelConsigne, BorderLayout.NORTH);
		this.add(panelChoix, BorderLayout.CENTER);
	}

	public void majFenetre(){
		choixPointComboBox.removeAllItems();
		ArrayList<Point> listePoint = controleurChoix.getListePointsExternesFromCarte();
		for(Point p : listePoint){
			choixPointComboBox.addItem(p);
		}
	}


	class ItemAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controleurChoix.setPointEau((Point) choixPointComboBox.getSelectedItem());
		}
	}

	class ComboRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		public ComboRenderer() {
			super();
		}

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			setText(affichagePoint((Point) value));
			setPreferredSize(new Dimension(240,20));

			return this;
		}
	}
	
	public String affichagePoint(Point p){
		return "Point n° " + p.getId() + " (" + p.getX() + ", " +	p.getY() + ", " + p.getZ() + ")";
	}

}