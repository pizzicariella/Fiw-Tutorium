package aufgaben.aufgabe7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import aufgaben.aufgabe8.TicTacToeTut2;

public class TicTacToe extends JFrame{
	
	JLabel label;
	JPanel gamePanel;
	JPanel startPanel;
	JButton[] buttonArray;
	boolean game;
	boolean rot;
	GameActions[] actionArray;
	JLabel hinweis;
	boolean maschine;
	int gameCount =0;
	
	public TicTacToe()
	{
		super("TicTacToe");
		this.setSize(460,460);
		this.setLocation(900, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.startPanel = initStartPanel();
		this.gamePanel=initGamePanel();
		this.label = new JLabel("TicTacToe");
		this.label.setFont(new Font("Verdana",Font.BOLD, 24));
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.add(this.label, BorderLayout.NORTH);
		this.add(this.gamePanel, BorderLayout.CENTER);
		this.add(this.startPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private JPanel initStartPanel() {
		JPanel panel = new JPanel();
		JPanel startEnde = new JPanel();
		startEnde.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		JPanel radio= new JPanel();
		radio.setLayout(new BorderLayout(2,2));
		JPanel r1= new JPanel();
		r1.setLayout(new GridLayout(2,1,10,10));
		
		JPanel r2 = new JPanel();
		r2.setLayout(new GridLayout(2,1,10,10));
		JButton start = new JButton("Start");
		start.setBackground(Color.DARK_GRAY);
		start.setForeground(Color.WHITE);
		JButton ende = new JButton("Ende");
		ende.setBackground(Color.DARK_GRAY);
		ende.setForeground(Color.WHITE);
		JRadioButton menschMensch = new JRadioButton("Mensch vs. Mensch");
		JRadioButton menschMaschine = new JRadioButton("Mensch vs. Maschine");
		JRadioButton schwarzStart = new JRadioButton("Schwarz beginnt");
		JRadioButton rotStart = new JRadioButton("Rot beginnt");
		this.hinweis = new JLabel(" ");
		this.hinweis.setHorizontalAlignment(JLabel.CENTER);
		this.hinweis.setForeground(Color.WHITE);
		this.hinweis.setBackground(Color.DARK_GRAY);
		this.hinweis.setOpaque(true);
		this.hinweis.setFont(new Font("Verdana", Font.PLAIN,15));
		
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TicTacToe.this.gameCount++;
				if(TicTacToe.this.gameCount > 1) {
					TicTacToe.this.endGame();
				}
					TicTacToe.this.game=true;
					
					if(menschMensch.isSelected()) {
						TicTacToe.this.maschine = false;
						if(rotStart.isSelected()) {
							
							TicTacToe.this.rot=true;
							TicTacToe.this.label.setText("Rot ist dran");
							TicTacToe.this.label.setForeground(Color.RED);
							TicTacToe.this.startGame();
						}
						else {
							
							TicTacToe.this.rot=false;
							TicTacToe.this.label.setText("Schwarz ist dran");
							TicTacToe.this.label.setForeground(Color.BLACK);
							TicTacToe.this.startGame();
						}
					}
					else {
						
						TicTacToe.this.maschine = true;
						if(rotStart.isSelected()) {
							TicTacToe.this.rot=true;
						}
						else {
							TicTacToe.this.rot=false;
						}
						
						TicTacToe.this.label.setText("Rot ist dran");
						TicTacToe.this.label.setForeground(Color.RED);
						TicTacToe.this.startGame();
					}
					
				}
		});
		
		ende.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showOptionDialog(null, "Wollen Sie wirklich beenden?", "Programmende",JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION,null,null,null)
						== JOptionPane.YES_OPTION) {
					TicTacToe.this.setVisible(false);
					TicTacToe.this.dispose();
					System.exit(0);
				}
			}
			
		});
		
		ButtonGroup meMa = new ButtonGroup();
		ButtonGroup roSchw = new ButtonGroup();
		
		
		menschMensch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TicTacToe.this.hinweis.setText(" ");
			}
			
		});
		menschMaschine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hinweis.setText("Hinweis: Maschine spielt immer in scharz");
			}
			
		});
		
		schwarzStart.setSelected(true);
		menschMensch.setSelected(true);
		
		roSchw.add(schwarzStart);
		roSchw.add(rotStart);
		meMa.add(menschMensch);
		meMa.add(menschMaschine);
		
		panel.setLayout(new BorderLayout());
		startEnde.add(start);
		startEnde.add(ende);
		panel.add(startEnde, BorderLayout.NORTH);
		
		r1.add(menschMensch);
		r1.add(menschMaschine);
		r2.add(schwarzStart);
		r2.add(rotStart);
		
		radio.add(r1, BorderLayout.WEST);
		radio.add(r2, BorderLayout.EAST);
		
		panel.add(radio, BorderLayout.CENTER);
		panel.add(hinweis, BorderLayout.SOUTH);
		return panel;
	}
	
	private JPanel initGamePanel() {
		JPanel panel = new JPanel();
		//panel.setBackground(JColorChooser.showDialog(null, "Bitte Hintergrundfarbe auswählen", panel.getBackground()));
		panel.setLayout(new GridLayout(3,3,20,20));
		this.buttonArray = new JButton[9];
		this.actionArray = new GameActions[9];
		for(int i =0; i<this.buttonArray.length;i++) {
			buttonArray[i] = new JButton("");
			buttonArray[i].setFont(new Font("Verdana", Font.BOLD, 60));
		}
		
		for(int i =0; i<this.buttonArray.length;i++) {
			panel.add(this.buttonArray[i]);
		}
		
		return panel;
	}
	
	class GameActions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object quelle = e.getSource();
			if(quelle instanceof JButton) {
				JButton button = (JButton)quelle;
				if(!TicTacToe.this.maschine) {
					if(TicTacToe.this.rot) {
						button.setText("O");
						button.setForeground(Color.RED);
						
						button.removeActionListener(this);
						TicTacToe.this.label.setText("Schwarz ist dran");
						TicTacToe.this.label.setForeground(Color.BLACK);
					}
					else {
						button.setText("X");
						button.setForeground(Color.BLACK);
						button.removeActionListener(this);
						TicTacToe.this.label.setText("Rot ist dran");
						TicTacToe.this.label.setForeground(Color.RED);
					}
					
					if(TicTacToe.this.checkGameStatus()) {
						TicTacToe.this.endGame();
					}
					else if (TicTacToe.this.checkStatusUnentschieden()) {
							TicTacToe.this.endGame();
					}
					if(TicTacToe.this.rot) {
						TicTacToe.this.rot=false;
					}
					else {
						TicTacToe.this.rot = true;
					}
				}
				else{
					button.setText("O");
					//System.out.println("a");
					button.setForeground(Color.RED);
					button.removeActionListener(this);
					if(TicTacToe.this.checkGameStatus()) {
						TicTacToe.this.endGame();
					}
					else if(TicTacToe.this.checkStatusUnentschieden()) {
							TicTacToe.this.endGame();
					}
					else {
						TicTacToe.this.rot = false;
						//System.out.println(TicTacToe.this.maschineWinPossible());
						//System.out.println(TicTacToe.this.maschineStopWinPossible());
						if(TicTacToe.this.maschineWinPossible() != -1) {
							int feld = TicTacToe.this.maschineWinPossible();
							TicTacToe.this.buttonArray[feld].setForeground(Color.BLACK);
							TicTacToe.this.buttonArray[feld].setText("X");
							TicTacToe.this.buttonArray[feld].removeActionListener(TicTacToe.this.actionArray[feld]);
							
						}
						else if(TicTacToe.this.maschineStopWinPossible() != -1) {
							int feld = TicTacToe.this.maschineStopWinPossible();
							TicTacToe.this.buttonArray[feld].setForeground(Color.BLACK);
							TicTacToe.this.buttonArray[feld].setText("X");
							TicTacToe.this.buttonArray[feld].removeActionListener(TicTacToe.this.actionArray[feld]);
							
						}
						else {
							for(int i =0;i<TicTacToe.this.buttonArray.length;i++){
								if(TicTacToe.this.buttonArray[i].getText().equals("")){
									TicTacToe.this.buttonArray[i].setText("X");
									//System.out.println("x");
									TicTacToe.this.buttonArray[i].setForeground(Color.BLACK);
									TicTacToe.this.buttonArray[i].removeActionListener(TicTacToe.this.actionArray[i]);
									break;
								}
							}
						}
						if(TicTacToe.this.checkGameStatus()) {
							TicTacToe.this.endGame();
						}
						else if(TicTacToe.this.checkStatusUnentschieden()){
								TicTacToe.this.endGame();
						}
						else {
							TicTacToe.this.rot = true;
						}
					}
				}
			}
			
		}
		
	}
	
	private int maschineWinPossible() {
			// Zeilen
			boolean z = false;
			int counterZ =0;
			int index = -1;
			for(int i = 0;i<7;i+=3) {
				for(int j=i;j<i+3;j++){
					if(this.buttonArray[j].getText().equals("X")) {
						counterZ++;
					}
					else if(this.buttonArray[j].getText().equals("")){
						index =j;
						z = true;
					}
					if(counterZ == 2 && z == true) {
						return index;
					}
				}
				z = false;
				counterZ = 0;	
				index = -1;
			}
			
			//Spalten
			for(int i = 0;i<3;i++) {
				for(int j=i;j<i+7;j+=3){
					if(this.buttonArray[j].getText().equals("X")) {
						counterZ++;
					}
					else if(this.buttonArray[j].getText().equals("")){
						index = j;
						z = true;
					}
					if(counterZ == 2 && z == true) {
						return index;
					}
				}
					z = false;
					counterZ = 0;
					index = -1;
			}
			//HD
			if(this.buttonArray[0].getText().equals("X")) {
				counterZ++;
			}
			else if(this.buttonArray[0].getText().equals("")){
				index = 0;
				z = true;
			}
			if(this.buttonArray[4].getText().equals("X")) {
				counterZ++;
			}
			else if(this.buttonArray[4].getText().equals("")){
				index = 4;
				z = true;
			}
			if(this.buttonArray[8].getText().equals("X")) {
				counterZ++;
			}
			else if(this.buttonArray[8].getText().equals("")){
				index = 8;
				z = true;
			}
			if(counterZ == 2 && z == true) {
				return index;
			}
			else {
				z = false;
				counterZ = 0;
				index = -1;
			}
			//ND
			if(this.buttonArray[2].getText().equals("X")) {
				counterZ++;
			}
			else if(this.buttonArray[2].getText().equals("")){
				index = 2;
				z = true;
			}
			if(this.buttonArray[4].getText().equals("X")) {
				counterZ++;
			}
			else if(this.buttonArray[4].getText().equals("")){
				index = 4;
				z = true;
			}
			if(this.buttonArray[6].getText().equals("X")) {
				counterZ++;
			}
			else if(this.buttonArray[6].getText().equals("")){
				index = 6;
				z = true;
			}
			if(counterZ == 2 && z == true) {
				return index;
			}
			else {
				return -1;
			}
	}
	
	private int maschineStopWinPossible() {
		// Zeilen
					boolean z = false;
					int counterZ =0;
					int index = -1;
					for(int i = 0;i<7;i+=3) {
						for(int j=i;j<i+3;j++){
							if(this.buttonArray[j].getText().equals("O")) {
								counterZ++;
							}
							else if(this.buttonArray[j].getText().equals("")){
								index = j;
								z = true;
							}
							if((counterZ == 2) && (z == true) && (index !=-1)) {
								return index;
							}
						}
							z = false;
							counterZ = 0;
							index = -1;
					}
					//Spalten
					for(int i = 0;i<3;i++) {
						for(int j=i;j<i+7;j+=3){
							if(this.buttonArray[j].getText().equals("O")) {
								counterZ++;
							}
							else if(this.buttonArray[j].getText().equals("")){
								index = j;
								z = true;
							}
							if(counterZ == 2 && z == true) {
								return index;
							}
						}
							z = false;
							counterZ = 0;
							index = -1;
					}
					//HD
					if(this.buttonArray[0].getText().equals("O")) {
						counterZ++;
					}
					else if(this.buttonArray[0].getText().equals("")){
						index = 0;
						z = true;
					}
					if(this.buttonArray[4].getText().equals("O")) {
						counterZ++;
					}
					else if(this.buttonArray[4].getText().equals("")){
						index = 4;
						z = true;
					}
					if(this.buttonArray[8].getText().equals("O")) {
						counterZ++;
					}
					else if(this.buttonArray[8].getText().equals("")){
						index = 8;
						z = true;
					}
					if(counterZ == 2 && z == true) {
						return index;
					}
					else {
						z = false;
						counterZ = 0;
						index = -1;
					}
					//ND
					if(this.buttonArray[2].getText().equals("O")) {
						counterZ++;
					}
					else if(this.buttonArray[2].getText().equals("")){
						index = 2;
						z = true;
					}
					if(this.buttonArray[4].getText().equals("O")) {
						counterZ++;
					}
					else if(this.buttonArray[4].getText().equals("")){
						index = 4;
						z = true;
					}
					if(this.buttonArray[6].getText().equals("O")) {
						counterZ++;
					}
					else if(this.buttonArray[6].getText().equals("")){
						index = 6;
						z = true;
					}
					if(counterZ == 2 && z == true) {
						return index;
					}
					else {
						return -1;
					}
	}
	
	private void startGame() {
		
		for(int i = 0; i<this.buttonArray.length;i++) {
			this.actionArray[i]=new GameActions();
			this.buttonArray[i].setText("");
			this.buttonArray[i].addActionListener(this.actionArray[i]);
		}
		if(!this.rot && this.maschine) {
			Random r = new Random();
			int firstX = r.nextInt(9);
			this.buttonArray[firstX].setText("X");
			this.buttonArray[firstX].setFont(new Font("Verdana", Font.BOLD, 60));
			this.buttonArray[firstX].setForeground(Color.BLACK);
			this.buttonArray[firstX].removeActionListener(this.actionArray[firstX]);
			this.rot = true;
		}
	}
	
	private void endGame() {
		for(int i=0; i<this.buttonArray.length;i++) {
			this.buttonArray[i].removeActionListener(this.actionArray[i]);
		}
	}
	
	private boolean checkGameStatus() {
		String dran = rot ? "O" : "X";
		System.out.println(dran);
		boolean gewonnen = false;
		//Spalten
		for(int i =0; i<3; i++) {
			if(this.buttonArray[i].getText().equals(dran) && this.buttonArray[i+3].getText().equals(dran) 
					&& this.buttonArray[i+6].getText().equals(dran)) {
				gewonnen= true;
			}
		}
		//Zeilen
		for(int i=0; i<7; i+=3) {
			if(this.buttonArray[i].getText().equals(dran) && this.buttonArray[i+1].getText().equals(dran) 
					&& this.buttonArray[i+2].getText().equals(dran)) {
				gewonnen = true;
			}
		}
		//Diagonalen
		if(this.buttonArray[0].getText().equals(dran) && this.buttonArray[4].getText().equals(dran) 
				&& this.buttonArray[8].getText().equals(dran)) {gewonnen = true;}
		if(this.buttonArray[2].getText().equals(dran) && this.buttonArray[4].getText().equals(dran) 
				&& this.buttonArray[6].getText().equals(dran)) {gewonnen= true;}
		
		if(dran.equals("O") && (gewonnen == true)) {
			this.label.setText("Rot hat gewonnen");
			this.label.setForeground(Color.RED);
		}
		else if (dran.equals("X") && (gewonnen == true)) {
			this.label.setText("Schwarz hat gewonnen");
			this.label.setForeground(Color.BLACK);
		}
		System.out.println(gewonnen);
		return gewonnen;
	}
	
	public boolean checkStatusUnentschieden() {
		for(int i=0; i<this.buttonArray.length;i++) {
			if(this.buttonArray[i].getText().equals("")) {
				return false;
			}
		}
		this.label.setText("Unentschieden");
		return true;
	}
	
	public static void main(String[] args) {
		new TicTacToe();
	}
}
