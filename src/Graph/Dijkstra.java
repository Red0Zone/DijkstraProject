package Graph;

import java.util.ArrayList;
import java.util.List;

import Graph.hashTable.Entry;
import List.Node;
import List.SingleLinkedList;

public class Dijkstra {
	private int vertex;
	private hashTable<Vertex, TableEntry> table;
	private Graph graph;

	public Dijkstra(Graph graph) {
		this.graph = graph;
	}

	private class TableEntry implements Comparable<TableEntry> {
		private Vertex source;
		private SingleLinkedList<Edge> header;
		private boolean known;
		private double dis;
		private Vertex prev;

		public TableEntry(Vertex vertex) {
			this.source = vertex;
			this.header = vertex.getList();
			this.known = false;
			this.dis = Double.MAX_VALUE;
		}

		@Override
		public int compareTo(TableEntry o) {
			if (this.dis > o.dis)
				return 1;
			else if (this.dis < o.dis)
				return -1;
			return 0;
		}

	}

	public List<Vertex> getShortestPath(Vertex source, Vertex distination) {
		table = new hashTable<Vertex, TableEntry>(graph.size() / 5);
		readGraph();
		table.get(source).dis = 0;
		dijkstra(source, distination);
		List<Vertex> list = new ArrayList<Vertex>();
		//get the path 
		TableEntry curr = table.get(distination);
		while (curr != null) {
			list.add(curr.source);
			curr = table.get(curr.prev);
		}
//		list.add(source);
		return list;

	}

	private void dijkstra(Vertex Start, Vertex distination) {
		MinHeap<TableEntry> heap = new MinHeap<TableEntry>();
		heap.enQueue(table.get(Start));

		while (!heap.isEmpty()) {
			TableEntry currVertexTableEntry = heap.deQueue();

			if (currVertexTableEntry.source.compareTo(distination) == 0) 
				break;
			
			currVertexTableEntry.known = true;

			Node<Edge> currNode = currVertexTableEntry.header.getFirst();
			while (currNode != null) {
				TableEntry AdjacentTable = table.get(currNode.getData().getDistination());

				if (!AdjacentTable.known) {
				
					if ((currVertexTableEntry.dis + currNode.getData().getCost()) < AdjacentTable.dis) {
						AdjacentTable.dis = currVertexTableEntry.dis + currNode.getData().getCost();
						AdjacentTable.prev = currVertexTableEntry.source;
						heap.enQueue(AdjacentTable);
					}
				}
				
				
				currNode = currNode.getNext();
			}
			
			
		}
	}

	public double getCost(Vertex distination) {
		return table.get(distination).dis;
	}

	private void readGraph() {
		for (SingleLinkedList<Entry> list : graph.getTable().getArray()) {
			Node<Entry> curr = list.getFirst();
			while (curr != null) {
				Vertex vertex = (Vertex) curr.getData().getValue();
				table.put(vertex, new TableEntry(vertex));
				curr = curr.getNext();

			}
		}
	}
}