package tests;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.naming.SizeLimitExceededException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import programme.SuiteChainee;
import programme.SuiteChaineeImpl;

public class ACFileTest {
	
	private static String filePath;
	
	@BeforeClass
	public static void init(){
		filePath="src/Files/chaine.properties";
	}
	
	@AfterClass
	public static void end() throws FileNotFoundException, IOException{
		new RandomAccessFile(filePath, "rw").setLength(0);
	}
	
	@Before
	public void setFile(){
		try {
			SuiteChaineeImpl set = new SuiteChaineeImpl(filePath, 1, 2, "add", 5, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test");
			fail("Initialisation du test echouée");
		} 
	}
	
	@After
	public void reset() throws FileNotFoundException, IOException{
		new RandomAccessFile(filePath, "rw").setLength(0);
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -9
	 * val2 = 5
	 * taille = 0
	 * etat = non vide
	 */
	@Test
	public void AC71() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,5,"add",0,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8");
			assertEquals(s.getSize(), 5);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 8
	 * val2 = 4
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void AC72() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,4,"add",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 8");
			assertEquals(s.getSize(), 6);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -7
	 * val2 = 3
	 * taille = 2
	 * etat = non vide
	 */
	@Test
	public void AC73() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,3,"add",2,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -7, 3");
			assertEquals(s.getSize(), 7);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}	
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 6
	 * val2 = 2
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void AC74() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,6,2,"add",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 6, 2, 8");
			assertEquals(s.getSize(), 8);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 4
	 * val2 = 0
	 * taille = 0
	 * etat = non vide
	 */
	@Test
	public void AC75() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,0,0,"add",0,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8");
			assertEquals(s.getSize(), 5);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -3
	 * val2 = 0
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void AC76() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-3,0,"add",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -3");
			assertEquals(s.getSize(), 6);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 2
	 * val2 = 0
	 * taille = 2
	 * etat = non vide
	 */
	@Test
	public void AC77() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,0,"add",2,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 2, 0");
			assertEquals(s.getSize(), 7);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -1
	 * val2 = 0
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void AC78() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-1,0,"add",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -1, 0, -1");
			assertEquals(s.getSize(), 8);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -9
	 * val2 = 5
	 * taille = 0
	 * etat = non vide
	 */
	@Test
	public void AC79() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,5,"sub",0,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8");
			assertEquals(s.getSize(), 5);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 8
	 * val2 = 4
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void AC80() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,4,"sub",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 8");
			assertEquals(s.getSize(), 6);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -7
	 * val2 = 3
	 * taille = 2
	 * etat = non vide
	 */
	@Test
	public void AC81() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,3,"sub",2,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -7, 3");
			assertEquals(s.getSize(), 7);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 6
	 * val2 = 2
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void AC82() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,6,2,"sub",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 6, 2, 4");
			assertEquals(s.getSize(), 8);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 4
	 * val2 = 0
	 * taille = 0
	 * etat = non vide
	 */
	@Test
	public void AC83() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,4,0,"sub",0,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8");
			assertEquals(s.getSize(), 5);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -3
	 * val2 = 0
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void AC84() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-3,0,"sub",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -3");
			assertEquals(s.getSize(), 6);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 2
	 * val2 = 0
	 * taille = 2
	 * etat = non vide
	 */
	@Test
	public void AC85() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,0,"sub",2,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 2, 0");
			assertEquals(s.getSize(), 7);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -1
	 * val2 = 0
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void AC86() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-1,0,"sub",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -1, 0, -1");
			assertEquals(s.getSize(), 8);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -9
	 * val2 = 5
	 * taille = 0
	 * etat = non vide
	 */
	@Test
	public void AC87() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-9,5,"mul",0,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8");
			assertEquals(s.getSize(), 5);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 8
	 * val2 = -4
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void AC88() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,8,-4,"mul",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 8");
			assertEquals(s.getSize(), 6);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -7
	 * val2 = 3
	 * taille = 2
	 * etat = non vide
	 */
	@Test
	public void AC89() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-7,3,"mul",2,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -7, 3");
			assertEquals(s.getSize(), 7);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 6
	 * val2 = 2
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void AC90() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,6,2,"mul",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 6, 2, 12");
			assertEquals(s.getSize(), 8);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 4
	 * val2 = 0
	 * taille = 0
	 * etat = non vide
	 */
	@Test
	public void AC91() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,4,0,"mul",0,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8");
			assertEquals(s.getSize(), 5);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -3
	 * val2 = 0
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void AC92() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-3,0,"mul",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -3");
			assertEquals(s.getSize(), 6);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 2
	 * val2 = 0
	 * taille = 2
	 * etat = non vide
	 */
	@Test
	public void AC93() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,0,"mul",2,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 2, 0");
			assertEquals(s.getSize(), 7);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -1
	 * val2 = 0
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void AC94() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-1,0,"mul",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -1, 0, 0");
			assertEquals(s.getSize(), 8);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -4
	 * val2 = -5
	 * taille = 0
	 * etat = non vide
	 */
	@Test
	public void AC95() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-4,-5,"div",0,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8");
			assertEquals(s.getSize(), 5);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 3
	 * val2 = -4
	 * taille = 1
	 * etat = non vide
	 */
	@Test
	public void AC96() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,3,-4,"div",1,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 3");
			assertEquals(s.getSize(), 6);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = -2
	 * val2 = 3
	 * taille = 2
	 * etat = non vide
	 */
	@Test
	public void AC97() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-2,3,"div",2,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, -2, 3");
			assertEquals(s.getSize(), 7);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide fichier non vide
	 * val1 = 1
	 * val2 = 2
	 * taille = 3
	 * etat = non vide
	 */
	@Test
	public void AC98() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,2,"div",3,false);
			assertEquals(s.toString(), filePath + " : 1, 2, 3, 5, 8, 1, 2, 0");
			assertEquals(s.getSize(), 8);		
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
}