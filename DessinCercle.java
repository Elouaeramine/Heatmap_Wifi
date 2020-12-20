import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D; import java.util.Random;

public class DessinCercle extends JFrame implements MouseListener{
    /**
     *
     */
    private static final long serialVersionUID = 9178676565830903081L;

    public DessinCercle() {
        super("exemple : MouseListener");
        //on ajoute le MouseListener à la fenêtre
        addMouseListener(this);
        //gestion de le fermeture
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //propriété de la fenêtre
        setSize(400,400);
        setVisible(true);
    }
/** *les méthodes de MouseListener à redéfinir */

    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
    public void mouseReleased(MouseEvent e){
        //on clique sur le bouton gauche
        if(e.getButton() == MouseEvent.BUTTON1){
            /* Récupérer les coordonnées(x,y) de la souris */
            int x= e.getX();
            int y= e.getY();
            Graphics g ; Graphics2D ga;
            g= this.getGraphics();
            ga = (Graphics2D)g;
            Aff_Cercle(ga, x, y);
        }
    }
    public void Aff_Cercle(Graphics2D ga, int x, int y){        
        Random rn = new Random();
        Shape circle = new Ellipse2D.Float(x-50, y-50, 50*2, 50*2);
        ga.draw(circle);
        Color C= new Color(128, rn.nextInt(200), rn.nextInt(200),
        rn.nextInt(200));
        /*  le premier paramètre est la transparence des couleurs, les autres c'est
        le RGB (Red, Green, Blue)  */
        ga.setPaint(C);
        ga.fill(circle);    
    }
public static void main(String[] args)
{ //création de la fenêtre
new DessinCercle();
}
}