/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.carteJOGL.geometrie3D;

/**
 *
 * @author JMB
 * 
 * 
 */
public class Quaternion {

    private double x;
    private double y;
    private double z;
    private double w;

    public Quaternion() {
        this.init();
    }

    public Quaternion(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion(Matrix3 mat) {

        double t=mat.getTrace()+1;
        double s;

        if (t>1e-5) {
            s=1/(2*Math.sqrt(t));
            this.x=(mat.getValue(2, 1)-mat.getValue(1, 2))*s;
            this.y=(mat.getValue(0, 2)-mat.getValue(2, 0))*s;
            this.z=(mat.getValue(1, 0)-mat.getValue(0, 1))*s;
            this.w=1/(4*s);

            this.normalize();
        } else if (Math.abs(t)<=1e-5) {

            double m00=mat.getValue(0, 0);
            double m11=mat.getValue(1, 1);
            double m22=mat.getValue(2, 2);

            double m01=mat.getValue(0, 1);
            double m10=mat.getValue(1, 0);
            double m02=mat.getValue(0, 2);
            double m20=mat.getValue(2, 0);
            double m12=mat.getValue(1, 2);
            double m21=mat.getValue(2, 1);

            double max=Math.max(m00, Math.max(m11, m22));

            if (Math.abs(max-m00)<1e-5) {
                s=2*Math.sqrt(1+m00-m11-m22);
                this.x=s/4;
                this.y=(m01+m10)/s;
                this.z=(m02+m20)/s;
                this.w=0;

                this.normalize();
            } else if (Math.abs(max-m11)<1e-5) {
                s=2*Math.sqrt(1-m00+m11-m22);
                this.x=(m01+m10)/s;
                this.y=s/4;
                this.z=(m12+m21)/s;
                this.w=0;

                this.normalize();
            } else if (Math.abs(max-m22)<1e-5) {
                s=2*Math.sqrt(1-m00-m11+m22);
                this.x=(m02+m20)/s;
                this.y=(m12+m21)/s;
                this.z=s/4;
                this.w=0;

                this.normalize();
            }
        } else {
            this.init();
                System.out.println("erreur d'initialisation du quaternion!");
        }

    }

    public final void init() {
        this.x = 1;
        this.y = 1;
        this.z = 1;
        this.w = 1;
        this.normalize();
    }
    
    public final void normalize() {

        double norm = Math.sqrt(x * x + y * y + z * z+w*w);

        if (norm > 0) {
            this.x = this.x / norm;
            this.y = this.y / norm;
            this.z = this.z / norm;
            this.w = this.w / norm;
        }
    }
    
    public Quaternion getConjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion mult(Quaternion quat) {
        Quaternion result= new Quaternion(w * quat.x + x * quat.w + y * quat.z - z * quat.y,
                w * quat.y + y * quat.w + z * quat.x - x * quat.z,
                w * quat.z + z * quat.w + x * quat.y - y * quat.x,
                w * quat.w - x * quat.x - y * quat.y - z * quat.z);
        
        result.normalize();
        return result;
    }

    public Vector3d mult(Vector3d vec) {
        Vector3d vn = new Vector3d(vec);
        vn.normalize();

        Quaternion vecQuat = new Quaternion();
        Quaternion resQuat;
        vecQuat.x = vn.getX();
        vecQuat.y = vn.getY();
        vecQuat.z = vn.getZ();
        vecQuat.w = 0.0f;

        resQuat = vecQuat.getConjugate();
        resQuat = this.mult(resQuat);

        return new Vector3d(resQuat.x, resQuat.y, resQuat.z);
    }

    public void FromAxis(Vector3d v, double angle) {

        double sinAngle;
        angle *= 0.5f;
        Vector3d vn = new Vector3d(v);
        vn.normalize();

        sinAngle = Math.sin(angle);

        this.x = (vn.getX() * sinAngle);
        this.y = (vn.getY() * sinAngle);
        this.z = (vn.getZ() * sinAngle);
        this.w = Math.cos(angle);
        
        this.normalize();
    }

    public Vector3d getAxisAngle() {
        Vector3d axis = new Vector3d();
        double scale = Math.sqrt(x * x + y * y + z * z);
        axis.setX(x / scale);
        axis.setY(y / scale);
        axis.setZ(z / scale);
        axis.setAngle(Math.acos(w) * 2.0);

        return axis;
    }

    public void FromEuler(double pitch, double yaw, double roll) {

        double PIOVER180 = Math.PI / 180.0;

        double pdeg = pitch * PIOVER180 / 2.0;
        double ydeg = yaw * PIOVER180 / 2.0;
        double rdeg = roll * PIOVER180 / 2.0;

        double sinp = Math.sin(pdeg);
        double siny = Math.sin(ydeg);
        double sinr = Math.sin(rdeg);
        double cosp = Math.cos(pdeg);
        double cosy = Math.cos(ydeg);
        double cosr = Math.cos(rdeg);

        this.x = sinr * cosp * cosy - cosr * sinp * siny;
        this.y = cosr * sinp * cosy + sinr * cosp * siny;
        this.z = cosr * cosp * siny - sinr * sinp * cosy;
        this.w = cosr * cosp * cosy + sinr * sinp * siny;

        this.normalize();
    }

    public Matrix4 getMatrix() {
        double x2 = x * x;
        double y2 = y * y;
        double z2 = z * z;
        double xy = x * y;
        double xz = x * z;
        double yz = y * z;
        double wx = w * x;
        double wy = w * y;
        double wz = w * z;

        return new Matrix4(1.0f - 2.0f * (y2 + z2), 2.0f * (xy - wz), 2.0f * (xz + wy), 0.0f,
                2.0f * (xy + wz), 1.0f - 2.0f * (x2 + z2), 2.0f * (yz - wx), 0.0f,
                2.0f * (xz - wy), 2.0f * (yz + wx), 1.0f - 2.0f * (x2 + y2), 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Matrix4 getMatrixInv() {

        Quaternion temp=new Quaternion(x, y, z, -w);

        return temp.getMatrix();
    }

    public Matrix3 getRotMatrix() {
        double x2 = x * x;
        double y2 = y * y;
        double z2 = z * z;
        double xy = x * y;
        double xz = x * z;
        double yz = y * z;
        double wx = w * x;
        double wy = w * y;
        double wz = w * z;

        return new Matrix3(1.0f - 2.0f * (y2 + z2), 2.0f * (xy - wz), 2.0f * (xz + wy),
                2.0f * (xy + wz), 1.0f - 2.0f * (x2 + z2), 2.0f * (yz - wx),
                2.0f * (xz - wy), 2.0f * (yz + wx), 1.0f - 2.0f * (x2 + y2));
    }


    public Matrix3 getRotMatrixInv() {

        Quaternion quatInv=new Quaternion(x, y, z, -w);

        return quatInv.getRotMatrix();
    }

    @Override
    public String toString() {
        return this.x+" / "+this.y+" / "+this.z+" / "+this.w;
    }

}
