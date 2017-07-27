package aufgaben.aufgabe12;

import java.awt.Point;

import aufgaben.aufgabe10.Model;
import aufgaben.aufgabe11.View;

public class Controller {
	Point from;
	Point to;
	public boolean movePossible = true;
	
	
	public Point getFrom() {
		return from;
	}
	public void setFrom(Point from) {
		this.from = from;
	}
	public Point getTo() {
		return to;
	}
	public void setTo(Point to) {
		this.to = to;
	}
	
}
