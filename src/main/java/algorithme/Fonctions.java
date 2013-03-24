package algorithme;

import geometrie.*;

import java.util.ArrayList;

/**
 * Classe contenant toutes les fonctions necessaires a l'algorithme.
 * @author Louis
 *
 */
public class Fonctions {

	/**
	 * Recherche l'ensemble des aretes externes.
	 * @param c
	 * @return
	 */
	public static ArrayList<Arete> aretesExternes(Carte c){
		ArrayList<Arete> listeArete = new ArrayList<Arete>();
		//pour l'ensemble des aretes, on regarde le nombre de triangle contigu
		for(int i=0; i<c.getEnsembleArete().size(); i++){			
			ArrayList<Triangle> listeTriangleContigu = new ArrayList<Triangle>();
			listeTriangleContigu = triangleContiguArete(c.getEnsembleArete().get(i), c);
			//S'il n'y a qu'un voisin, c'est une arete externe, on l'ajoute
			if(listeTriangleContigu.size()==1){listeArete.add(c.getEnsembleArete().get(i));}
		}		
		return listeArete;
	}

	/**
	 * Renvoie la liste des points a hauteur d'eau de la carte.
	 * Les sommets seront contenus autant de fois qu'il y a d'aretes contigues.
	 * Les aretes a hauteur d'eau seront representees par leur deux extremites.
	 * @param c
	 * @param heau
	 * @return
	 */
	public static ArrayList<PointEau> listePointsEau(Carte c, double heau){
		ArrayList<PointEau> listePointsEau = new ArrayList<PointEau>();
		
		for(Arete a : c.getEnsembleArete()){
			if(a.getP1().getZ() == heau){
				listePointsEau.add(new PointEau(a.getP1(), a.getId()));
			}
			if(a.getP2().getZ() == heau){
				listePointsEau.add(new PointEau(a.getP2(), a.getId()));
			}
			//Si l'un est au-dessus et l'autre en dessous
			if((a.getP1().getZ() < heau && a.getP2().getZ() > heau)
					|| (a.getP1().getZ() > heau && a.getP2().getZ() < heau)){
				listePointsEau.add(intersection(a, heau));
			}
		}
		return listePointsEau;
	}

	/**
	 * Renvoie le ou les points d'eau contenus dans une arete (1 ou 2).
	 * @param c
	 * @param listePointsEau
	 * @param a
	 * @return
	 */
	public static ArrayList<Point> recherchePointEau(Carte c, ArrayList<PointEau> listePointsEau, Arete a){
		ArrayList<Point> liste = new ArrayList<Point>();
		for(PointEau pEau : listePointsEau){
			if(pEau.getAreteId()==a.getId()){
				Point p = pointEauEstUnSommet(c, pEau);
				if(p != null){
					liste.add(p);					
				}
				else{liste.add(pEau);}
			}
		}
		return liste;
	}

	/**
	 * Renvoie le sommet equivalent au point eau s'il existe.
	 * @param c
	 * @param pEau
	 * @return
	 */
	public static Point pointEauEstUnSommet(Carte c, PointEau pEau){
		for(Point p : c.getEnsemblePoint()){
			if(p.getX()==pEau.getX() && p.getY()==pEau.getY() && p.getZ()==pEau.getZ()){
				return p;
			}
		}
		return null;
	}

	/**
	 * Determine si un point est a l'exterieur.
	 * @param c
	 * @param p
	 * @return
	 */
	public static boolean pointExterne(Carte c, Point p){
		ArrayList<Arete> listeArete = aretesExternes(c);
		for(Arete a : listeArete){
			if(p instanceof PointEau){
				if(pointAppartientArete((PointEau) p, a)){return true;}
			}
			else{
				if(pointAppartientExtremiteArete(p, a)){return true;}
			}
		}
		return false;
	}

	/**
	 * Methode renvoyant la liste des triangles contenant une arete a (resultat : un ou deux).
	 * @param a
	 * @param c
	 * @return
	 */
	public static ArrayList<Triangle> triangleContiguArete(Arete a, Carte c){
		ArrayList<Triangle> listeTriangleContigu = new ArrayList<Triangle>();
		for(int j=0; j<c.getEnsembleTriangle().size(); j++){
			if(areteAppartientTriangle(a, c.getEnsembleTriangle().get(j))){
				listeTriangleContigu.add(c.getEnsembleTriangle().get(j));
			}
		}
		return listeTriangleContigu;
	}

