package aufbauKlausur.klausurGrundgeruest;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Klausur extends JFrame{
	Canvas canvas;
	JPanel panel;
	public Klausur(){
		super("Klausur");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.canvas=new Canvas();
		this.add(this.canvas);
		this.panel = init();
		this.add(this.panel); //Einfügen an entsprechender Stelle zb BorderLayout.SOUTH
		this.setVisible(true);
	}
	
	private JPanel init(){
		JPanel panel = new JPanel();
		//hier JButtons, Label usw mit entsprechenden Listenern  hinzufügen
		return panel;
	}
	
	public static void main(String[] args) {
		new Klausur();
	}
	
	private class Canvas extends JPanel{ //eventuell implements MouseListener, MouseMotionListener oder anderer Listener
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
			//hier Methoden zum Zeichen hinzufügen
		}
	}
}

