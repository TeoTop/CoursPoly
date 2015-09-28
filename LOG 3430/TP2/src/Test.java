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
		
		/*Elem nd3 = new NoeudImp(2, 12, null);
		Elem nd2 = new NoeudImp(1, 7, nd3);
		Elem nd1 = new NoeudImp(0, 5, nd2);
		MyList ch1 = new MyListImpl(nd1, filePath, "op", 3);
		MyList ch2 = new MyListImpl();
		MyList ch3 = new MyListImpl();*/
		
		try {
			/*
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
			*/
			MyList list1 = new MyListImpl();
			MyList list2 = new MyListImpl(new Elem(23));
			Elem el1 = new Elem();
			el1.setValue(15);
			Elem[] elems = {new Elem(20), el1, new Elem(9,30, el1)};
			MyList list3 = new MyListImpl(elems);
			
			System.out.println(list1.toString());
			System.out.println(list2.toString());
			System.out.println(list3.toString());
			System.out.println(list3.getSize());
			
			list3.add(new Elem(100));
			System.out.println(list3.toString());
			System.out.println(list3.getSize());
			
			System.out.println(list3.getAt(2).getValue());
			list3.setAt(50, 2);
			System.out.println(list3.getAt(2).getValue());
			
			list3.removeItem(75);
			System.out.println(list3.toString());
			System.out.println(list3.getSize());
			
			list3.reset();
			System.out.println(list3.toString());
			System.out.println(list3.getSize());
			
			SuiteChainee sc1 = new SuiteChaineeImpl(filePath, 23, 10, "add", 5, true);
			System.out.println(sc1.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
