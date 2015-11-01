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

public class ECTests {
	
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
	 * operateur = add
	 * val1 = -5
	 * val2 = -5
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void EC1() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-5,-5,"add",0,true);
			assertEquals(s.toString(), filePath + " : ");
			assertEquals(s.getSize(), 0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * operateur = sub
	 * val1 = 8
	 * val2 = 0
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void EC2() {
		try{
			SuiteChaineeImpl set = new SuiteChaineeImpl(filePath, 1, 2, "add", 5, true);
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,0,"sub",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 8");
			assertEquals(s.getSize(), 6);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * operateur = mul
	 * val1 = -6
	 * val2 =  3
	 * taille = 2
	 * etat = vide
	 */
	@Test
	public void EC3() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-6,3,"mul",2,true);
			assertEquals(s.toString(), filePath + " : -6, 3");
			assertEquals(s.getSize(), 2);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * operateur = div
	 * val1 = 8
	 * val2 = 4
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void EC4() throws Exception {
		try{
			SuiteChaineeImpl set = new SuiteChaineeImpl(filePath, 1, 2, "add", 5, true);
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,4,"div",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 8, 4, 2");
			assertEquals(s.getSize(), 8);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier vide
	 * operateur = add
	 * val1 = 3
	 * val2 = 0
	 * taille = 10
	 * etat = non vide
	 */
	@Test
	public void EC5() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,0,"add",10,false);
			assertEquals(s.toString(), filePath + " : 3, 0, 3, 3, 6, 9, 15, 24, 39, 63");
			assertEquals(s.getSize(), 10);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
}