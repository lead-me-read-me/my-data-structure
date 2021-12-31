package mytree;

public class MyTree<E> {
	class Node<T> {
		private Node<T> _left;
		private T _element;
		private Node<T> _right;

		private Node<T> left() {return this._left;}
		private T element() {return this._element;}
		private Node<T> right() {return this._right;}

		private void setLeft(Node<T> newLeft) {this._left = newLeft;}
		private void setElement(T newElement) {this._element = newElement;}
		private void setRight(Node<T> newRight) {this._right = newRight;}

		public Node(T obj){
			this.setLeft(null);
			this.setElement(obj);
			this.setRight(null);
		}
	}
}
