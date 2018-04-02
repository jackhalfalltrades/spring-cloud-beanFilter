package com.spring.beanFilter.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.beanFilter.exception.BeanFilterRuntimeException;
import com.spring.beanFilter.exception.InternalServerErrorException;
import com.spring.beanFilter.exception.ResourceNotFoundException;
import com.spring.beanFilter.model.Dropdown;
import com.spring.beanFilter.model.ResponseFilter;
import com.spring.beanFilter.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Repository("operationDao")
public class DAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DAO.class);

    private ApplicationsRepository applicationRepository;
    private EnvironmentRepository environmentRepository;
    private InfrastructureMappingRespository infrastructureMappingRespository;
    private ServerTypeRepository serverTypeRepository;
    private TechnologyRepository technologyRepository;
    private TaskRepository taskRepository;

    @Autowired
    public DAO(ApplicationsRepository applicationRepository, EnvironmentRepository environmentRepository, InfrastructureMappingRespository infrastructureMappingRespository, ServerTypeRepository serverTypeRepository, TechnologyRepository technologyRepository, TaskRepository taskRepository) {
        this.applicationRepository = applicationRepository;
        this.environmentRepository = environmentRepository;
        this.infrastructureMappingRespository = infrastructureMappingRespository;
        this.serverTypeRepository = serverTypeRepository;
        this.technologyRepository = technologyRepository;
        this.taskRepository = taskRepository;
    }

    @HystrixCommand(groupKey = "getDropdownCommand", commandKey = "getDropdownCommand", threadPoolKey = "getDropdownCommand")
    public List<Dropdown> getDropdown(String dropdownName, ResponseFilter responseFilter) {

        try {

            LOGGER.info("getDropdownCommand(): Initiated!!!");
            LOGGER.debug("getDrodownCommand(): loading dropdown {} filtered by {}", dropdownName, responseFilter);

            List<Dropdown> dropdownList = new ArrayList<>();
            List<Dropdown> dropdowns = new ArrayList<>();

            switch (dropdownName) {
                case "applications":
                    return infrastructureMappingRespository.findAllApplications()
                            .stream().map(e -> Dropdown.builder().appId(e.getAppId()).appMerlinCode(e.getAppMerlinCode()).appName(e.getAppName()).build()).collect(Collectors.toList());
                case "environment":
                    return infrastructureMappingRespository.queryEnvironment(
                            applicationRepository.findById(responseFilter.getApplication().get(0)).get())
                            .stream().map(e -> Dropdown.builder().envId(e.getEnvId()).envName(e.getEnvName()).build()).collect(Collectors.toList());

                case "type":
                    return infrastructureMappingRespository.queryServerType(
                            applicationRepository.findById(responseFilter.getApplication().get(0)).get(),
                            environmentRepository.findById(responseFilter.getEnvironment().get(0)).get()).stream()
                            .map(e -> Dropdown.builder().serverType(e.getServerType()).serverTypeId(e.getServerTypeId()).build()).collect(Collectors.toList());

                case "technology":
                    return infrastructureMappingRespository.queryTechnology(
                            applicationRepository.findById(responseFilter.getApplication().get(0)).get(),
                            environmentRepository.findById(responseFilter.getEnvironment().get(0)).get(),
                            serverTypeRepository.findById(responseFilter.getType().get(0)).get())
                            .stream().map(e -> Dropdown.builder().technologyId(e.getTechnologyId()).technologyName(e.getTechnologyName()).build()).collect(Collectors.toList());

                case "mode":
                    return infrastructureMappingRespository.queryMode(
                            applicationRepository.findById(responseFilter.getApplication().get(0)).get(),
                            environmentRepository.findById(responseFilter.getEnvironment().get(0)).get(),
                            serverTypeRepository.findById(responseFilter.getType().get(0)).get(),
                            technologyRepository.findById(responseFilter.getTechnology().get(0)).get())
                            .stream().map(e -> Dropdown.builder().modeId(e.getModeId()).modeDescription(e.getModeDescription()).build()).collect(Collectors.toList());

                case "domain":
                    return infrastructureMappingRespository.queryDomainNames(
                            responseFilter.getApplication().get(0), responseFilter.getEnvironment().get(0), responseFilter.getType().get(0))
                            .stream().map(e -> Dropdown.builder().domainName(e).build()).collect(Collectors.toList());

                case "cluster":
                    return infrastructureMappingRespository.queryClusterNames(
                            responseFilter.getApplication().get(0), responseFilter.getEnvironment().get(0),
                            responseFilter.getType().get(0), responseFilter.getDomain())
                            .stream().map(e -> Dropdown.builder().clusterName(e).build()).collect(Collectors.toList());

                case "host":
                    return infrastructureMappingRespository.queryHosts(
                            applicationRepository.findById(responseFilter.getApplication().get(0)).get(),
                            environmentRepository.findById(responseFilter.getEnvironment().get(0)).get(),
                            serverTypeRepository.findById(responseFilter.getType().get(0)).get(),
                            responseFilter.getDomain(), responseFilter.getCluster())
                            .stream().map(e -> Dropdown.builder().hostId(e.getHostId()).hostName(e.getHostName()).build()).collect(Collectors.toList());

                case "instance":
                    return infrastructureMappingRespository.queryInstances(responseFilter.getApplication().get(0),
                            responseFilter.getEnvironment().get(0), responseFilter.getType().get(0),
                            responseFilter.getDomain(), responseFilter.getCluster(), responseFilter.getHost())
                            .stream().map(e -> Dropdown.builder().mappingId(e[0].toString()).instanceName(e[1].toString()).build()).collect(Collectors.toList());

                case "action":
                    return taskRepository.findAll()
                            .stream().map(e -> Dropdown.builder().taskId(e.getTaskId()).taskName(e.getTaskName()).build()).collect(Collectors.toList());

                case "artifact":
                    if ("1".equals(responseFilter.getAction().get(0))) {
                        infrastructureMappingRespository.queryArtifactNames(responseFilter.getMapping()).stream().forEach(e -> {
                            Arrays.stream(e.split("-")).forEach(e1 -> {
                                dropdownList.add(Dropdown.builder().artifactId(e).artifactName(e1).build());
                            });
                        });
                    } else
                        dropdownList.add(Dropdown.builder().artifactId("NA").artifactName("NA").build());
                    return dropdownList;

                case "artifactPath":
                    infrastructureMappingRespository.queryArtifactPath(responseFilter.getMapping())
                            .stream().forEach(e -> {
                        responseFilter.getArtifact().stream().forEach(artifact -> {
                            Optional<String> sourcePathOptional = Arrays.asList(e[0].toString().split(",")).stream()
                                    .filter(s -> artifact.equalsIgnoreCase(s.split(":")[0].toString()))
                                    .findFirst();
                            Optional<String> destinationPathOptional = Arrays.stream(e[1].toString().split(","))
                                    .filter(s -> artifact.equalsIgnoreCase(s.split(":")[0]))
                                    .findFirst();
                            if (destinationPathOptional.isPresent() && sourcePathOptional.isPresent())
                                dropdownList.add(Dropdown.builder().artifactSourcePath(sourcePathOptional.get().split(":")[1])
                                        .artifactDestinationPath(destinationPathOptional.get().split(":")[1])
                                        .build());
                        });
                    });
                    return dropdownList;

                default:
                    throw new Exception("dropdown name " + dropdownName + "invalid");
            }
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("No " + dropdownName + " values found for the filter: " + responseFilter, new Object[]{e.getCause(), e.getMessage(), e});
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getLocalizedMessage(), new Object[]{e.getCause(), e.getMessage(), e});
        } catch (NullPointerException npe) {
            throw new InternalServerErrorException(npe.getLocalizedMessage(), new Object[]{npe.getCause(), npe.getMessage(), npe.getStackTrace(), npe});
        } catch (Exception e) {
            throw new BeanFilterRuntimeException(e.getLocalizedMessage(), e.getCause());
        }
    }
}