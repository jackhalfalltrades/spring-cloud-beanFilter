package com.maat.bestbuy.integration.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonFilter("dropdown")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dropdown implements Serializable {

    private static final long serialVersionUID = 6158358640295856403L;

    private String appName;
    private String appId;
    private String appMerlinCode;
    private String envName;
    private String envId;
    private String serverType;
    private String serverTypeId;
    private String technologyName;
    private String technologyId;
    private String modeDescription;
    private String modeId;
    private String domainName;
    private String  domainId;
    private String clusterName;
    private String hostName;
    private String hostId;
    private String mappingId;
    private String instanceName;
    private String taskName;
    private String taskId;
    private String artifactId;
    private String artifactName;
    private String artifactSourcePath;
    private String artifactDestinationPath;
}

