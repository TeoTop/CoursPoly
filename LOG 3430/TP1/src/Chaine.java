
public interface Chaine {

	Chaine SuiteChaine(String chemin, String operateur, int val1, int val2, int tailleListe, boolean vide);
	
	void add(Noeud elememt);
	
	void removeAt(int position);
	
	void removeItem(Noeud element);
	
	void setAt(int element, int position) throws Exception;
	
	Noeud getAt(int position) throws Exception;
	
	int getSize();
	
	void reset();
	
	boolean isValide(); 
	
	int add(int a, int b);
	
	int substract(int a, int b);
	
	int multiply(int a, int b);
	
	int divide(int a, int b) throws Exception;
	
	
	//void printList();
}
