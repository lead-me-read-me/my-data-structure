package myhash;

import mylist.*;
import java.util.Iterator;
import java.lang.Iterable;

public class MyHash<K,V> implements MyHashInterface<K,V>, Iterable<K> {
	static class MyHashElement<K,V> implements Comparable<MyHashElement<K,V>> {
		private K _key;
		private V _value;
		public MyHashElement(K key, V value) {
			this._key = key;
			this._value = value;
		}
		public K key() {return this._key;}
		public V value() {return this._value;}
		@Override
		public int compareTo(MyHashElement<K,V> o) {
			return (((Comparable<K>)this.key()).compareTo(o.key()));
		}
	}

	class MyIterator<T> implements Iterator<T> {
		
		private T[] _keys;
		private int _position;

		private T[] keys() {return this._keys;}
		private int position() {return this._position;}

		private void setPosition(int newPosition) {this._position = newPosition;}


		public MyIterator(MyHash<K,V> myHash) {
			this._keys = ((T[]) new Object[myHash.numberOfElements()]);
			this.setPosition(0);

			int position = 0;
			for (MyDoubleLinkedList<MyHashElement<K,V>> myHashElements : myHash.hashArray()) {
				for (MyHashElement<K,V> myHashElement : myHashElements) {
					this.keys()[position++] = ((T) myHashElement.key());
				}
			}
		}

		@Override
		public boolean hasNext() {
			return (this.position() < this.keys().length);
		}
		@Override
		public T next() {
			if (!this.hasNext()) {return null;}
			T nextElement = this.keys()[this.position()];
			this.setPosition(this.position() + 1);
			return nextElement;
		}
	}

		private static final int DEFAULT_TABLE_SIZE = 16;
		private static final double DEFAULT_MAX_LOAD_FACTOR = 0.75;

		private int _numberOfElements;
		private int _tableSize;
		private double _maxLoadFactor;
		private MyDoubleLinkedList<MyHashElement<K,V>>[] _hashArray;

		private int numberOfElements() {return this._numberOfElements;}
		private int tableSize() {return this._tableSize;}
		private double maxLoadFactor() {return this._maxLoadFactor;}
		private MyDoubleLinkedList<MyHashElement<K,V>>[] hashArray() {return this._hashArray;}

		private void setNumberOfElements(int newNumberOfElements) {this._numberOfElements = newNumberOfElements;}

		public MyHash() {
			this(MyHash.DEFAULT_TABLE_SIZE, MyHash.DEFAULT_MAX_LOAD_FACTOR);
		}

		public MyHash(int tableSize, double maxLoadFactor) {
			this._tableSize = tableSize;
			this._maxLoadFactor = maxLoadFactor;
			this._numberOfElements = 0;

			this._hashArray = (MyDoubleLinkedList<MyHashElement<K,V>>[]) new MyDoubleLinkedList[this.tableSize()];

			for (int i = 0; i < this._tableSize; i++) {
				this._hashArray[i] = new MyDoubleLinkedList<MyHashElement<K,V>>();
			}
		}

		private double loadFactor() {return (double)this.numberOfElements() / (double)this.tableSize();}
		private boolean resize(int newTableSize) {
			MyDoubleLinkedList<MyHashElement<K,V>>[] newHashArray = (MyDoubleLinkedList<MyHashElement<K,V>>[]) new MyDoubleLinkedList[newTableSize];
			for (int i = 0; i < newHashArray.length; i++) {
				newHashArray[i] = new MyDoubleLinkedList<MyHashElement<K,V>>();
			}

			this._tableSize = newTableSize;
			for (K key : this) {
				V value = this.getValue(key);
				MyHashElement<K,V> newHashElement = new MyHashElement<K,V>(key, value);
				int hashValue = this.hashCode2hashValue(key.hashCode());
				newHashArray[hashValue].add(newHashElement);
			}
			this._hashArray = newHashArray;
			return true;
		}
		private int hashCode2hashValue(int hashCode) {
			int hashValue = hashCode & 0x7FFFFFFF; // Make hashCode to be positive.
			hashValue = hashValue % this.tableSize(); // Make hashCode to belong "0 ~ tableSize".
			return hashValue;
		}

		@Override
		public Iterator<K> iterator() {
			return (new MyIterator<K>(this));
		}

		@Override
		public boolean add(K key, V value) {
			if (this.loadFactor() > this.maxLoadFactor()) {
				this.resize(this.tableSize() * 2);
			}
			MyHashElement<K,V> newElement = new MyHashElement<K,V>(key, value);
			int hashValue = this.hashCode2hashValue(key.hashCode());
			this._hashArray[hashValue].add(newElement);
			this.setNumberOfElements(this.numberOfElements() + 1);
			return true;
		}
		
		@Override
		public boolean remove(K key, V value) {
			int hashValue = this.hashCode2hashValue(key.hashCode());
			MyDoubleLinkedList<MyHashElement<K,V>> listOnHashValue = this._hashArray[hashValue];
			if (listOnHashValue == null) {return false;}

			MyHashElement<K,V> removeTarget = new MyHashElement<K,V>(key, value);
			return listOnHashValue.remove(removeTarget);
		}

		@Override
		public V getValue(K key) {
			int hashValue = this.hashCode2hashValue(key.hashCode());
			for (MyHashElement<K,V> hashElement : this._hashArray[hashValue]) {
				if (((Comparable<K>)hashElement.key()).compareTo(key) == 0) {
					return hashElement.value();
				}
			}
			return null;
		}
}
