package algorithme;

import geometrie.*;

import java.util.ArrayList;


//On construit la surface en traitant le probleme de proche en proche.
public class Algorithme {
	
	/**
	 * Methode principale renvoyant la liste d'arete definissant le contour de la zone innondee.
	 * @param c
	 * @param m
	 * @param heau
	 * @return
	 */
	public static ArrayList<Arete> algorithme(Carte c, Point m, double heau){
		ArrayList<Arete> listeFinaleArete = new ArrayList<Arete>();
		ArrayList<Point> listeFinalePoint = new ArrayList<Point>();
		
		//On verifie d'abord que le point m se trouve bien en dessous du niveau de l'eau.
		if(m.getZ()>=heau){
			System.out.println("Attention : m(z) doit etre inferieur (strictement) a heau.");
			return null;
		}
		
		//On calcule l'ensemble des points a hauteur d'eau
		ArrayList<PointEau> listePointsEau = Fonctions.listePointsEau(c, heau);
		
		
		//Initialisation : on recherche le premier point p1 et la premiere arete a1
		//en commencant dans le sens horaire.
		listeFinalePoint.add(m);
		Point p = m;//point courant
		Point p1 = Fonctions.pointDepartSensHoraire(c, m);
		Arete a = Fonctions.trouverArete(c, p, p1);//Arete courante
		//Si p1 est en dessous du niveau de l'eau, il est le point suivant, sinon,
		//on recherche l'intersection.
		if(p1.getZ()<=heau){p = p1;}
		else{
			p = Fonctions.recherchePointEau(c, listePointsEau, a).get(0);
			a = new Arete(m, p);
		}
		listeFinalePoint.add(p);
		listeFinaleArete.add(a);
		//Affichage d'informations
		Fonctions.infoPoint(p);
		Fonctions.infoArete(a);
		System.out.println("Initialisation : OK.");
		
		//Premiere partie de l'algo : tant que le dernier point (m) de la liste n'est pas le meme
		//que le dernier, on continue. On obtient une surface S.
		while(p!=m){
			//Si p est immerge, c'est un sommet externe du graphe, le chemin continue sur
			//l'arete externe suivante
			if(p.getZ()<heau){
				Arete a2 = Fonctions.areteExterneSuivante(c, a, p);
				p1 = Fonctions.autrePointArete(a2, p);
				Point p2 = p;
				if(p1.getZ()<=heau){
					p=p1;
				}
				else{
					p = Fonctions.recherchePointEau(c, listePointsEau, a2).get(0);
				}
				a=Fonctions.rechercheAreteConnaissantPoints(c, p2, p);
			}
			/*S'il n'est pas immerge, on recherche l'ensemble des points d'altitude heau a
			proximite (dans les triangles contigus), on se dirige vers le point le plus a droite.
			J'ai nomme cet algo l'algorithme du labyrinthe (pour dessiner une carte de labyrinthe,
			il faut toujours tourner dans le meme sens). Cette affirmation peut paraitre rapide,
			mais elle se demontre.
			*/
			else{
				p1 = Fonctions.pointPlusADroite(c, listePointsEau, p, a, heau);
				a = new Arete(p, p1);
				p = p1;
			}
			listeFinalePoint.add(p);
			listeFinaleArete.add(a);
			
			Fonctions.infoPoint(p);
			Fonctions.infoArete(a);
		}
		
		
		//Deuxieme partie de l'algo : on recherche l'ensemble des surfaces immergees
		//a l'interieur de S.
		//On commence par chercher les sommets emerges a l'interieur de la zone precedente.
		ArrayList<Point> listePointsEmergesInternes = new ArrayList<Point>();
		for(Point pi : c.getEnsemblePoint()){
			//On exclut les sommets appartenant a listeArete, ainsi que les sommets immerges.
			if(pi.getZ() >= heau && !listeFinalePoint.contains(pi)){
				if(Fonctions2.pointAppartientZone(pi, listeFinaleArete, listeFinalePoint)){
					listePointsEmergesInternes.add(pi);
				}
			}
		}
		//Tant que la liste creee precedemment n'est pas vide, on cree des nouvelles zones (liste d'aretes)
		//autour du premier point de la liste, puis on vide la liste de tous les points contenus dans
		//cette zone (avec la methode de la droite horizontale).
		while(!listePointsEmergesInternes.isEmpty()){
			ArrayList<Point> listePointsIle = new ArrayList<Point>();
			ArrayList<Arete> listeAretesIle = new ArrayList<Arete>();
			
			//On choisit le PointEau le plus proche du sommet.
			p = Fonctions2.pointPlusProche(listePointsEmergesInternes.get(0), listePointsEau);
			listePointsIle.add(p);
			
			//On cherche l'arete de depart.
			Arete areteSommetPointEau = new Arete(listePointsEmergesInternes.get(0), p);
			p1 = Fonctions.pointPlusADroite(c, listePointsEau, p, areteSommetPointEau, heau);
			a = new Arete(p, p1);
			p = p1;
			listePointsIle.add(p);
			listeAretesIle.add(a);
			
			while(listePointsIle.get(listePointsIle.size()-1) != listePointsIle.get(0)){
				p1 = Fonctions.pointPlusADroite(c, listePointsEau, p, a, heau);
				a = new Arete(p, p1);
				p = p1;
				listePointsIle.add(p);
				listeAretesIle.add(a);
			}
			
			for(Point pi : listePointsIle){
				listeFinalePoint.add(pi);
				Fonctions.infoPoint(pi);
			}
			for(Arete ai : listeAretesIle){
				listeFinaleArete.add(ai);
				Fonctions.infoArete(ai);
			}
			
			//On vide la liste de sommets emerges en supprimant les sommets a l'interieur a la zone.
			listePointsEmergesInternes.remove(0);
			ArrayList<Integer> listeIndex = new ArrayList<Integer>();
			for(Point pi : listePointsEmergesInternes){
				if(Fonctions2.pointAppartientZone(pi, listeAretesIle, listePointsIle)){
					listeIndex.add(listePointsEmergesInternes.indexOf(pi));
				}
			}
			for(Integer i : listeIndex){
				listePointsEmergesInternes.remove((int) i);
			}
		}
		
		
		return listeFinaleArete;
	}

}
