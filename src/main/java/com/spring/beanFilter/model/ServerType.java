package com.spring.beanFilter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SERVER_TYPE_MASTER")
@Data
public class ServerType {

    @Id
    @Column(name = "ID")
    private String serverTypeId;
    @Column(name = "Type")
    private String serverType;
    @OneToMany(mappedBy = "SERVER_TYPE")
    private List<InfrastructureMapping> mappings = new ArrayList<>();

	/*public void addMappings(InfrastructureMapping mapping) {
		this.mappings.add(mapping);
	}
	public void removeMappings(InfrastructureMapping mapping) {
		this.mappings.remove(mapping);
	}*/


}
