package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import programme.SuiteChainee;
import programme.SuiteChaineeImpl;

public class ACEmptyFileTest {
	
	private static String wrongFilePath;
	private static String filePath;
	
	@BeforeClass
	public static void init(){
		wrongFilePath="src/Timote/chaine.properties";
		filePath="src/Files/chaine.properties";
	}
	
	@AfterClass
	public static void end() throws FileNotFoundException, IOException{
		new RandomAccessFile(filePath, "rw").setLength(0);
	}
	
	@Before
	public void reset() throws FileNotFoundException, IOException{
		new RandomAccessFile(filePath, "rw").setLength(0);
	}
	
	
	public void setFile() throws FileNotFoundException, IOException{
		//SuiteChaineeImpl set = new SuiteChaineeImpl(filePath, val1, val2, operateur, tailleListe, vide) 
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -10
	 * val2 = 10
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC1() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-10,10,"add",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -9
	 * val2 = 9
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC2() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,9,"add",0,false);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -8
	 * val2 = 8
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC3() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-8,8,"add",1,true);
			assertEquals(s.toString(), filePath + " : -8");
			assertEquals(s.getSize(), 1);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -7
	 * val2 = 7
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC4() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,7,"add",1,false);
			assertEquals(s.toString(), filePath + " : -7");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -6
	 * val2 = 6
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC5() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-6,6,"add",2,true);
			assertEquals(s.toString(), filePath + " : -6, 6");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -5
	 * val2 = 5
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC6() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,5,"add",2,false);
			assertEquals(s.toString(), filePath + " : -5, 5");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -4
	 * val2 = -4
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC7() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-4,-4,"add",3,true);
			assertEquals(s.toString(), filePath + " : -4, -4, -8");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 3
	 * val2 = 3
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC8() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,3,"add",3,false);
			assertEquals(s.toString(), filePath + " : 3, 3, 6");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -2
	 * val2 = 2
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC9() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-2,2,"add",10,true);
			assertEquals(s.toString(), filePath + " : -2, 2, 0, 2, 2, 4, 6, 10, 16, 26");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 1
	 * val2 = -1
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC10() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,-1,"add",10,false);
			assertEquals(s.toString(), filePath + " : 1, -1, 0, -1, -1, -2, -3, -5, -8, -13");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 0
	 * val2 = 0
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC11() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,0,0,"add",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 1
	 * val2 = 0
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC12() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,0,"add",0,false);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 2
	 * val2 = 0
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC13() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,0,"add",1,true);
			assertEquals(s.toString(), filePath + " : 2");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 3
	 * val2 = 0
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC14() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,0,"add",1,false);
			assertEquals(s.toString(), filePath + " : 3");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 4
	 * val2 = 0
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC15() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,4,0,"add",2,true);
			assertEquals(s.toString(), filePath + " : 4, 0");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 5
	 * val2 = 0
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC16() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,5,0,"add",2,false);
			assertEquals(s.toString(), filePath + " : 5, 0");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 6
	 * val2 = 0
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC17() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,6,0,"add",3,true);
			assertEquals(s.toString(), filePath + " : 6, 0, 6");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -7
	 * val2 = 0
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC18() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,0,"add",3,false);
			assertEquals(s.toString(), filePath + " : -7, 0, -7");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 8
	 * val2 = 0
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC19() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,0,"add",10,true);
			assertEquals(s.toString(), filePath + " : 8, 0, 8, 8, 16, 24, 40, 64, 104, 168");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -9
	 * val2 = 0
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC20() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,0,"add",10,false);
			assertEquals(s.toString(), filePath + " : -9, 0, -9, -9, -18, -27, -45, -72, -117, -189");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -10
	 * val2 = 10
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC21() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-10,10,"sub",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -9
	 * val2 = 9
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC22() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,9,"sub",0,false);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -8
	 * val2 = 8
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC23() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-8,8,"sub",1,true);
			assertEquals(s.toString(), filePath + " : -8");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -7
	 * val2 = 7
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC24() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,-7,"sub",1,false);
			assertEquals(s.toString(), filePath + " : -7");
			assertEquals(s.getSize(), 1);	
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -6
	 * val2 = 6
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC25() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-6,6,"sub",2,true);
			assertEquals(s.toString(), filePath + " : -6, 6");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -5
	 * val2 = 5
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC26() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,5,"sub",2,false);
			assertEquals(s.toString(), filePath + " : -5, 5");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -4
	 * val2 = -4
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC27() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-4,-4,"sub",3,true);
			assertEquals(s.toString(), filePath + " : -4, -4, 0");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 3
	 * val2 = 3
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC28() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,3,"sub",3,false);
			assertEquals(s.toString(), filePath + " : 3, 3, 0");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -2
	 * val2 = 2
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC29() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-2,2,"sub",10,true);
			assertEquals(s.toString(), filePath + " : -2, 2, -4, 6, -10, 16, -26, 42, -68, 110");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 1
	 * val2 = -1
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC30() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,-1,"sub",10,false);
			assertEquals(s.toString(), filePath + " : 1, -1, 2, -3, 5, -8, 13, -21, 34, -55");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 0
	 * val2 = 0
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC31() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,0,0,"sub",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 1
	 * val2 = 0
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC32() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,0,"sub",0,false);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 2
	 * val2 = 0
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC33() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,0,"sub",1,true);
			assertEquals(s.toString(), filePath + " : 2");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 3
	 * val2 = 0
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC34() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,0,"sub",1,false);
			assertEquals(s.toString(), filePath + " : 3");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 4
	 * val2 = 0
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC35() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,4,0,"sub",2,true);
			assertEquals(s.toString(), filePath + " : 4, 0");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 5
	 * val2 = 0
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC36() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,5,0,"sub",2,false);
			assertEquals(s.toString(), filePath + " : 5, 0");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 6
	 * val2 = 0
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC37() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,6,0,"sub",3,true);
			assertEquals(s.toString(), filePath + " : 6, 0, 6");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -7
	 * val2 = 0
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC38() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,0,"sub",3,false);
			assertEquals(s.toString(), filePath + " : -7, 0, -7");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 8
	 * val2 = 0
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC39() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,0,"sub",10,true);
			assertEquals(s.toString(), filePath + " : 8, 0, 8, -8, 16, -24, 40, -64, 104, -168");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -9
	 * val2 = 0
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC40() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,0,"sub",10,false);
			assertEquals(s.toString(), filePath + " : -9, 0, -9, 9, -18, 27, -45, 72, -117, 189");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -10
	 * val2 = 10
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC41() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-10,10,"mul",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);	
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -9
	 * val2 = 9
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC42() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,9,"mul",0,false);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -8
	 * val2 = 8
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC43() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-8,8,"mul",1,true);
			assertEquals(s.toString(), filePath + " : -8");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 =-7
	 * val2 = 7
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC44() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,7,"mul",1,false);
			assertEquals(s.toString(), filePath + " : -7");
			assertEquals(s.getSize(), 1);	
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -6
	 * val2 = 6
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC45() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-6,6,"mul",2,true);
			assertEquals(s.toString(), filePath + " : -6, 6");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -5
	 * val2 = 5
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC46() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,5,"mul",2,false);
			assertEquals(s.toString(), filePath + " : -5, 5");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -4
	 * val2 = -4
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC47() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-4,-4,"mul",3,true);
			assertEquals(s.toString(), filePath + " : -4, -4, 16");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 3
	 * val2 = 3
	 * taille = 3
	 * etat = vide
	 */
	@Test
	public void AC48() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,3,"mul",3,false);
			assertEquals(s.toString(), filePath + " : 3, 3, 9");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -2
	 * val2 = 2
	 * taille = 10
	 * etat = vide
	 */
	/*@Test(expected = ArithmeticException.class)
	public void AC49() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,-2,2,"mul",10,true);
		fail("Erreur - Exception overflow MAXINT attendu");
	}*/
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 1
	 * val2 = -1
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC50() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,-1,"mul",10,false);
			assertEquals(s.toString(), filePath + " : 1, -1, -1, 1, -1, -1, 1, -1, -1, 1");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 0
	 * val2 = 0
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC51() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,0,0,"mul",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 1
	 * val2 = 0
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC52() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,0,"mul",0,false);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 2
	 * val2 = 0
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC53() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,0,"mul",1,true);
			assertEquals(s.toString(), filePath + " : 2");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 3
	 * val2 = 0
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC54() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,0,"mul",1,false);
			assertEquals(s.toString(), filePath + " : 3");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 4
	 * val2 = 0
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC55() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,4,0,"mul",2,true);
			assertEquals(s.toString(), filePath + " : 4, 0");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 5
	 * val2 = 0
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC56() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,5,0,"mul",2,false);
			assertEquals(s.toString(), filePath + " : 5, 0");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 6
	 * val2 = 0
	 * taille = 9
	 * etat = vide
	 */
	@Test
	public void AC57() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,6,0,"mul",3,true);
			assertEquals(s.toString(), filePath + " : 6, 0, 0");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -7
	 * val2 = 0
	 * taille = 9
	 * etat = vide
	 */
	@Test
	public void AC58() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,0,"mul",3,false);
			assertEquals(s.toString(), filePath + " : -7, 0, 0");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 8
	 * val2 = 0
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC59() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,0,"mul",10,true);
			assertEquals(s.toString(), filePath + " : 8, 0, 0, 0, 0, 0, 0, 0, 0, 0");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -9
	 * val2 = 0
	 * taille = 10
	 * etat = vide
	 */
	@Test
	public void AC60() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,0,"mul",10,false);
			assertEquals(s.toString(), filePath + " : -9, 0, 0, 0, 0, 0, 0, 0, 0, 0");
			assertEquals(s.getSize(), 10);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -5
	 * val2 = -5
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC61() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"div",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -4
	 * val2 = -4
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void AC62() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-4,-4,"div",0,false);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -3
	 * val2 = -3
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC63() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-3,-3,"div",1,true);
			assertEquals(s.toString(), filePath + " : -3");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 2
	 * val2 = -2
	 * taille = 1
	 * etat = vide
	 */
	@Test
	public void AC64() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,-2,"div",1,false);
			assertEquals(s.toString(), filePath + " : 2");
			assertEquals(s.getSize(), 1);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -1
	 * val2 = -1
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC65() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-1,-1,"div",2,true);
			assertEquals(s.toString(), filePath + " : -1, -1");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 1
	 * val2 = 1
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void AC66() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,1,"div",2,false);
			assertEquals(s.toString(), filePath + " : 1, 1");
			assertEquals(s.getSize(), 2);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 2
	 * val2 = -2
	 * taille = 9
	 * etat = vide
	 */
	@Test
	public void AC67() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,-2,"div",3,true);
			assertEquals(s.toString(), filePath + " : 2, -2, -1");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 3
	 * val2 = 2
	 * taille = 9
	 * etat = vide
	 */
	@Test
	public void AC68() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,2,"div",3,false);
			assertEquals(s.toString(), filePath + " : 3, 2, 1");
			assertEquals(s.getSize(), 3);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = -4
	 * val2 = 4
	 * taille = 10
	 * etat = vide
	 */
	@Test(expected = ArithmeticException.class)
	public void AC69() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,-4,4,"div",10,true);
		fail("Erreur - Exception overflow MAXINT ou MININT attendu");
	}
	
	/**
	 * chemin = valide fichier vide
	 * val1 = 5
	 * val2 = 5
	 * taille = 10
	 * etat = vide
	 */
	@Test(expected = ArithmeticException.class)
	public void AC70() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,5,5,"div",10,false);
		fail("Erreur - Exception overflow MAXINT attendu");
	}
}