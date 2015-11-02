package programme;
/**
 * Implémentation de l'interface Chaine. Représente une chaine simple chainée.
 * version 0.5
 *
 */
public interface MyList {
	
	public Elem getInit();
	/**
	 * Ajoute un noeud à la chaine.
	 * @param elememt Noeud à ajouter. 
	 */
	void add(Elem elememt)throws Exception;
	
	/**
	 * Retire le noeud à la position indiquée par position.
	 * @param position
	 * @throws Exception 
	 */
	void removeAt(int position) throws Exception;
	
	/**
	 * Si un noeud de la chaine est égal à élément, celui-ci est retiré de la chaine. 
	 * @param element index du Noeud à retirer.
	 * @throws Exception 
	 */
	void removeItem(int element) throws Exception;
	
	/**
	 * Modifie la valeur du noeud situé à l'index - indiqué par la position - de la chaine 
	 * @param element Valeur à modifier du noeud
	 * @param position Index du noeud à modifier
	 * @throws Exception
	 */
	void setAt(int element, int position) throws Exception;
	
	/**
	 * Retourne le noeud de la chaine situé à l'index indiqué par position
	 * @param position Indique l'index
	 * @return Noeud de la chaine situé à l'index indiqué par position
	 * @throws Exception
	 */
	Elem getAt(int position) throws Exception;
	
	/**
	 * Retourne la taille de la chaine (nombre de noeuds)
	 * @return
	 */
	int getSize();
	
	/**
	 * Vide la chaine
	 * @throws Exception
	 */
	void reset();
}
