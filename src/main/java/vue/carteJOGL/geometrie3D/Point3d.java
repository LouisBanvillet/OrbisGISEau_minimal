package vue.carteJOGL.geometrie3D;

public class Point3d {

    public String id;
    private double x;
    private double y;
    private double z;

    public Point3d() {
        init();
    }

    public Point3d(double a, double b, double c) {
        init();
        this.x = a;
        this.y = b;
        this.z = c;
    }

    public final void init() {
        this.id = "noname";
        this.setX(0);
        this.setY(0);
        this.setZ(0);
    }

    public void add(double a, double b, double c) {
        this.setX(this.getX() + a);
        this.setY(this.getY() + b);
        this.setZ(this.getZ() + c);
    }

    public void add(Vector3d vector) {
        this.setX(this.getX() + vector.getX());
        this.setY(this.getY() + vector.getY());
        this.setZ(this.getZ() + vector.getZ());
    }

    public void ratio(double r) {
        this.setX(this.getX() / r);
        this.setY(this.getY() / r);
        this.setZ(this.getZ() / r);
    }

    @Override
    public String toString() {
        String _chaine = "x=" + this.getX() + " y=" + this.getY() + " z=" + this.getZ();
        return _chaine;
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

    public Point3d translate(Vector3d vector) {
        Point3d temp=new Point3d();
        temp.setX(this.getX() + vector.getX());
        temp.setY(this.getY() + vector.getY());
        temp.setZ(this.getZ() + vector.getZ());
        return temp;
    }

    public Point3d translate(double tx,double ty,double tz) {
        Point3d temp=new Point3d();
        temp.setX(this.getX() + tx);
        temp.setY(this.getY() + ty);
        temp.setZ(this.getZ() + tz);
        return temp;
    }
}
