package nsdb.hibernate.entity;

import java.util.*;

/**
 * Entity to store the graph.
 * Nodes and Edges are connected via graph_has_node and edge_has_node.
 * @author Michael
 *
 */
public class Graph {
	/** unique id */
	private Long 	id;
	private Type 	type;
	private Xref 	xref;
	private Owner 	owner;
	private Set<Node>   nodes = new HashSet<Node>();
	private Set<Edge>   edges = new HashSet<Edge>();
	private Set<Attribute>   attributes = new HashSet<Attribute>();
	private Set<Mapping>   mappings = new HashSet<Mapping>();
	
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
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Set<Node> getNodes() {
		return nodes;
	}
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
	public Set<Edge> getEdges() {
		return edges;
	}
	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}
	public Set<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}
	public Set<Mapping> getMappings() {
		return mappings;
	}
	public void setMappings(Set<Mapping> mappings) {
		this.mappings = mappings;
	}
	
	/** Print the Graph information */
	public void printGraph(){
		System.out.println("--------------------------------");
		System.out.println("Graph: " + this.getXref().getName() + " [" + this.getType().getName() + "]");
		System.out.println("--------------------------------");
		for (Node n : this.getNodes()){
			System.out.println("Node: " + n.getXref().getName() + " [" + n.getType().getName() + "]");
		}
		for (Edge e : this.getEdges()){
			System.out.println("Edge: " + e.getXref().getName() + " [" + e.getType().getName() + "]\t" +
					e.getSource().getXref().getName() + "-->" + e.getTarget().getXref().getName());
		}
		System.out.println("--------------------------------");
	}
	


}
