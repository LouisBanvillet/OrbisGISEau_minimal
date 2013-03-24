package main;

import geometrie.Arete;
import geometrie.Carte;
import geometrie.Point;
import geometrie.Triangle;

import java.util.ArrayList;


public class InstancesCarte {

	public static Carte carte1(){
		Point m = new Point(300,300,-100);
		Point a1 = new Point(200,400,100);
		Point a2 = new Point(200,300,0);
		Point a3 = new Point(300,200,0);
		Point a4 = new Point(400,300,0);
		ArrayList<Point> listePoint = new ArrayList<Point>();
		listePoint.add(m);
		listePoint.add(a1);
		listePoint.add(a2);
		listePoint.add(a3);
		listePoint.add(a4);
		ArrayList<Arete> listeArete = new ArrayList<Arete>();
		listeArete.add(new Arete(a1, m));
		listeArete.add(new Arete(a2, m));
		listeArete.add(new Arete(a3, m));
		listeArete.add(new Arete(a4, m));
		listeArete.add(new Arete(a1, a2));
		listeArete.add(new Arete(a2, a3));
		listeArete.add(new Arete(a3, a4));
		ArrayList<Triangle> listeTriangle = new ArrayList<Triangle>();
		listeTriangle.add(new Triangle(a1,m,a2));
		listeTriangle.add(new Triangle(a2,m,a3));
		listeTriangle.add(new Triangle(a3,m,a4));
		return new Carte(listePoint, listeArete, listeTriangle);
	}
	
