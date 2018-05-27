package aufgaben.aufgabe4;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SetOperations {
	
		private Set<Integer> numbers1;
		private Set<Integer> numbers2;
		private Set<Integer> both;
		
		public SetOperations()
		{
			this.numbers1=new TreeSet<>();
			this.numbers2=new TreeSet<>();
			this.both=new TreeSet<>();
		}
		
		public void fill()
		{
			Random r = new Random();
			for(int i=0;i<100;i++)
			{
				this.numbers1.add(Integer.valueOf(r.nextInt(100)));
				this.numbers2.add(Integer.valueOf(r.nextInt(100)));
			}
		}
		
		public void fillBothUnion()
		{
			this.both.clear();
			this.both.addAll(this.numbers1);
			this.both.addAll(this.numbers2);
		}
		
		public void fillBothIntersection()
		{
			Set<Integer> intersection = new TreeSet<>();
			intersection.addAll(this.numbers1);
			intersection.retainAll(this.numbers2);
			this.both.clear();
			this.both.addAll(intersection);
		}
		
		public void fillBothDifference()
		{
			Set<Integer> difference =new TreeSet<>();
			difference.addAll(this.numbers1);
			difference.removeAll(this.numbers2);
			this.both.clear();
			this.both.addAll(difference);
		}
		
		public void print()
		{
			int c1=0;
			int c2=0;
			int b=0;
			for(int j=0;j<10;j++)
			{
				for(int i=0; i<10;i++)
				{
					if(this.numbers1.contains(Integer.valueOf(c1++)))
					{
						System.out.print(" "+'\u25cf');
					}
					else
					{
						System.out.print(" "+'\u2009');
					}
				}
				System.out.print("          ");
				for(int i=0; i<10;i++)
				{
					if(this.numbers2.contains(Integer.valueOf(c2++)))
					{
						System.out.print(" "+'\u25cf');
					}
					else
					{
						System.out.print(" "+'\u2009');
					}
				}
				System.out.print("          ");
				for(int i=0; i<10;i++)
				{
					if(this.both.contains(Integer.valueOf(b++)))
					{
						System.out.print(" "+'\u25cf');
					}
					else
					{
						System.out.print(" "+'\u2009');
					}
				}
				System.out.println();
			}
		}
		
		public void printHeaderUnion()
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("         A                             B                           A \u222a B   ");
			System.out.println("--------------------------------------------------------------------------------");
			
		}
		
		public void printHeaderIntersection()
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("         A                             B                           A \u2229 B   ");
			System.out.println("--------------------------------------------------------------------------------");
			
		}
		
		public void printHeaderDifference()
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("         A                             B                           A - B   ");
			System.out.println("--------------------------------------------------------------------------------");
			
		}

	

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
