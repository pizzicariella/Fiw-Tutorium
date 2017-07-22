package aufgaben.aufgabe3;

import java.util.Random;

public class Dame {
	
	int[] damen;
	Random r;

	
	public Dame()
	{
		this.damen= new int[8];
		this.r=new Random();
	}
	
	public boolean checkSpalte(int zahl, int index)
	{
		int spalte=zahl;
		for(int i=0; i<index;i++)
		{
			if(spalte==this.damen[i])
			{
				return false;
			}			
		}
		return true;
	}
	
	public boolean checkDiagonal(int zahl, int index)
	{
		for(int i=0; i<index;i++)
		{
			if(this.damen[i] == zahl-(index-i) || this.damen[i]==zahl+(index-i) )
			{
				return false;
			}
		}
		return true;
	}
	public void fillArray()
	{
		int spalte=this.r.nextInt(8);
		this.damen[0] = r.nextInt(8);
		int j =0;
		for(int i=1;i<this.damen.length;i++)
		{
			j=0;
			while(!((this.checkSpalte(spalte, i)) && (this.checkDiagonal(spalte, i))))
			{
				spalte = this.r.nextInt(8);
				j++;
				if(j>80) 
				{
					i-=1;
					break;
				}
			}
			if(j<=80)
			{
				this.damen[i]= spalte;
			}
		}
	}
	
	@Override
	public String toString()
	{
		String s="";
		for(int i =0; i<this.damen.length;i++)
		{
			s+=String.valueOf(this.damen[i]);
			
		}
		return s;
	}
	
	public void print()
	{
		System.out.println(this.toString());
	}

	public static void main(String[] args) {
		
		Dame d1 = new Dame();
		d1.fillArray();
		d1.print();
		

	}

}
