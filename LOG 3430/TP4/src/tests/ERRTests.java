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

public class ERRTests {
	
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
	 * Test d'erreur : un chemin incorrect ou invalide vers un fichier
	 * @throws Exception
	 */
	@Test(expected = FileNotFoundException.class)
	public void ERRC_incorrectPath() throws Exception {
			SuiteChainee s = new SuiteChaineeImpl(wrongFilePath,2,5,"add",10,true);
			fail("Erreur - Chemin incorrect devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : operateur incorrect (différent de add, sub, mul ou div)
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ERRC_incorrectOperateur() throws Exception {
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,5,"24",5,true);
			fail("Erreur - Operateur incorrect devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : valeur 1 superieur à MAXINT
	 * @throws Exception
	 */
	@Test(expected = ArithmeticException.class)
	public void ERRC_incorrectVal1() throws Exception {
			SuiteChainee s = new SuiteChaineeImpl(filePath,Integer.MAX_VALUE+1,2,"add",5,true);
			fail("Erreur - Val1 incorrect devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : valeur 2 inférieur à MININT
	 * @throws Exception
	 */
	@Test(expected = ArithmeticException.class)
	public void ERRC_incorrectVal2() throws Exception {
			SuiteChainee s = new SuiteChaineeImpl(filePath,2,Integer.MIN_VALUE-1,"sub",5,true);
			fail("Erreur - Val2 incorrect devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : taille de liste inférieur à 0 (test aux limites)
	 * @throws Exception
	 */
	@Test(expected = SizeLimitExceededException.class)
	public void ERRC_NegativeListSize() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,2,3,"add",-1,false);
		fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : taille de liste supérieur à la taille max (test aux limites)
	 * @throws Exception
	 */
	@Test(expected = SizeLimitExceededException.class)
	public void ERRC_OverMaxSize() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,2,3,"add",11,false);
		fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : division par zero
	 * @throws Exception
	 */
	@Test(expected = ArithmeticException.class)
	public void ERRC_DivisionByZero() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,2,0,"div",5,false);
		fail("Erreur - Val2 = 0 et operateur = div ; Division par zero devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : Fichier non vide avec un etat à true
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ERRC_FileEmptyExpected() throws Exception {
		SuiteChaineeImpl set = new SuiteChaineeImpl(filePath, 1, 2, "add", 5, true);
		SuiteChainee s = new SuiteChaineeImpl(filePath,2,3,"add",3,true);
		fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : Fichier non vide et taille qui donne une taille cumulé de la chaine supérieur à la taille de chaine max autorisée.
	 * @throws Exception
	 */
	@Test(expected = SizeLimitExceededException.class)
	public void ERRC_OverMaxSizeAdd() throws Exception {
		SuiteChaineeImpl set = new SuiteChaineeImpl(filePath, 1, 2, "add", 5, true);
		SuiteChainee s = new SuiteChaineeImpl(filePath,2,3,"add",7,false);
		fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
	}
}