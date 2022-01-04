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

	private Node<E> inorderSuccessorOf(Node<E> node) {
		if (node == null || node.left() == null) {return null;}
		Node<E> parentNode = node;
		Node<E> currentNode = node.left();
		while (currentNode != null) {
			parentNode = currentNode;
			currentNode = currentNode.right();
		}
		return parentNode;
	}

	private Node<E> inorderPredessorOf(Node<E> node) {
		if (node == null || node.right() == null) {return null;}
		Node<E> parentNode = node;
		Node<E> currentNode = node.right();
		while (currentNode != null) {
			parentNode = currentNode;
			currentNode = currentNode.left();
		}
		return parentNode;
	}

	private void swapNodeWithLeaf(Node<E> node, Node<E> leaf) {
		
	}

	@Override
	public void remove(E data) {
		if (!this.contains(data)) {return;}

		Node<E> parentNode = null;
		Node<E> currentNode = this.root();
		
		while (((Comparable) data).compareTo(currentNode.element()) != 0) {
			parentNode = currentNode; 
			if (((Comparable) data).compareTo(currentNode.element()) > 0) {
				currentNode = currentNode.right();
			}
			else if (((Comparable) data).compareTo(currentNode.element()) < 0) {
				currentNode = currentNode.left();
			}
		}

		if (currentNode.left() == null && currentNode.right() == null) {
			if (parentNode.left() == currentNode) {
				parentNode.setLeft(null);
			}
			else if (parentNode.right() == currentNode) {
				parentNode.setRight(null);
			}
		}
		else if (currentNode.left() == null && currentNode.right() != null) {
			if (parentNode.left() == currentNode) {
				parentNode.setLeft(currentNode.right());
			}
			else if (parentNode.right() == currentNode) {
				parentNode.setRight(currentNode.right());
			}
		}
		else if (currentNode.left() != null && currentNode.right() == null) {
			if (parentNode.left() == currentNode) {
				parentNode.setLeft(currentNode.left());
			}
			else if (parentNode.right() == currentNode) {
				parentNode.setRight(currentNode.left());
			}
		}
		else if (currentNode.left() != null && currentNode.right() != null) {	
			Node<E> inorderSuccessorOfCurrentNode = this.inorderSuccessorOf(currentNode);
			Node<E> inorderPredessorOfCurrentNode = null;
			if (inorderSuccessorOfCurrentNode != null) {
				// swap inorderSuccessorOfCurrentNode with currentNode
				// delete swapped-currentNode
			}
			else {
				inorderPredessorOfCurrentNode = this.inorderPredessorOf(currentNode);
			}

			if (parentNode.left() == currentNode) {
			}
			else if (parentNode.right() == currentNode) {
				currentNode.left().setRight(currentNode.right());
				parentNode.setRight(currentNode.right());
			}
		}
	}

	public Node<E> leftRotate(Node<E> node) {
		Node<E> rightChild = node.right();
		node.setRight(rightChild.left());
		rightChild.setLeft(node);
		return rightChild;
	}

	public Node<E> rightRotate(Node<E> node) {
		Node<E> leftChild = node.left();
		node.setLeft(leftChild.right());
		leftChild.setRight(node);
		return leftChild;
	}

	public Node<E> rightLeftRotate(Node<E> node) {
		node.setRight(this.rightRotate(node.right()));
		return this.leftRotate(node);
	}

	public Node<E> leftRightRotate(Node<E> node) {
		node.setLeft(this.leftRotate(node));
		return this.rightRotate(node);
	}

	public static void main(String[] args) {
		MyTree<Integer> myTree = new MyTree<Integer>();
		
		/*
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
		*/
		
		myTree.add(6);
		myTree.add(8);
		myTree.add(10);
		
		myTree.travel();
		System.out.println("----");
		
		myTree.setRoot(myTree.leftRotate(myTree.root()));
		myTree.travel();

		System.out.println(myTree.contains(3));
		System.out.println(myTree.contains(15));
	}

}
