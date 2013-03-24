package vue.carte2D;

import geometrie.Arete;
import geometrie.Point;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import main.Controleur;

/**
 * Panneau contenant la carte 2D.
 */
public class PanneauCarte2D extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Controleur controleurChoix;
	protected Vector<ObjetGraphique> ObjetsGraphiques; // les objets a tracer

	public PanneauCarte2D(Controleur controleur) {
		super();
		this.controleurChoix = controleur;
		ObjetsGraphiques = new Vector<ObjetGraphique>();
		setPreferredSize(new Dimension(Controleur.largeurFenetreCarte-50, Controleur.hauteurFenetreCarte-120));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for( ObjetGraphique oG : ObjetsGraphiques) {
			oG.colorerObjet(g); // chaque objet graphique a une methode colorerObjet
			oG.dessinerObjet(g); // chaque objet graphique a une methode dessinerObjet
		}
	}

	public void ajoutObjet(ObjetGraphique objet) {
		ObjetsGraphiques.add(objet);
	}

	public void majFenetre(){		
		ObjetsGraphiques.clear();
		// pour l'ensemble des aretes de la carte, on l'ajoute a la fenetre
		for(Arete a : controleurChoix.getCarte().getEnsembleArete()){
			AreteGraphique aGraphique = new AreteGraphique(a, Color.black, 1, controleurChoix);
			ajoutObjet(aGraphique);			
		}
		// Coloration des points graphiques selon leur hauteur/niveau d'eau
		for(Point p : controleurChoix.getCarte().getEnsemblePoint()){
			PointGraphique pGraphique = new PointGraphique(p, Color.black, 7, controleurChoix);
			if(p.getZ() < controleurChoix.getHeau()){
				pGraphique.setCouleur(Color.cyan);
			}
			else if(p.getZ() == controleurChoix.getHeau()){
				pGraphique.setCouleur(Color.orange);				
			}
			else{
				pGraphique.setCouleur(Color.red);
			}
			ajoutObjet(pGraphique);
		}

		//Liste des points d'intersection avec le plan d'eau
		for(Point p : controleurChoix.getListePointsEau()){
			PointGraphique pGraphique = new PointGraphique(p, Color.magenta, 4, controleurChoix);
			ajoutObjet(pGraphique);
		}

		// Trace avec rafraichissement de la zone de dessin de la zone delimitee
		for(Arete a : controleurChoix.getListeFinaleArete()){
			AreteGraphique aGraphique = new AreteGraphique(a, Color.blue, 2, controleurChoix);
			ajoutObjet(aGraphique);
		}
		repaint();
	}
}
