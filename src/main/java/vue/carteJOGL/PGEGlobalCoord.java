package vue.carteJOGL;

import javax.media.opengl.GL2;


public class PGEGlobalCoord {

    public void draw(GL2 gl) {
        // rouge: axe X
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(1, 0, 0);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(0.5, 0, 0);
        
        // vert:  axe Y
        gl.glColor3d(0, 1, 0);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(0, 0.5, 0);
        
        // bleu:  axe Z
        gl.glColor3d(0, 0, 255);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(0, 0, 0.5);
        gl.glEnd();
    }

}
