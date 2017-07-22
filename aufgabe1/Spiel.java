package aufgaben.aufgabe1;

import javax.swing.JOptionPane;

public class Spiel 
{
	public static int siegPunkte;
	private Spieler[] spieler;
	
	public Spiel(int siegPunkte, int anzSpieler)
	{
		Spiel.siegPunkte=siegPunkte;
		this.spieler= new Spieler[anzSpieler];
		for(int i=0; i<this.spieler.length;i++)
		{
			this.spieler[i]=new Spieler(JOptionPane.showInputDialog(null, "Name des Spielers "+(i+1)));
		}

	}
	
	public void spielen()
	{
		boolean spiel=false;
		while(!spiel)
		{
			for(int i=0; (i<this.spieler.length) && (!spiel);i++)
			{
				boolean spiel1= spieler[i].wuerfeln();
				if(spiel1)
				{
					JOptionPane.showMessageDialog(null, spieler[i].getName()+" hat das Spiel gewonnen!");
					spiel=true;
				}
			}
		}
	}
}
