
public class Test {

	public static void main(String[] args) {
		String filePath="src/Files/chaine.properties";
		
		Noeud nd3 = new NoeudImp(2, 12, null);
		Noeud nd2 = new NoeudImp(1, 7, nd3);
		Noeud nd1 = new NoeudImp(0, 5, nd2);
		SuiteChaineImp ch1 = new SuiteChaineImp(nd1, filePath, "op", 3);
		//ch1.SuiteChaine("f", "add", 1, 2, 5, true);
		
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
			
			ch1.suiteChaine(filePath, "add", 6, 2, 4, false);
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			ch1.suiteChaine(filePath, "sub", 18, 28, 5, false);
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			ch1.suiteChaine(filePath, "mul", -52, 0, 4, false);
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			ch1.suiteChaine(filePath, "div", 200002, 2, 4, false);
			System.out.println(ch1.toString());
			System.out.println(ch1.getSize());
			
			
			 
			System.out.println("Valide : "+ch1.isValide());
			
			/*String test="1, 2, 3, 4, 5, 6, 7";
			String split[]=test.split(", ");
			System.out.println(Integer.parseInt( split[0]));
			System.out.println(split.length);*/
			//Testing operators methods
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
