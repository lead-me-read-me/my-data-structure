package mytree;

public interface MyTreeInterface<E> {

	/*
	 * Tree should be tracked by one of pre-order, in-order, post-order.
	 */
	public void travel();

	/*
	 * Add the input data into the tree data structure.
	 */
	public void add(E data);

	/*
	 * @return true if data is in the tree data structure else false.
	 */
	public boolean contains(E data);

	/*
	 * Remove the input data in the tree data structure.
	 */
	public void remove(E data);
}
