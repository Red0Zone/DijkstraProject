package Graph;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {

	private List<T> heap;

	public MinHeap() {
		this.heap = new ArrayList<>();
	}

	public void enQueue(T element) {
		heap.add(element);
		heapifyUp();
	}

	public T deQueue() {
		if (isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		}

		T min = heap.get(0);
		int lastIdx = heap.size() - 1;
		heap.set(0, heap.get(lastIdx));
		heap.remove(lastIdx);

		heapifyDown();

		return min;
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	private void heapifyUp() {
		int index = heap.size() - 1;

		while (hasParent(index) && getParent(index).compareTo(heap.get(index)) > 0) {
			swap(index, getParentIndex(index));
			index = getParentIndex(index);
		}
	}

	private void heapifyDown() {
		int index = 0;

		while (hasLeftChild(index)) {
			int smallerChildIndex = getLeftChildIndex(index);

			if (hasRightChild(index) && getRightChild(index).compareTo(getLeftChild(index)) < 0) {
				smallerChildIndex = getRightChildIndex(index);
			}

			if (heap.get(index).compareTo(heap.get(smallerChildIndex)) < 0) {
				break;
			} else {
				swap(index, smallerChildIndex);
			}

			index = smallerChildIndex;
		}
	}

	private boolean hasParent(int i) {
		return i > 0;
	}

	private int getLeftChildIndex(int i) {
		return 2 * i + 1;
	}

	private int getRightChildIndex(int i) {
		return 2 * i + 2;
	}

	private int getParentIndex(int i) {
		return (i - 1) / 2;
	}

	private boolean hasLeftChild(int i) {
		return getLeftChildIndex(i) < heap.size();
	}

	private boolean hasRightChild(int i) {
		return getRightChildIndex(i) < heap.size();
	}

	private T getLeftChild(int i) {
		return heap.get(getLeftChildIndex(i));
	}

	private T getRightChild(int i) {
		return heap.get(getRightChildIndex(i));
	}

	private T getParent(int i) {
		return heap.get(getParentIndex(i));
	}

	private void swap(int i, int j) {
		T temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

}
