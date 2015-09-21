
public interface Chaine {

	Chaine SuiteChaine(String chemin, String operateur, int val1, int val2, int tailleListe, boolean vide) throws Exception;
	
	void add(Noeud elememt);
	
	void removeAt(int position);
	
	void removeItem(Noeud element);
	
	void setAt(int element, int position) throws Exception;
	
	Noeud getAt(int position) throws Exception;
	
	int getSize();
	
	void reset();
	
	boolean isValide(); 
}
