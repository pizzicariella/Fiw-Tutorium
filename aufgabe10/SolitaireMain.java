package aufgaben.aufgabe10;

import java.awt.Point;

public class SolitaireMain {

	public static void main(String[] args) {
		Model sol = new Model();
		sol.initialiseModel();
		//sol.print();
		sol.move(new Point(3,5), new Point(3,3));
		sol.print();
		System.out.println();
		sol.lastMoveUndo();
		sol.print();
	}

}
