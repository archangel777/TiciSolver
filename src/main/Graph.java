package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Graph {
	
	private Map<Long, DistanceVector> fetched;
	private Map<Long, Node> nodes;
	private List<Edge> edges;
	
	public Graph(){
		nodes = new HashMap<>();
		edges = new ArrayList<>();
	}
	
	public void addNode(Node node) {
		nodes.put(node.getId(), node);
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
		nodes.get(edge.getFromNode()).addAdjacent(edge);
	}
	
	public Node getNode(Long id) {
		return nodes.get(id);
	}
	
	public Integer getNumberOfNodes() {
		return nodes.size();
	}
	
	public Integer getNumberOfEdges() {
		return edges.size();
	}
		
	public DistanceVector runDijkstra(Long sourceId) {
		DistanceVector vector = new DistanceVector();
		Queue<DistanceElement> toVisit = new PriorityQueue<>();
		
		init(sourceId, vector);
		
		toVisit.add(vector.getElement(sourceId));
		while (!toVisit.isEmpty()) {
			DistanceElement min = toVisit.remove();
			
			if (min.isVisited()) continue;
			min.setVisited(true);
			
			for (Edge e : nodes.get(min.getId()).getAdjacents()) {
				DistanceElement neighbor = vector.getElement(e.getToNode());
				
				Double newDistance = min.getDistance() + e.getCost();
				if (neighbor.getDistance() > newDistance && !neighbor.isVisited()) {
					neighbor.changeDistance(newDistance);
					toVisit.add(neighbor);
				}
			}
		}
		
		return vector;
	}
	
	public void init(Long sourceId, DistanceVector vector) {
		for (Long i = 1l; i<=nodes.size(); i++) {
			DistanceElement element = new DistanceElement(i);
			if (element.getId().equals(sourceId)) {
				element.changeDistance(0.);
			}
			vector.addElement(element);
		}
	}
	
	public long getRandomNodeId() {
		return Math.abs(new Random().nextLong()%this.getNumberOfNodes()) + 1;
	}
	
	public List<Long> getRandomPath() {
		List<Long> path = new ArrayList<>();
		int counter = 0;
		Long currentPoint = Math.abs(new Random().nextLong()%this.getNumberOfNodes()) + 1;
		System.out.println(currentPoint);
		path.add(currentPoint);
		counter++;
		while (counter <= 40 && !this.getNode(currentPoint).getAdjacents().isEmpty()) {
			counter++;
			currentPoint = this.getNode(currentPoint).getAdjacents().get(0).getToNode();
			path.add(currentPoint);
		}
		return path;
	}
	
	public void fetchToMemory(Long sourceId) {
		long startTime = System.currentTimeMillis();
		if (fetched.size() > 100) {
			boolean b = true;
			for (Map.Entry<Long, DistanceVector> iterator : fetched.entrySet()) {
				if (b) fetched.remove(iterator.getKey());
				b = !b;
			}
		}
		fetched.put(sourceId, runDijkstra(sourceId));
		System.out.println("Fetching one to memory took " + (System.currentTimeMillis() - startTime) + " ms!");
	}
	
	public void fetchToMemory(List<Long> path) {
		long startTime = System.currentTimeMillis();
		fetched = new HashMap<>();
		for (Long l : path) {
			fetched.put(l, runDijkstra(l));
		}
		System.out.println("Fetching to memory took " + (System.currentTimeMillis() - startTime) + " ms!");
	}
	
	public Double getCostInMemory(Long sourceId, Long targetId) {
		if (!fetched.containsKey(sourceId)) fetchToMemory(sourceId);
		return fetched.get(sourceId).getElement(targetId).getDistance();
	}
	
}
