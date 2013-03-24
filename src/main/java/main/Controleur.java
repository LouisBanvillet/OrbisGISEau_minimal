package main;

import geometrie.Arete;
import geometrie.Carte;
import geometrie.Point;
import geometrie.PointEau;

import java.io.File;
import java.util.ArrayList;

import algorithme.Algorithme;
import algorithme.Fonctions;

import vue.FenetreControle;

/**
 * Ce controleur gere les les interractions entre les différentes données de l'application.
 * 
 * @author Louis
 */
public class Controleur {

	protected File fichierCarte;
	protected Carte carte;
	protected Point pointEau;
	protected double heau;
	protected ArrayList<PointEau> listePointsEau;
	protected ArrayList<Point> listePointsExternes;
	protected ArrayList<Arete> listeFinaleArete;

	public static int largeurFenetreChoix = 400;
	public static int hauteurFenetreChoix = 400;
	public static int largeurFenetreCarte = 700;
	public static int hauteurFenetreCarte = 700;
	protected double minimumXCarte, minimumYCarte, minimumZCarte, maximumXCarte, maximumYCarte, maximumZCarte;

	//Paramètres utilisés pour la vue 2D
	protected double zoom;
	//Paramètres utilisés pour la vue 3D
	protected double zoom3D;

	public Controleur() {
		new FenetreControle(this);
		fichierCarte = null;
		carte = null;
		pointEau = null;
		heau = 0.0;
		listePointsEau = null;
		listePointsExternes = new ArrayList<Point>();
		listeFinaleArete = null;
	}

	//Renvoie la liste de points contenue dans le fichier fichierCarte.
	public ArrayList<Point> getListePointsExternesFromCarte(){

		//Provisoire :
		//Lecture du fichier renvoyant une carte
		//carte = lectureFichier(fichierCarte)
		carte = InstancesCarte.carte2();
		for(Point p : carte.getEnsemblePoint()){
			if(Fonctions.pointExterne(carte, p)){
				listePointsExternes.add(p);
			}
		}
		return listePointsExternes;
	}

	//Génère la carte résultante de l'algorithme
	public void generer(){
		listeFinaleArete = Algorithme.algorithme(carte, pointEau, heau);
		listePointsEau = Fonctions.listePointsEau(carte, heau);
		
		minimumXCarte(); maximumXCarte(); minimumYCarte(); maximumYCarte(); minimumZCarte(); maximumZCarte();
		zoom = Math.max((maximumXCarte- minimumXCarte)/Controleur.largeurFenetreCarte, 
				(maximumYCarte - minimumYCarte)/Controleur.hauteurFenetreCarte)/0.8;
		zoom3D = Math.max((maximumXCarte- minimumXCarte)/2, 
				(maximumYCarte - minimumYCarte)/2)/0.8;
		zoom3D = Math.max(zoom3D, (maximumZCarte - minimumZCarte)/2)/2;
		
	}
	
	public void minimumXCarte(){
		double minimumX = carte.getEnsemblePoint().get(0).getX();
		for(Point p : carte.getEnsemblePoint()){
			if(p.getX() < minimumX){
				minimumX = p.getX();
			}
		}
		minimumXCarte = minimumX;
	}
	
	public void maximumXCarte(){
		double maximumX = carte.getEnsemblePoint().get(0).getX();
		for(Point p : carte.getEnsemblePoint()){
			if(p.getX() > maximumX){
				maximumX = p.getX();
			}
		}
		maximumXCarte =  maximumX;
	}
	
	public void minimumYCarte(){
		double minimumY = carte.getEnsemblePoint().get(0).getY();
		for(Point p : carte.getEnsemblePoint()){
			if(p.getX() < minimumY){
				minimumY = p.getY();
			}
		}
		minimumYCarte =  minimumY;
	}
	
	public void maximumYCarte(){
		double maximumY = carte.getEnsemblePoint().get(0).getY();
		for(Point p : carte.getEnsemblePoint()){
			if(p.getX() > maximumY){
				maximumY = p.getY();
			}
		}
		maximumYCarte = maximumY;
	}
	
	public void minimumZCarte(){
		double minimumZ = carte.getEnsemblePoint().get(0).getZ();
		for(Point p : carte.getEnsemblePoint()){
			if(p.getX() < minimumZ){
				minimumZ = p.getZ();
			}
		}
		minimumZCarte = minimumZ;
	}
	
	public void maximumZCarte(){
		double maximumZ = carte.getEnsemblePoint().get(0).getZ();
		for(Point p : carte.getEnsemblePoint()){
			if(p.getX() > maximumZ){
				maximumZ = p.getZ();
			}
		}
		maximumZCarte = maximumZ;
	}

	public File getFichierCarte() {
		return fichierCarte;
	}

	public void setFichierCarte(File fichierCarte) {
		this.fichierCarte = fichierCarte;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public Point getPointEau() {
		return pointEau;
	}

	public void setPointEau(Point pointEau) {
		this.pointEau = pointEau;
	}

	public double getHeau() {
		return heau;
	}

	public void setHeau(double heau) {
		this.heau = heau;
	}

	public ArrayList<PointEau> getListePointsEau() {
		return listePointsEau;
	}

	public void setListePointsEau(ArrayList<PointEau> listePointsEau) {
		this.listePointsEau = listePointsEau;
	}

	public ArrayList<Point> getListePointsExternes() {
		return listePointsExternes;
	}

	public void setListePointsExternes(ArrayList<Point> listePointsExternes) {
		this.listePointsExternes = listePointsExternes;
	}

	public ArrayList<Arete> getListeFinaleArete() {
		return listeFinaleArete;
	}

	public void setListeFinaleArete(ArrayList<Arete> listeFinaleArete) {
		this.listeFinaleArete = listeFinaleArete;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public double getZoom3D() {
		return zoom3D;
	}

	public void setZoom3D(double zoom3d) {
		zoom3D = zoom3d;
	}

	public double getMinimumXCarte() {
		return minimumXCarte;
	}

	public void setMinimumXCarte(double minimumXCarte) {
		this.minimumXCarte = minimumXCarte;
	}

	public double getMinimumYCarte() {
		return minimumYCarte;
	}

	public void setMinimumYCarte(double minimumYCarte) {
		this.minimumYCarte = minimumYCarte;
	}

	public double getMinimumZCarte() {
		return minimumZCarte;
	}

	public void setMinimumZCarte(double minimumZCarte) {
		this.minimumZCarte = minimumZCarte;
	}

	public double getMaximumXCarte() {
		return maximumXCarte;
	}

	public void setMaximumXCarte(double maximumXCarte) {
		this.maximumXCarte = maximumXCarte;
	}

	public double getMaximumYCarte() {
		return maximumYCarte;
	}

	public void setMaximumYCarte(double maximumYCarte) {
		this.maximumYCarte = maximumYCarte;
	}

	public double getMaximumZCarte() {
		return maximumZCarte;
	}

	public void setMaximumZCarte(double maximumZCarte) {
		this.maximumZCarte = maximumZCarte;
	}
	
}

