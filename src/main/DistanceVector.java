package main;
import java.util.HashMap;
import java.util.Map;

public class DistanceVector {
	private Map<Long, DistanceElement> vector;
	
	public DistanceVector() {
		vector = new HashMap<>();
	}
	
	public void addElement(DistanceElement element) {
		vector.put(element.getId(), element);
	}
	
	public DistanceElement getElement(Long id) {
		return vector.get(id);
	}
	
}
