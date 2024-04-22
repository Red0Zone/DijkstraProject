package List;

import java.util.LinkedList;

public class SingleLinkedList<T extends Comparable> {

	private Node<T> dummyHead;
	private Node<T> head;
	private Node<T> tail;

	// a constructor where the Dummy head object inistialaized and the head refer to
	// the new DummyNode object;
	public SingleLinkedList() {
		dummyHead = new Node<>(null);
		head = dummyHead;
	}

//	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // //

	public void insertSorted(T data) {
		Node<T> curr = head.getNext();
		Node<T> newNode = new Node<>(data);
		if (head.getNext() == null) {
			head.setNext(newNode);
			tail = newNode;

		} else if (head.getNext().getData().compareTo(newNode.getData()) > 0) {
			newNode.setNext(head.getNext());
			head.setNext(newNode);

		} else {
			while (curr.getNext() != null && curr.getNext().getData().compareTo(newNode.getData()) < 1) {
				curr = curr.getNext();
			}
			newNode.setNext(curr.getNext());
			curr.setNext(newNode);

		}
		if (tail.getNext() != null) {
			tail = tail.getNext();
		}
	}
//	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // //

	public void insertAtHead(T element) {
		Node<T> newNode = new Node<>(element);
		if (head.getNext() != null)
			newNode.setNext(head.getNext());
		head.setNext(newNode);

		if (tail.getNext() != null)
			tail.setNext(tail.getNext());
	}

	public void insortAtTail(T element) {
		Node<T> newNode = new Node<>(element);
		tail.setNext(newNode);
		tail = tail.getNext();

	}

	public T search(T data) {
		Node<T> curr = head.getNext();
		while (curr != null && curr.getData().compareTo(data) < 1) {
			if (curr.getData().compareTo(data) == 0)
				return curr.getData();
			curr = curr.getNext();
		}
		return null;
	}
//	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // //

//	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // //

	public boolean Delete(T data) {

		Node<T> curr = head.getNext();
		if (head.getNext() != null) {
			if (head.getNext().getData().compareTo(data) == 0) {
				head.setNext(curr.getNext());
				return true;
			}

			while (curr.getNext() != null && curr.getNext() != tail && curr.getNext().getData().compareTo(data) < 1) {
				if (curr.getNext().getData().compareTo(data) == 0) {
					curr.setNext(curr.getNext().getNext());
					return true;
				}
				curr = curr.getNext();

			}
			if (curr.getNext() == tail && curr.getNext().getData().compareTo(data) == 0) {
				tail = curr;
				curr.setNext(curr.getNext().getNext());
				return true;

			}
		}
		return false;
	}

//	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // // //	// // //

	public void traverase() {
		System.out.print("Head->");
		Node<T> curr = head.getNext();
		while (curr != null) {
			System.out.println(curr.getData() + "->");
			curr = curr.getNext();
		}
		System.out.print("null\n");
	}

	public Node<T> getFirst() {
		return head.getNext();
	}

	public Node<T> getLast() {
		return tail;
	}

	public boolean isEmpty() {
		if (getFirst() != null)
			return false;
		return true;
	}

	public int getSize() {
		int count = 0;
		Node<T> curr = head.getNext();

		while (curr != null) {
			count++;
			curr = curr.getNext();
		}

		return count;
	}

}
