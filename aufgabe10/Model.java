package aufgaben.aufgabe10;

import java.awt.Point;
import java.util.LinkedList;

public class Model {
	private State[][] field;	//Spielfeld	
	LinkedList<Point> moves;	//zuege
	
	public Model() {
		this.field=new State[7][7];
		this.moves = new LinkedList<Point>();
		initialiseModel();
		print();
	}
	
	public void initialiseModel() {
		for(int i = 0; i<this.field.length;i++) {
			for(int j =0; j<this.field.length;j++) {
				if((i != 2 && i!=3 && i!=4) && (j != 2 && j!=3 && j!=4)) {
					this.field[i][j]= State.NOT;
				}
				else  {
					this.field[i][j]= State.USED;
				}
			}
		}
		this.field[3][3] =State.FREE;
		moves.clear();
	}
	
	public void print() {
		for(int i =0;i<this.field.length;i++) {
			for(int j =0;j<this.field.length;j++) {
				System.out.print(" ");
				if(this.field[i][j] == State.FREE || this.field[i][j] == State.NOT){
					System.out.print(" ");
				}
				else {
					System.out.print("o");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private boolean pointsValid(Point from, Point to){
		if(from.equals(to)) {
			//System.out.println("equals");
			return false;
		}
		if(Math.abs(from.x - to.x) != 2 && Math.abs(from.y - to.y) !=2) {
			//System.out.println("abstand");
			return false;
		}
		if(Math.abs(from.x - to.x) != 0 && Math.abs(from.y - to.y) !=0) {
			//System.out.println("abstand");
			return false;
		}
		else {
			//System.out.println("gut");
			return true;
		}
	}
	
	public void move(Point from, Point to) {
		if(this.pointsValid(from, to)) {
			int mX; int mY;
			if(from.x - to.x == 0) {
				mX = from.x;
				if(from.y > to.y) {
					mY = from.y-1;
				}
				else {
					mY =from.y+1;
				}
			}
			else {
				mY =from.y;
				if(from.x> to.x) {
					mX = from.x-1;
				}
				else{
					mX =from.x+1;
				}
			}
			Point middle = new Point(mX,mY);
			
			if(this.field[from.x][from.y] == State.USED && this.field[to.x][to.y]==State.FREE && 
					this.field[middle.x][middle.y] == State.USED) {
				this.field[from.x][from.y] = State.FREE;
				this.field[to.x][to.y]= State.USED;
				this.field[middle.x][middle.y] =State.FREE;
			}
			moves.add(from);
			moves.add(middle);
			moves.add(to);
		}
	}
	public void lastMoveUndo() {
		if(!moves.isEmpty()) {
			int laenge =moves.size();
			this.field[moves.get(laenge-1).x][moves.get(laenge-1).y] = State.FREE;
			this.field[moves.get(laenge-2).x][moves.get(laenge-2).y] = State.USED;
			this.field[moves.get(laenge-3).x][moves.get(laenge-3).y] = State.USED;
			
			moves.remove(laenge-1);
			moves.remove(laenge-2);
			moves.remove(laenge-3);
		}
	}
}
