package com.maat.bestbuy.integration.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@NamedQueries({

        @NamedQuery(name = "InfrastructureMapping.findAllApplications", query = "select distinct m.APPLICATION from InfrastructureMapping m"),
        @NamedQuery(name = "InfrastructureMapping.queryEnvironment", query = "select m.ENVIRONMENT FROM InfrastructureMapping m where m.APPLICATION = :application"),
        @NamedQuery(name = "InfrastructureMapping.queryServerType", query = "select m.SERVER_TYPE FROM InfrastructureMapping m where m.APPLICATION = :application and m.ENVIRONMENT = :environment"),
        @NamedQuery(name = "InfrastructureMapping.queryTechnology", query = "select m.TECHNOLOGY FROM InfrastructureMapping m where m.APPLICATION = :application and m.ENVIRONMENT = :environment and m. SERVER_TYPE = :serverType"),
        @NamedQuery(name = "InfrastructureMapping.queryMode", query = "select m.INSTALLATION_MODE FROM InfrastructureMapping m where m.APPLICATION = :application and m.ENVIRONMENT = :environment and m. SERVER_TYPE = :serverType and m.TECHNOLOGY = :technology"),
        @NamedQuery(name = "InfrastructureMapping.queryHosts", query = "select m.HOST FROM InfrastructureMapping m where m.APPLICATION = :application and m.ENVIRONMENT = :environment and m. SERVER_TYPE = :serverType and m.DomainNames IN (:domainNames) and m.ClusterNames IN (:clusterNames)")})
@Cacheable
@Table(name = "INFRASTRUCTURE_MAPPING")
@Data
public class InfrastructureMapping {

    @Id
    @Column(name = "ID")
    private String mappingId;
    @Column(name = "JAVA_HOME_PATH")
    private String JAVAHomePath;
    @Column(name = "INSTALLATION_HOME_PATH")
    private String InstallationHomePath;
    @Column(name = "MASTER_SLAVE")
    private String MasterSlave;
    @Column(name = "NUMBER_OF_PROFILES")
    private String NumberofProfiles;
    @Column(name = "PROFILE_NAME")
    private String ProfileName;
    @Column(name = "NUMBER_OF_DOMAINS")
    private String NumberofDomains;
    @Column(name = "DOMAIN_NAMES")
    private String DomainNames;
    @Column(name = "NUMBER_OF_CLUSTERS")
    private String NumberofClusters;
    @Column(name = "CLUSTER_NAMES")
    private String ClusterNames;
    @Column(name = "NUMBER_OF_INSTANCES")
    private String NumberofInstances;
    @Column(name = "INSTANCES_NAME")
    private String InstancesName;
    @Column(name = "STARTUP_SCRIPT_NAME")
    private String StartupScriptName;
    @Column(name = "STARTUP_SCRIPT_PATH")
    private String StartupScriptPath;
    @Column(name = "STOP_SCRIPT_NAME")
    private String StopScriptName;
    @Column(name = "STOP_SCRIPT_PATH")
    private String StopScriptPath;
    @Column(name = "CERTIFICATE_NAME")
    private String CertificateName;
    @Column(name = "CERTIFICATE_SERIAL_NUMBER")
    private String CertificateSerialNumber;
    @Column(name = "CERTIFICATE_PATH")
    private String CertificatePath;
    @Column(name = "CERTIFICATE_CREATED_DATE")
    private String CertificateCreatedName;
    @Column(name = "CERTIFICATE_EXPIRATION_DATE")
    private String CertificateExpirationDate;
    @Column(name = "CERTIFICATE_CN_SAN_ENTRIES")
    private String CertificateCNSANEntries;
    @Column(name = "DEPLOYMENT_PATH_DEST")
    private String DeploymentPath;
    @Column(name = "DEPLOYMENT_TYPE")
    private String DeploymentType;
    @Column(name = "ARTIFACT_NAME")
    private String ArtifactName;
    @Column(name = "LOG_FILE_NAME")
    private String LogFileName;
    @Column(name = "LOG_FILE_PATH")
    private String LogFilePath;
    @Column(name = "MANAGEMENT_HTTP_PORT")
    private String ManagementHTTPPort;
    @Column(name = "MANAGEMENT_HTTPS_PORT")
    private String ManagementHTTPSPort;
    @Column(name = "MANAGEMENT_CLI_PORT")
    private String ManagementCLIPort;
    @Column(name = "MANAGEMENT_HTTPS_URL")
    private String ManagementHTTPSURL;
    @Column(name = "MANAGEMENT_HTTP_URL")
    private String ManagementHTTPURL;
    @Column(name = "MANAGEMENT_CLI_URL")
    private String ManagementCLIURL;
    @Column(name = "INSTANCE_HTTP_PORT")
    private String InstanceHTTPPort;
    @Column(name = "INSTANCE_HTTPS_PORT")
    private String InstanceHTTPSPort;
    @Column(name = "INSTANCE_HTTP_URL")
    private String InstanceHTTPURL;
    @Column(name = "INSTANCE_HTTPS_URL")
    private String InstanceHTTPSURL;
    @ManyToOne
    private VirtualMachine HOST;
    @ManyToOne
    private Application APPLICATION;
    @ManyToOne
    private Environment ENVIRONMENT;
    @ManyToOne
    private Mode INSTALLATION_MODE;
    @ManyToOne
    private ServerType SERVER_TYPE;
    @ManyToOne
    private Technology TECHNOLOGY;
    @Column(name = "ARTIFACTSOURCEPATH")
    private String artifactSourcePath;
    @Column(name="CLI_PATH")
    private String cliPath;
}
