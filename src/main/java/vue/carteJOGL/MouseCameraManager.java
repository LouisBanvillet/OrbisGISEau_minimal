package vue.carteJOGL;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author JMB
 */
public class MouseCameraManager implements MouseListener, MouseMotionListener {

    private PgePanel panel;
    private Camera camera;
    private double width;
    private double height;
    
    private double xOld, yOld;
    private double xNew, yNew;
    
    private int mode;
    
    static int DRAG=1;
    static int ROTATE=2;
    static int ZOOM=3;
    
    public MouseCameraManager(PgePanel pan) {
        this.panel=pan;
        this.updateData();
    }

    public final void updateData() {
        this.camera=panel.getCamera();
        this.width=panel.getWidth();
        this.height=panel.getHeight();
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Non utilisé actuellement
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Non utilisé actuellement
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Non utilisé actuellement
    }

    @Override
    public void mousePressed(MouseEvent e) {

        this.panel.requestFocus();
        
        xOld=e.getX();
        yOld=e.getY();
        
        int button=e.getButton();
        
        if (button==MouseEvent.BUTTON1) {
            
            this.mode=MouseCameraManager.ROTATE;
            
        } else if (button==MouseEvent.BUTTON2) {
            
            this.mode=MouseCameraManager.ZOOM;
            
        } else if (button==MouseEvent.BUTTON3) {
            
            this.mode=MouseCameraManager.DRAG;
            
        } 
     }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Non utilisé actuellement
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        xNew=e.getX();
        yNew=e.getY();
        
        if (this.mode==MouseCameraManager.DRAG) {
            
            this.camera.translateX((xNew-xOld)/this.width*10);
            this.camera.translateY(-(yNew-yOld)/this.height*10);
            
        } else if (this.mode==MouseCameraManager.ROTATE) {
            
            this.camera.rotateY(-(xNew-xOld)/this.width*360);
            this.camera.rotateX(-(yNew-yOld)/this.height*360);
            
        } else if (this.mode==MouseCameraManager.ZOOM) {
            
            double deltaX=(xNew-xOld)/this.width*10;
            double deltaY=(yNew-yOld)/this.height*10;
            
            if (Math.abs(deltaX)>Math.abs(deltaY)) {
                this.camera.translateZ(deltaX);
            } else {
                this.camera.translateZ(deltaY);
            }
        } 
        
        this.panel.display();
        
        xOld=xNew;
        yOld=yNew;
        
   }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Non utilisé actuellement
    }

}
