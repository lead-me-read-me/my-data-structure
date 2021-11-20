package myheap;

public class MyMaxHeap<E> extends MyHeap<E> {

	// If big is really bigger than small, then return true.
	// If not, return false;
	private boolean isBigger(E big, E small) {
		return (((Comparable<E>)big).compareTo(small) > 0);
	}

	public MyMaxHeap() {
		super();
	}

	public MyMaxHeap(int userDefinedHeapArraySize) {
		super(userDefinedHeapArraySize);
	}

	@Override
	public void trickleUp(int childIndex) {
		if (this.isOutOfBound(childIndex)) {return;}
		if (this.isRootIndex(childIndex)) {return;}

		int parentIndex = this.parentIndexOf(childIndex);

		E childElement = this.heapArrayElementOf(childIndex);
		E parentElement = this.heapArrayElementOf(parentIndex);

		// Remove a heap violation.
		if (this.isBigger(childElement, parentElement)) {
			this.swap(childIndex, parentIndex);
			this.trickleUp(parentIndex);
		}
	}

	@Override
	public void trickleDown(int parentIndex) {
		if (this.isOutOfBound(parentIndex)) {return;}
		if (this.isLeafIndex(parentIndex)) {return;}

		int leftChildIndex = this.leftChildIndex(parentIndex);
		int rightChildIndex = this.rightChildIndex(parentIndex);

		E leftChildElement = null;
		if (!this.isOutOfBound(leftChildIndex)) {
			leftChildElement = this.heapArrayElementOf(leftChildIndex);
		}
		E rightChildElement = null;
		if (!this.isOutOfBound(rightChildIndex)) {
			rightChildElement = this.heapArrayElementOf(rightChildIndex);
		}

		//if ((leftChildElement == null) && (rightChildElement == null)) {return;}

		E parentElement = this.heapArrayElementOf(parentIndex);

		int maxChildIndex = -1;
		E maxChildElement = null;

		if (rightChildElement == null) {
			maxChildIndex = leftChildIndex;
			maxChildElement = leftChildElement;
		}
		else if (this.isBigger(leftChildElement, rightChildElement)) {
			maxChildIndex = leftChildIndex;
			maxChildElement = leftChildElement;
		}
		else {
			maxChildIndex = rightChildIndex;
			maxChildElement = rightChildElement;
		}

		//System.out.print(parentIndex + ", ");
		//System.out.println(maxChildIndex);
		//System.out.println(this.toString());
		// Remove a heap violation.
		if (this.isBigger(maxChildElement, parentElement)) {
			this.swap(maxChildIndex, parentIndex);
			//System.out.println(this.toString());
			this.trickleDown(maxChildIndex);
		}
	}

/*
	public static void main(String[] args) {
		MyMaxHeap<String> mmh = new MyMaxHeap<String>();
		mmh.add("a");
		mmh.add("f");
		mmh.add("c");
		mmh.add("j");
		mmh.add("z");
		mmh.add("u");
		mmh.add("o");
		mmh.add("w");
		mmh.add("q");
		mmh.add("l");
		mmh.add("m");
		mmh.add("b");
		mmh.add("n");
		mmh.add("p");
		mmh.add("d");
		mmh.add("g");
		mmh.add("r");

		System.out.println(mmh.toString());
		System.out.println("\n\n\n");
		for (int i = 0; i < 16; i++) {
			mmh.remove();
		}

		System.out.println(mmh.toString());
	}
*/
}

