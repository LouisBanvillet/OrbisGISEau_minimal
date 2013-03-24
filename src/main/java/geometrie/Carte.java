package geometrie;

import java.util.ArrayList;

public class Carte {
	
	protected ArrayList<Point> ensemblePoint;
	protected ArrayList<Arete> ensembleArete;
	protected ArrayList<Triangle> ensembleTriangle;
	
	public Carte(ArrayList<Point> ensemblePoint, ArrayList<Arete> ensembleArete, 
			ArrayList<Triangle> ensembleTriangle) {
		this.ensemblePoint = ensemblePoint;
		this.ensembleArete = ensembleArete;
		this.ensembleTriangle = ensembleTriangle;
	}
	
	//retourne la liste des triangles connaissant la liste des aretes
	public ArrayList<Triangle> listeTriangle(){
		return null;
	}

	public ArrayList<Point> getEnsemblePoint() {
		return ensemblePoint;
	}

	public void setEnsemblePoint(ArrayList<Point> ensemblePoint) {
		this.ensemblePoint = ensemblePoint;
	}

	public ArrayList<Arete> getEnsembleArete() {
		return ensembleArete;
	}

	public void setEnsembleArete(ArrayList<Arete> ensembleArete) {
		this.ensembleArete = ensembleArete;
	}

	public ArrayList<Triangle> getEnsembleTriangle() {
		return ensembleTriangle;
	}

	public void setEnsembleTriangle(ArrayList<Triangle> ensembleTriangle) {
		this.ensembleTriangle = ensembleTriangle;
	}

}
