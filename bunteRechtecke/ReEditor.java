package klausurvorbereitung.bunteRechtecke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReEditor extends JFrame{
	
	Canvas canvas;
	JPanel panel;
	
	public ReEditor(){
		super("Rechtecke zeichnen");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 800);
		this.canvas=new Canvas();
		this.canvas.addMouseListener(this.canvas);
		this.canvas.addMouseMotionListener(this.canvas);
		this.add(this.canvas);
		this.panel=init();
		this.add(this.panel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private JPanel init(){
		JPanel panel = new JPanel();
		JButton neu = new JButton("Neu");
		neu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ReEditor.this.canvas.map.clear();
				ReEditor.this.canvas.current = null;
				ReEditor.this.canvas.repaint();
			}
			
		});
		panel.add(neu);
		JButton exit = new JButton("Ende");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ReEditor.this.setVisible(false);
				System.exit(0);
				dispose();
			}
			
		});
		panel.add(exit);
		return panel;
	}
	
	private class Canvas extends JPanel implements MouseListener, MouseMotionListener{
		
		Graphics2D g2;
		Rechteck current;
		Color c;
		LinkedHashMap<Color,Rechteck> map = new LinkedHashMap<>();
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			this.g2 = (Graphics2D)g;
			
			for(Map.Entry<Color, Rechteck> eintrag : map.entrySet()){
				g2.setColor(eintrag.getKey());
				g2.fillRect(eintrag.getValue().start.x, eintrag.getValue().start.y, eintrag.getValue().breite, 
						eintrag.getValue().hoehe);
			}
			if(this.current!=null){
				g2.setColor(c);
				g2.fillRect(this.current.start.x, this.current.start.y, this.current.breite,this.current.hoehe);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			current.neuBerechnen(e.getPoint());
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
		public void mousePressed(MouseEvent e) {
			this.current=new Rechteck();
			this.current.setStart(e.getPoint());
			this.c = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			this.current.neuBerechnen(e.getPoint());
			repaint();
			Rechteck r = this.current;
			this.map.put(new Color(c.getRed(),c.getGreen(),c.getBlue()), r);
			
		}
	}

	public static void main(String[] args) {
		new ReEditor();
	}

}
