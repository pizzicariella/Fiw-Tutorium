package klausurvorbereitung.kreiseUndVerbindungen;

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
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Marienkaefer extends JFrame {
	Canvas canvas;
	JToggleButton edges;
	
	public Marienkaefer(){
		super("Klausur");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.canvas=new Canvas();
		this.canvas.addMouseListener(canvas);
		this.add(canvas, BorderLayout.CENTER);
		JPanel buttonPanel =init();
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private JPanel init(){
		JPanel panel=new JPanel();
		JButton exit=new JButton("exit");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Marienkaefer.this.setVisible(false);
				System.exit(0);
				dispose();
			}
			
		});
		panel.add(exit);
		JButton chColor = new JButton("Change Color");
		chColor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Marienkaefer.this.canvas.elipColor = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
				Marienkaefer.this.canvas.repaint();
			}
			
		});
		panel.add(chColor);
		JButton plus = new JButton("+");
		plus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Marienkaefer.this.canvas.faktor <=0.99){
					Marienkaefer.this.canvas.faktor += 0.01;
					Marienkaefer.this.canvas.repaint();
				}
			}
			
		});
		panel.add(plus);
		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Marienkaefer.this.canvas.faktor > 0.01){
					Marienkaefer.this.canvas.faktor-=0.01;
					Marienkaefer.this.canvas.repaint();
				}
			}
			
		});
		panel.add(minus);
		this.edges = new JToggleButton("Show Edges");
		edges.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(edges.isSelected()){
					edges.setText("Hide Edges");
				}
				else{
					edges.setText("Show Edges");
				}
				Marienkaefer.this.canvas.repaint();
			}
			
		});
		panel.add(edges);
		return panel;
	}
	
	private class Canvas extends JPanel implements MouseListener{
		Color elipColor = Color.RED;
		Graphics2D g2;
		LinkedList<Point> kreisListe = new LinkedList<>();
		
		double faktor =0.9;
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			this.g2 = (Graphics2D) g;
			g2.setColor(this.elipColor);
			
			int breite = (int)(this.getWidth()*this.faktor);
			int hoehe = (int)(this.getHeight()*this.faktor);
			g2.fillOval((int)(this.getWidth()*((1-faktor)/2)), (int)(this.getHeight()*((1-faktor)/2)), breite, hoehe);	
			
			g2.setColor(Color.BLACK);
			for(int i = 0; i<this.kreisListe.size();i++){
				g2.fillOval(this.kreisListe.get(i).x, this.kreisListe.get(i).y, 30, 30);
			}
			if(Marienkaefer.this.edges.isSelected()){
				g2.setStroke(new BasicStroke(3));
				for(int j = 0; j<this.kreisListe.size()-1;j++){
					g2.drawLine(this.kreisListe.get(j).x+15, this.kreisListe.get(j).y+15, this.kreisListe.get(j+1).x+15,
							this.kreisListe.get(j+1).y+15);
				}
				g2.drawLine(this.kreisListe.getFirst().x+15, this.kreisListe.getFirst().y+15,
						this.kreisListe.getLast().x+15,this.kreisListe.getLast().y+15);
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = new Point(e.getPoint());
			boolean b = false;
			for(int i =0; i<this.kreisListe.size();i++) {
				if(this.kreisListe.get(i).distance(p)<=30.0){
					this.kreisListe.remove(this.kreisListe.get(i));
					b=true;
				}
			}
			if(!b){
				this.kreisListe.add(new Point(e.getX()-15, e.getY()-15));
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
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	public static void main(String[] args) {
		new Marienkaefer();

	}

}
