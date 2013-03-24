/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.carteJOGL.geometrie3D;

import java.nio.FloatBuffer;

/**
 *
 * @author JMB
 */
public class Matrix4 {

    private static int SIZE = 4;
    private double[][] coef = new double[4][4];

    public Matrix4() {
        this.init();
    }

    public Matrix4(double m11, double m12, double m13, double m14,
            double m21, double m22, double m23, double m24,
            double m31, double m32, double m33, double m34,
            double m41, double m42, double m43, double m44) {

        this.coef[0][0] = m11;
        this.coef[0][1] = m12;
        this.coef[0][2] = m13;
        this.coef[0][3] = m14;

        this.coef[1][0] = m21;
        this.coef[1][1] = m22;
        this.coef[1][2] = m23;
        this.coef[1][3] = m24;

        this.coef[2][0] = m31;
        this.coef[2][1] = m32;
        this.coef[2][2] = m33;
        this.coef[2][3] = m34;

        this.coef[3][0] = m41;
        this.coef[3][1] = m42;
        this.coef[3][2] = m43;
        this.coef[3][3] = m44;
    }

    public Matrix4(double[][] values) {
        this.coef = values;
    }

    public Matrix4(FloatBuffer buff) {
        if (buff.array().length != (SIZE * SIZE)) {
            this.init();
        } else {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    this.coef[j][i] = buff.get(i * SIZE + j);
                }
            }
        }
    }

    public final void init() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.coef[i][j] = 0;
            }
        }
    }

    public void setIdentity() {
        this.init();

        for (int i = 0; i < SIZE; i++) {
            this.coef[i][i] = 1;
        }
    }

    public static Matrix4 getTranslationMatrix(Vector3d vect) {

        Matrix4 mat = new Matrix4();
        mat.setIdentity();

        for (int i = 0; i < 3; i++) {
            mat.coef[i][3] = vect.getComponent(i);
        }

        return mat;
    }

    public static Matrix4 getTranslationMatrix(Vector3d vect, double factor) {

        Matrix4 mat = new Matrix4();
        mat.setIdentity();

        for (int i = 0; i < 3; i++) {
            mat.coef[i][3] = vect.getComponent(i) * factor;
        }

        return mat;
    }

    public static Matrix4 getScaleMatrix(double s) {

        Matrix4 mat = new Matrix4();

        for (int i = 0; i < 3; i++) {
            mat.coef[i][i] = s;
        }

        mat.coef[3][3] = 1;

        return mat;
    }

    public double[][] getValues() {
        return this.coef;
    }

    public double getValue(int row,int column) {
        return this.coef[row][column];
    }

    public double getTrace() {
        return this.coef[0][0]+this.coef[1][1]+this.coef[2][2]+this.coef[3][3];
    }

   public Matrix4 mult(Matrix4 mult) {

        Matrix4 temp = new Matrix4();
        temp.init();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                double som = 0;

                for (int k = 0; k < SIZE; k++) {
                    som = som + this.coef[i][k] * mult.coef[k][j];
                }
                temp.coef[i][j] = som;
            }
        }

        return temp;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                str.append(this.coef[i][j]).append(" ");
            }
            str.append(" | ");
        }

        return str.toString();
    }

    public FloatBuffer toFloatBuffer() {

        FloatBuffer temp = FloatBuffer.allocate(SIZE * SIZE);

        temp.put(0, (float) this.coef[0][0]);
        temp.put(1, (float) this.coef[1][0]);
        temp.put(2, (float) this.coef[2][0]);
        temp.put(3, (float) this.coef[3][0]);

        temp.put(4, (float) this.coef[0][1]);
        temp.put(5, (float) this.coef[1][1]);
        temp.put(6, (float) this.coef[2][1]);
        temp.put(7, (float) this.coef[3][1]);

        temp.put(8, (float) this.coef[0][2]);
        temp.put(9, (float) this.coef[1][2]);
        temp.put(10, (float) this.coef[2][2]);
        temp.put(11, (float) this.coef[3][2]);

        temp.put(12, (float) this.coef[0][3]);
        temp.put(13, (float) this.coef[1][3]);
        temp.put(14, (float) this.coef[2][3]);
        temp.put(15, (float) this.coef[3][3]);

        return temp;
    }

}
