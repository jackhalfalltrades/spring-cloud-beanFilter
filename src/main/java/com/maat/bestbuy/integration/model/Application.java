package com.maat.bestbuy.integration.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APPLICATION_MASTER")
@NamedQuery(name = "find_all_applications", query = "select a from Application a")
@Data
public class Application {

    @Id
    @Column(name = "ID")
    private String appId;
    @Column(name = "NAME")
    private String appName;
    @Column(name = "MERLIN_CODE")
    private String appMerlinCode;
    @Column(name = "ARTIFACTORY_PATH")
    private String artifactoryPath;
    @OneToMany(mappedBy = "APPLICATION")
    private List<InfrastructureMapping> mappings = new ArrayList<>();
}
