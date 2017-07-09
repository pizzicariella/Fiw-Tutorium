package aufgaben.aufgabe6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Taschenrechner extends JFrame implements ActionListener {

	JTextField display;
	JPanel panel;
	double a;
	double b;
	double result;
	String operation;
	
	public Taschenrechner()
	{
		super("Taschenrechner");
		this.setSize(280, 380);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		this.display = new JTextField(300);
		this.display.setPreferredSize(new Dimension(300,40));
		this.panel = this.outsidePanel();
		
		this.getContentPane().add(this.display,BorderLayout.NORTH);
		this.getContentPane().add(this.panel);
		this.setVisible(true);
	}
	
	private JPanel outsidePanel()
	{
		
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(7,3,10,10));
		JButton b1 = new JButton("1");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		JButton b4 = new JButton("4");
		JButton b5 = new JButton("5");
		JButton b6 = new JButton("6");
		JButton b7 = new JButton("7");
		JButton b8 = new JButton("8");
		JButton b9 = new JButton("9");
		JButton b0 = new JButton("0");
		JButton bKlammerAuf = new JButton("(");
		JButton bKlammerZu = new JButton(")");
		JButton bMal = new JButton("*");
		JButton bGeteilt = new JButton("/");
		JButton bPlus = new JButton("+");
		JButton bMinus = new JButton("-");
		JButton bPunkt = new JButton(".");
		JButton bGleich = new JButton("=");
		JButton bClear = new JButton("clear");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		bKlammerAuf.addActionListener(this);
		b0.addActionListener(this);
		bKlammerZu.addActionListener(this);
		bMal.addActionListener(this);
		bGeteilt.addActionListener(this);
		bPlus.addActionListener(this);
		bMinus.addActionListener(this);
		bPunkt.addActionListener(this);
		bGleich.addActionListener(this);
		bClear.addActionListener(this);
		b1.setPreferredSize(new Dimension(80,40));
		keyPanel.add(b1);
		keyPanel.add(b2);
		keyPanel.add(b3);
		keyPanel.add(b4);
		keyPanel.add(b5);
		keyPanel.add(b6);
		keyPanel.add(b7);
		keyPanel.add(b8);
		keyPanel.add(b9);
		keyPanel.add(bKlammerAuf);
		keyPanel.add(b0);
		keyPanel.add(bKlammerZu);
		keyPanel.add(bMal);
		keyPanel.add(bGeteilt);
		keyPanel.add(bPlus);
		keyPanel.add(bMinus);
		keyPanel.add(bPunkt);
		keyPanel.add(bGleich);
		keyPanel.add(bClear);
		
		return keyPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton)
		{
			JButton key = (JButton)source;
			String s = key.getText();
			switch(s)
			{
				case "1": {this.display.setText(this.display.getText()+"1");break;}
				case "2": {this.display.setText(this.display.getText()+"2");break;}
				case "3": {this.display.setText(this.display.getText()+"3");break;}
				case "4": {this.display.setText(this.display.getText()+"4");break;}
				case "5": {this.display.setText(this.display.getText()+"5");break;}
				case "6": {this.display.setText(this.display.getText()+"6");break;}
				case "7": {this.display.setText(this.display.getText()+"7");break;}
				case "8": {this.display.setText(this.display.getText()+"8");break;}
				case "9": {this.display.setText(this.display.getText()+"9");break;}
				case "(": {this.display.setText(this.display.getText()+"(");break;}
				case "0": {this.display.setText(this.display.getText()+"0");break;}
				case ")": {this.display.setText(this.display.getText()+")");break;}
				case "*": {
					this.a = Double.valueOf(this.display.getText());
					this.operation = "*";
					this.display.setText("");break;
				}
				case "/": {
					this.a = Double.valueOf(this.display.getText());
					this.operation = "/";
					this.display.setText("");break;
				}
				case "+": {
					this.a = Double.valueOf(this.display.getText());
					this.operation = "+";
					this.display.setText("");break;
				}
				case "-": {
					this.a = Double.valueOf(this.display.getText());
					this.operation = "+";
					this.display.setText("");break;
				}
				case ".": {this.display.setText(this.display.getText()+".");break;}
				case "=": {
					this.b=Double.valueOf(this.display.getText());
					switch(this.operation)
					{
						case "*" : {this.result =this.a*this.b;break;}
						case "/" : {this.result=this.a/this.b;break;}
						case "+" : {this.result=this.a+this.b;break;}
						case "-" : {this.result = this.a-this.b;break;}
					}
					this.display.setText(String.valueOf(this.result));break;
				}
				case "clear" :{this.display.setText("");break;}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Taschenrechner();
	}
	
}
