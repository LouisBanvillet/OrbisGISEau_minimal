package vue;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

/**
 * Permet de mieux gerer les vues. A chaque fois que l'on appelle une nouvelle vue,
 * on la met a jour.
 * 
 * @author Louis
 */
public class LayoutControleur extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CardLayout gestionnaireDeVues;
    
    public LayoutControleur() {
        this.gestionnaireDeVues = new CardLayout();
        this.setLayout(this.gestionnaireDeVues);
    }
    
    public Component add(AbstractVue comp) {
        return super.add(comp);
    }
    
    public AbstractVue getCurrentCard() {
        AbstractVue card = null;

        for (Component comp : this.getComponents()) {
            if (comp.isVisible() == true) {
                card = (AbstractVue) comp;
                break;
            }
        }

        return card;
    }
        
    public void next() {
        gestionnaireDeVues.next(this);
        this.getCurrentCard().majFenetre();
    }
    
    public void previous() {
        gestionnaireDeVues.previous(this);
        this.getCurrentCard().majFenetre();
    }
}