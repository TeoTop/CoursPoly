import java.io.Console;

/**
 * Classe de test et de démarrage du projet.
 * verion 0.5
 *
 */
public class Test {

	public static void main(String[] args) {
		String filePath="src/Files/chaine.properties";
		String filePath2="src/Files/chaine2.properties";
		
		Noeud nd3 = new NoeudImp(2, 12, null);
		Noeud nd2 = new NoeudImp(1, 7, nd3);
		Noeud nd1 = new NoeudImp(0, 5, nd2);
		SuiteChaine ch1 = new SuiteChaineImp(nd1, filePath, "op", 3);
		SuiteChaine ch2 = new SuiteChaineImp();
		SuiteChaine ch3 = new SuiteChaineImp();
		
		try {
			
			System.out.println(ch1.toString());
			int val1 = ((NoeudImp)ch1.getAt(1)).getValue();
			System.out.println("Test val1 :" + val1);
			
			ch1.setAt(80, 1);
			
			val1 = ((NoeudImp)ch1.getAt(1)).getValue();
			System.out.println("Test new val1 :" + val1);
			System.out.println(ch1.toString());
			
			ch1.add(new NoeudImp(45));
			System.out.println(ch1.toString());
			System.out.println("Taille : "+ch1.getSize());
			
			ch1.add(new NoeudImp(23));
			System.out.println(ch1.toString());
			System.out.println("Taille : "+ch1.getSize());
			
			ch1.add(new NoeudImp(36));
			System.out.println(ch1.toString());
			System.out.println("Taille : "+ch1.getSize());
			
			ch1.removeItem(nd1);
			System.out.println(ch1.toString());
			System.out.println("Taille : "+ch1.getSize());
			
			ch1.reset();
			System.out.println(ch1.toString());
			System.out.println("Taille : "+ch1.getSize());
			
			ch1.suiteChaine(filePath, "add", 6, 2, 4, true);
			System.out.println(ch1.toString());
			
			System.out.println("Valide : "+ch1.isValide());
			
			ch1.suiteChaine(filePath, "sub", 18, 28, 3, false);
			System.out.println(ch1.toString());
			 
			System.out.println("Valide : "+ch1.isValide());			
			ch1.sauvgarderChaine();

			ch2.suiteChaine(filePath2, "mul", -52, 0, 4, true);
			System.out.println(ch2.toString());
			
			ch2.sauvgarderChaine();
			ch3.chargerChaine(filePath2);
			
			ch3.suiteChaine("pas_de_sauvegarde", "div", 200002, 2, 3, false);
			System.out.println(ch3.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
