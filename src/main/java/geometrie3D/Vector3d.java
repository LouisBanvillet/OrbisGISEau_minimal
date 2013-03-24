package vue.carteJOGL.geometrie3D;

/**
 * Geometry class implementing a 3D vector.
 * @author JMB
 */
public class Vector3d {
    private double x;
    private double y;
    private double z;
    private double angle;
    
    public Vector3d() {
        this.init();
    }
    
    public Vector3d(double x,double y,double z) {
        this.x=x;
        this.y=y;
        this.z=z;
        this.angle=0;
    }
    
    public Vector3d(Vector3d vec) {
        this.x=vec.x;
        this.y=vec.y;
        this.z=vec.z;
        this.angle=vec.angle;
    }

    public Vector3d(Point3d a,Point3d b) {
        this.x=b.getX()-a.getX();
        this.y=b.getY()-a.getY();
        this.z=b.getZ()-a.getZ();
        this.angle=0;
    }

    public Vector3d(Point3d a) {
        this.x=a.getX();
        this.y=a.getY();
        this.z=a.getZ();
        this.angle=0;
    }

    public final void init() {
        this.setX(0);
        this.setY(0);
        this.setZ(0);
        this.setAngle(0);
   }
    
    public void normalize() {
        
        double norm=Math.sqrt(getX()*getX()+getY()*getY()+getZ()*getZ());
        
        if (norm>0) {
            this.setX(this.getX() / norm);
            this.setY(this.getY() / norm);
            this.setZ(this.getZ() / norm);
        }
        
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

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

   public double getComponent(int i) {
        
        if ((i>=0)&&(i<3)) {
            if (i==0) {
                return this.getX();
            } else if (i==1) {
                return this.getY();
            } else {
                return this.getZ();
            }
        } else {
            return 0;
        }
    }

    public void setComponent(int i,double val) {
        
        if (i==0) {
            this.setX(val);
        } else if (i==1) {
            this.setY(val);
        } else if (i==2) {
            this.setZ(val);
        } 
    }

    public void add(Vector3d vect) {
        this.setX(this.getX() + vect.getX());
        this.setY(this.getY() + vect.getY());
        this.setZ(this.getZ() + vect.getZ());
        this.setAngle(this.getAngle() + vect.getAngle());
    }
    
    public void add(double x,double y,double z) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        this.setZ(this.getZ() + z);
    }

    public void mult(double value) {
        this.x=this.x*value;
        this.y=this.y*value;
        this.z=this.z*value;
    }

    public static double scalarProduct(Vector3d a, Vector3d b) {

        return a.x*b.x+a.y*b.y+a.z*b.z;
    }

    public static Vector3d crossProduct(Vector3d a, Vector3d b) {

        Vector3d temp=new Vector3d();
        temp.setX(a.getY() * b.getZ() - a.getZ() * b.getY());
        temp.setY(a.getZ() * b.getX() - a.getX() * b.getZ());
        temp.setZ(a.getX() * b.getY() - a.getY() * b.getX());
        temp.setAngle(0);

        return temp;
    }
    
    public static Vector3d getCombination(Vector3d vectA,double valA,Vector3d vectB,double valB) {
        double compX=vectA.x*valA+vectB.x*valB;
        double compY=vectA.y*valA+vectB.y*valB;
        double compZ=vectA.z*valA+vectB.z*valB;
        return new Vector3d(compX, compY, compZ);
    }
    
    @Override
    public String toString() {
        return this.getX()+", "+this.getY()+", "+this.getZ()+", "+this.getAngle();
    }

    public Vector3d copy() {
        return new Vector3d(this.x, this.y, this.z);
    }

}
