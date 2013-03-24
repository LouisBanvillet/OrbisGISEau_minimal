package geometrie;

public class Arete {
	
	public static int compteurArete = 0;
	
	protected int id;
	protected Point P1;
	protected Point P2;
	
	public Arete(Point p1, Point p2) {
		this.id = compteurArete;
		compteurArete++;
		this.P1 = p1;
		this.P2 = p2;
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

	
}
