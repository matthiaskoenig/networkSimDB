package nsdb.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Collection {
	private Long id;
	private Xref xref;
	private Owner owner;
	
	private Set<Descriptor> descriptors = new HashSet<Descriptor>();
	private Set<Mapping> mappings = new HashSet<Mapping>();
	
	
	public Long getId() {
		return id;
	}
	@SuppressWarnings("unused")
	private void setId(long id) {
		this.id = id;
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
	
	public Set<Descriptor> getDescriptors() {
		return descriptors;
	}
	public void setDescriptors(Set<Descriptor> descriptors) {
		this.descriptors = descriptors;
	}

	public Set<Mapping> getMappings() {
		return mappings;
	}
	public void setMappings(Set<Mapping> mappings) {
		this.mappings = mappings;
	}
}
