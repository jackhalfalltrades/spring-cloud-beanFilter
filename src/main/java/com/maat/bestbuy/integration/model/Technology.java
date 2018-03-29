package com.maat.bestbuy.integration.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TECHNOLOGY_MASTER")
@Data
public class Technology {

	@Id
	@Column(name="ID")
	private String technologyId;
	@Column(name="NAME")
	private String technologyName;
	@Column(name="VERSION")
	private String version;
	@OneToMany(mappedBy="TECHNOLOGY")
	private List<InfrastructureMapping> mappings= new ArrayList<>();
	
	/*public void addMappings(InfrastructureMapping mapping) {
		this.mappings.add(mapping);
	}
	public void removeMappings(InfrastructureMapping mapping) {
		this.mappings.remove(mapping);
	}*/
}
