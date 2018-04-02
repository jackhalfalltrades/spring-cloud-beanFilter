package com.spring.beanFilter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HOST_MASTER")
@Data
public class VirtualMachine {

    @Id
    @Column(name = "ID")
    private String hostId;
    @Column(name = "NAME")
    private String hostName;
    @Column(name = "NUMBER_OF_CORES")
    private String numberOfCores;
    @Column(name = "OS_VERSION")
    private String osVersion;
    @Column(name = "RAM")
    private String RAM;
    @Column(name = "CPU")
    private String CPU;
    @Column(name = "VM_TYPE")
    private String vmType;
    @Column(name = "DATA_CENTER")
    private String dataCenter;
    @Column(name = "PLATFORM_VERSION")
    private String platformVersion;
    @OneToMany(mappedBy = "HOST")
    private List<InfrastructureMapping> mappings = new ArrayList<>();

/*	public void addMappings(InfrastructureMapping mapping) {
		this.mappings.add(mapping);
	}
	public void removeMappings(InfrastructureMapping mapping) {
		this.mappings.remove(mapping);
	}*/
}
