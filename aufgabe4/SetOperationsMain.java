package aufgaben.aufgabe4;

public class SetOperationsMain {

	public static void main(String[] args) {
		SetOperations so1=new SetOperations();
		SetOperations so2=new SetOperations();
		SetOperations so3=new SetOperations();
		so1.fill();
		so1.fillBothUnion();
		so1.printHeaderUnion();
		so1.print();
		System.out.println();
		System.out.println();

		
		so2.fill();
		so2.fillBothIntersection();
		so2.printHeaderIntersection();
		so2.print();
		System.out.println();
		System.out.println();

		
		so3.fill();
		so3.fillBothDifference();
		so3.printHeaderDifference();
		so3.print();
		System.out.println();
		System.out.println();

	}

}
