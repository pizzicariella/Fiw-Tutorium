package aufgaben.aufgabe2;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MyIntegerTest {
	public static MyInteger i1, i2, i3, i4, i5, i6, i7;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		i1 = new MyInteger(127);
		i2 = new MyInteger(-2);
		i3 = new MyInteger(2147483647);
		i4 = new MyInteger(-2147483648);
		i5 = new MyInteger("127");
		i6 = new MyInteger("-2");
		i7 = new MyInteger("Hallo");
	}
	
	@Test 
	public void CompareDoubleAndIntValue() {
		assertTrue("doubleValue() oder intValue() wrong", i1.doubleValue()==(double)i1.intValue());
	}
	
	@Test 
	public void testEquals() {
		assertTrue("Die equals() Methode vergleicht nicht korrekt", i1.equals(i5));
	}
	
	@Test 
	public void testUnequals() {
		assertFalse("Die equals() Methode vergleicht nicht korrekt", i1.equals(i3));
	}
	
	@Test
	public void testExceptionCatch() {
		assertEquals("Der Objektvariable wurde kein Wert oder ein falscher Wert zugewiesen",i7.intValue(),0);
	}

	@Test
	public void testHashCode127() {
		assertEquals("Hash codes not equal", i1.hashCode(), i5.hashCode());
	}
	
	@Test
	public void testHashCodeMinus2() {
		assertEquals("Hash codes not equal", i2.hashCode(), i6.hashCode());
	}

	@Test
	public void testMyIntegerInt127() {
		assertNotNull("kein Objekt!", i1);
	}

	@Test
	public void testMyIntegerString127() {
		assertNotNull("kein Objekt!", i5);
	}

	@Test
	public void testCompareMyIntegerMyInteger() {
		assertEquals("compare(MyInteger, MyInteger) wrong!", MyInteger.compare(i1,i2), 127+2);
	}

	@Test
	public void testCompareIntInt() {
		assertEquals("compare(int, int) oder intValue() wrong!", MyInteger.compare(i6.intValue(),i5.intValue()), -2-127);
	}

	@Test
	public void testCompareTo() {
		assertEquals("compareTo(MyInteger) wrong!", i1.compareTo(i2), 127+2);
	}

	@Test
	public void testEqualsObjectTrue() {
		assertTrue("equals() wrong!", i2.equals(i6));
	}
	
	@Test
	public void testEqualsObjectFalse() {
		assertFalse("equals() wrong!", i2.equals(i3));
	}

	@Test
	public void testToString() {
		assertEquals("toString() wrong", i4.toString(), "-2147483648");
	}

	@Test
	public void testIntValue() {
		assertEquals("intValue() wrong", i4.intValue(), -2147483648);
	}

	@Test
	public void testDoubleValue() {
		assertTrue("doubleValue() wrong", i3.doubleValue() - 2147483647.0 == 0.0);
	}

	@Test
	public void testValueOfInt() {
		assertTrue("valueOf(int) wrong", (MyInteger.valueOf(-2147483648)).equals(i4));
	}

	@Test
	public void testValueOfString() {
		assertTrue("valueOf(String) wrong", (MyInteger.valueOf("-2147483648")).equals(i4));
	}

}
