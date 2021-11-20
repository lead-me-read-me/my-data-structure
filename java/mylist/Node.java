package mylist;

public class Node<T> {
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
