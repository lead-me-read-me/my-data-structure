package mylist;

import java.util.Iterator;
import java.lang.Iterable;

public class MyDoubleLinkedList<E> implements MyList<E>, Iterable<E> {

	static class Node<T> {
		private T _element;
		private Node<T> _next;
		private Node<T> _prev;
		
		public Node(T obj) {
			this._element = obj;
			this._next = this._prev = null;
		}
		
		public T element() {return this._element;}
		public Node<T> next() {return this._next;}
		public Node<T> prev() {return this._prev;}
		
		public void setElement(T obj) {this._element = obj;}
		public void setNext(Node<T> node) {this._next = node;}
		public void setPrev(Node<T> node) {this._prev = node;}
	}

	static class MyIterator<T> implements Iterator<T> {
		private Node<T> _currentNode;
		public MyIterator(MyDoubleLinkedList<T> myDoubleLinkedList) {
			this._currentNode = myDoubleLinkedList.head();
		}
		@Override
		public boolean hasNext() {
			return (this._currentNode != null);
		}
		@Override
		public T next() {
			T elementInCurrentNode = this._currentNode.element();
			this._currentNode = this._currentNode.next();
			return elementInCurrentNode;
		}
	}

	private int _size;
	private Node<E> _head;
	private Node<E> _tail;

	private boolean isOutOfBound(int index) {
		return ((index < 0) || (index >= this.size()));
	}

	public Node<E> head() {return this._head;}

	@Override
	public Iterator<E> iterator() {
		return (new MyIterator<E>(this));
	}

	@Override
	public void add(E obj) {
		this.addFirst(obj);
	}

	@Override
	public void addFirst(E obj) {
		if (this.isFull()) {return;}
		Node<E> node = new Node<E>(obj);
		if (this.isEmpty()) {this._head = this._tail = node;}
		else {
			node.setNext(this._head);
			this._head.setPrev(node);
			this._head = node;
		}
		this._size++;
	}
	@Override
	public void addLast(E obj) {
		if (this.isFull()) {return;}
		Node<E> node = new Node<E>(obj);
		if (this.isEmpty()) {this._head = this._tail = node;}
		else {
			node.setPrev(this._tail);
			this._tail.setNext(node);
			this._tail = node;
		}
		this._size++;
	}

	@Override
	public E remove() {
		return this.removeFirst();
	}

	@Override
	public boolean remove(E obj) {
		int indexOfObj = this.indexOf(obj);
		if (indexOfObj == -1) {return false;}
		if (this.remove(indexOfObj)) {return true;}
		else {return false;}
	}

	@Override
	public boolean remove(int index) {
		if (this.isEmpty()) {return false;}
		if (this.isOutOfBound(index)) {return false;}

		if (index == 0) {
			this.removeFirst();
			return true;
		}
		if (index == this.size() - 1) {
			this.removeLast();
			return true;
		}

		Node<E> prev = null;
		Node<E> curr = this._head;
		for (int i = 0; i < index; i++) {
			prev = curr;
			curr = curr.next();
		}
		prev.setNext(prev.next());
		curr.next().setPrev(curr.prev());
		this._size--;
		return true;
	}

	@Override
	public E removeFirst() {
		if (this.isEmpty()) {return null;}
		E removedElement = this._head.element();
		if (this.size() == 1) {
			this._head = this._tail = null;
		}
		else {
			this._head = this._head.next();
			this._head.setPrev(null);
		}
		this._size--;
		return removedElement;
	}
	@Override
	public E removeLast() {
		if (this.isEmpty()) {return null;}
		E removedElement = this._tail.element();
		if (this.size() == 1) {
			this._head = this._tail = null;
		}
		else {
			this._tail = this._tail.prev();
			this._tail.setNext(null);
		}
		this._size--;
		return removedElement;
	}

	@Override
	public E get(int index) {
		if (this.isEmpty()) {return null;}
		Node<E> tmp = this._head;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next();
		}
		E elementInIndex = tmp.element();
		return elementInIndex;
	}
	@Override
	public boolean set(int index, E obj) {
		if (index < 0 || index >= this.size()) {return false;}
		Node<E> tmp = this._head;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next();
		}
		tmp.setElement(obj);
		E elementInIndex = tmp.element();
		return true;
	}

	@Override
	public int size() {return this._size;}

	@Override
	public void printListElement() {}

	@Override
	public boolean isFull() {return false;}
	@Override
	public boolean isEmpty() {
		return (this._head == null);
	}

	@Override
	public int indexOf(E obj) {
		if (this.isEmpty()) {return -1;}
		int index = -1;
		Node<E> tmp = this._head;
		while (tmp.next() != null) {
			index++;
			if (tmp.element().equals(obj)) {
				return index;
			}
		}
		return -1;
	}
	@Override
	public boolean contains(E obj) {
		if (this.isEmpty()) {return false;}
		int index = -1;
		Node<E> tmp = this._head;
		while (tmp.next() != null) {
			index++;
			if (tmp.element().equals(obj)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void clear() {
		if (this.isEmpty()) {return;}
		Node<E> tmp = this._head;
		while (tmp.next() != null) {
			Node<E> tmpNext = tmp.next();
			tmp.setNext(null);
			tmpNext.setPrev(null);
			tmp = tmpNext;
		}
		this._head = this._tail = null;
		this._size = 0;
	}

	/*
	public static void main(String[] args) {
		MyDoubleLinkedList<String> myList = new MyDoubleLinkedList<String>();

		myList.add("a");
		myList.add("b");
		myList.add("c");
		myList.add("d");
		myList.add("e");
		myList.add("f");
		myList.add("g");

		for (String s : myList) {
			System.out.println(s);
		}
	}
	*/
}
