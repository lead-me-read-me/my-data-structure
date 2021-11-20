package mylist;

import java.util.Iterator;
import java.lang.Iterable;

public class MyLinkedList<E> implements MyList<E>, Iterable<E> {

	static class Node<T> {
		private T _element;
		private Node<T> _next;
		
		public Node(T element) {
			this._element = element;
			this._next = null;
		}
		
		public T element() {
			return this._element;
		}
		public Node<T> next() {
			return this._next;
		}
		public void setNext(Node<T> newNext) {
				this._next = newNext;
		}
		public void setElement(T newElement) {
			this._element = newElement;
		}
	}

	static class MyIterator<T> implements Iterator<T> {
		private Node<T> _currentNode;
		public MyIterator(MyLinkedList<T> myLinkedList) {
			this._currentNode = myLinkedList.head();
		}
		@Override
		public boolean hasNext() {
			return (this._currentNode != null);
		}
		@Override
		public T next() {
			T nextElement = this._currentNode.element();
			this._currentNode = this._currentNode.next();
			return nextElement;
		}
	}

	private int _size;
	private Node<E> _head;

	public MyLinkedList() {
		this._size = 0;
		this._head = null;
	}

	public Node<E> head() {return this._head;}

	private boolean isOutOfBound(int index) {
		return ((index < 0) || (index >= this.size()));
	}

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
		if (this.isEmpty()) {this._head = node;}
		else {
			node.setNext(this._head);
			this._head = node;
		}
		this._size++;
	}
	@Override
	public void addLast(E obj) {
		if (this.isFull()) {return;}
		Node<E> node = new Node<E>(obj);
		if (this.isEmpty()) {this._head = node;}
		else {
			Node<E> tmp = this._head;
			while (tmp.next() != null) {
				tmp = tmp.next();
			}
			tmp.setNext(node);
		}
		this._size++;
	}
	@Override
	public E remove() {return this.removeFirst();}
	@Override
	public boolean remove(int index) {
		if (this.isEmpty()) {return false;}
		if (this.isOutOfBound(index)) {return false;}
		
		Node<E> prev = null;
		Node<E> curr = this._head;
		for (int i = 0; i < index; i++) {
			prev = curr;
			curr = curr.next();
		}
		prev.setNext(curr.next());
		this._size--;
		return true;
	}
	@Override
	public boolean remove(E obj) {
		int indexOfObj = this.indexOf(obj);
		if (indexOfObj == -1) {return false;}
		else {return this.remove(indexOfObj);}
	}
	@Override
	public E removeFirst() {
		if (this.isEmpty()) {return null;}
		E removedElement = this._head.element();
		this._head = this._head.next();
		this._size--;
		return removedElement;
	}
	@Override
	public E removeLast() {
		if (this.isEmpty()) {return null;}
		Node<E> previous = null;
		Node<E> current = this._head;
		while (current.next() != null) {
			previous = current;
			current = current.next();
		}
		E removedElement = current.element();
		previous.setNext(null);
		this._size--;
		return null;
	}
	@Override
	public E get(int index) {
		if (this.isEmpty()) {return null;}
		if (index >= this.size() || index < 0) {return null;}
		Node<E> tmp = this._head;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next();
		}
		return tmp.element();
	}
	@Override
	public boolean set(int index, E obj) {
		if (this.isEmpty()) {return false;}
		if (index >= this.size() || index < 0) {return false;}
		Node<E> tmp = this._head;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next();
		}
		tmp.setElement(obj);
		return true;
	}
	@Override
	public int size() {return this._size;}
	@Override
	public void printListElement() {
		if (this.isEmpty()) {
			System.out.println("There is no elements.");
			return;
		}
		Node<E> tmp = this._head;
		while (tmp != null) {
			System.out.println(tmp.element().toString());
			tmp = tmp.next();
		}
	}
	@Override
	public boolean isFull() {return false;}
	@Override
	public boolean isEmpty() {
		return (this._head == null);
	}
	@Override
	public int indexOf(E obj) {
		if (this.isEmpty()) {return -1;}
		
		int index = 0;
		Node<E> tmp = this._head;
		while (tmp != null) {
			if (tmp.element().equals(obj)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	@Override
	public boolean contains(E obj) {
		return (this.indexOf(obj) != -1);
	}
	@Override
	public void clear() {
		this._head = null;
	}

	/*
	public static void main(String[] args) {
		MyLinkedList<String> ml = new MyLinkedList<String>();
		
		ml.addFirst("a");
		ml.addFirst("b");
		ml.addFirst("c");
		ml.addFirst("d");
		ml.addFirst("e");
		ml.addFirst("f");
		ml.addFirst("g");
		System.out.println(ml.size());
		//ml.printListElement();
		
		for (String s : ml) {
			System.out.println(s);
		}

		ml.removeLast();
		ml.removeLast();
		ml.removeLast();
		ml.removeLast();
		ml.removeLast();
		System.out.println(ml.size());
		ml.printListElement();
	}
	*/
}
