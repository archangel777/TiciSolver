package main;

import java.util.ArrayList;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;

public class TrajectoryAsSet implements Comparable<TrajectoryAsSet>{
	protected int uid;
	protected int tid;
	protected LineString geom = null;
	protected ArrayList<Long> ts_array = null;
	protected ArrayList<Coordinate> coord_array = null;
	protected int timeWindow;
	//TODO road network constraint
	protected ArrayList<Edge> edge_array = null; 
	
	
	//for free movement
	public TrajectoryAsSet(int _uid, int _tid, Geometry _geom, ArrayList<Long> time_array,
			ArrayList<Coordinate> coord_array) {
		uid = _uid;
		tid = _tid;
		geom = (LineString) _geom;
		ts_array = time_array;
		this.coord_array = coord_array;
		this.edge_array=new ArrayList<Edge>();
	}
	
	
	public TrajectoryAsSet(int _uid, int _tid, ArrayList<Long> time_array,ArrayList<Coordinate> coord_array) {
		uid = _uid;
		tid = _tid;
		ts_array = time_array;
		this.coord_array = coord_array;
		this.edge_array=new ArrayList<Edge>();
	}

	public TrajectoryAsSet(int _uid, int _tid, ArrayList<Long> time_array,ArrayList<Coordinate> coord_array, ArrayList<Edge> edge_arrray) {
		uid = _uid;
		tid = _tid;
		ts_array = time_array;
		this.coord_array = coord_array;
		this.edge_array=edge_arrray;
	}


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public LineString getGeom() {
		return geom;
	}

	public void setGeom(LineString geom) {
		this.geom = geom;
	}

	public ArrayList<Long> getTsArray() {
		return ts_array;
	}
	
	public Long getTsArrayElement(int i) {
		return ts_array.get(i);
	}

	public void setTsStartElement(int i, Long ts_start) {
		this.ts_array.set(i, ts_start);
	}
	
	public void setCoordElement(int i, Coordinate newCoord) {
		coord_array.set(i, newCoord);
	}
	
	public ArrayList<Coordinate> getCoordArray() {
		return coord_array;
	}
	
	public ArrayList<Edge> getEdge_array() {
		return edge_array;
	}

	public void setEdge_array(ArrayList<Edge> edge_array) {
		this.edge_array = edge_array;
	}

	@Override
	public int compareTo(TrajectoryAsSet o) {
		return Integer.compare(tid, o.tid);
	}
	
	public boolean equals(Object o) {
		if (o == null || !(o instanceof TrajectoryAsSet))
			return false;
		TrajectoryAsSet co = (TrajectoryAsSet) o;
		return getTid()==co.getTid();
	}
	public int getTimeWindow() {
		return timeWindow;
	}

	public void setTimeWindow(int timewindow) {
		this.timeWindow=timewindow;
	}

	public Edge getEdgeArrayElement(int j) {
		return this.edge_array.get(j);
	}
	
	public void removePosition(int index){
		this.coord_array.remove(index);
		this.ts_array.remove(index);
	}
	
	public void removePositionAndRoadSegment(int index){
		this.coord_array.remove(index);
		this.ts_array.remove(index);
		this.edge_array.remove(index);
	}
	
	
	@Override
	public String toString(){
		String ret = "";
		for (int i = 0; i < edge_array.size(); i++) {
			ret +="Edge "+edge_array.get(i).getRoadID()+" at time "+ts_array.get(i)+"\n";
		}
		return ret;
	}
}
