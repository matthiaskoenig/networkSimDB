package nsdb.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Mapping {
	private Long id;
	private Collection collection;
	private String name;
	private Owner owner;
	
	private Set<Descriptor> descriptors = new HashSet<Descriptor>();
	private Set<Graph> graphs = new HashSet<Graph>();
	private Set<Attribute> attributes = new HashSet<Attribute>();


	public Long getId() {
		return id;
	}
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Set<Descriptor> getDescriptors() {
		return descriptors;
	}
	public void setDescriptors(Set<Descriptor> descriptors) {
		this.descriptors = descriptors;
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
