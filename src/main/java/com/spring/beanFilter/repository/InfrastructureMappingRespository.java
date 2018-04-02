package com.spring.beanFilter.repository;


import com.spring.beanFilter.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfrastructureMappingRespository extends JpaRepository<InfrastructureMapping, Integer> {
    @Query(name = "InfrastructureMapping.findAllApplications")
    List<Application> findAllApplications();

    @Query(name = "InfrastructureMapping.queryEnvironment")
    List<Environment> queryEnvironment(@Param("application") Application application);

    @Query(name = "InfrastructureMapping.queryServerType")
    List<ServerType> queryServerType(@Param("application") Application application, @Param("environment") Environment environment);

    @Query(name = "InfrastructureMapping.queryTechnology")
    List<Technology> queryTechnology(@Param("application") Application application, @Param("environment") Environment environment, @Param("serverType") ServerType serverType);

    @Query(name = "InfrastructureMapping.queryMode")
    List<Mode> queryMode(@Param("application") Application application, @Param("environment") Environment environment, @Param("serverType") ServerType serverType, @Param("technology") Technology technology);

    @Query(value = "Select m.DOMAIN_NAMES From INFRASTRUCTURE_MAPPING m where m.APPLICATION_ID = :applicationId and m.ENVIRONMENT_ID = :environmentId and m.SERVER_TYPE_ID = :serverTypeId",
            nativeQuery = true)
    List<String> queryDomainNames(@Param("applicationId") String applicationId, @Param("environmentId") String environmentId, @Param("serverTypeId") String serverTypeId/*, @Param("technologyId") String technologyId, @Param("modeId") String modeId*/);

    @Query(value = "Select m.CLUSTER_NAMES From INFRASTRUCTURE_MAPPING m where m.APPLICATION_ID = :applicationId and m.ENVIRONMENT_ID = :environmentId and m.SERVER_TYPE_ID = :serverTypeId and m.DOMAIN_NAMES IN (:domainNames)",
            nativeQuery = true)
    List<String> queryClusterNames(@Param("applicationId") String applicationId, @Param("environmentId") String environmentId, @Param("serverTypeId") String serverTypeId, /*@Param("technologyId") String technologyId, @Param("modeId") String modeId,*/ @Param("domainNames") List<String> domainNames);

    @Query(name = "InfrastructureMapping.queryHosts")
    List<VirtualMachine> queryHosts(@Param("application") Application application, @Param("environment") Environment environment, @Param("serverType") ServerType serverType,/* @Param("technology") Technology technology, @Param("mode") Mode mode,*/ @Param("domainNames") List<String> domainNames, @Param("clusterNames") List<String> clusterNames);

    @Query(value = "Select m.ID, m.INSTANCES_NAME From INFRASTRUCTURE_MAPPING m where m.APPLICATION_ID = :applicationId and m.ENVIRONMENT_ID = :environmentId and m.SERVER_TYPE_ID = :serverTypeId and m.DOMAIN_NAMES IN (:domainNames) and m.CLUSTER_NAMES IN (:clusterNames) and HOST_ID IN (:hostId)",
            nativeQuery = true)
    List<Object[]> queryInstances(@Param("applicationId") String applicationId, @Param("environmentId") String environmentId, @Param("serverTypeId") String serverTypeId, /*@Param("technologyId") String technologyId, @Param("modeId") String modeId,*/ @Param("domainNames") List<String> domainNames, @Param("clusterNames") List<String> clusterNames, @Param("hostId") List<String> hostId);

    @Query(value = "Select m.ARTIFACT_NAME From INFRASTRUCTURE_MAPPING m where m.ID IN ( :mappingId )",
            nativeQuery = true)
    List<String> queryArtifactNames(@Param("mappingId") List<String> mappingId);

    @Query(value = "select m.ARTIFACTSOURCEPATH, m.DEPLOYMENT_PATH_DEST From INFRASTRUCTURE_MAPPING m where m.ID IN (:mappingId)", nativeQuery = true)
    List<Object[]> queryArtifactPath(@Param("mappingId") List<String> mappingId);

}