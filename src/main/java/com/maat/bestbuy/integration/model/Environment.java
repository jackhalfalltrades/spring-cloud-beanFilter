package com.maat.bestbuy.integration.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ENVIRONMENT_MASTER")
@Data
public class Environment {

    @Id
    @Column(name = "ID")
    private String envId;
    @Column(name = "ENVIRONMENT")
    private String envName;
    @OneToMany(mappedBy = "ENVIRONMENT")
    private List<InfrastructureMapping> mappings = new ArrayList<>();
}
