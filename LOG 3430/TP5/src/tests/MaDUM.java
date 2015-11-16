package tests;

import org.junit.Test;

import programme.MyList;
import programme.MyListImpl;

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.NoSuchElementException;

public class MaDUM {
	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), add(4), getSize(), removeAt(0), getSize(), removeItem(4), getSize(), reset(), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS1()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.reset();
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), add(4), getSize(), removeAt(0), getSize(), reset(), getSize(), removeItem(4), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS2()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeItem(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), add(4), getSize(), reset(), getSize(), removeAt(0), getSize(), removeItem(4), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS3()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.add(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.removeItem(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), add(4), getSize(), reset(), getSize(), removeItem(4), getSize(), removeAt(0), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS4()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.add(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), add(4), getSize(), removeItem(4), getSize(), reset(), getSize(), removeAt(0), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS5()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeAt(0);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), add(4), getSize(), removeItem(4), getSize(), removeAt(0), getSize(), reset(), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS6()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.reset();
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), reset(), getSize(), add(4), getSize(), removeAt(0), getSize(), removeItem(4), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS7()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.reset();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.removeItem(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), reset(), getSize(), add(4), getSize(), removeItem(4), getSize(), removeAt(0), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS8()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.reset();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), reset(), getSize(), removeItem(4), getSize(), add(4), getSize(), removeAt(0), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS9()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.reset();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), reset(), getSize(), removeItem(4), getSize(), removeAt(0), getSize(), add(4), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS10()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.reset();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.add(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), reset(), getSize(), removeAt(0), getSize(), add(4), getSize(), removeItem(4), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS11()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.reset();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeItem(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), reset(), getSize(), removeAt(0), getSize(), removeItem(4), getSize(), add(4), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS12()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.reset();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.add(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeItem(4), getSize(), reset(), getSize(), add(4), getSize(), removeAt(0), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS13()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeItem(4), getSize(), reset(), getSize(), removeAt(0), getSize(), add(4), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS14()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.add(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeItem(4), getSize(), removeAt(0), getSize(), reset(), getSize(), add(4), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS15()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.reset();
		list.getSize();
		list.add(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeItem(4), getSize(), removeAt(0), getSize(), add(4), getSize(), reset(), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS16()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.add(4);
		list.getSize();
		list.reset();
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeItem(4), getSize(), add(4), getSize(), reset(), getSize(), removeAt(0), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS17()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.add(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeAt(0);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeItem(4), getSize(), add(4), getSize(), removeAt(0), getSize(), reset(), getSize(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS18()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.reset();
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeAt(0), getSize(), removeItem(4), getSize(), reset(), getSize(), add(4), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS19()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.add(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeAt(0), getSize(), removeItem(4), getSize(), add(4), getSize(), reset(), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS20()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.add(4);
		list.getSize();
		list.reset();
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeAt(0), getSize(), add(4), getSize(), removeItem(4), getSize(), reset(), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS21()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.reset();
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeAt(0), getSize(), add(4), getSize(), reset(), getSize(), removeItem(4), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS22()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.add(4);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeItem(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeAt(0), getSize(), reset(), getSize(), add(4), getSize(), removeItem(4), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS23()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.reset();
		list.getSize();
		list.add(4);
		list.getSize();
		list.removeItem(4);
		list.getSize();
	}

	/**
	*Attribut: size
	*sequence: MyListImpl(), getSize(), removeAt(0), getSize(), reset(), getSize(), removeItem(4), getSize(), add(4), getSize(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS24()throws Exception{
		MyList list = new MyListImpl();
		list.getSize();
		list.removeAt(0);
		list.getSize();
		list.reset();
		list.getSize();
		list.removeItem(4);
		list.getSize();
		list.add(4);
		list.getSize();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), add(4), getStart(), setAt(6,0), getStart(), getAt(0), getStart(), removeAt(0), getStart(), removeItem(4), getStart(), reset(), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS25()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.add(4);
		list.getStart();
		list.setAt(6,0);
		list.getStart();
		list.getAt(0);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.reset();
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), add(4), getStart(), removeAt(0), getStart(), reset(), getStart(), removeItem, getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS26()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeItem(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), add(4), getStart(), reset(), getStart(), removeItem(4), getStart(), removeAt(0), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS27()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.add(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), add(4), getStart(), reset(), getStart(), removeAt(0), getStart(), removeItem(4), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS28()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.add(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.removeItem(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), add(4), getStart(), removeItem(4), getStart(), removeAt(0), getStart(), reset(), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS29()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.reset();
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), add(4), getStart(), removeItem(4), getStart(), reset(), getStart(), removeAt(0), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS30()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeAt(0);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeAt(0), getStart(), add(4), getStart(), removeItem(4), getStart(), reset(), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS31()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.reset();
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeAt(0), getStart(), add(4), getStart(), reset(), getStart(), removeItem(4), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS32()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.add(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeItem(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeAt(0), getStart(), reset(), getStart(), removeItem(4), getStart(), add(4), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS33()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.add(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeAt(0), getStart(), reset(), getStart(), add(4), getStart(), removeItem(4), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS34()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.reset();
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeItem(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeAt(0), getStart(), removeItem(4), getStart(), add(4), getStart(), reset(), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS35()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.add(4);
		list.getStart();
		list.reset();
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeAt(0), getStart(), removeItem(4), getStart(), reset(), getStart(), add(4), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS36()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.add(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeItem(4), getStart(), add(4), getStart(), removeAt(0), getStart(), reset(), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS37()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.reset();
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeItem(4), getStart(), add(4), getStart(), reset(), getStart(), removeAt(0), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS38()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.add(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeAt(0);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeItem(4), getStart(), reset(), getStart(), removeAt(0), getStart(), add(4), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS39()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.add(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeItem(4), getStart(), reset(), getStart(), add(4), getStart(), removeAt(0), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS40()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.reset();
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeItem(4), getStart(), removeAt(0), getStart(), add(4), getStart(), reset(), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS41()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.add(4);
		list.getStart();
		list.reset();
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), removeItem(4), getStart(), removeAt(0), getStart(), reset(), getStart(), add(4), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS42()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.reset();
		list.getStart();
		list.add(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), reset(), getStart(), add(4), getStart(), removeAt(0), getStart(), removeItem(4), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS43()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.reset();
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.removeItem(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), reset(), getStart(), add(4), getStart(), removeItem(4), getStart(), removeAt(0), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS44()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.reset();
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), reset(), getStart(), removeItem(4), getStart(), removeAt(0), getStart(), add(4), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS45()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.reset();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.add(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), reset(), getStart(), removeItem(4), getStart(), add(4), getStart(), removeAt(0), getStart(), 
	**/
	@Test(expected=NoSuchElementException.class)
	public void testS46()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.reset();
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeAt(0);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), reset(), getStart(), removeAt(0), getStart(), add(4), getStart(), removeItem(4), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS47()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.reset();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.add(4);
		list.getStart();
		list.removeItem(4);
		list.getStart();
	}

	/**
	*Attribut: start
	*sequence: MyListImpl(), getStart(), reset(), getStart(), removeAt(0), getStart(), removeItem(4), getStart(), add(4), getStart(), 
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testS48()throws Exception{
		MyList list = new MyListImpl();
		list.getStart();
		list.reset();
		list.getStart();
		list.removeAt(0);
		list.getStart();
		list.removeItem(4);
		list.getStart();
		list.add(4);
		list.getStart();
	}


}
