
import static org.junit.Assert.*;

import org.junit.Test;

public class SuiteChaineTest {

	String wrongFilePath="src/Timote/chaine.properties";
	String filePath="src/Files/chaine.properties";
	
	@Test
	public void EC1_incorrectPathE() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(wrongFilePath,2,5,"add",10,true);
			fail("Erreur - Chemin incorrect devrait generer une exception "+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Définir le comportement : je pense que mettre une taille de 0,1 ou 2 ne pose pas de problème, on oblige la création de
	 * deux éléments. 
	 * Autre comportement possible, on autorise de valeur null pour val1 et val2, à ce moment la, on vérifie
	 * bien que la taille de liste correspond au élément qu'elle contiendra. 
	 * Autre comportement possible, on crée autant d'élèment qu'indiqué par la tailles sans ce préocuper de val1 et val2.
	 */
	@Test
	public void EC2_emptyListSizeWithValsE() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,5,5,"mul",0,true);
			//fail("Erreur - Taille de Liste nulle avec des valeurs devrait generer une exception "+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void EC3_Ok() {
		SuiteChainee s;
		try {
			s = new SuiteChaineeImpl(filePath,10,2,"div",3,true);
			assertEquals("10 / 2 =5",filePath+" : 10, 2, 5",s.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erreur - Taille de Liste nulle avec des valeurs devrait generer une exception ");
		}
	}
	
	/**
	 * Définir le comportement : je pense que mettre une taille de 0,1 ou 2 ne pose pas de problème, on oblige la création de
	 * deux éléments. 
	 * Autre comportement possible, on autorise de valeur null pour val1 et val2, à ce moment la, on vérifie
	 * bien que la taille de liste correspond au élément qu'elle contiendra. 
	 * Autre comportement possible, on crée autant d'élèment qu'indiqué par la tailles sans ce préocuper de val1 et val2.
	 */
	@Test
	public void EC4_incorrectSizeE() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,15,10,"sub",1,false);
			//fail("Erreur - Taille de Liste incorrecte avec des valeurs devrait generer une exception"+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void EC6_NegativeListSize_invalidValsE() {
		try{
			SuiteChainee s = new SuiteChaineeImpl(filePath,'a','b',"add",-1,false);
			fail("Erreur - Taille de Liste negative et valeurs invalides devrait generer une exception "+s.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	


}
