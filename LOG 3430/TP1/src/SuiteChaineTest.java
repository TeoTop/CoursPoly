
import static org.junit.Assert.*;

import org.junit.Test;

public class SuiteChaineTest {

	
	@Test
	public void EC1_incorrectPathE() {
		try{

			String filePath="src/Timote/chaine.properties";
			SuiteChaineImpl s = new SuiteChaineImpl(filePath,2,5,"add",10,true);
			fail("Erreur - Chemin incorrect devrait generer une exception "+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void EC2_emptyListSizeWithValsE() {
		try{

			String filePath="src/Files/chaine.properties";
			SuiteChaineImpl s = new SuiteChaineImpl(filePath,5,5,"mul",0,true);
			fail("Erreur - Taille de Liste nulle avec des valeurs devrait generer une exception "+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void EC3_Ok() {
		String filePath="src/Files/chaine.properties";
		SuiteChaineImpl s;
		try {
			s = new SuiteChaineImpl(filePath,10,2,"div",3,true);
			assertEquals("10 / 2 =5",filePath+" : 10, 2, 5",s.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erreur - Taille de Liste nulle avec des valeurs devrait generer une exception ");
		}
	}
	
	@Test
	public void EC4_incorrectSizeE() {
		try{

			String filePath="src/Files/chaine.properties";
			SuiteChaineImpl s = new SuiteChaineImpl(filePath,15,10,"sub",1,false);
			fail("Erreur - Taille de Liste incorrecte avec des valeurs devrait generer une exception"+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void EC6_NegativeListSize_invalidValsE() {
		try{

			String filePath="src/Files/chaine.properties";
			SuiteChaineImpl s = new SuiteChaineImpl(filePath,'a','b',"add",-1,false);
			fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	


}
