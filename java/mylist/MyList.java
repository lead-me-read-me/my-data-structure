package mylist;

public interface MyList<T> {
	/**
	 * Add a given object into list.
	 *
	 */
	public void add(T obj);
	
	/**
	 * Add a given object into the first index of list.
	 *
	 */
	public void addFirst(T obj);
	
	/**
	 * Add a given object into the last index of list.
	 *
	 */
	public void addLast(T obj);


	/**
	 * Remove an object from list.
	 * 
	 * @return The removed object.
	 */
	public T remove();
	
	/**
	 * Remove an object from list[index].
	 * 
	 * @return The removed object.
	 */
	public boolean remove(int index);
	
	/**
	 * Remove the given object from list if exists.
	 * 
	 * @return true if the given object is removed from list
	 *         false if the given object doesn't exist in list.
	 */
	public boolean remove(T obj);
	
	/**
	 * Remove the first object in list.
	 * If list is empty, return null.
	 * 
	 * @return The removed object.
	 */
	public T removeFirst();
	
	/**
	 * Remove the last object in list.
	 * If list is empty, return null.
	 * 
	 * @return The removed object.
	 */
	public T removeLast();

	/**
	 * Get the object from list having integer index.
	 * 
	 * @return The object in list.
	 */
	public T get(int index);
	
	/**
	 * Change the object from list having integet index to given object.
	 * 
	 * @return true if the change is success.
	 *         false if not.
	 */
	public boolean set(int index, T obj);

	/**
	 * Return the number of objects in the list.
	 *
	 * @return the number of objects in the list.
	 */
	public int size();

	/**
	 * Print all objects in list on console.
	 *
	 */
	public void printListElement();

	/**
	 * Check whether the list is full or not.
	 *
	 */
	public boolean isFull();
	/**
	 * Check whether the list is empty or not.
	 *
	 */
	public boolean isEmpty();

	/**
	 * Return the index of the given object in the list.
	 *
	 * @return the index of the given object in the list if theobject exists in the list.
	 *         -1 if the object doesn't exist in the list.
	 */
	public int indexOf(T obj);
	
	/**
	 * Check that the given object exists in the list.
	 *
	 * @return true if the object is in the list.
	 *         false if not.
	 */
	public boolean contains(T obj);
	
	/**
	 * Delete all object in the list.
	 * (In here, we use GC.)
	 *
	 */
	public void clear();
}
