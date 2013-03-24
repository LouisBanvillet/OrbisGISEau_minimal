package geometrie;

public class Point {

	public static int compteurPoint = 0;
	
	protected int id;
	protected double x;
	protected double y;
	protected double z;
	
	public Point(double x, double y, double z) {
		this.id = compteurPoint;
		compteurPoint++;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(int id, double x, double y, double z) {
		this.id = id;
		compteurPoint++;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//Rayon en coordonnees polaires par rapport a l'origine
	public double getR(){
		return (Math.sqrt(Math.pow(x,2) + Math.pow(y,2)));
	}
	
	//Angle en coordonnees polaires : le point doit etre distinct de (0,0,0)
	public double getTheta(){
		double theta = 0;
		if(x>0 && y>=0){theta = Math.atan(y/x);}
		if(x>0 && y<0){theta = Math.atan(y/x) + 2*Math.PI;}
		if(x<0){theta = Math.atan(y/x) + Math.PI;}
		if(x==0 && y>0){theta = Math.PI/2;}
		if(x==0 && y<0){theta = 3*Math.PI/2;}
		return theta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
}
