/**
 * Implémentation de l'interface Chaine. Représente une chaine simple chainée.
 * @see Chaine
 * version 0.5
 *
 */
public interface Chaine {
	
	/**
	 * Permet de construire une chaine à partir des deux premiers éléments (val1, val2) et d'éléments générés.
	 * La chaine se compose d'autant d'élément qu'indiqué par tailleListe. Si la chaine possède déjà des éléments, on
	 * additionne tailleListe à la taille de la chaine. Les éléments générés de la chaine sont
	 * calculés avec l'opérateur indiqué.
	 * @param chemin Fichier dans lequel sera enregistré la chaine.
	 * @param operateur Opérateur de calcul pour les éléments générés de la chaine. 
	 * @param val1 Première valeur ajoutée à la chaine
	 * @param val2 Seconde valeur ajoutée à la chaine
	 * @param tailleListe Taille de la iste à générer ou à ajouter
	 * @param vide La liste doit être vide lors de la conception de la chaine
	 * @return La chaine complété par les valeurs
	 * @throws Exception La chaine n'est pas vide alors que vide = true.
	 */
	Chaine suiteChaine(String chemin, String operateur, int val1, int val2, int tailleListe, boolean vide) throws Exception;
	
	/**
	 * Ajoute un noeud à la chaine. L'attribut {@link Noeud#index} et {@link Noeud#next} du noeud
	 * peuvent être modfiés pour appartenir à la chaine.
	 * @param elememt Noeud à ajouter. 
	 */
	void add(Noeud elememt);
	
	/**
	 * Retire le noeud à la position indiquée par position.
	 * @param position
	 */
	void removeAt(int position);
	
	/**
	 * Si un noeud de la chaine est égal à élément, celui-ci est retiré de la chaine. 
	 * @param element Noeud à retirer.
	 */
	void removeItem(Noeud element);
	
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
	Noeud getAt(int position) throws Exception;
	
	/**
	 * Retourne la taille de la chaine (nombre de noeuds)
	 * @return
	 */
	int getSize();
	
	/**
	 * Vide la chaine
	 */
	void reset();
	
	/**
	 * Vérifie si la suite contenue dans la chaine est respectée.
	 * @return true si la suite est respectée, sinon faux
	 */
	boolean isValide(); 
}
