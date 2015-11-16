package programme;

import programme.MyListImpl.Elem;

public interface MyList {
	public Elem getStart();

	public Elem getCurrent();

	public int getSize();
	
	public void add(int e);

	public void removeAt(int pos);

	public int removeItem(int item);

	public void setAt(int item, int pos);

	public int getAt(int pos);

	public void reset();
}
