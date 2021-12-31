package myhash;

public interface MyHashInterface<K,V> {

	/**
	 * Add a object having the given key and the given value into the hash data structure.
	 *
	 * @return true if the adding process is successful or false if not.
	 */
	public boolean add(K key, V value);

	/**
	 * Remove a object having the given key and the given value from the hash data structure.
	 *
	 * @return true if the removing process is successful or false if not.
	 */
	public boolean remove(K key, V value);

	/**
	 * Get the value corresponding to the given key.
	 *
	 * @return The value object corresponding to the given key if the key-value pair exists in the hash datastructure or null if not.
	 */
	public V getValue(K key);
}
