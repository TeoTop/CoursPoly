
public class NoeudImp implements Noeud {

	private int index;
	private int value;
	private Noeud next;
	
	
	public NoeudImp() {
		super();
	}
	
	public NoeudImp(int value) {
		super();
		this.value = value;
	}

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
