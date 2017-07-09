package aufgaben.aufgabe8;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeTut2 extends JFrame{
	JButton[] buttons; 
	GameActions[] actionArray;
	JLabel label1;
	JButton bNeu, bEnde;
	JRadioButton rbMensch, rbMaschine, rbSchwarz, rbRot;
//	JButton bNeuRot, bNeuSchwarz;
	boolean rotDran;
	boolean ende;
	boolean computer;
	int gameCount = 0;
	
	TicTacToeTut2()
	{
		super();
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel hauptPanel = init();
		this.add(hauptPanel, BorderLayout.CENTER);
		JPanel labelPanel = new JPanel();
		label1 = new JLabel();
		label1.setFont(new Font("Verdana", Font.BOLD, 24));
		label1.setText(" ");
		labelPanel.add(label1);
		this.add(labelPanel, BorderLayout.NORTH);	
		JPanel buttonPanel = erzeugeSteuerPanel();
		this.add(buttonPanel, BorderLayout.SOUTH);			
		setSize(400,400);
		setVisible(true);
	}
	private JPanel init()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3,10,10));

		buttons = new JButton[9];
		for (int i=0; i<buttons.length; i++)
		{
			buttons[i]=new JButton();
			buttons[i].setFont(new Font("Verdana", Font.BOLD, 48));
			panel.add(buttons[i]);
		}

		return panel;
	}
	private JPanel erzeugeSteuerPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel();
		bNeu = new JButton("Start");
		bNeu.setBackground(Color.DARK_GRAY);
		bNeu.setForeground(Color.WHITE);
		bNeu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TicTacToeTut2.this.gameCount++;
				if(TicTacToeTut2.this.gameCount > 1) {
					TicTacToeTut2.this.endGame();
				}
				TicTacToeTut2.this.startGame();
				if(TicTacToeTut2.this.rbMensch.isSelected())
				{
					TicTacToeTut2.this.computer=false;			
				}
				else //rbMaschine.isEnabled()
				{
					TicTacToeTut2.this.computer=true;
				}
				if(TicTacToeTut2.this.rbSchwarz.isSelected())
				{
					TicTacToeTut2.this.rotDran=false;
					TicTacToeTut2.this.label1.setForeground(Color.BLACK);
					TicTacToeTut2.this.label1.setText("Schwarz ist dran");
				}
				else //rbRot.isEnabled()
				{
					TicTacToeTut2.this.rotDran=true;
					TicTacToeTut2.this.label1.setForeground(Color.RED);
					TicTacToeTut2.this.label1.setText("Rot ist dran");
				}
				if(TicTacToeTut2.this.computer && !TicTacToeTut2.this.rotDran) {
					Random r = new Random();
					int index = r.nextInt(9);
					TicTacToeTut2.this.buttons[index].setForeground(Color.BLACK);
					TicTacToeTut2.this.buttons[index].setText("X");
					TicTacToeTut2.this.buttons[index].removeActionListener(TicTacToeTut2.this.actionArray[index]);
					TicTacToeTut2.this.rotDran = true;
					TicTacToeTut2.this.label1.setForeground(Color.RED);
					TicTacToeTut2.this.label1.setText("Rot ist dran");
				}
			}
		});
		bEnde = new JButton("Ende");
		bEnde.setBackground(Color.DARK_GRAY);
		bEnde.setForeground(Color.WHITE);
		bEnde.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(TicTacToeTut2.this, "Wollen Sie wirklich beenden?", "Programmende", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(ok==JOptionPane.OK_OPTION)
				{
					setVisible(false);
					dispose();
					System.exit(0);
				}
			}
		});
		centerPanel.add(bNeu);
		centerPanel.add(bEnde);

		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(2,1, 5, 5));
		rbMensch = new JRadioButton("Mensch vs. Mensch", true);
		rbMaschine = new JRadioButton("Mensch vs. Maschine");
		ButtonGroup rbg1 = new ButtonGroup();
		rbg1.add(rbMensch);
		rbg1.add(rbMaschine);
		westPanel.add(rbMensch);
		westPanel.add(rbMaschine);
		buttonPanel.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(2,1, 5, 5));
		rbSchwarz = new JRadioButton("Schwarz beginnt", true);
		rbRot = new JRadioButton("Rot beginnt");
		ButtonGroup rbg2 = new ButtonGroup();
		rbg2.add(rbSchwarz);
		rbg2.add(rbRot);
		eastPanel.add(rbSchwarz);
		eastPanel.add(rbRot);
		buttonPanel.add(eastPanel, BorderLayout.EAST);

		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.DARK_GRAY);
		southPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel l1 = new JLabel("Hinweis: Maschine spielt stets in Schwarz");
		l1.setForeground(Color.WHITE);
		southPanel.add(l1);

		buttonPanel.add(southPanel, BorderLayout.SOUTH);
		buttonPanel.add(centerPanel,BorderLayout.NORTH);


		return buttonPanel;
	}
	

	

	public class GameActions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object quelle = e.getSource();
			JButton button = (JButton)quelle;
			if(!TicTacToeTut2.this.ende) {
				if(TicTacToeTut2.this.rotDran) 
				{
					button.setForeground(Color.RED); 
					button.setText("O");
					button.removeActionListener(this);
					if(gewonnen())
					{
						TicTacToeTut2.this.label1.setText("Rot hat gewonnen!");
						TicTacToeTut2.this.ende = true;
						TicTacToeTut2.this.endGame();
					}
					else if(unentschieden())
					{
						TicTacToeTut2.this.label1.setText("Unentschieden");
						TicTacToeTut2.this.ende = true;
						TicTacToeTut2.this.endGame();
					}
					else if(!TicTacToeTut2.this.computer)
					{
						TicTacToeTut2.this.rotDran=false; 
						TicTacToeTut2.this.label1.setForeground(Color.BLACK);
						TicTacToeTut2.this.label1.setText("Schwarz ist dran");
					}
					else { //computer
						TicTacToeTut2.this.rotDran=false; 
						nextPlay();
						if(gewonnen())
						{
							TicTacToeTut2.this.label1.setForeground(Color.BLACK);
							TicTacToeTut2.this.label1.setText("Schwarz hat gewonnen!");
							TicTacToeTut2.this.ende=true;
							TicTacToeTut2.this.endGame();
						}
						else if(unentschieden())
						{
							TicTacToeTut2.this.label1.setText("Unentschieden");
							TicTacToeTut2.this.ende=true;
							TicTacToeTut2.this.endGame();
						}
						else
						{
							TicTacToeTut2.this.rotDran=true; 
							TicTacToeTut2.this.label1.setForeground(Color.RED);
							TicTacToeTut2.this.label1.setText("Rot ist dran");
						}
					}
				}
				else 
				{
					button.setForeground(Color.BLACK); 
					button.setText("X"); 
					button.removeActionListener(this);
					if(gewonnen())
					{
						label1.setText("Schwarz hat gewonnen!");
						TicTacToeTut2.this.ende = true;
						TicTacToeTut2.this.endGame();
					}
					else if(unentschieden())
					{
						label1.setText("Unentschieden");
						TicTacToeTut2.this.ende = true;
						TicTacToeTut2.this.endGame();
					}
					else
					{
						TicTacToeTut2.this.rotDran=true; 
						label1.setForeground(Color.RED);
						label1.setText("Rot ist dran");
					}
				}
			}
		}
	}

	public void nextPlay() 
	{
		// kann der Computer gewinnen??
		for(int i=0; i<9 && !ende; i++)
		{
			if(buttons[i].getText().equals(""))
			{
				buttons[i].setForeground(Color.BLACK); 
				buttons[i].setText("X"); 
				if(gewonnen())
				{
					label1.setText("Schwarz hat gewonnen!");
					ende=true;
				}
				else
				{
					buttons[i].setText(""); 
				}
			}
		}
		if(ende) return;

		// kann O gewinnen??
		boolean zugende = false;
		rotDran=true;
		for(int i=0; i<9 && !zugende; i++)
		{
			if(buttons[i].getText().equals(""))
			{
				buttons[i].setText("O"); 
				if(gewonnen())
				{
					buttons[i].setForeground(Color.BLACK); 
					buttons[i].setText("X");
					buttons[i].removeActionListener(this.actionArray[i]);
					zugende=true;
				}
				else
				{
					buttons[i].setText(""); 
				}
			}
		}

		// n�chste freie
		for(int i=0; i<9 && !zugende; i++)
		{
			if(buttons[i].getText().equals(""))
			{
				buttons[i].setForeground(Color.BLACK); 
				buttons[i].setText("X");
				buttons[i].removeActionListener(this.actionArray[i]);
				zugende = true;
			}
		}
		if(zugende) 
		{
			rotDran=true;
			label1.setForeground(Color.RED);
			label1.setText("Rot ist dran");
			return;
		}
	}
	
	public void startGame() {
		this.actionArray = new GameActions[9];
		for(int i=0;i<this.buttons.length;i++) {
			this.actionArray[i] = new GameActions();
			this.buttons[i].addActionListener(this.actionArray[i]);
			this.buttons[i].setText("");
			this.ende =false;
		}
	}
	public void endGame() {
		for(int i =0;i<this.buttons.length;i++) {
			this.buttons[i].removeActionListener(this.actionArray[i]);
		}
	}
	
	boolean gewonnen()
	{
		String dran = rotDran ? "O" : "X"; 							//?
		//Spalten
		for(int i=0; i<3; i++)
		{
			if( buttons[i].getText().equals(dran) && 
					buttons[i+3].getText().equals(dran) && 
					buttons[i+6].getText().equals(dran)) return true;
		}
		//Zeilen
		for(int i=0; i<7; i+=3)
		{
			if( buttons[i].getText().equals(dran) && 
					buttons[i+1].getText().equals(dran) && 
					buttons[i+2].getText().equals(dran)) return true;
		}
		//Diagonalen
		if( buttons[0].getText().equals(dran) && 
				buttons[4].getText().equals(dran) && 
				buttons[8].getText().equals(dran)) return true;
		if( buttons[2].getText().equals(dran) && 
				buttons[4].getText().equals(dran) && 
				buttons[6].getText().equals(dran)) return true;
		return false;
	}
	
	boolean voll()
	{
		for(int i=0; i<buttons.length; i++)
		{
			if(!(buttons[i].getText().equals("X") || buttons[i].getText().equals("O"))) return false;
		}
		return true;
	}

	boolean unentschieden()
	{
		return (voll() && !gewonnen());
	}
	
	public static void main(String[] args) {
		new TicTacToeTut2();
	}
}
