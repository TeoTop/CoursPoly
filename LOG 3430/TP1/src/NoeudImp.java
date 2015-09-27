/**
 * Implémentation de l'interface Noeud. Représente un noeud (élément de la {@link ChaineImp}).
 * @see Noeud
 * @see ChaineImp
 * @version 0.5
 */
public class NoeudImp implements Noeud {
	/**
	 * Position dans la chaine. Dépend dela constante {@link ChaineImp#BEGININDEX}
	 */
	private int index;
	
	/**
	 * Valeur contenu par le noeud
	 */
	private int value;
	
	/**
	 * Pointeur vers le noeud suivant de la chaine (Chaine à simple chainage)
	 */
	private Noeud next;
	
	/**
	 * Constructeur vide. Initialise attribut de la façon suiavnte : <br>
	 *    - {@link #index} = 0<br>
	 *    - {@link #value} = 0<br>
	 *    - {@link #next} = null<br>
	 */
	public NoeudImp() {
		super();
		this.index = 0;
		this.value = 0;
		this.next = null;
		
	}
	
	/**
	 * Constructeur à un paramètre. Initialise attribut de la façon suiavnte : <br>
	 *    - {@link #index} = 0<br>
	 *    - {@link #value} = value<br>
	 *    - {@link #next} = null<br>
	 *    
	 * @param value Valeur attribuée au noeud.
	 */
	public NoeudImp(int value) {
		super();
		this.index = 0;
		this.value = value;
		this.next = null;
	}

	/**
	 * Constructeur complet. Initialise attribut de la façon suiavnte : <br>
	 *    - {@link #index} = index<br>
	 *    - {@link #value} = value<br>
	 *    - {@link #next} = next<br>
	 *    
	 * @param index
	 * @param value
	 * @param next
	 */
	public NoeudImp(int index, int value, Noeud next) {
		super();
		this.index = index;
		this.value = value;
		this.next = next;
	}

	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public NoeudImp getNext() {
		return (NoeudImp) next;
	}
	
	public void setNext(Noeud next) {
		this.next = next;
	}

}
