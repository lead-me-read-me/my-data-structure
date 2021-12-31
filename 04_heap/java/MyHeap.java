package myheap;

public abstract class MyHeap<E> {

	private static final int ROOT_INDEX = 0;
	private static final int DEFAULT_HEAP_ARRAY_SIZE = 16;

	private int _lastPosition;
	private E[] _heapArray;

	protected int lastPosition() {return this._lastPosition;}
	protected E[] heapArray() {return this._heapArray;}
	protected E heapArrayElementOf(int index) {return this._heapArray[index];}

	protected void setLastPostion(int newLastPosition) {this._lastPosition = newLastPosition;}
	protected void setHeapArray(E[] newHeapArray) {this._heapArray = newHeapArray;}
	protected void setHeapArrayElementOf(int index, E newObj) {this._heapArray[index] = newObj;}

	public MyHeap() {
		this(MyHeap.DEFAULT_HEAP_ARRAY_SIZE);
	}

	public MyHeap(int userDefinedHeapArraySize) {
		this._lastPosition = -1;
		this._heapArray = (E[]) new Object[userDefinedHeapArraySize];
		for (int index = 0; index < this._heapArray.length; index++) {
			this._heapArray[index] = null;
		}
	}

	private int heapArraySize() {
		return this.heapArray().length;
	}

	protected boolean isRootIndex(int index) {return index == MyHeap.ROOT_INDEX;}

	protected boolean isLeafIndex(int parentIndex) {
		int leftChildIndex = this.leftChildIndex(parentIndex);
		int rightChildIndex = this.rightChildIndex(parentIndex);

		E leftChildElement = null;
		if (!(this.isOutOfBoundOfWholeHeap(leftChildIndex)) && !(this.isOutOfBound(leftChildIndex))) {
			leftChildElement = this.heapArrayElementOf(leftChildIndex);
		}
		E rightChildElement = null;
		if (!(this.isOutOfBoundOfWholeHeap(rightChildIndex)) && !(this.isOutOfBound(rightChildIndex))) {
			rightChildElement = this.heapArrayElementOf(rightChildIndex);
		}

		return ((leftChildElement == null) && (rightChildElement == null));
	}

	protected boolean isOutOfBoundOfWholeHeap(int index) {
		return ((index < 0) || (index >= this.heapArraySize()));
	}

	protected boolean isOutOfBound(int index) {
		return ((index < 0) || (index > this.lastPosition()));
	}

	protected int parentIndexOf(int childIndex) {
		if (childIndex == MyHeap.ROOT_INDEX) {return MyHeap.ROOT_INDEX;}
		int parentIndex = (childIndex - 1) / 2;
		return parentIndex;
	}

	protected int leftChildIndex(int parentIndex) {
		int leftChildIndex = 2 * parentIndex + 1;
		return leftChildIndex;
	}
	
	protected int rightChildIndex(int parentIndex) {
		int rightChildIndex = 2 * parentIndex + 2;
		return rightChildIndex;
	}

	protected boolean isFull() {
		return !((this.lastPosition() < this.heapArraySize()) && this.lastPosition() >= -1);
	}
	protected boolean isEmpty() {return (this.lastPosition() == -1);}
	//public boolean isEmpty() {return (this.lastPosition() == -1);}

	protected boolean resize() {
		int newHeapArraySize = 2 * this.heapArraySize();
		E[] newHeapArray = (E[]) new Object[newHeapArraySize];
		for (int index = 0; index <= this.lastPosition() - 1; index++) {
			newHeapArray[index] = this.heapArrayElementOf(index);
		}
		this.setHeapArray(newHeapArray);
		return true;
	}

	public void add(E obj) {
		this.setLastPostion(this.lastPosition() + 1);
		if (this.isFull()) {this.resize();}
		this.heapArray()[this.lastPosition()] = obj;
		this.trickleUp(this.lastPosition());
	}

	public E remove() {
		E removedElement = this.heapArrayElementOf(MyHeap.ROOT_INDEX);
		this.swap(this.lastPosition(), MyHeap.ROOT_INDEX);
		this.setLastPostion(this.lastPosition() - 1);
		this.trickleDown(MyHeap.ROOT_INDEX);
		return removedElement;
	}

	public void swap(int from, int to) {
		E tmp = this.heapArrayElementOf(from);
		this.setHeapArrayElementOf(from, this.heapArrayElementOf(to));
		this.setHeapArrayElementOf(to, tmp);
	}

	@Override
	public String toString() {
		String result = "";
		
		for (int index = 0; index < this.heapArray().length; index++) {
			E indexElement = this.heapArrayElementOf(index);
			if (indexElement != null) {
				result += indexElement.toString() + " | ";
			}
		}

		return result;
	}

	public abstract void trickleUp(int childIndex);
	public abstract void trickleDown(int parentIndex);
}
