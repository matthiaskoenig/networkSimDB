package nsdb.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private Long 	id;
	private Type 	type;
	private Xref 	xref;
	private Set<Edge>   sourceEdges = new HashSet<Edge>(); 
	private Set<Edge>   targetEdges = new HashSet<Edge>();
	private Set<Graph> graphs = new HashSet<Graph>();
	private Set<Attribute> attributes = new HashSet<Attribute>();
	
	public Long getId() {
		return id;
	}
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Xref getXref() {
		return xref;
	}
	public void setXref(Xref xref) {
		this.xref = xref;
	}
	
	public Set<Edge> getSourceEdges() {
		return sourceEdges;
	}
	public void setSourceEdges(Set<Edge> sourceEdges) {
		this.sourceEdges = sourceEdges;
	}
	public Set<Edge> getTargetEdges() {
		return targetEdges;
	}
	public void setTargetEdges(Set<Edge> targetEdges) {
		this.targetEdges = targetEdges;
	}
	
	public Set<Graph> getGraphs() {
		return graphs;
	}
	public void setGraphs(Set<Graph> graphs) {
		this.graphs = graphs;
	}
	public Set<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	
}