	/**
	 * Methode renvoyant la liste des aretes contenant un point p aux extremites.
	 * @param c
	 * @param p
	 * @return
	 */
	public static ArrayList<Arete> areteContigue(Carte c, Point p){
		ArrayList<Arete> listeAreteContigue = new ArrayList<Arete>();
		for(int j=0; j<c.getEnsembleArete().size(); j++){
			if(pointAppartientExtremiteArete(p, c.getEnsembleArete().get(j))){
				listeAreteContigue.add(c.getEnsembleArete().get(j));
			}
		}
		return listeAreteContigue;
	}

	/**
	 * Methode renvoyant la liste des triangles contenant un point p sur ses aretes.
	 * @param c
	 * @param p
	 * @return
	 */
	public static ArrayList<Triangle> trianglesContigusPoint(Carte c, Point p){
		ArrayList<Triangle> listeTrianglesContigus = new ArrayList<Triangle>();
		for(Triangle t : c.getEnsembleTriangle()){
			//Si c'est un sommet
			if(p == t.getP1() || p == t.getP2() || p== t.getP3()){
				listeTrianglesContigus.add(t);
			}
			else if(p instanceof PointEau){
				if(pointAppartientArete((PointEau) p, trouverArete(c, t.getP1(), t.getP2()))
						|| pointAppartientArete((PointEau) p, trouverArete(c, t.getP2(), t.getP3()))
						|| pointAppartientArete((PointEau) p, trouverArete(c, t.getP3(), t.getP1()))){
					listeTrianglesContigus.add(t);
				}

			}
		}
		return listeTrianglesContigus;
	}

