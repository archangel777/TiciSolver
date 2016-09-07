package trajectory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.TrajectoryAsSet;

public class TrajectoryMatrix {
	
	private boolean isGenerated;
	private Map<Integer, Map<Integer, Double>> distanceMap;
	private ShortestPathDistance calculator;
	
	public TrajectoryMatrix() {
		calculator = new ShortestPathDistance();
		distanceMap = new HashMap<>();
		isGenerated = false;
	}
	
	public void generateMatrix(List<TrajectoryAsSet> trajectoryMatrix) {
		int progress = 0, total = trajectoryMatrix.size();
		for (TrajectoryAsSet T1: trajectoryMatrix) {
			for (TrajectoryAsSet T2: trajectoryMatrix) {
				Integer id1 = Math.min(T1.getTid(), T2.getTid()), id2 = Math.max(T1.getTid(), T2.getTid());
				double distance;
				Map<Integer, Double> targetMap;
				if (!T1.equals(T2)) {
					distance = (T2.getTid() < T1.getTid()) ? calculator.distance(T2, T1) : calculator.distance(T1, T2);
					
					targetMap = distanceMap.get(id1);
					if (targetMap.containsKey(id2)) {
						targetMap.put(id2, (targetMap.get(id2) + distance)/2);
					}
					else targetMap.put(id2, distance);
				}
			}
			progress++;
			System.out.println("Progress - " + progress*100./total + "%");
		}
		isGenerated = true;
	}
	
	public double getDistanceFromMemory(TrajectoryAsSet T1, TrajectoryAsSet T2) {
		if (T1.equals(T2)) return 0;
		Integer id1 = Math.min(T1.getTid(), T2.getTid()), id2 = Math.max(T1.getTid(), T2.getTid());
		if (!isGenerated || !distanceMap.containsKey(id1)) {
			System.out.println("Trajectories not in memory, getting distance slowly.");
			return (calculator.distance(T1, T2) + calculator.distance(T2, T1)) / 2;
		}
		
		// Expected :
		return distanceMap.get(id1).get(id2);
	}
	
}
