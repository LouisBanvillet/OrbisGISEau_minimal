package geometrie;

/**
 * Cette classe est destinee aux points a hauteur d'eau et contient
 * une information supplementaire : l'arete contenant le point.
 * @author Louis
 *
 */
public class PointEau extends Point{
	
	protected int areteId;

	public PointEau(double x, double y, double z, int areteId) {
		super(x, y, z);
		this.areteId = areteId;
	}
	
	public PointEau(Point p, int areteId) {
		super(p.getX(),p.getY(),p.getZ());
		this.areteId = areteId;
	}

	public int getAreteId() {
		return areteId;
	}

	public void setAreteId(int areteId) {
		this.areteId = areteId;
	}
	
}
