package trajectory;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.TrajectoryAsSet;

public class TrajectoryMatrix extends ShortestPathDistance {
	
	private boolean isGenerated;
	private Map<Integer, Map<Integer, Double>> distanceMap;
	
	public TrajectoryMatrix() {
		super();
		distanceMap = new HashMap<>();
		isGenerated = false;
	}
	
	@Override
	public void generateMatrix(Collection<TrajectoryAsSet> trajectoryMatrix) {
		System.out.println("Started generation process!");		
		int progress = 0, total = trajectoryMatrix.size();
		for (TrajectoryAsSet T1: trajectoryMatrix) {
			for (TrajectoryAsSet T2: trajectoryMatrix) {	
				if (!T1.equals(T2)) {
					updateDistance(T1, T2, super.distance(T1, T2));
				}
			}
			progress++;
			System.out.println("Progress - " + new DecimalFormat("#.##").format(progress*100./total) + "%");
		}
		isGenerated = true;
	}
	
	public void updateDistance(TrajectoryAsSet T1, TrajectoryAsSet T2, double distance) {
		if (!distanceMap.containsKey(T1.getTid())) distanceMap.put(T1.getTid(), new HashMap<Integer, Double>());
		if (!distanceMap.containsKey(T2.getTid())) distanceMap.put(T2.getTid(), new HashMap<Integer, Double>());
		
		Map<Integer, Double> targetMap;
		targetMap = distanceMap.get(T1.getTid());
		if (targetMap.containsKey(T2.getTid())) {
			targetMap.put(T2.getTid(), (targetMap.get(T2.getTid()) + distance)/2);
		}
		else targetMap.put(T2.getTid(), distance);
		
		targetMap = distanceMap.get(T2.getTid());
		if (targetMap.containsKey(T1.getTid())) {
			targetMap.put(T1.getTid(), (targetMap.get(T1.getTid()) + distance)/2);
		}
		else targetMap.put(T1.getTid(), distance);
	}
	
	@Override
	public double distance(TrajectoryAsSet T1, TrajectoryAsSet T2) {
		if (T1.equals(T2)) return 0;
		if (!isGenerated || !distanceMap.containsKey(T1.getTid())) {
			System.out.println("Trajectories not in memory, getting distance slowly.");
			return (super.distance(T1, T2) + super.distance(T2, T1)) / 2;
		}
		
		// Expected :
		return distanceMap.get(T1.getTid()).get(T2.getTid());
	}
	
}
