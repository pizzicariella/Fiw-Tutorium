package aufgaben.aufgabe11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import aufgaben.aufgabe10.Model;
import aufgaben.aufgabe10.State;

public class View extends JFrame implements ActionListener{

	Model model;
	Canvas canvas;
	JButton undo;
	
	public View(Model model) {
		super("Solitaer");
		this.model = model;
		this.canvas=new Canvas();
		this.canvas.addMouseListener(canvas);
		this.undo = new JButton("Undo");
		this.undo.addActionListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,650);
		this.add(canvas, BorderLayout.CENTER);
		this.add(undo, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private class Canvas extends JPanel implements MouseListener{
		private Graphics2D g2;
		
		
		final private int FELDSIZE =75;
		final private int KREISSIZE = 15;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.g2 = (Graphics2D)g;
			int x = 20;
			int y =20;
			for(int i =0; i<View.this.model.getField().length;i++) {
				for(int j=0;j<View.this.model.getField().length;j++) {
					if(View.this.model.getField()[i][j] == State.NOT) {
						rectZeichnen(Color.LIGHT_GRAY,x,y);
					}
					else if(View.this.model.getField()[i][j] == State.FREE) {
						rectZeichnen(new Color(222,184,135), x,y);
					}
					else if(View.this.model.getField()[i][j] == State.USED) {
						rectZeichnen(new Color(222,184,135),x,y);
						ovalZeichnen(x+this.FELDSIZE/2-this.KREISSIZE/2, y+this.FELDSIZE/2-this.KREISSIZE/2);
					}
					x+=75;
				}
				y+=75;
				x=20;
			}
			
		}
		
		private void rectZeichnen(Color c, int x, int y) {
			g2.setColor(c);
			g2.fillRect(x, y,this.FELDSIZE, this.FELDSIZE);
			g2.setColor(Color.BLACK);
			g2.drawRect(x, y,this.FELDSIZE,this.FELDSIZE);
		}
		
		private void ovalZeichnen(int x, int y) {
			g2.setColor(Color.WHITE);
			g2.fillOval(x,y, this.KREISSIZE,this.KREISSIZE);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = (e.getX()-20)/this.FELDSIZE;
			int y = (e.getY()-20)/this.FELDSIZE;
			Point p = new Point(x,y);
			System.out.println(p.y+","+p.x);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public static void main(String[] args) {
		new View(new Model());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.lastMoveUndo();
		this.canvas.repaint();
	}
	

}
