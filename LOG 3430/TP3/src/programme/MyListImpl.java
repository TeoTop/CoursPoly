package programme;
/**
 * Impl�mentation de l'interface SuiteChaine.
 * @see MyList
 * @version 0.5
 *
 */
public class MyListImpl implements MyList {
	
	
	
	private final int BEGININDEX = 0;
	
	private Elem init;
	
	/**
	 * Constructeur vide. Initialise attribut de la fa�on suiavnte : <br>
	 *    - {@link #init} = null<br>
	 *    
	 */
	public MyListImpl() {
		super();
		this.init = null;
	}
	
	/**
	 * L'attribut {@link NoeudImp#index} et {@link NoeudImp#next} du noeud
	 * peuvent �tre modfi�s pour appartenir � la chaine.
	 */
	@Override
	public void add(Elem element)throws Exception {
		
		Elem curser = null;
		
		try {
			/**
			 * V�rifier si la chaine est vide avant de cherche un �l�ment de la chaine 
			 */
			if(getSize() != 0)
				curser = getAt(BEGININDEX + getSize()-1);
		} catch (Exception e) {
			throw e;
		}
		
		element.setIndex(BEGININDEX + getSize());
		element.setNext(null);
		
		/**
		 * V�rification si la chaine est vide
		 */
		if(getSize() == 0){
			init = element;
		} else {
			curser.setNext(element);
		}
		
	}

	@Override
	public void removeAt(int position) throws Exception {
		
		Elem curser = null;
		
		if(position == BEGININDEX){
			curser = getAt(position);
			init = curser.getNext();
		} else {
			curser = getAt(position-1);
			curser.setNext(curser.getNext().getNext());
			curser = curser.getNext();                  //pour la mise � jour
		}
		
		
		/**
		 * Mise a jour des index en partant du noeud modifi�
		 */
		for(int i = curser.getIndex() ; i < getSize() + BEGININDEX ; i++) {
			curser.setIndex(curser.getIndex()-1);
			curser = curser.getNext();
		}
	}

	/**
	 * A supprimer peut etre 
	 * @throws Exception 
	 */
	@Override
	public void removeItem(int element) throws Exception {
		Elem curser = this.init;
		
		while(curser != null && curser.getValue()!=element){
			curser = curser.getNext();
		}
		
		if(curser != null) removeAt(curser.getIndex());
	}

	
	@Override
	public void setAt(int element, int position) throws Exception {
		Elem curser = null;
		
		try {
			curser = getAt(position);
		} catch (Exception e) {
			throw new Exception("Erreur setAt() : Appel getAt\n" + e.getMessage());
		}
		
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
	public void reset()throws Exception {
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
