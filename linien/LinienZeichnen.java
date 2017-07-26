package klausurvorbereitung.linien;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
public class LinienZeichnen extends JFrame{
	Canvas canvas;
	JPanel panel;
	JToggleButton toggle;
	
	public LinienZeichnen(){
		super("Klausur");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.canvas=new Canvas();
		canvas.addMouseListener(canvas);
		canvas.addMouseMotionListener(canvas);
		this.add(this.canvas);
		this.panel = init();
		this.add(this.panel,BorderLayout.SOUTH); 
		this.setVisible(true);
	}
	
	private JPanel init(){
		JPanel panel = new JPanel();
		JButton neu = new JButton("Neu");
		neu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LinienZeichnen.this.canvas.liste.removeAll(LinienZeichnen.this.canvas.liste);
				LinienZeichnen.this.canvas.lzListe.removeAll(LinienZeichnen.this.canvas.lzListe);
				LinienZeichnen.this.canvas.listenListe.removeAll(LinienZeichnen.this.canvas.listenListe);
				LinienZeichnen.this.canvas.farben.removeAll(LinienZeichnen.this.canvas.farben);
				LinienZeichnen.this.canvas.repaint();
				LinienZeichnen.this.canvas.current =null;
			}
			
		});
		JButton ende = new JButton("Ende");
		ende.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null, "Programmende", "Wollen Sie wirklich beenden?",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					LinienZeichnen.this.setVisible(false);
					System.exit(0);
					dispose();
				}
			}
			
		});
		this.toggle = new JToggleButton("Linienzug");
		panel.add(toggle);
		panel.add(neu);
		panel.add(ende);
		return panel;
	}
	
	public static void main(String[] args) {
		new LinienZeichnen();
	}
	
	private class Canvas extends JPanel implements MouseMotionListener, MouseListener{
		Linie current;
		LinkedList<Linie> liste = new LinkedList<>();
		LinkedList<Point> lzListe = new LinkedList<>();
		LinkedList<LinkedList<Point>> listenListe = new LinkedList<>();
		LinkedList<Color> farben =new LinkedList<>();
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(5));
			if(this.current != null) {
				g2.drawLine(this.current.getStart().x,this.current.getStart().y,this.current.getEnd().x,this.current.getEnd().y);
			}
			Iterator<Linie> i = liste.iterator();
			while(i.hasNext()) {
				Linie l = i.next();
				g2.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
			}
			
			for(int index = 0; index<this.lzListe.size()-1;index++) {
				g2.drawLine(lzListe.get(index).x, lzListe.get(index).y, lzListe.get(index+1).x, lzListe.get(index+1).y);
			}
			
			
			for(int index2 = 0;index2<this.listenListe.size();index2++){
				for(int index3 = 0; index3<this.listenListe.get(index2).size()-1;index3++) {
					g2.drawLine(this.listenListe.get(index2).get(index3).x, listenListe.get(index2).get(index3).y, 
							listenListe.get(index2).get(index3+1).x, listenListe.get(index2).get(index3+1).y);
					if(this.listenListe.get(index2).size()>2){
						if(this.listenListe.get(index2).getFirst().distance(this.listenListe.get(index2).getLast()) <= 5.0){
							g2.drawLine(listenListe.get(index2).getFirst().x,listenListe.get(index2).getFirst().y,
									listenListe.get(index2).getFirst().x,listenListe.get(index2).getLast().y);
							g2.setColor(this.farben.get(index2));
							int[] xKoord = new int[this.listenListe.get(index2).size()];
							int[] yKoord = new int[this.listenListe.get(index2).size()];
							for(int in =0;in<this.listenListe.get(index2).size();in++){
								xKoord[in] = this.listenListe.get(index2).get(in).x;
								yKoord[in] = this.listenListe.get(index2).get(in).y;
							}
							g2.fillPolygon(xKoord, yKoord, this.listenListe.get(index2).size());
							g2.setColor(Color.BLACK);
						}
					}
				}
			}
		}
		@Override
		public void mouseClicked(MouseEvent me) {
			if(LinienZeichnen.this.toggle.isSelected()) {
				this.lzListe.add(new Point(me.getX(),me.getY()));
			}
			if(me.getClickCount()==2){
				LinkedList<Point> rem = new LinkedList<>();
				for(int i =0; i<this.lzListe.size();i++){
					rem.add(this.lzListe.get(i));
				}
				this.listenListe.add(rem);
				this.lzListe.removeAll(this.lzListe);
				this.farben.add(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
			}
			repaint();
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent me) {
			if(!LinienZeichnen.this.toggle.isSelected()){
				this.current = new Linie();
				this.current.setStart(new Point(me.getX(),me.getY()));
			}
		}
		@Override
		public void mouseReleased(MouseEvent me) {
			if(!LinienZeichnen.this.toggle.isSelected()){
				this.current.setEnd(new Point(me.getX(), me.getY()));
				liste.add(this.current);
			}
		}
		@Override
		public void mouseDragged(MouseEvent me) {
			if(!LinienZeichnen.this.toggle.isSelected()){
				this.current.setEnd(new Point(me.getX(),me.getY()));
				repaint();
			}
		}
		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
