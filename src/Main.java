
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
public static Map<Long, DistanceVector> fetched;
	
	public static void fetchToMemory(List<Long> path, Graph g) {
		long startTime = System.currentTimeMillis();
		fetched = new HashMap<>();
		for (Long l : path) {
			fetched.put(l, g.runDijkstra(l));
		}
		System.out.println("Fetching to memory took " + (System.currentTimeMillis() - startTime) + " ms!");
	}
	
	public static Double getCostInMemory(Long sourceId, Long targetId) {
		return fetched.get(sourceId).getElement(targetId).getDistance();
	}
	
	public static void main(String[] args) {
		//Choose a graph to run the application.
		Graph g = TableParserUtils.getBeijingGraph();
		//Graph g = TableParserUtils.getSmallTestGraph();
		//Graph g = TableParserUtils.getMediumTestGraph();
		//Graph g = TableParserUtils.getYuriGraph();
		List<Long> path;
		long startAll = System.currentTimeMillis();
		for (int j = 0; j<400; j++) {
			path = g.getRandomPath();
			
			fetchToMemory(path, g);
			
			long startTime = System.currentTimeMillis();
			for (int i = 0; i<400; i++) {
				for (Long source : path) {
					getCostInMemory(source, g.getRandomNodeId());
				}
			}
			System.out.println((path.size() * 400) + " path costs computed in " + (System.currentTimeMillis() - startTime) + " ms!");		
		}
		
		System.out.println("400 full tests ended in " + (System.currentTimeMillis() - startAll) + " ms");
	}
}
