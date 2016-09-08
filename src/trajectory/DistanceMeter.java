package trajectory;

import java.util.Collection;

public interface DistanceMeter<T> {
	public double distance(T o1, T o2);
	public void generateMatrix(Collection<T> list);
	public Collection<T> neighbors(T core, Collection<T> objects, double eps);
	
}
