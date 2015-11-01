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

import programme.SuiteChainee;
import programme.SuiteChaineeImpl;
import programme.Calculator;
import programme.CalculatorImpl;
import org.junit.Test;

public class ACBoiteBlanche {
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
	
	/*********************************************************/
	/*                Test SuiteChaineeImp                   */
	/*********************************************************/
	
	/**
	 * Tests chargerChaine
	 */
	@Test
	public void chargerChaineCoverage() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",5,true);
			s.chargerChaine(filePath2);
			assertEquals(s.toString(), filePath2 + " : -52, 0, 0, 0");
			assertEquals(s.getSize(), 4);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests sauvegarderChaine
	 */
	@Test
	public void sauvegarderChaineCoverage() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",5,true);
			s.sauvgarderChaine();
			assertTrue(true);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests getAt : position > BEGININDEX + sizeList
	 */
	@Test(expected = Exception.class)
	public void getAtOverSizeCoverage() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",5,true);
		s.getAt(10);
	}
	
	/**
	 * Tests removeItem
	 */
	@Test
	public void removeItemCoverage() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",5,true);
			s.removeItem(-10);
			assertEquals(s.toString(), filePath + " : -5, -5, -15, -25");
			assertEquals(s.getSize(), 4);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests removeItem : empty list
	 */
	@Test(expected = Exception.class)
	public void removeItemEmptyListCoverage() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",0,true);
		s.removeItem(-10);
	}
	
	/**
	 * Tests setAt
	 */
	@Test
	public void setAtCoverage() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",5,true);
			s.setAt(10,3);
			assertEquals(s.toString(), filePath + " : -5, -5, -10, 10, -25");
			assertEquals(s.getSize(), 5);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
		

	/**
	 * Tests isValide for size equal to 3 or more
	 */
	@Test
	public void isValideCoverageTSup3() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",4,true);
			assertTrue(s.isValide());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests isValide for size equal to 2 or less
	 */
	@Test
	public void isValideCoverageTinf3() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",2,true);
			assertTrue(s.isValide());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests isValide false
	 */
	@Test
	public void isValideCoverageFalse() {
		try{
			SuiteChainee s1 = new SuiteChaineeImpl(filePath,-5,-5,"add",2,true);
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"mul",2,false);
			assertFalse(s.isValide());
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}	
	
	
	/**
	 * Tests removeAt
	 */
	@Test
	public void removeAtCoverageZ() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",5,true);
			s.removeAt(0);
			assertEquals(s.toString(), filePath + " : -5, -10, -15, -25");
			assertEquals(s.getSize(), 4);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	

	
	/*********************************************************/
	/*                  Test Calculator                      */
	/*********************************************************/
	/**
	 * Tests setOp Fals
	 */
	@Test
	public void setOpCoverageF() {
		try{
			Calculator o= new CalculatorImpl();
			assertFalse(o.isValide("lol"));
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests add : b > 0 et a = Min Value
	 */
	@Test(expected = ArithmeticException.class)
	public void addBPosMinValCoverage() throws Exception {
		CalculatorImpl o= new CalculatorImpl();
		o.setOp("add");
		o.calcul(Integer.MIN_VALUE-1,5);
	}
	
	/**
	 * Tests add : b < 0 et a = Max Value
	 */
	@Test(expected = ArithmeticException.class)
	public void addBNegMaxValCoverage() throws Exception {
		CalculatorImpl o= new CalculatorImpl();
		o.setOp("add");
		o.calcul(Integer.MAX_VALUE+1,-5);
	}
	
	/**
	 * Tests add : b < 0 et a = Min Value
	 */
	@Test(expected = ArithmeticException.class)
	public void addBNegMinValCoverage() throws Exception {
		CalculatorImpl o= new CalculatorImpl();
		o.setOp("add");
		o.calcul(Integer.MIN_VALUE-1,-5);
	}
	
	/**
	 * Tests multiply : b > 0
	 */
	@Test
	public void multiplyBOverZeroCoverage() {
		try{
			CalculatorImpl o= new CalculatorImpl();
			o.setOp("mul");
			assertEquals(o.calcul(-1,3),-3);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests divide : a==b
	 */
	@Test
	public void divideAEqualBCoverage() {
		try{
			CalculatorImpl o= new CalculatorImpl();
			o.setOp("div");
			assertEquals(o.calcul(1,1),1);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	

	
	/**
	 * Tests divide : a = 0
	 */
	@Test
	public void divideAZeroCoverage() {
		try{
			CalculatorImpl o= new CalculatorImpl();
			o.setOp("div");
			assertEquals(o.calcul(0,-3),0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests divide b<0 et a<0 et a<=b
	 */
	@Test
	public void divideANegBNEgAInfBCoverage() {
		try{
			CalculatorImpl o= new CalculatorImpl();
			o.setOp("div");
			assertEquals(o.calcul(-2,-1),2);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests divide b>0 et a<0 et -a>b
	 */
	@Test
	public void divideANegBPosASupBCoverage() {
		try{
			CalculatorImpl o= new CalculatorImpl();
			o.setOp("div");
			assertEquals(o.calcul(-2,1),-2);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * Tests divide b<0 et a>0 et a>=-b
	 */
	@Test
	public void divideAPosBNEgASupBCoverage() {
		try{
			CalculatorImpl o= new CalculatorImpl();
			o.setOp("div");
			assertEquals(o.calcul(4,-3),-1);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}

	


}

