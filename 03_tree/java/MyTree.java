package mytree;


public class MyTree<E> implements MyTreeInterface<E> {

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
		public Node() {
			this(null);
		}
	}
	

	private Node<E> _root;

	public MyTree() {
		this._root = null;
	}


	private Node<E> root() {return this._root;}
	private void setRoot(Node<E> newRoot) {this._root = newRoot;}

	/*
	 * root -> left -> right
	 */
	private void preOrder(Node<E> node) {
		if (node == null) {return;}
		System.out.print(node.element().toString() + " ");
		this.preOrder(node.left());
		this.preOrder(node.right());
	}

	/*
	 * left -> root -> right
	 */
	private void inOrder(Node<E> node) {
		if (node == null) {return;}
		this.inOrder(node.left());
		System.out.print(node.element().toString() + " ");
		this.inOrder(node.right());
	}

	/*
	 * left -> right -> root
	 */
	private void postOrder(Node<E> node) {
		if (node == null) {return;}
		this.postOrder(node.left());
		this.postOrder(node.right());
		System.out.print(node.element().toString() + " ");
	}

	@Override
	public void travel() {
		this.preOrder(this._root);
		System.out.println("");
		this.inOrder(this._root);
		System.out.println("");
		this.postOrder(this._root);
		System.out.println("");
	}
	
	private void add(Node<E> node, E data) {
		if (((Comparable) data).compareTo(node.element()) > 0) {
			if (node.right() == null) {
				node.setRight(new Node<E>(data));
				return;
			} 
			this.add(node._right, data);
			return;
		}
		else {
			if (node.left() == null) {
				node.setLeft(new Node<E>(data));
				return;
			}
			this.add(node._left, data);
			return;
		}
	}

	@Override
	public void add(E data) {
		if (this.root() == null) {
			this.setRoot(new Node<E>(data));
		}
		else {
			this.add(this.root(), data);
		}
	}

	private boolean contains(Node<E> node, E data) {
		if (node == null) {return false;}
		if (((Comparable) data).compareTo(node.element()) > 0) {
			return this.contains(node.right(), data);
		}
		else if (((Comparable) data).compareTo(node.element()) < 0) {
			return this.contains(node.left(), data);
		}
		else {
			return true;
		}
	}

	@Override
	public boolean contains(E data) {
		return this.contains(this.root(), data);
	}
	

	private void remove(Node<E> node, E data) {
		// TODO
		return;
	}

	@Override
	public void remove(E data) {
		if (this.contains(data)) {
			this.remove(this.root(), data);
		}
		else {
			return;
		}
	}

	public Node<E> leftRotate(Node<E> node) {
		// TODO
		Node<E> rightChild = node.right();
		rightChild.setLeft(node);
		node.setRight(null);
		return rightChild;
	}

	public Node<E> rightRotate(Node<E> node) {
		// TODO
		return null;
	}

	public Node<E> rightLeftRotate(Node<E> node) {
		node.setRight(this.rightRotate(node.right()));
		return this.rightRotate(node);
	}

	public Node<E> leftRightRotate(Node<E> node) {
		node.setLeft(this.leftRotate(node));
		return this.rightRotate(node);
	}

	public static void main(String[] args) {
		MyTreeInterface<Integer> myTree = new MyTree<Integer>();
		
		myTree.add(8);
		myTree.add(6);
		myTree.add(10);
		myTree.add(5);
		myTree.add(7);
		myTree.add(9);
		myTree.add(11);
		myTree.add(12);
		myTree.add(5);
		myTree.add(3);
		myTree.add(2);
		myTree.add(1);

		myTree.travel();
		System.out.println(myTree.contains(3));
		System.out.println(myTree.contains(15));
	}

}
