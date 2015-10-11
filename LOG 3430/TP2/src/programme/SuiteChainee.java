package programme;

public interface SuiteChainee {
	/**
	 * V�rifie si la suite contenue dans la chaine est respect�e.
	 * @return true si la suite est respect�e, sinon faux
	 * @throws Exception 
	 */
	boolean isValide() throws Exception; 
	
	/**
	 * Sauvegarde la chaine actuelle et la remplace par la chaine contenue dans le fichier
	 * @param fichier Chemin du fichier contenant la chaine � charger
	 */
	void chargerChaine(String fichier); 
	
	/**
	 * Sauvegarde la chaine actuelle dans le fichier indiqu� par l'attribut fichier
	 */
	void sauvgarderChaine();
	
	/**
	 * Ajoute un �lement � la liste
	 */
	void add(Elem elememt) throws Exception;

	
	void removeAt(int position) throws Exception;

	
	void removeItem(int element) throws Exception;

	
	void setAt(int element, int position) throws Exception;

	
	Elem getAt(int position) throws Exception;

	int getSize();

	void reset() throws Exception;
}
