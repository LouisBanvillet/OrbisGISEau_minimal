package algorithme;

import geometrie.*;

import java.util.ArrayList;


/**
 * Classe contenant toutes les fonctions necessaires a l'algorithme (2ème partie).
 * @author Louis
 *
 */
public class Fonctions2 {
	
	/**
	 * Indique si un point appartient a une zone.
	 * @param p
	 * @param listeArete
	 * @param listePoint
	 * @return
	 */
	public static boolean pointAppartientZone(Point p, ArrayList<Arete> listeArete, ArrayList<Point> listePoint){
		
		int nbContactGauche = 0;
		int nbContactDroit = 0;
		
		//On calcule le nombre de contacts a gauche et a droite du point.
		//Pour cela, on peut imaginer la droite horizontale passant par le point et compter
		//le nombre d'intersection avec la zone.
		for(Arete a : listeArete){
			//Si l'horizontale coupe l'arete en un point autre que l'extremite...
			if( (a.getP1().getY() < p.getY() && a.getP2().getY() > p.getY()) ||
					(a.getP1().getY() > p.getY() && a.getP2().getY() < p.getY()) ){
				if(areteAGauche(p, a)){
					nbContactGauche++;
				}
				else{
					nbContactDroit++;					
				}
			}
		}
		for(Point pi : listePoint){
			//Si l'horizontale coupe un sommet de la zone...
			if(p.getY() == pi.getY()){
				if(pointSommetCourbe(pi, listeArete)){
					if(pi.getX() < p.getX()){
						nbContactGauche+=2;
					}
					else{
						nbContactDroit+=2;						
					}
				}
				else{
					if(pi.getX() < p.getX()){
						nbContactGauche++;
					}
					else{
						nbContactDroit++;						
					}					
				}
			}	
		}
		
		//Si le nombre de contacts a gauche et a droite est impair, le point appartient a la zone.
		if ( (nbContactGauche % 2) == 1 && (nbContactDroit % 2) == 1){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Renvoie vrai si l'arete est a gauche du point considere.
	 * @param p
	 * @param a
	 * @return
	 */
	public static boolean areteAGauche(Point p, Arete a){
		//Si toute l'arete est a gauche...
		if(a.getP1().getX() < p.getX() && a.getP2().getX() < p.getX()){
			return true;
		}
		//Si toute l'arete est a droite...
		if(a.getP1().getX() > p.getX() && a.getP2().getX() > p.getX()){
			return false;
		}
		
		//Sinon, on a bien x1!=x2, on peut donc calculer le coefficient directeur.
		double coefDir = (a.getP2().getY() - a.getP1().getY())/(a.getP2().getX() - a.getP1().getX());
		double ordAOrigine = a.getP1().getY() - coefDir*a.getP1().getX();
		double xInter = (p.getY() - ordAOrigine)/coefDir;
		if(p.getX() > xInter){
			return true;
		}
		return false;
	}
	
	/**
	 * Renvoie vrai si le point est un pic ou un creux de la courbe (en ordonnee).
	 * @param pi
	 * @param listeArete
	 * @return
	 */
	public static boolean pointSommetCourbe(Point pi, ArrayList<Arete> listeArete){
		ArrayList<Arete> areteContenantPi = new ArrayList<Arete>();
		//On cherche les aretes de la liste contenant pi.
		for(Arete a : listeArete){
			if(a.getP1() == pi || a.getP2() == pi){
				areteContenantPi.add(a);
			}
		}
		//Si les deux autres extremites de ces deux aretes sont en dessous de pi...
		if(Fonctions.autrePointArete(areteContenantPi.get(0), pi).getY() <= pi.getY()
				&& Fonctions.autrePointArete(areteContenantPi.get(1), pi).getY() <= pi.getY()){
			return true;
		}
		//Si les deux autres extremites de ces deux aretes sont au-dessus de pi...
		if(Fonctions.autrePointArete(areteContenantPi.get(0), pi).getY() > pi.getY()
				&& Fonctions.autrePointArete(areteContenantPi.get(1), pi).getY() > pi.getY()){
			return true;
		}
		return false;
	}
	
	/**
	 * Renvoie le point le plus proche d'un point p parmi une liste de point (d'eau ici).
	 * @param p
	 * @param listePoints
	 * @return
	 */
	public static Point pointPlusProche(Point p, ArrayList<PointEau> listePoints){
		double distanceMin = Fonctions.longueurPlanArete(p, listePoints.get(0));
		Point pointProche = listePoints.get(0);
		for(Point pi : listePoints){
			double distance = Fonctions.longueurPlanArete(p, pi);
			if(distance < distanceMin){
				distanceMin = distance;
				pointProche = pi;
			}
		}
		return pointProche;
	}

}
