/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.carteJOGL;

import vue.carteJOGL.geometrie3D.*;

import java.nio.FloatBuffer;

/**
 *
 * @author JMB
 */
public class Camera {

    private Quaternion quatRot;
    private Vector3d transVect;
    private Vector3d transCDR;
    private Point3d location;
    private Point3d target;
    private Point3d rotCenter;
    private Vector3d upVector;

    public Camera(Point3d loc, Point3d targ, Vector3d up, Point3d rotCenter) {
        this.location = loc;
        this.target = targ;
        this.upVector = up;
        this.rotCenter = rotCenter;

        this.updateQuaternion();
        this.updateTranslations();
    }

    public final void updateQuaternion() {

        Vector3d y1 = this.upVector;
        Vector3d z1 = new Vector3d(this.target, this.location);
        z1.normalize();
        Vector3d x1 = Vector3d.crossProduct(y1, z1);
        x1.normalize();
        Vector3d y2 = Vector3d.crossProduct(z1, x1);

        this.quatRot = new Quaternion(new Matrix3(x1, y2, z1));
    }

    public final void updateTranslations() {
        Point3d origin = new Point3d(0, 0, 0);

        Vector3d transVectInit = new Vector3d(this.location, this.rotCenter);
        transVectInit = this.quatRot.getRotMatrix().mult(transVectInit);

        this.setTranslation(transVectInit);
        this.setTranslationToRotCenter(new Vector3d(this.rotCenter, origin));
    }

    public void setTranslation(Vector3d vect) {
        this.transVect = vect;
    }

    public void setTranslation(double x, double y, double z) {
        this.transVect = new Vector3d(x, y, z);
    }

    public void setTranslationToRotCenter(Vector3d vect) {
        this.transCDR = vect;
    }

    public void setTranslationToRotCenter(double x, double y, double z) {
        this.transCDR = new Vector3d(x, y, z);
    }

    public FloatBuffer getCameraMatrix() {

        Matrix4 matCDR = Matrix4.getTranslationMatrix(transCDR);
        Matrix4 matRot = this.quatRot.getMatrixInv();
        Matrix4 matTrans = Matrix4.getTranslationMatrix(this.transVect);

        return matTrans.mult(matRot).mult(matCDR).toFloatBuffer();
    }

    public void rotateX(double angle) {
        Quaternion rot = new Quaternion();
        rot.FromAxis(new Vector3d(1, 0, 0), angle / 180.0 * Math.PI);

        this.quatRot = this.quatRot.mult(rot);
        this.quatRot.normalize();
    }

    public void rotateY(double angle) {
        Quaternion rot = new Quaternion();
        rot.FromAxis(new Vector3d(0, 1, 0), angle * Math.PI / 180.0);

        this.quatRot = this.quatRot.mult(rot);
        this.quatRot.normalize();
    }

    public void translateX(double tx) {
        this.transVect.add(tx, 0, 0);
    }

    public void translateX() {
        this.transVect.add(0, 1, 0);
    }

    public void translateY(double ty) {
        this.transVect.add(0, ty, 0);
    }

    public void translateY() {
        this.transVect.add(0, 0, 1);
    }

    public void translateZ(double tz) {
        this.transVect.add(0, 0, tz);
    }

}
