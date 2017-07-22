package aufgaben.aufgabe1;

import java.util.Random;

import javax.swing.JOptionPane;

public class Spieler 
{
	private String name;
	private int aktStand;
	private Random r;
	
	public Spieler(String name)
	{
		this.name = name;
		this.aktStand=0;
		this.r= new Random();
	}
	
	public String getName() {
		return name;
	}

	public boolean wuerfeln()
	{
		int aktRunde=0;
		System.out.println();
		System.out.println("Spieler "+this.name+" ist an der Reihe (bisher "+this.aktStand+" Punkte)");
		System.out.println("----------------------------------------------------------------");
		while(JOptionPane.showConfirmDialog(null, this.name+" wollen Sie weiter wuerfeln?", "Weiter wuerfeln?", 
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		{
				int wuerfel=this.r.nextInt(6)+1;
				System.out.println(this.name+" hat eine "+wuerfel+" gewuerfelt");
				if(wuerfel != 6)
				{
					aktRunde+=wuerfel;
					System.out.println("in diesem Versuch bisher "+aktRunde+" Punkte -- insgesamt "+(this.aktStand+aktRunde)+" Punkte");
					if((this.aktStand+aktRunde)>=Spiel.siegPunkte)
					{
						this.aktStand+=aktRunde;
						System.out.println(this.name+" hat insgesamt "+this.aktStand+" Punkte und somit gewonnen!!");
						return true;
					}
				}
				else
				{
					System.out.println(this.name+" hat eine 6 gewuerfelt -- Versuch zu Ende");
					System.out.println("Aktueller Spielstand von "+this.name+" : "+this.aktStand+" Punkte");
					System.out.println("Der naechste Spieler ist dran");
					return false;
				}
				
		}
		this.aktStand+=aktRunde;
		return false; 
	}
}
