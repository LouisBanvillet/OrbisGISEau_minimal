package geometrie;

public class Triangle {
	
public static int compteurTriangle = 0;
	
	protected int id;
	protected Point P1;
	protected Point P2;
	protected Point P3;
	
	public Triangle(Point p1, Point p2, Point p3) {
		this.id = compteurTriangle;
		compteurTriangle++;
		this.P1 = p1;
		this.P2 = p2;
		this.P3 = p3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point getP1() {
		return P1;
	}

	public void setP1(Point p1) {
		P1 = p1;
	}

	public Point getP2() {
		return P2;
	}

	public void setP2(Point p2) {
		P2 = p2;
	}

	public Point getP3() {
		return P3;
	}

	public void setP3(Point p3) {
		P3 = p3;
	}
	

}
