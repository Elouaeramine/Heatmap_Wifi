// package wifi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Fenetre {

	ArrayList<AccessPoint> APs;
	ArrayList<Host> HS;
	ArrayList<Obstacle> OS;
	JFrame f;
	JToggleButton[] b = new JToggleButton[3];
	JPanel p1, p2;
	JPanel pb1, pb2, pb3, pt, pl1;
	JPanel pf;
	JLabel l1;
	JFrame f1;
	JLabel m1;
	// la zone de texte pourla puissance
	JTextField t1;
	// JTextArea ta;

	public Fenetre() {

		APs = new ArrayList<AccessPoint>();
		HS = new ArrayList<Host>();
		OS = new ArrayList<Obstacle>();
		f = new JFrame();

		b[0] = new JToggleButton("ajouter point d'acces");
		b[1] = new JToggleButton("ajouter hote");
		b[2] = new JToggleButton("ajouter obstacle");
		p1 = new JPanel();
		p2 = new JPanel();
		l1 = new JLabel("Puissance");
		t1 = new JTextField(15);
		// ta = new JTextArea(35,70);
		pb1 = new JPanel();
		pb2 = new JPanel();
		pb3 = new JPanel();
		pt = new JPanel();
	}

	public void creer() {

		class Ecouteur extends WindowAdapter {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}

		Ecouteur e = new Ecouteur();

		f.setLayout(new BorderLayout());
		p1.setLayout(new FlowLayout());
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		f.add(p1, BorderLayout.CENTER);
		f.add(p2, BorderLayout.WEST);
		f.pack();
		pb1.add(b[0]);
		pb2.add(b[1]);
		pb3.add(b[2]);
		pt.add(t1);

		p1.setBackground(Color.white);
		p2.add(pb1);
		p2.add(l1);
		p2.add(pt);
		p2.add(pb2);
		p2.add(pb3);
		f.setSize(1000, 600);
		f.setVisible(true);
		f.addWindowListener(e);

		p1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (b[0].isSelected() && t1.getText() != null) {
						Graphics g;
						Graphics2D ga;
						g = p1.getGraphics();
						ga = (Graphics2D) g;
						double x = e.getX();// r�cup�rer les coordonn�es(x,y) de la souris
						double y = e.getY();
						AccessPoint AP = new AccessPoint(x, y, t1.getText());
						
						APs.add(AP);
						double r = AP.getRc();
						//ga.drawString(String.valueOf(AccessPoint.getNum()), (int)x,(int) y);
						celluleDegrader(ga, x, y, r);
					} 
					else if (b[1].isSelected()) {
						double x = e.getX();// r�cup�rer les coordonn�es(x,y) de la souris
						double y = e.getY();
						Graphics g;
						Graphics2D ga;
						g = p1.getGraphics();
						ga = (Graphics2D) g;

						Host h = new Host(x, y);
						HS.add(h);
						h.appartient(APs);
						Color C1 = new Color(128, 98, 91, 72);
						Shape circle = new Ellipse2D.Double(x - 15, y - 15, 30, 30);
						ga.draw(circle);
						ga.setPaint(C1);
						ga.fill(circle);
						ga.drawString(String.valueOf(Host.getId()), (int)x, (int)y);
						m1 = new JLabel();
						JPanel pm1 =new JPanel();
						pm1.add(m1);
						pf.add(pm1);
						m1.setText(h.connexion()+"\n");
					

					} else if (b[2].isSelected()) {
						int x = e.getX();// r�cup�rer les coordonn�es(x,y) de la souris
						int y = e.getY();
						Graphics g;
						Graphics2D ga;
						g = p1.getGraphics();
						ga = (Graphics2D) g;
						Obstacle o = new Obstacle(x, y);
						OS.add(o);
						Color C = new Color(128, 116, 208, 241);

						Shape rectangle = new RoundRectangle2D.Float(x - 15, y - 15, 70, 30, o.getL(), o.getH());
						ga.draw(rectangle);
						ga.setPaint(C);
						ga.fill(rectangle);
						CheckAP(ga,x,y);

					}
				}
			}

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

		});
		for (JToggleButton tb : b) {
			tb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						for (JToggleButton jToggleButton : b) {
							if (!jToggleButton.equals(tb))
								jToggleButton.setSelected(false); // <-- this will change the state of toggle button
						}
					} catch (Exception e) {

					}
				}
			});
		}

	}

//////////////////////
	void celluleDegrader(Graphics2D ga, double xc, double yc, double r) {
		Random rn = new Random();
		int colorR = rn.nextInt(200);
		int colorG = rn.nextInt(200);
		int colorB = rn.nextInt(200);
		Shape circle = new Ellipse2D.Double(xc - r, yc - r, r * 2, r * 2);
		ga.draw(circle);
		for (int i = 0; i < 2 * r; i += 1)
			for (int j = 0; j < 2 * r; j += 1) {
				double x = (xc - r) + i;
				double y = (yc - r) + j;
				double f = Facteur_Attinuation(xc, yc, r, x, y);
				int G = (int) (255.0 - f * 255.0);
				if (f != 0) {
					Color C = new Color(G, (colorR+colorB)/2, (colorG+colorB)/2);
					Shape square = new Rectangle2D.Double(x, y, 1, 1);
					ga.setPaint(C);
					ga.fill(square);
				}
			}
		ga.setPaint(Color.white);
	 ga.drawString(String.valueOf(APs.size()),(int)xc,(int)yc);
	}

	double Facteur_Attinuation(double xc, double yc, double r, double x, double y) {
		//double f1 = 100 + 20 * ((double)Math.log(distance(xc, yc, x, y)));
		//double f=Math.pow(10,f1/10);
		double f= 1.0-distance(xc, yc, x, y)/((double)r);
		if (f < 0) {
			return 0;
		}
		return (f);
	}

	double distance(double x1, double y1, double x, double y) {
		return Math.sqrt((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y));
	}

	/*public String connection_hote() {
		for (Host h : HS) {
			return (h.connexion());
		}
		return null;
	}*/

	public void create_frame() {
		f1 = new JFrame("Connexions des hotes");
		pf=new JPanel();
		pf.setLayout(new BoxLayout(pf,BoxLayout.Y_AXIS));
		f1.add(pf, BorderLayout.WEST);
		f1.pack();
		//f1.pack();
		//m1.setText(connection_hote());
		f1.setSize(600, 600);
		f1.setVisible(true);
		
	}
	public void CheckAP(Graphics2D ga,int x , int y) {
		for(int i=0;i<APs.size();i++) {
			int APx=(int)APs.get(i).getX();
			int APy=(int)APs.get(i).getY();
			double distance =Math.sqrt((x-APx)*(x-APx)+(y-APy)*(y-APy));
			if(distance<=APs.get(i).getRc()) {
				Color c =new Color(255,0,0,50);
				Shape circle = new Ellipse2D.Double(APx -APs.get(i).getRc()/2 , APy - APs.get(i).getRc()/2, APs.get(i).getRc(), APs.get(i).getRc());
				ga.draw(circle);
				ga.setPaint(c);
				ga.fill(circle);
				APs.get(i).setRc(APs.get(i).getRc()/2);
			}
			
			
		}
	}

}
