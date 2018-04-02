package com.spring.beanFilter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INSTALLATION_MODE_MASTER")
@Data
public class Mode {
    @Id
    @Column(name = "ID")
    private String modeId;
    @Column(name = "NAME")
    private String modeDescription;
    @OneToMany(mappedBy = "INSTALLATION_MODE")
    private List<InfrastructureMapping> mappings = new ArrayList<>();
}
