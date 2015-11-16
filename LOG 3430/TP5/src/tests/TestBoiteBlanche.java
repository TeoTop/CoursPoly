package tests;

import org.junit.Test;

import programme.MyList;
import programme.MyListImpl;

import static org.junit.Assert.*;

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.NoSuchElementException;

public class TestBoiteBlanche {
	/**
	* Test getCurrent();
	* List : 4
	**/
	@Test
	public void testCurrent() {
		MyList list = new MyListImpl();
		list.add(4);
		assertNotNull(list.getCurrent());
	}
	
	/**
	* Test get Out of Bounds index
	* List : 4, 8
	* getAt : 2
	* List Result : 4, 8;
	* Excpetion : ArrayIndexOutOfBoundsException
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testgetAtOutOfBounds() throws Exception {
		MyList list = new MyListImpl();
		list.add(4);
		list.add(8);
		list.getAt(2);
	}
	
	/**
	* Test add second element 
	* List : 4
	* Add : 8
	* List Result : 4, 8;
	**/
	@Test
	public void testAddSecondElem() {
		MyList list = new MyListImpl();
		list.add(4);
		list.add(8);
		assertEquals(list.getAt(1), 8);
	}
	
	/**
	* Test removeAt last element 
	* List : 4, 8, 12
	* RemoveAt : 2
	* List Result : 4, 8;
	**/
	@Test
	public void testRemoveAtSecondElem() {
		MyList list = new MyListImpl();
		list.add(4);
		list.add(8);
		list.add(12);
		list.removeAt(2);
		assertEquals(list.getSize(), 2);
		assertEquals(list.getAt(1), 8);
	}
	
	/**
	* Test removeItem second element 
	* List : 4, 8, 12
	* RemoveItem : 12
	* List Result : 4, 8;
	**/
	@Test
	public void testRemoveItemSecondElem() {
		MyList list = new MyListImpl();
		list.add(4);
		list.add(8);
		list.add(12);
		list.removeItem(12);
		assertEquals(list.getSize(), 2);
		assertEquals(list.getAt(0), 4);
	}
	
	/**
	* Test setAt second element 
	* List : 4, 8
	* SetAt : 1, 12
	* List Result : 4, 12;
	**/
	@Test
	public void testSetAtSecondElem() {
		MyList list = new MyListImpl();
		list.add(4);
		list.add(8);
		list.setAt(12, 1);
		assertEquals(list.getSize(), 2);
		assertEquals(list.getAt(1), 12);
	}
	
	/**
	* Test setAt Out of Bounds
	* List : 4, 8
	* SetAt : 3, 12
	* List Result : 4, 8;
	* Exception : ArrayIndexOutOfBoundsException
	**/
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testSetAtOutOfBounds() {
		MyList list = new MyListImpl();
		list.add(4);
		list.add(8);
		list.setAt(12, 3);
	}
}

//(expected=NoSuchElementException.class)