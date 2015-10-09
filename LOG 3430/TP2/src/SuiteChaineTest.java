
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SuiteChaineTest {
	
	private static String wrongFilePath;
	private static String filePath;
	
	@BeforeClass
	public static void init(){
		wrongFilePath="src/Timote/chaine.properties";
		filePath="src/Files/chaine.properties";
	}
	
	@Before
	public void reset() throws FileNotFoundException, IOException{
		new RandomAccessFile(filePath, "rw").setLength(0);
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
	 * Test d'erreur : taille de liste inférieur à 0 (test aux limites)
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ERRC_NegativeListSize() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,2,3,"add",-1,false);
		fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
	}
	
	/**
	 * Test d'erreur : taille de liste sipérieur à la taille max (test aux limites)
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ERRC_OverMaxSize() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,2,3,"add",-1,false);
		fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
	}

	/**
	 * chemin = valide
	 * val1 = negatif impair
	 * val2 = negatif impair
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void EC1() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-11,-11,"add",0,true);
			assertEquals(s.getSize(), 0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide
	 * val1 = negatif impair
	 * val2 = negatif impair
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void EC2() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-10,-10,"sub",1,false);
			assertEquals(s.toString(), filePath + " : -10");
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	
	/**
	 * chemin = valide
	 * val1 = negatif impair
	 * val2 = negatif impair
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void EC3() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,-1,-1,"mul",2,true);
			assertEquals(s.toString(), filePath + " : -1, -1");
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");

		}
	}
	
	/**
	 * chemin = valide
	 * val1 = negatif impair
	 * val2 = negatif impair
	 * taille = 0
	 * etat = vide
	 */
	@Test(expected = ArithmeticException.class)
	public void EC4() throws Exception {
		SuiteChainee s = new SuiteChaineeImpl(filePath,0,0,"div",9,false);
		fail("Erreur - Exception attendu : division par zero");
	}
	
	/**
	 * chemin = valide
	 * val1 = negatif impair
	 * val2 = negatif impair
	 * taille = 0
	 * etat = vide
	 */
	@Test
	public void EC5() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,1,1,"add",10,true);
			assertEquals(s.toString(), filePath + " : 1, 1, 2, 3, 5, 8, 13, 21, 34, 55");
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Erreur - Exception inattendu");
		}
	}
	

}
