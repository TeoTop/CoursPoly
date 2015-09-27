/**
 * Impl�mentation de l'interface Chaine. Repr�sente une chaine simple chain�e.
 * version 0.5
 *
 */
public interface SuiteChaine {
	
	/**
	 * Permet de construire une chaine � partir des deux premiers �l�ments (val1, val2) et d'�l�ments g�n�r�s.
	 * La chaine se compose d'autant d'�l�ment qu'indiqu� par tailleListe. Si la chaine poss�de d�j� des �l�ments, on
	 * additionne tailleListe � la taille de la chaine. Les �l�ments g�n�r�s de la chaine sont
	 * calcul�s avec l'op�rateur indiqu�.
	 * @param chemin Fichier dans lequel sera enregistr� la chaine.
	 * @param operateur Op�rateur de calcul pour les �l�ments g�n�r�s de la chaine. 
	 * @param val1 Premi�re valeur ajout�e � la chaine
	 * @param val2 Seconde valeur ajout�e � la chaine
	 * @param tailleListe Taille de la iste � g�n�rer ou � ajouter
	 * @param vide La liste doit �tre vide lors de la conception de la chaine
	 * @return La chaine compl�t� par les valeurs
	 * @throws Exception La chaine n'est pas vide alors que vide = true.
	 */
	SuiteChaine suiteChaine(String chemin, String operateur, int val1, int val2, int tailleListe, boolean vide) throws Exception;
	
	/**
	 * Ajoute un noeud � la chaine.
	 * @param elememt Noeud � ajouter. 
	 */
	void add(Noeud elememt);
	
	/**
	 * Retire le noeud � la position indiqu�e par position.
	 * @param position
	 */
	void removeAt(int position);
	
	/**
	 * Si un noeud de la chaine est �gal � �l�ment, celui-ci est retir� de la chaine. 
	 * @param element Noeud � retirer.
	 */
	void removeItem(Noeud element);
	
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
	Noeud getAt(int position) throws Exception;
	
	/**
	 * Retourne la taille de la chaine (nombre de noeuds)
	 * @return
	 */
	int getSize();
	
	/**
	 * Vide la chaine
	 * @throws Exception
	 */
	void reset() throws Exception;
	
	/**
	 * V�rifie si la suite contenue dans la chaine est respect�e.
	 * @return true si la suite est respect�e, sinon faux
	 */
	boolean isValide(); 
	
	/**
	 * Sauvegarde la chaine actuelle et la remplace par la chaine contenue dans le fichier
	 * @param fichier Chemin du fichier contenant la chaine � charger
	 */
	void chargerChaine(String fichier); 
	
	/**
	 * Sauvegarde la chaine actuelle dans le fichier indiqu� par l'attribut fichier
	 */
	void sauvgarderChaine();
}
