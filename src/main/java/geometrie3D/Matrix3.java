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
public class Matrix3 {

    private static int SIZE=3;
    private double[][] coef = new double[3][3];

    public Matrix3() {
        this.init();
    }

    public Matrix3(double m11, double m12, double m13,
            double m21, double m22, double m23,
            double m31, double m32, double m33) {

        this.coef[0][0] = m11;
        this.coef[1][0] = m12;
        this.coef[2][0] = m13;

        this.coef[0][1] = m21;
        this.coef[1][1] = m22;
        this.coef[2][1] = m23;

        this.coef[0][2] = m31;
        this.coef[1][2] = m32;
        this.coef[2][2] = m33;

    }

    public Matrix3(double[][] values) {
        this.coef = values;
    }

    public Matrix3(Vector3d v1,Vector3d v2,Vector3d v3) {

        this.coef[0][0] = v1.getX();
        this.coef[1][0] = v1.getY();
        this.coef[2][0] = v1.getZ();

        this.coef[0][1] = v2.getX();
        this.coef[1][1] = v2.getY();
        this.coef[2][1] = v2.getZ();

        this.coef[0][2] = v3.getX();
        this.coef[1][2] = v3.getY();
        this.coef[2][2] = v3.getZ();
    }

    public Matrix3(FloatBuffer buff) {
        if (buff.array().length != (SIZE*SIZE)) {
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

    public double[][] getValues() {
        return this.coef;
    }

    public double getValue(int row,int column) {
        return this.coef[row][column];
    }

    public double getTrace() {
        return this.coef[0][0]+this.coef[1][1]+this.coef[2][2];
    }

    public void setIdentity() {
        this.init();

        for (int i = 0; i < SIZE; i++) {
            this.coef[i][i] = 1;
        }
    }

    public Matrix3 mult(Matrix3 mult) {

        Matrix3 temp = new Matrix3();
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

    public Vector3d mult(Vector3d vect) {

        Vector3d temp = new Vector3d();
        temp.init();

        for (int i = 0; i < SIZE; i++) {
                double som = 0;

                for (int k = 0; k < SIZE; k++) {
                    som = som + this.coef[i][k] * vect.getComponent(k);
                }
                temp.setComponent(i, som);
            
        }

        return temp;
    }

    @Override
    public String toString() {
        
        StringBuilder str=new StringBuilder();
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                str.append(this.coef[i][j]).append(" ");
            }
            str.append(" | ");
        }

        return str.toString();
    }
    
    public FloatBuffer toFloatBuffer() {

        FloatBuffer temp = FloatBuffer.allocate(SIZE*SIZE);

        temp.put(0, (float) this.coef[0][0]);
        temp.put(1, (float) this.coef[1][0]);
        temp.put(2, (float) this.coef[2][0]);

        temp.put(3, (float) this.coef[0][1]);
        temp.put(4, (float) this.coef[1][1]);
        temp.put(5, (float) this.coef[2][1]);

        temp.put(6, (float) this.coef[0][2]);
        temp.put(7, (float) this.coef[1][2]);
        temp.put(8, (float) this.coef[2][2]);

        return temp;
    }

}