	public static Carte carte2(){
		Point m = new Point(300,500,-100);
		Point a1 = new Point(200,500,-50);
		Point a2 = new Point(150,550,10);
		Point a3 = new Point(80,520,30);
		Point a4 = new Point(70,475,30);
		Point a5 = new Point(40,400,50);
		Point a6 = new Point(120,320,10);
		Point a7 = new Point(200,220,-50);
		Point a8 = new Point(280,180,-100);
		Point a9 = new Point(400,230,-80);
		Point a10 = new Point(480,300,-10);
		Point a11 = new Point(520,390,20);
		Point a12 = new Point(450,480,40);
		Point a13 = new Point(350,490,-40);
		Point a14 = new Point(250,440,30);//inverser
		Point a15 = new Point(150,475,-40);
		Point a16 = new Point(140,390,-20);//inverser
		Point a17 = new Point(120,360,50);
		Point a18 = new Point(200,320,-10);//inverser
		Point a19 = new Point(300,270,-20);
		Point a20 = new Point(400,300,10);
		Point a21 = new Point(420,390,-10);//mettre 0
		Point a22 = new Point(340,400,-10);
		Point a23 = new Point(290,360,-100);//inverser
		ArrayList<Point> listePoint = new ArrayList<Point>();
		listePoint.add(m);
		listePoint.add(a1);
		listePoint.add(a2);
		listePoint.add(a3);
		listePoint.add(a4);
		listePoint.add(a5);
		listePoint.add(a6);
		listePoint.add(a7);
		listePoint.add(a8);
		listePoint.add(a9);
		listePoint.add(a10);
		listePoint.add(a11);
		listePoint.add(a12);
		listePoint.add(a13);
		listePoint.add(a14);
		listePoint.add(a15);
		listePoint.add(a16);
		listePoint.add(a17);
		listePoint.add(a18);
		listePoint.add(a19);
		listePoint.add(a20);
		listePoint.add(a21);
		listePoint.add(a22);
		listePoint.add(a23);
		ArrayList<Arete> listeArete = new ArrayList<Arete>();
		listeArete.add(new Arete(m, a1));
		listeArete.add(new Arete(a1, a2));
		listeArete.add(new Arete(a2, a3));
		listeArete.add(new Arete(a3, a4));
		listeArete.add(new Arete(a4, a5));
		listeArete.add(new Arete(a5, a6));
		listeArete.add(new Arete(a6, a7));
		listeArete.add(new Arete(a7, a8));
		listeArete.add(new Arete(a8, a9));
		listeArete.add(new Arete(a9, a10));
		listeArete.add(new Arete(a10, a11));
		listeArete.add(new Arete(a11, a12));
		listeArete.add(new Arete(a12, a13));
		listeArete.add(new Arete(a13, m));
		listeArete.add(new Arete(a14, m));
		listeArete.add(new Arete(a14, a1));
		listeArete.add(new Arete(a14, a15));
		listeArete.add(new Arete(a14, a16));
		listeArete.add(new Arete(a14, a23));
		listeArete.add(new Arete(a14, a22));
		listeArete.add(new Arete(a14, a13));
		listeArete.add(new Arete(a15, a1));
		listeArete.add(new Arete(a15, a2));
		listeArete.add(new Arete(a15, a3));
		listeArete.add(new Arete(a15, a4));
		listeArete.add(new Arete(a15, a16));
		listeArete.add(new Arete(a16, a4));
		listeArete.add(new Arete(a16, a5));
		listeArete.add(new Arete(a16, a14));
		listeArete.add(new Arete(a16, a17));
		listeArete.add(new Arete(a16, a18));
		listeArete.add(new Arete(a16, a23));
		listeArete.add(new Arete(a17, a5));
		listeArete.add(new Arete(a17, a6));
		listeArete.add(new Arete(a17, a18));
		listeArete.add(new Arete(a18, a6));
		listeArete.add(new Arete(a18, a7));
		listeArete.add(new Arete(a18, a8));
		listeArete.add(new Arete(a18, a19));
		listeArete.add(new Arete(a18, a23));
		listeArete.add(new Arete(a19, a8));
		listeArete.add(new Arete(a19, a9));
		listeArete.add(new Arete(a19, a20));
		listeArete.add(new Arete(a19, a23));
		listeArete.add(new Arete(a20, a9));
		listeArete.add(new Arete(a20, a10));
		listeArete.add(new Arete(a20, a21));
		listeArete.add(new Arete(a20, a22));
		listeArete.add(new Arete(a20, a23));
		listeArete.add(new Arete(a21, a10));
		listeArete.add(new Arete(a21, a11));
		listeArete.add(new Arete(a21, a12));
		listeArete.add(new Arete(a21, a13));
		listeArete.add(new Arete(a21, a22));
		listeArete.add(new Arete(a22, a13));
		listeArete.add(new Arete(a22, a23));
		ArrayList<Triangle> listeTriangle = new ArrayList<Triangle>();
		listeTriangle.add(new Triangle(a1,m,a14));
		listeTriangle.add(new Triangle(a14,m,a13));
		listeTriangle.add(new Triangle(a14,a13,a22));
		listeTriangle.add(new Triangle(a14,a22,a23));
		listeTriangle.add(new Triangle(a14,a23,a16));
		listeTriangle.add(new Triangle(a14,a15,a16));
		listeTriangle.add(new Triangle(a14,a15,a1));
		listeTriangle.add(new Triangle(a1,a2,a15));
		listeTriangle.add(new Triangle(a2,a3,a15));
		listeTriangle.add(new Triangle(a3,a4,a15));
		listeTriangle.add(new Triangle(a4,a15,a16));
		listeTriangle.add(new Triangle(a4,a5,a16));
		listeTriangle.add(new Triangle(a5,a16,a17));
		listeTriangle.add(new Triangle(a5,a6,a17));
		listeTriangle.add(new Triangle(a6,a17,a18));
		listeTriangle.add(new Triangle(a16,a17,a18));
		listeTriangle.add(new Triangle(a16,a18,a23));
		listeTriangle.add(new Triangle(a6,a7,a18));
		listeTriangle.add(new Triangle(a7,a8,a18));
		listeTriangle.add(new Triangle(a8,a18,a19));
		listeTriangle.add(new Triangle(a18,a19,a23));
		listeTriangle.add(new Triangle(a8,a9,a19));
		listeTriangle.add(new Triangle(a9,a19,a20));
		listeTriangle.add(new Triangle(a19,a20,a23));
		listeTriangle.add(new Triangle(a9,a10,a20));
		listeTriangle.add(new Triangle(a20,a22,a23));
		listeTriangle.add(new Triangle(a20,a21,a22));
		listeTriangle.add(new Triangle(a10,a20,a21));
		listeTriangle.add(new Triangle(a10,a11,a21));
		listeTriangle.add(new Triangle(a11,a12,a21));
		listeTriangle.add(new Triangle(a12,a13,a21));
		listeTriangle.add(new Triangle(a13,a21,a22));
		return new Carte(listePoint, listeArete, listeTriangle);
	}

}
