package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.naming.SizeLimitExceededException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import programme.Elem;
import programme.MyList;
import programme.MyListImpl;
import programme.SuiteChainee;
import programme.SuiteChaineeImpl;
import programme.Calculator;
import programme.CalculatorImpl;

import org.junit.Test;

public class TestsEtat {
	private static String wrongFilePath;
	private static String filePath;
	private static String filePath2;
	
	@BeforeClass
	public static void init(){
		wrongFilePath="src/Timote/chaine.properties";
		filePath="src/Files/chaine.properties";
		filePath2="src/Files/chaine2.properties";
	}
	
	@AfterClass
	public static void end() throws FileNotFoundException, IOException{
		new RandomAccessFile(filePath, "rw").setLength(0);
	}
	
	@Before
	public void reset() throws FileNotFoundException, IOException{
		new RandomAccessFile(filePath, "rw").setLength(0);
	}
	/****
	 *Vide: myListImpl.init==null
	 *NonVide: myListImpl.init!=null
	 *Failure: Etat final exception
	 */
	
	/*********************************************************/
	/*                Test SuiteChaineeImp                   */
	/*********************************************************/
	
	/**
	 * Sequence : MyListImpl(), reset()
	 * Oracle: Vide
	 */
	@Test
	public void TE1() {
		try{
			MyList l = new MyListImpl();
			l.reset();
			assertNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), toString()
	 * Oracle: Vide
	 */
	@Test
	public void TE2() {
		try{
			MyList l = new MyListImpl();
			l.toString();
			assertNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), getInit()
	 * Oracle: Vide
	 */
	@Test
	public void TE3() {
		try{
			MyList l = new MyListImpl();
			assertNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), getSize()
	 * Oracle: Vide
	 */
	@Test
	public void TE4() {
		try{
			MyList l = new MyListImpl();
			assertEquals(l.getSize(),0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e==null]
	 * Oracle: Failure
	 */
	@Test(expected = Exception.class)
	public void TE5() throws Exception {
		MyList l = new MyListImpl();
		l.add(null);
	}
	
	/**
	 * Sequence : MyListImpl(), removeAt(0)
	 * Oracle: Failure
	 */
	@Test(expected = Exception.class)
	public void TE6() throws Exception {
		MyList l = new MyListImpl();
		l.removeAt(0);
	}
	
	/**
	 * Sequence : MyListImpl(), removeItem(0)
	 * Oracle: Failure
	 */
	@Test(expected = Exception.class)
	public void TE7() throws Exception {
		MyList l = new MyListImpl();
		l.removeItem(0);
	}
	/**
	 * Sequence : MyListImpl(), setAt(0,0)
	 * Oracle: Failure
	 */
	@Test(expected = Exception.class)
	public void TE8() throws Exception {
		MyList l = new MyListImpl();
		l.setAt(0,0);
	}
	
	/**
	 * Sequence : MyListImpl(), getAt(0)
	 * Oracle: Failure
	 */
	@Test(expected = Exception.class)
	public void TE9() throws Exception {
		MyList l = new MyListImpl();
		l.getAt(0);
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], reset()
	 * Oracle: Vide
	 */
	@Test
	public void TE10() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.reset();
			assertNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], removeItem(1)[e==init && getSize()==1]
	 * Oracle: Vide
	 */
	@Test
	public void TE11() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.removeItem(1);
			assertNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], removeAt(i)[i==BEGININDEX && getSize()==1]
	 * Oracle: Vide
	 */
	@Test
	public void TE12() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.removeAt(0);
			assertNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], add(e)[e!=null]
	 * Oracle: NonVide
	 */
	@Test
	public void TE13() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.add(e);
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], getAt(i)[i>=BEGININDEX && i<getSize()+BEGININDEX]
	 * Oracle: NonVide
	 */
	@Test
	public void TE14() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.getAt(0);
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], setAt(e,i)[i>=BEGININDEX && i<getSize()+BEGININDEX]
	 * Oracle: NonVide
	 */
	@Test
	public void TE15() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.setAt(2,0);
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], getInit()
	 * Oracle: NonVide
	 */
	@Test
	public void TE16() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.getInit();
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], toString()
	 * Oracle: NonVide
	 */
	@Test
	public void TE17() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.toString();
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], getSize()
	 * Oracle: NonVide
	 */
	@Test
	public void TE18() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.getSize();
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], add(e)[e!=null],removeAt(i)[i>=BEGININDEX && i<getSize()+BEGININDEX &&getSize()>1]
	 * Oracle: NonVide
	 */
	@Test
	public void TE19() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.add(e);
			l.add(e);
			l.removeAt(1);
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Sequence : MyListImpl(), add(e)[e!=null], add(e)[e!=null],removeItem(e)[ NonVide contains e &&getSize()>1]
	 * Oracle: NonVide
	 */
	@Test
	public void TE20() {
		try{
			MyList l = new MyListImpl();
			Elem e= new Elem(1);
			l.add(e);
			l.add(e);
			l.removeItem(1);
			assertNotNull(l.getInit());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	// 19 a 26


	/**
	* Sequence : MyListImpl(), add(e1!null), setAt(e2,i < BEGININDEX )
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE21() throws Exception{
		MyList l = new MyListImpl();
		Elem e = new Elem(10);
		l.add(e);
		l.setAt(5,-1);
		
	}

	/**
	* Sequence : MyListImpl(), add(e1!null), setAt(e2,i >= getsize() + BEGININDEX )
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE22() throws Exception{
		MyList l = new MyListImpl();
		Elem e = new Elem(10);
		l.add(e);
		l.setAt(5,2);
		
	}


	/**
	* Sequence : MyListImpl(), add(e1!null), getAt(i < BEGININDEX )
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE23() throws Exception{
		MyList l = new MyListImpl();
		Elem e = new Elem(10);
		l.add(e);
		l.getAt(-1);
		
	}

	/**
	* Sequence : MyListImpl(), add(e1!null), getAt(i >= getsize() + BEGININDEX )
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE24() throws Exception{
		MyList l = new MyListImpl();
		Elem e = new Elem(10);
		l.add(e);
		l.getAt(2);
		
	}


	/**
	* Sequence : MyListImpl(), add(e1!null), removeAt(i < BEGININDEX )
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE25() throws Exception{
		MyList l = new MyListImpl();
		Elem e = new Elem(10);
		l.add(e);
		l.removeAt(-1);
		
	}

	/**
	* Sequence : MyListImpl(), add(e1!null), removeAt(i >= getsize() + BEGININDEX )
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE26() throws Exception{
		MyList l = new MyListImpl();
		Elem e = new Elem(10);
		l.add(e);
		l.removeAt(2);
		
	}


	/**
	* Sequence : MyListImpl(), add(e1!null), removeItem(e2!=e1)
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE27() throws Exception{
		MyList l = new MyListImpl();
		Elem e1 = new Elem(10);
		l.add(e1);
		l.removeItem(5);
	}


	/**
	* Sequence : MyListImpl(), add(e1!null), add(e2==null)
	* Oracle : Failure
	*/
	@Test(expected = Exception.class)
	public void TE28() throws Exception{
		MyList l = new MyListImpl();
		Elem e = new Elem(10);
		l.add(e);
		l.add(null);
		
	}
	
}

