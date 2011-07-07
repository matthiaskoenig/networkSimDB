package nsdb.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Xref {

	private Long 	id;
	private String 	name;
	private String 	link;
	private Set<Edge>  edges = new HashSet<Edge>(); 
	private Set<Collection>  collections = new HashSet<Collection>();
	private Set<Graph> graphs = new HashSet<Graph>(); 
	private Set<Node> nodes = new HashSet<Node>();
	
	public Long getId() {
		return id;
	}
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Set<Edge> getEdges() {
		return edges;
	}
	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}
	public Set<Collection> getCollections() {
		return collections;
	}
	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}
	public Set<Graph> getGraphs() {
		return graphs;
	}
	public void setGraphs(Set<Graph> graphs) {
		this.graphs = graphs;
	}
	public Set<Node> getNodes() {
		return nodes;
	}
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
	
	
}
