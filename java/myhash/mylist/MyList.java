package mylist;

public interface MyList<T> {
	public void add(T obj);
	public void addFirst(T obj);
	public void addLast(T obj);

	public T remove();
	public boolean remove(int index);
	public boolean remove(T obj);
	public T removeFirst();
	public T removeLast();

	public T get(int index);
	public boolean set(int index, T obj);

	public int size();

	public void printListElement();

	public boolean isFull();
	public boolean isEmpty();

	public int indexOf(T obj);
	public boolean contains(T obj);
	public void clear();
}
