import java.io.Console;


public class Test {

	public static void main(String[] args) {
		Noeud nd3 = new NoeudImp(2, 12, null);
		Noeud nd2 = new NoeudImp(1, 7, nd3);
		Noeud nd1 = new NoeudImp(0, 5, nd2);
		ChaineImp ch1 = new ChaineImp(nd1, nd1, "fichier", "op", 3);
		//ch1.SuiteChaine("f", "add", 1, 2, 5, true);
		
		try {
			int val1 = ((NoeudImp)ch1.getAt(1)).getValue();
			System.out.println("Test val1 :" + val1);
			
			ch1.setAt(80, 1);
			
			val1 = ((NoeudImp)ch1.getAt(1)).getValue();
			System.out.println("Test new val1 :" + val1);
			System.out.println(ch1.toString());
			
			ch1.add(new NoeudImp(45));
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			ch1.add(new NoeudImp(23));
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			ch1.add(new NoeudImp(36));
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			ch1.removeItem(nd1);
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			ch1.reset();
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
