package nsdb.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Edge {

	private Long 	id;
	private Type 	type;
	private Xref 	xref;
	private Node   source; 
	private Node   target;
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
	public Node getSource() {
		return source;
	}
	public void setSource(Node source) {
		this.source = source;
	}
	public Node getTarget() {
		return target;
	}
	public void setTarget(Node target) {
		this.target = target;
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
