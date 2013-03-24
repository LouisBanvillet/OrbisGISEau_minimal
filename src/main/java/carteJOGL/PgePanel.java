package vue.carteJOGL;

import geometrie.*;
import vue.carteJOGL.geometrie3D.*;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilitiesImmutable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

import main.Controleur;

public class PgePanel extends GLJPanel implements GLEventListener {
	
	protected Controleur controleur;
    
    public PgePanel(GLCapabilitiesImmutable caps, Carte c, ArrayList<Arete> listeAretes,
    		Controleur controleur) {
        super(caps);
		this.controleur = controleur;
        this.carte = new PGECarte(c, controleur);
        this.listeAretes = new PGEListeAretes(listeAretes, controleur);
        this.initComponents();
    }

    private void initComponents() {
        this.addGLEventListener(this);

        // instanciation et connexion du MouseCameraManager
        this.mcm = new MouseCameraManager(this);
        this.addMouseListener(this.mcm);
        this.addMouseMotionListener(this.mcm);

        this.setFocusable(true);

        this.coord = new PGEGlobalCoord();
    }

    @Override
    public void init(GLAutoDrawable arg0) {

        // position de la camera: point (3,5,2)
        // point de visee: point (0,0,0)
        // vecteur "up": point (0,0,1)
        // centre de rotation de la scène: point (0,0,0)
        Point3d loc = new Point3d(3, 5,2);
        Point3d targ = new Point3d(0,0, 0);
        Vector3d up = new Vector3d(0, 0, 1);

        Point3d rotCenter=new Point3d(0, 0, 0);

        if (this.camera == null) {
            this.camera = new Camera(loc, targ, up, rotCenter);
        }

        this.mcm.updateData();
    }

    @Override
    public void display(GLAutoDrawable arg0) {

        GL2 gl = arg0.getGL().getGL2();
        GLU glu = new GLU();
        
        gl.glViewport(0, 0, this.winAW, this.winAH);
        
        // initialisation de la matrice de projection
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        
        // definition de la matrice de projection
        // vue en perspective, angle 45°, ratio largeur/hauteur, plan de clipping "near" et "far"
        glu.gluPerspective(45, (double) this.winAW / (double) this.winAH, 0.1, 100.0);
        
        // définition de la matrice MODELVIEW à partir de la matrice camera
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadMatrixf(this.camera.getCameraMatrix());

        // initialisation de la matrice modèle
        // couleur de fond: noir 
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        
        //dessine la carte
        this.carte.draw(gl);
      
        // dessine un repere global
        this.coord.draw(gl);

        //dessine la liste d'aretes
        this.listeAretes.draw(gl);
         
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        //reinitialisation des dimensions de la zone d'affichage
        this.winAW = this.getWidth();
        this.winAH = this.getHeight();
    }
    
    @Override
    public void dispose(GLAutoDrawable glad) {}

    public Camera getCamera() {
        return this.camera;
    }
    
    private int winAW;              // largeur de la zone d'affichage
    private int winAH;              // hauteur de la zone d'affichage
    private MouseCameraManager mcm; // Listener souris
    private Camera camera = null;   // caméra associée au PGEPanel
    
    private PGEGlobalCoord coord;
    private PGECarte carte;
    private PGEListeAretes listeAretes;
    
	private static final long serialVersionUID = 1L;
    
}
