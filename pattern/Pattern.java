package klausurvorbereitung.pattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class Pattern extends JFrame{
	
	JPanel buttonPanel;
	Canvas canvas;
	JLabel label;
	JButton pattern;
	JToggleButton tr;
	
	public Pattern(){
		super("Klausur");
		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.canvas= new Canvas();
		this.canvas.addMouseListener(canvas);
		this.add(this.canvas);
		this.buttonPanel = init();
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private JPanel init(){
		JPanel panel = new JPanel(); 
		JPanel norden = new JPanel();
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Pattern.this.setVisible(false);
				System.exit(0);
				dispose();
			}
			
		});
		norden.add(exit);
		JButton chColor = new JButton("Change Color");
		chColor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Pattern.this.canvas.c = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
				Pattern.this.canvas.repaint();
			}
			
		});
		norden.add(chColor);
		this.pattern = new JButton("Show Pattern");
		pattern.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				if(b.getText().equals("Show Pattern")){
					b.setText("Hide Pattern");
				}
				else{
					b.setText("Show Pattern");
				}
				Pattern.this.canvas.repaint();
			}
			
		});
		norden.add(pattern);
		this.tr = new JToggleButton("Try");
		tr.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton b = (JToggleButton)e.getSource();
				if(b.isSelected()){
					Pattern.this.canvas.start=true;
				}
				else{
					Pattern.this.canvas.start=false;
					Pattern.this.canvas.p.set.clear();
				}
				Pattern.this.canvas.repaint();
				Pattern.this.canvas.p.set.add(Pattern.this.canvas.zweiNull);
				Pattern.this.canvas.p.set.add(Pattern.this.canvas.nullDrei);
				Pattern.this.canvas.p.set.add(Pattern.this.canvas.dreiEins);
			}
			
		});
		norden.add(tr);
		JPanel sueden = new JPanel();
		this.label=new JLabel("Quadrant: ");
		this.label.setFont(new Font("Verdana", Font.PLAIN,22));
		panel.setLayout(new BorderLayout());
		panel.add(norden, BorderLayout.NORTH);
		sueden.add(this.label);
		panel.add(sueden, BorderLayout.SOUTH);
		return panel;
	}
	
	private class Canvas extends JPanel implements MouseListener{
		Graphics2D g2;
		int KREISDM = 30;
		Color c = Color.GRAY;
		int qBreite;
		int qHoehe;
		Pattern2 p = new Pattern2();
		boolean start;
		int click1;
		int click2;
		MyLine zweiNull = new MyLine(2,0);
		MyLine nullDrei = new MyLine(0,3);
		MyLine dreiEins = new MyLine(3,1);
		public Canvas(){
			

			this.p.addLine(zweiNull);
			this.p.addLine(nullDrei);
			this.p.addLine(dreiEins);
			
		}
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			this.g2 = (Graphics2D)g;
			this.qBreite =this.getWidth()/4;
			this.qHoehe = this.getHeight()/4;
			if(Pattern.this.tr.isSelected()){
				this.p.set.remove(zweiNull);
				this.p.set.remove(nullDrei);
				this.p.set.remove(dreiEins);
			}
			
			
			
			if(Pattern.this.pattern.getText().equals("Hide Pattern") || Pattern.this.tr.isSelected()){
				this.KREISDM = 50;
				this.c = Color.RED;
				patternZeichnen();
			}
			else{
				
				this.KREISDM =30;
			}
			kreisZeichnen(new Point(qBreite, qHoehe));
			kreisZeichnen( new Point(qBreite*3, qHoehe));
			kreisZeichnen(new Point(qBreite, qHoehe*3));
			kreisZeichnen(new Point(qBreite*3, qHoehe*3));
		}
		
		private void kreisZeichnen(Point p){
			g2.setColor(c);
			g2.fillOval(p.x-this.KREISDM/2, p.y-this.KREISDM/2, this.KREISDM, this.KREISDM);
		}
		
		private void patternZeichnen(){
			Iterator<MyLine> i = this.p.set.iterator();
			while(i.hasNext()){
				MyLine ml = i.next();
				int start = ml.getStart();
				int end = ml.getEnd();
				Point sP;
				Point eP;
				if(start == 0){
					sP = new Point(qBreite, qHoehe);
				}
				else if(start == 1){
					sP = new Point(qBreite*3, qHoehe);
				}
				else if(start ==2){
					sP=new Point(qBreite,qHoehe*3);
				}
				else{
					sP = new Point(qBreite*3,qHoehe*3);
				}
				if(end == 0){
					eP = new Point(qBreite, qHoehe);
				}
				else if(end == 1){
					eP = new Point(qBreite*3, qHoehe);
				}
				else if(end ==2){
					eP=new Point(qBreite,qHoehe*3);
				}
				else{
					eP = new Point(qBreite*3,qHoehe*3);
				}
				g2.setColor(Color.RED);
				g2.drawLine(sP.x, sP.y, eP.x, eP.y);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			Point click = e.getPoint();
			if(click.x < qBreite*2 && click.y < qHoehe*2){
				Pattern.this.label.setText("Quadrant: 0");
				if(Pattern.this.tr.isSelected()){
					if(start){
						start = false;
						click1 = 0;
					}
					else{
						start = true;
						click2 =0;
					}
				}
			}
			else if(click.x >= qBreite*2 && click.y < qHoehe*2) {
				Pattern.this.label.setText("Quadrant: 1");
				if(Pattern.this.tr.isSelected()){
					if(start){
						start = false;
						click1 = 1;
					}
					else{
						start = true;
						click2 =1;
					}
				}
			}
			else if(click.x < qBreite*2 && click.y >= qHoehe*2) {
				Pattern.this.label.setText("Quadrant: 2");
				if(Pattern.this.tr.isSelected()){
					if(start){
						start = false;
						click1 = 2;
					}
					else{
						start = true;
						click2 =2;
					}
				}
			}
			else{
				Pattern.this.label.setText("Quadrant: 3");
				if(Pattern.this.tr.isSelected()){
					if(start){
						start = false;
						click1 = 3;
					}
					else{
						start = true;
						click2 =3;
						
					}
				}
			}
			
			if(Pattern.this.tr.isSelected()){
				if(start){
					MyLine ml = new MyLine(click1,click2);
					this.p.set.add(ml);
					click1 = click2;
					this.start = false;
					repaint();
				}
			}
			
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
		new Pattern();
		
	}
}
