package nsdb.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Descriptor {
	private Long id;
	private String name;
	private String description;
	
	private Set<Collection> collections = new HashSet<Collection>();
	private Set<Mapping> mappings = new HashSet<Mapping>();
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Collection> getCollections() {
		return collections;
	}
	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}
	public Set<Mapping> getMappings() {
		return mappings;
	}
	public void setMappings(Set<Mapping> mappings) {
		this.mappings = mappings;
	}

}
