package Graph;

import java.util.LinkedList;

import List.Node;
import List.SingleLinkedList;

/**
 * HashTable class represents a generic hash table implementation using
 * chaining.
 * 
 * This class utilizes an array of linked lists to handle collisions and stores
 * key-value pairs within its structure, allowing efficient retrieval and
 * storage of data based on keys.
 * 
 * @param <K> The type of keys stored in the hash table (must be Comparable)
 * @param <V> The type of values associated with keys in the hash table
 */

public class hashTable<K extends Comparable<K>, V> {
	private int capacity; // Capacity of the hash table
	private SingleLinkedList<Entry<K, V>>[] table; // Array of linked lists to store entries

	/**
	 * Constructs a HashTable with a specified capacity. Initializes the array and
	 * its linked lists.
	 * 
	 * @param capacity The initial capacity of the hash table
	 */
	public hashTable(int capacity) {
		this.capacity = capacity;
		table = new SingleLinkedList[capacity]; // Initializing the array of linked lists
		for (int i = 0; i < capacity; i++) {
			table[i] = new SingleLinkedList<>(); // Initializing each linked list
		}
	}

	/**
	 * Calculates the hash value for a given key.
	 * 
	 * @param key The key for which the hash value is calculated
	 * @return The hash value for the given key within the hash table's capacity
	 */
	private int hash(K key) {
		return Math.abs(key.hashCode() % capacity); // Calculating the hash index
	}

	/**
	 * Inserts a key-value pair into the hash table. Handles collisions by chaining.
	 * 
	 * @param key   The key associated with the value to be inserted
	 * @param value The value to be inserted into the hash table
	 */

	public void put(K key, V value) {

		int index = hash(key);
		SingleLinkedList<Entry<K, V>> list = table[index];
		Node<Entry<K, V>> curr = list.getFirst();
		while (curr != null) {
			Entry<K, V> entry = curr.getData();
			if (entry.getKey().compareTo(key) == 0) {
				entry.setValue(value);
				return;
			}
			curr = curr.getNext();
		}

		list.insertSorted(new Entry<>(key, value));
	}

	/**
	 * Retrieves the value associated with a given key from the hash table.
	 * 
	 * @param key The key for which the associated value is retrieved
	 * @return The value associated with the given key, or null if not found
	 */

	public V get(K key) {
		if (key == null)
			return null;
		int index = hash(key);
		SingleLinkedList<Entry<K, V>> list = table[index];
		Node<Entry<K, V>> curr = list.getFirst();
		while (curr != null) {
			Entry<K, V> entry = curr.getData();
			if (entry.getKey().compareTo(key) == 0) {
				return entry.getValue();
			}
			curr = curr.getNext();
		}

		return null; // Key not found
	}

	/**
	 * Removes a key-value pair from the hash table based on the given key.
	 * 
	 * @param key The key whose associated entry will be removed
	 */

	public void remove(K key) {
		int index = hash(key);
		SingleLinkedList<Entry<K, V>> list = table[index];
		list.Delete(new Entry<K, V>(key, null));

	}

	/**
	 * Displays the contents of the hash table. Shows each bucket along with its
	 * linked list of entries.
	 */

	public void display() {
		for (int i = 0; i < capacity; i++) {
			SingleLinkedList<Entry<K, V>> list = table[i];
			if (!list.isEmpty()) {
				System.out.print("Bucket " + i + ": ");
				Node<Entry<K, V>> curr = list.getFirst();
				System.out.print("-->");
				while (curr != null) {
					Entry<K, V> entry = curr.getData();
					System.out.print("(" + entry.getKey() + ", " + entry.getValue() + ") -->");
					curr = curr.getNext();
				}
				System.out.print("null");
				System.out.println();
			}
		}
	}

	/**
	 * Retrieves the size (number of buckets) of the hash table.
	 * 
	 * @return The size of the hash table
	 */

	public int getTableLength() {
		return table.length;
	}

	public int getSize() {
		int counter = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null)
				counter += table[i].getSize();
		}
		return counter;
	}

	public SingleLinkedList<Entry<K, V>>[] getArray() {
		return table;
	}

	public static void main(String[] args) {
		hashTable<String, String> hashTable = new hashTable<String, String>(10);

		hashTable.put("Ramallah", "Ramallah");
		hashTable.put("Nablus", "Nablus");
		hashTable.put("Tera", "Tera");
		hashTable.put("Yatta", "Yatta");
		hashTable.put("Jenin ", "Jenin");

		System.out.println("Displaying Hash Table:");
		hashTable.display();

		String searchKey = "Birzeit";
		String value = hashTable.get(searchKey);
		if (value != null) {
			System.out.println(searchKey + " found in the Hash Table with value: " + value);
		} else {
			System.out.println(searchKey + " not found in the Hash Table.");
		}

		String deleteKey = "Birzeit";
		hashTable.remove(deleteKey);
		System.out.println("After deleting " + deleteKey + ":");
		hashTable.display();
	}

	/**
	 * Entry class represents a key-value pair for the HashTable.
	 * 
	 * This class encapsulates a key and its associated value, providing methods to
	 * retrieve, update, and compare entries based on their keys for ordering
	 * purposes within the HashTable.
	 * 
	 * The key is immutable once initialized, ensuring integrity and consistency in
	 * key-value associations.
	 */

	public static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
		private final K key; // The key of the entry
		private V value; // The value associated with the key

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		// Implementation of the compareTo method from the Comparable interface
		// It compares the keys of two entries for ordering purposes
		@Override
		public int compareTo(Entry<K, V> o) {
			return this.key.compareTo(o.key);
		}

		@Override
		public String toString() {
			return "(" + getKey() + ", " + getValue() + ") -->";
		}
	}
}
