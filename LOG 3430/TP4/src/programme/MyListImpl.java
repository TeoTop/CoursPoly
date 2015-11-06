package programme;
/**
 * Implémentation de l'interface SuiteChaine.
 * @see MyList
 * @version 0.5
 *
 */
public class MyListImpl implements MyList {
	
	
	
	private final int BEGININDEX = 0;
	
	private Elem init;
	
	/**
	 * Constructeur vide. Initialise attribut de la façon suiavnte : <br>
	 *    - {@link #init} = null<br>
	 *    
	 */
	public MyListImpl() {
		super();
		this.init = null;
	}
	
	
	public Elem getInit() {
		return init;
	}


	/**
	 * L'attribut {@link NoeudImp#index} et {@link NoeudImp#next} du noeud
	 * peuvent être modfiés pour appartenir à la chaine.
	 */
	@Override
	public void add(Elem element) throws Exception {
		
		Elem curser = null;
		Elem add = null;
		
		/**
		 * Vérification si la chaine est vide
		 */
		if(getSize() == 0){
			element.setIndex(BEGININDEX);
			element.setNext(null);
			init = element;
		} else {
			curser = getAt(BEGININDEX + getSize()-1);
			add = element.clone();
			add.setIndex(BEGININDEX + getSize());
			add.setNext(null);
			curser.setNext(add);
		}
	}

	@Override
	public void removeAt(int position) throws Exception {
		
		Elem curser = null;
		
		if(position == BEGININDEX){
			curser = getAt(position);
			init = curser.getNext();
			curser = init;
		} else {
			curser = getAt(position-1);
			curser.setNext(curser.getNext().getNext());
			curser = curser.getNext();                  //pour la mise à jour
		}
		
		
		/**
		 * Mise a jour des index en partant du noeud modifié
		 */
		if(curser != null) {
			for(int i = curser.getIndex() ; i <= getSize() + BEGININDEX ; i++) {
				curser.setIndex(curser.getIndex()-1);
				curser = curser.getNext();
			}
		}
	}

	/**
	 * @throws Exception 
	 */
	@Override
	public void removeItem(int element) throws Exception {
		Elem curser = this.init;
		
		while(curser != null && curser.getValue()!=element){
			curser = curser.getNext();
		}
		
		if(curser != null) {removeAt(curser.getIndex());}
		else {throw new Exception();}
	}

	
	@Override
	public void setAt(int element, int position) throws Exception {
		Elem curser = null;
		
		curser = getAt(position);
		
		curser.setValue(element);
	}

	@Override
	public Elem getAt(int position) throws Exception {
		if(position < BEGININDEX || position >= getSize() + BEGININDEX){
			throw new Exception("Erreur getAt() : "+ position +" -- Position de chaine invalide\n");
		}
		
		Elem curser = this.init;
		while(curser.getIndex()!=position){
			curser = curser.getNext();
		}
		
		return curser;
	}

	@Override
	public int getSize() {
		int taille = 0;
		Elem curser = this.init;
		while(curser!=null){
			curser = curser.getNext();
			taille++;
		}
		
		return taille;
	}

	@Override
	public void reset(){
		this.init = null;
	}
	
	
	/**
	 * String = noeuds + (", ");
	 */
	public String toString(){
		String str="";
		
		if(this.getSize()!=0)
		{
			Elem curser = this.init;
			
			for(int i = BEGININDEX ; i < getSize() + BEGININDEX ; i++) {
				str += curser.getValue() + ", ";
				curser = curser.getNext();
			}
			
			str = str.substring(0, str.length()-2);
		}
		
		return str;	
	}
}
