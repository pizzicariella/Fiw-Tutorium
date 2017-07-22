package aufgaben.aufgabe2;

public class MyInteger 
{
	private int value;
	
	public MyInteger(int value)
	{
		this.value=value;
	}
	
	public MyInteger(String s)
	{
		try
		{
			this.value=Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Der übergebene Wert entspricht keinem Integer Wert. Dem Object wird der Wert 0 zugewiesen.");
			this.value=0;
		}
	}
	
	public static int compare(MyInteger i1, MyInteger i2)
	{
		if(i1.value == i2.value)
		{
			return 0;
		}
		else if(i1.value < i2.value)
		{
			return -(Math.abs(i1.value)+Math.abs(i2.value));
		}
		else
		{
			return Math.abs(i1.value)+Math.abs(i2.value);
		}
	}
	
	public static int compare(int i1, int i2)
	{
		if(i1 == i2)
		{
			return 0;
		}
		else if(i1 < i2)
		{
			return -(Math.abs(i1)+Math.abs(i2));
		}
		else
		{
			return Math.abs(i1)+Math.abs(i2);
		}
	}
	
	public int compareTo(MyInteger otherMyInteger)
	{
		if(this.value == otherMyInteger.value)
		{
			return 0;
		}
		else if(this.value < otherMyInteger.value)
		{
			return -(Math.abs(this.value)+Math.abs(otherMyInteger.value));
		}
		else
		{
			return Math.abs(this.value)+Math.abs(otherMyInteger.value);
		}
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null) {return false;}
		else if(this == o) {return true;}
		else if(this.getClass() != o.getClass()) {return false;}
		
		MyInteger newO = (MyInteger)o;
		if(this.value == newO.value)
		{
			return true;
		}
		else {return false;}
	}
	
	@Override
	public int hashCode()
	{
		return this.value*13+27;
	}
	
	@Override
	public String toString()
	{
		String s = String.valueOf(this.value);
		return s;
	}
	
	public int intValue()
	{
		return this.value;
	}
	
	public double doubleValue()
	{
		double d = (double)this.value;
		return d;
	}
	
	public static MyInteger valueOf(int i)
	{
		return new MyInteger(i);
	}
	
	public static MyInteger valueOf(String s) throws NumberFormatException
	{
		return new MyInteger(s);
	}
}