	/**
	 * Methode permettant de savoir si une arete appartient a un triangle.
	 * @param a
	 * @param T
	 * @return
	 */
	public static boolean areteAppartientTriangle(Arete a, Triangle T){
		if(a.getP1()==T.getP1() || a.getP1()==T.getP2() || a.getP1()==T.getP3()){
			if(a.getP2()==T.getP1() || a.getP2()==T.getP2() || a.getP2()==T.getP3()){
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode permettant de savoir si un point est une extremite d'une arete.
	 * @param p
	 * @param a
	 * @return
	 */
	public static boolean pointAppartientExtremiteArete(Point p, Arete a){
		if(a.getP1()==p || a.getP2()==p){
			return true;
		}
		return false;
	}


	/**
	 * Methode permettant de savoir si un point appartient a une arete.
	 * @param p
	 * @param a
	 * @return
	 */
	public static boolean pointAppartientArete(PointEau p, Arete a){
		if(p.getAreteId()==a.getId()){return true;}
		return false;
	}


	/**
	 * Recherche l'intersection entre le plan (mer) et un segment (dont les sommets ont
	 * une altitude differente de hEau).
	 * @param a
	 * @param heau
	 * @return
	 */
	public static PointEau intersection(Arete a, double heau){
		double k = (a.getP1().getZ() - a.getP2().getZ())/(heau - a.getP2().getZ());
		double xP = (a.getP1().getX() - a.getP2().getX())/k + a.getP2().getX();
		double yP = (a.getP1().getY() - a.getP2().getY())/k + a.getP2().getY();
		return new PointEau(xP, yP, heau, a.getId());
	}


	/**
	 * Determine le point qui permet de partir dans le sens horaire a partir du point initial.
	 * @param c
	 * @param m
	 * @return
	 */
	public static Point pointDepartSensHoraire(Carte c, Point m){
		ArrayList<Arete> areteContigue = new ArrayList<Arete>();
		areteContigue = areteContigue(c, m);
		//Si le point M est sur une pointe (et n'appartient donc qu'a un triangle)
		if(areteContigue.size()==2){
			//On attribue a1 et a2
			Point a1 = autrePointArete(areteContigue.get(0), m);
			Point a2 = autrePointArete(areteContigue.get(1), m);
			//Le point suivant est le suivant dans le sens horaire
			return pointSuivantHoraire(m, a1, a2);					
		}
		//Si m appartient a plusieurs triangles, on considere une arete externe et on regarde si elle
		//se trouve dans le sens horaire (c'est la bonne) ou non (c'est l'autre arete externe)
		else{
			//On cherche d'abord les deux aretes externes
			ArrayList<Arete> aretesExternes = aretesExternes(c);
			ArrayList<Arete> aretesContiguesExternes = new ArrayList<Arete>();
			aretesContiguesExternes = intersectionListeArete(aretesExternes, areteContigue);
			Arete arete1 = aretesContiguesExternes.get(0);
			Arete arete2 = aretesContiguesExternes.get(1);
			Point a1 = autrePointArete(arete1, m);
			Point a2 = autrePointArete(arete2, m);
			//On considere a1 et le triangle (m a1 a12) qui contient arete1
			Triangle triangleContiguA1 = triangleContiguArete(arete1, c).get(0);
			Point a12 = autrePointTriangle(triangleContiguA1, m, a1);
			//Si a1 est le successeur de m dans le sens horaire, c'est le point recherche
			if(a1 == pointSuivantHoraire(m, a1, a12)){
				return a1;}
			else{return a2;}
		}
	}


	/**
	 * Renvoie, dans un triangle (m a1 a2), le point qui succede a m lors d'une rotation.
	 * dans le sens horaire
	 * @param m
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static Point pointSuivantHoraire(Point m, Point a1, Point a2){
		if(angleOrienteVecteur(m, a1, a2)<0){return a1;}
		else{return a2;}
	}

	
	/**
	 * Renvoie l'angle oriente en donnant deux vecteurs ma1 et ma2.
	 * @param m
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static double angleOrienteVecteur(Point m, Point a1, Point a2){
		double prodVect = (a1.getX()-m.getX())*(a2.getY()-m.getY())
				-(a1.getY()-m.getY())*(a2.getX()-m.getX());
		double angle = Math.asin(prodVect/(longueurPlanArete(m, a1)*longueurPlanArete(m, a2)));
		//Si le produit vectoriel est nul et le produit scalaire est negatif, m est entre a1 et a2
		if(prodVect == 0){
			double prodScalaire = (a1.getX()-m.getX())*(a2.getX()-m.getX())
					+ (a1.getY()-m.getY())*(a2.getY()-m.getY());
			if(prodScalaire < 0){angle = -Math.PI;}
			else{angle = 0;}
		}
		return angle;
	}
	

	/**
	 * Longueur d'une arete dans le plan (x, y).
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double longueurPlanArete(Point p1, Point p2){
		double longueur = (double) Math.sqrt(Math.pow((p1.getX()-p2.getX()),2)+Math.pow((p1.getY()-p2.getY()),2));
		return longueur;
	}


	/**
	 * Renvoie la liste intersection entre deux listes d'arete.
	 * @param A1
	 * @param A2
	 * @return
	 */
	public static ArrayList<Arete> intersectionListeArete(ArrayList<Arete> A1, ArrayList<Arete> A2){
		ArrayList<Arete> A = new ArrayList<Arete>();
		for(Arete a : A1){
			if(A2.contains(a)){A.add(a);}
		}
		return A;
	}

	/**
	 * Connaissant une arete et un point de cette arete, la methode renvoie l'autre point.
	 * @param a
	 * @param p1
	 * @return
	 */
	public static Point autrePointArete(Arete a, Point p1){
		if(p1 == a.getP1()){return a.getP2();}
		else{return a.getP1();}
	}

	/**
	 * Connaissant un triangle et 2 de ses points, la methode renvoie l'autre point.
	 * @param t
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static Point autrePointTriangle(Triangle t, Point p1, Point p2){
		if(p1 == t.getP1()){
			if(p2 == t.getP2()){return t.getP3();}
			if(p2 == t.getP3()){return t.getP2();}
		}
		else if(p1 == t.getP2()){
			if(p2 == t.getP1()){return t.getP3();}
			if(p2 == t.getP3()){return t.getP1();}
		}
		else if(p1 == t.getP3()){
			if(p2 == t.getP1()){return t.getP2();}
			if(p2 == t.getP2()){return t.getP1();}
		}
		return null;
	}

	/**
	 * Renvoie l'arete externe suivant une arete externe.
	 * @param c
	 * @param a
	 * @param p
	 * @return
	 */
	public static Arete areteExterneSuivante(Carte c, Arete a, Point p){
		ArrayList<Arete> areteContigueExterne = new ArrayList<Arete>();
		//On recupere la liste des deux aretes externes contigues a p
		areteContigueExterne = intersectionListeArete(areteContigue(c, p), aretesExternes(c));
		if(areteContientArete(c, areteContigueExterne.get(0), a)){return areteContigueExterne.get(1);}
		else{return areteContigueExterne.get(0);}
	}

	/**
	 * Permet de savoir si une arete a1 contient une arete a2.
	 * @param c
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static boolean areteContientArete(Carte c, Arete a1, Arete a2){
		//Si a2 est une arete du graphe initial
		if(c.getEnsemblePoint().contains(a2.getP1()) && c.getEnsemblePoint().contains(a2.getP2())){
			if(pointAppartientExtremiteArete(a2.getP1(), a1) && pointAppartientExtremiteArete(a2.getP2(), a1)){
				return true;
			}
		}
		//Sinon, si a2.p1 est un point du graphe (p2 est un PointEau)
		else if(c.getEnsemblePoint().contains(a2.getP1())){
			if(((PointEau) a2.getP2()).getAreteId() == a1.getId()){
				return true;
			}
		}
		else if(c.getEnsemblePoint().contains(a2.getP2())){
			if(((PointEau) a2.getP1()).getAreteId() == a1.getId()){
				return true;
			}
		}
		return false;
	}

	/**
	 * Renvoie le point le plus a droite lorsque l'on parcourt l'arete a en arrivant a p.
	 * @param c
	 * @param listePointsEau
	 * @param p
	 * @param a
	 * @return
	 */
	public static Point pointPlusADroite(Carte c, ArrayList<PointEau> listePointsEau, 
			Point p, Arete a, double heau){
		ArrayList<Point> listePoints = ensemblePointHauteurEauTrianglesContigus(c, listePointsEau, a, p);
		//Si la liste est vide, c'est que p est arrive sur une extremite et qu'il doit poursuivre
		//jusqu'au prochain sommet (a droite). Il existe un autre cas : a.p1 et a.p2 sont des points d'eau.
		if(listePoints.size() == 0){
			//On traite le cas rare ou l'arete est a hauteur d'eau, le point courant est a l'interieur
			//et il n'y a pas d'arete a hauteur d'eau ensuite.
			if(a.getP1().getZ() == heau && a.getP2().getZ() == heau && !pointExterne(c, p)){
				return autrePointArete(a, p);
			}
			//Si p est un sommet du graphe
			if(c.getEnsemblePoint().contains(p)){
				ArrayList<Arete> listeAretesContiguesExternes = intersectionListeArete(
						areteContigue(c, p), aretesExternes(c));
				Arete a1 = listeAretesContiguesExternes.get(0);
				Arete a2 = listeAretesContiguesExternes.get(1);
				Point p1 = autrePointArete(a1, p);
				Point p2 = autrePointArete(a2, p);
				Point p3 = autrePointArete(a, p);
				if(pointSuivantHoraire(p, p1, p3) == p1){return p1;}
				else{return p2;}
			}
			//Si p est sur une arete, on compare les deux points de l'arete contenant p
			else{
				Arete a1 = rechercheAreteContenantPoint(c, (PointEau) p);
				Point p1 = a1.getP1();
				Point p2 = a1.getP2();
				Point p3 = autrePointArete(a, (PointEau) p);
				if(pointSuivantHoraire(p, p1, p3) == p1){return p1;}
				else{return p2;}
			}
		}
		Point pAncien = autrePointArete(a, p);
		Point pointADroite = listePoints.get(0);
		//On selectionne comme premier point de comparaison le premier point de listePoints
		for(Point pi : listePoints){
			//Si [p pi] est entre a et [p pointADroite], on remplace pointADroite
			if(angleOrienteVecteur(p, pAncien, pi) > angleOrienteVecteur(p, pAncien, pointADroite)){
				pointADroite = pi;			
			}
		}
		return pointADroite;
	}
	

	/**
	 * Cette methode renvoie l'ensemble des points a hauteur d'eau dans les triangles contigus
	 * (sauf l'autre point extremite de l'arete a).
	 * @param c
	 * @param listePointsEau
	 * @param a
	 * @param p
	 * @return
	 */
	public static ArrayList<Point> ensemblePointHauteurEauTrianglesContigus(Carte c, 
			ArrayList<PointEau> listePointsEau, Arete a, Point p){
		ArrayList<Point> listePoints = new ArrayList<Point>();
		Point pAncien;
		if(a != null){pAncien = autrePointArete(a, p);}
		else{pAncien = null;}
		ArrayList<Triangle> trianglesContigus = trianglesContigusPoint(c, p);
		for(Triangle t : trianglesContigus){
			ArrayList<Point> listePointsHauteurEauTriangle = pointHauteurEauTriangle(c, listePointsEau, t);
			for(Point pi : listePointsHauteurEauTriangle){
				if(pi != pAncien && pi != p){listePoints.add(pi);}				
			}
		}
		return listePoints;
	}
	

	/**
	 * Renvoie l'ensemble des points a hauteur d'eau sur les aretes d'un triangle.
	 * Dans le cas d'une arete a hauteur d'eau, ne renvoie que les extremites.
	 * Ne doit pas renvoyer les points de l'arete precedente.
	 * @param c
	 * @param listePointsEau
	 * @param t
	 * @return
	 */
	public static ArrayList<Point> pointHauteurEauTriangle(Carte c,	ArrayList<PointEau> listePointsEau,	Triangle t){
		ArrayList<Point> listePoint = new ArrayList<Point>();
		for(Arete ai : getListeAreteDansTriangle(c, t)){
			for(Point p : recherchePointEau(c, listePointsEau, ai)){
				listePoint.add(p);
			}
		}
		return listePoint;
	}
	

	/**
	 * Renvoie la liste des aretes d'un triangle.
	 * @param c
	 * @param t
	 * @return
	 */
	public static ArrayList<Arete> getListeAreteDansTriangle(Carte c, Triangle t){
		ArrayList<Arete> listeArete = new ArrayList<Arete>();
		listeArete.add(rechercheAreteConnaissantPoints(c, t.getP1(), t.getP2()));
		listeArete.add(rechercheAreteConnaissantPoints(c, t.getP2(), t.getP3()));
		listeArete.add(rechercheAreteConnaissantPoints(c, t.getP3(), t.getP1()));
		return listeArete;
	}
	
	/**
	 * Renvoie une arete connaissant deux points.
	 * @param c
	 * @param p1
	 * @param p2
	 * @return a
	 */
	public static Arete trouverArete(Carte c, Point p1, Point p2){
		for(Arete a : c.getEnsembleArete()){
			if(pointAppartientExtremiteArete(p1, a)
					&& pointAppartientExtremiteArete(p2, a)){
				return a;
			}
		}
		return null;
	}

	
	/**
	 * Renvoie l'arete connaissant deux points.
	 * @param c
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static Arete rechercheAreteConnaissantPoints(Carte c, Point p1, Point p2){
		for(Arete a : c.getEnsembleArete()){
			if((a.getP1() == p1 && a.getP2() == p2)
					|| (a.getP1() == p2 && a.getP2() == p1)){
				return a;
			}
		}
		return new Arete(p1, p2);
	}

	/**
	 * Renvoie l'arete contenant un point p qui n'est pas une extremite.
	 * @param c
	 * @param p
	 * @return
	 */
	public static Arete rechercheAreteContenantPoint(Carte c, PointEau p){
		for(Arete a : c.getEnsembleArete()){
			if(pointAppartientArete(p, a)){
				return a;
			}
		}
		return null;
	}

	/**
	 * Donne des informations sur un point.
	 * @param p
	 */
	public static void infoPoint(Point p){
		System.out.println("Point " + p.getId() + " : (" + p.getX() + ", " + p.getY() + ", " + p.getZ() + ").");
	}
	

	/**
	 * Donne des informations sur une arete.
	 * @param a
	 */
	public static void infoArete(Arete a){
		System.out.println("Arete " + a.getId() + " : [pt" + a.getP1().getId() + " pt" + a.getP2().getId() + "].");
	}
}
