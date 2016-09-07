package main;

import com.vividsolutions.jts.geom.Coordinate;

public class Edge {

	private Long road_id;
	private Double offset;
	private Long vertex_source_id;
	private Long vertex_target_id;
	private Coordinate source;
	private Coordinate target;
	
	
	public Edge() {
		
	}
	
	public Edge(Long road_id, Long vertex_source_id, Long vertex_target_id){
		this.setRoadID(road_id);
		this.setFromNode(vertex_source_id);
		this.setToNode(vertex_target_id);
	}
	
	public Edge(Long road_id, Long vertex_source_id, Long vertex_target_id, Double offset){
		this(road_id, vertex_source_id, vertex_target_id);
		this.setCost(offset);
	}
	
	public Edge(Long road_id, Long vertex_source_id, Long vertex_target_id, Coordinate c_source, Coordinate c_target){
		this(road_id, vertex_source_id, vertex_target_id);
		this.setTarget(c_target);
		this.setSource(c_source);
	}
	
	
	public void set(Long road_id, Long vertex_source_id, Long vertex_target_id){
		this.setRoadID(road_id);
		this.setFromNode(vertex_source_id);
		this.setToNode(vertex_target_id);
	}
	
	public void set(Long road_id, Long vertex_source_id, Long vertex_target_id, Coordinate c){
		this.set(road_id, vertex_source_id, vertex_target_id);
		this.setSource(c);
	}
	
	public double getCost() {
		return offset;
	}

	public void setCost(Double offset) {
		this.offset = offset;
	}

	public Coordinate getSource() {
		return source;
	}

	public void setSource(Coordinate source) {
		this.source = source;
	}

	public Coordinate getTarget() {
		return target;
	}

	public void setTarget(Coordinate target) {
		this.target = target;
	}

	public Long getRoadID() {
		return road_id;
	}

	public void setRoadID(Long road_id) {
		this.road_id = road_id;
	}

	public Long getFromNode() {
		return vertex_source_id;
	}

	public void setFromNode(Long vertex_source_id) {
		this.vertex_source_id = vertex_source_id;
	}

	public Long getToNode() {
		return vertex_target_id;
	}

	public void setToNode(Long vertex_target_id) {
		this.vertex_target_id = vertex_target_id;
	}
	
	
}