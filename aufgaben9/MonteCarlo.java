package aufgaben.aufgaben9;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MonteCarlo extends JFrame implements ChangeListener{
	
	JPanel sliderPanel;
	int punkte;
	int rot;
	int blau;
	double pi;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JLabel piLabel;
	
	public MonteCarlo() {
		super("Berechnung von Pi");
		this.setSize(480,650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.punkte = 10000;
		
		this.add(new DrawPanel(), BorderLayout.CENTER);
		
		this.sliderPanel=initSliderPanel();
		this.add(sliderPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	
	private JPanel initSliderPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel norden = new JPanel();
		JSlider slider = new JSlider(JSlider.HORIZONTAL,10000,90000, 10000);
		Dimension d = slider.getPreferredSize();
		slider.setPreferredSize(new Dimension(d.width+200,d.height+30));
		slider.setMajorTickSpacing(20000);
		slider.setMinorTickSpacing(5000);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		norden.add(slider);
		
		JPanel westen = new JPanel();
		westen.setLayout(new  GridLayout(3,1,10,10));
		JLabel punkteGesamt =new JLabel("Anzahl Punkte");
		JLabel punkteRot = new JLabel("Punkte rot");
		JLabel punkteBlau = new JLabel("Punkte blau");
		westen.add(punkteGesamt);
		westen.add(punkteRot);
		westen.add(punkteBlau);
		
		JPanel osten= new JPanel();
		osten.setLayout(new GridLayout(3,1));
		this.tf1 = new JTextField(String.valueOf(this.punkte));
		this.tf2 = new JTextField();
		this.tf3 = new JTextField();
		tf1.setPreferredSize(new Dimension(200,20));
		osten.add(tf1);
		osten.add(tf2);
		osten.add(tf3);
		
		JPanel sueden = new JPanel();
		this.piLabel=new JLabel();
		sueden.add(piLabel);
		
		panel.add(norden, BorderLayout.NORTH);
		panel.add(westen,BorderLayout.WEST);
		panel.add(osten, BorderLayout.EAST);
		panel.add(sueden,BorderLayout.SOUTH);
		return panel;
	}
	
	private class DrawPanel extends JPanel {
		final int LAENGE = 400;
	
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.drawRect(30, 30, LAENGE, LAENGE);
			g2.drawArc(30,30,LAENGE*2,LAENGE*2 ,90, 90 );
			Random r = new Random();
			//g2.fillOval(430, 430, 5, 5);
			MonteCarlo.this.blau=0;
			MonteCarlo.this.rot=0;
			for(int i = 0; i<MonteCarlo.this.punkte;i++) {
				int x = r.nextInt(401)+30;
				int y = r.nextInt(401)+30;
				final int MIPUNKTXY = 430;
				int abstand = (int) Math.sqrt((MIPUNKTXY-x)*(MIPUNKTXY-x)+(MIPUNKTXY-y)*(MIPUNKTXY-y));
				if(abstand > LAENGE) {
					g2.setColor(Color.BLUE);
					MonteCarlo.this.blau++;
				}
				else {
					g2.setColor(Color.RED);
					MonteCarlo.this.rot++;
				}
				g2.fillOval(x,y,2,2);
			}
			MonteCarlo.this.tf2.setText(String.valueOf(MonteCarlo.this.rot));
			MonteCarlo.this.tf3.setText(String.valueOf(MonteCarlo.this.blau));
			MonteCarlo.this.pi = ((double)MonteCarlo.this.rot/(double)MonteCarlo.this.punkte)*4;
			double wert = MonteCarlo.this.pi * 100000;
			wert = Math.round(wert);
			wert /= 100000;
			MonteCarlo.this.piLabel.setText("PI ist ungefähr: "+wert);
		}
	}

	public static void main(String[] args) {
		new MonteCarlo();

	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		JSlider slider = (JSlider)ce.getSource();
		this.punkte = slider.getValue();
		this.tf1.setText(String.valueOf(slider.getValue()));

		this.repaint();
		
	}

}