
/**
 * Impl�mentation d'un �l�ment. Repr�sente un noeud (�l�ment de la {@link MyListImpl}).
 * @version 0.5
 * 
 */
public class Elem {
	/**
	 * Position dans la chaine. D�pend dela constante {@link MyListImpl#BEGININDEX}
	 */
	private int index;
	
	/**
	 * Valeur contenu par le noeud
	 */
	private int value;
	
	/**
	 * Pointeur vers le noeud suivant de la chaine (Chaine � simple chainage)
	 */
	private Elem next;
	
	/**
	 * Constructeur vide. Initialise attribut de la fa�on suiavnte : <br>
	 *    - {@link #index} = 0<br>
	 *    - {@link #value} = 0<br>
	 *    - {@link #next} = null<br>
	 */
	public Elem() {
		super();
		this.index = 0;
		this.value = 0;
		this.next = null;
		
	}
	
	/**
	 * Constructeur � un param�tre. Initialise attribut de la fa�on suiavnte : <br>
	 *    - {@link #index} = 0<br>
	 *    - {@link #value} = value<br>
	 *    - {@link #next} = null<br>
	 *    
	 * @param value Valeur attribu�e au noeud.
	 */
	public Elem(int value) {
		super();
		this.index = 0;
		this.value = value;
		this.next = null;
	}

	/**
	 * Constructeur complet. Initialise attribut de la fa�on suiavnte : <br>
	 *    - {@link #index} = index<br>
	 *    - {@link #value} = value<br>
	 *    - {@link #next} = next<br>
	 *    
	 * @param index Position du noeud dans la suite
	 * @param value Valeur attribu�e au noeud.
	 * @param next Pointeur vers le prochain noeud de la liste
	 */
	public Elem(int index, int value, Elem next) {
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
	
	public Elem getNext() {
		return next;
	}
	
	public void setNext(Elem next) {
		this.next = next;
	}

}