package klausurvorbereitung.bunteRechtecke;

import java.awt.Point;

public class Rechteck {
	Point start = new Point();
	
	public int hoehe;
	public int breite;
	
	public void setStart(Point start) {
		this.start = start;
	}
	
	public Point getStart() {
		return start;
	}
	
	
	public void neuBerechnen(Point ende){
		this.breite = Math.abs(start.x-ende.x);
		this.hoehe = Math.abs(start.y-ende.y);
	}
}
