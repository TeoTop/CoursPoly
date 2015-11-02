package programme;
/**
 * Impl�mentation de l'interface Chaine. Repr�sente une chaine simple chain�e.
 * version 0.5
 *
 */
public interface MyList {
	
	public Elem getInit();
	/**
	 * Ajoute un noeud � la chaine.
	 * @param elememt Noeud � ajouter. 
	 */
	void add(Elem elememt)throws Exception;
	
	/**
	 * Retire le noeud � la position indiqu�e par position.
	 * @param position
	 * @throws Exception 
	 */
	void removeAt(int position) throws Exception;
	
	/**
	 * Si un noeud de la chaine est �gal � �l�ment, celui-ci est retir� de la chaine. 
	 * @param element index du Noeud � retirer.
	 * @throws Exception 
	 */
	void removeItem(int element) throws Exception;
	
	/**
	 * Modifie la valeur du noeud situ� � l'index - indiqu� par la position - de la chaine 
	 * @param element Valeur � modifier du noeud
	 * @param position Index du noeud � modifier
	 * @throws Exception
	 */
	void setAt(int element, int position) throws Exception;
	
	/**
	 * Retourne le noeud de la chaine situ� � l'index indiqu� par position
	 * @param position Indique l'index
	 * @return Noeud de la chaine situ� � l'index indiqu� par position
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
