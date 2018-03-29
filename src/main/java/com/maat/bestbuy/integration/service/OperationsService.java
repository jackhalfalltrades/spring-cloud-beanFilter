package com.maat.bestbuy.integration.service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.maat.bestbuy.integration.dao.OperationsDao;
import com.maat.bestbuy.integration.exception.InternalServerErrorException;
import com.maat.bestbuy.integration.exception.MAATRuntimeException;
import com.maat.bestbuy.integration.model.Dropdown;
import com.maat.bestbuy.integration.model.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import rx.Observable;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("operationsService")
public class OperationsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationsService.class);

    private static final String FILTER_ID = "dropdown";

    private OperationsDao operationsDao;

    public OperationsService(OperationsDao operationsDao) {
        this.operationsDao = operationsDao;
    }

    public MappingJacksonValue getDropdown(Payload payload) {

        Observable<FilterProvider> beanPropertyFilterObservable = Observable.fromCallable(() ->
                getBeanFilter(payload.getDropdownName())
        );

        Observable<List<Dropdown>> dropdownObservable = Observable.fromCallable(() ->
                operationsDao.getDropdown(payload.getDropdownName(), payload.getResponseFilter())
        );

        return Observable.zip(beanPropertyFilterObservable, dropdownObservable, (FilterProvider filterProvider, List<Dropdown> dropdownList) -> {
            LOGGER.debug("getDropdownService(): dropdowList {}", dropdownList);
            LOGGER.debug("getDropdownService(): filterConstruct {}", filterProvider);
            MappingJacksonValue dropdownBeanFilterMapping = new MappingJacksonValue(dropdownList);
            dropdownBeanFilterMapping.setFilters(filterProvider);
            return dropdownBeanFilterMapping;
        }).toBlocking().single();
    }

    private FilterProvider getBeanFilter(String dropdownName) {
        LOGGER.info("BeanFIlter(): Configure filter");
        FilterProvider filterProvider = null;
        switch (dropdownName) {
            case "applications":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("appName", "appId", "appMerlinCode"));
                break;
            case "environment":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("envName", "envId"));
                break;
            case "type":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("serverType", "serverTypeId"));
                break;
            case "technology":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("technologyName", "technologyId"));
                break;
            case "mode":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("modeDescription", "modeId"));
                break;
            case "domain":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("domainName"));
                break;
            case "cluster":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("clusterName"));
                break;
            case "host":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("hostName", "hostId"));
                break;
            case "instance":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("mappingId", "instanceName"));
                break;
            case "action":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("taskName", "taskId"));
                break;
            case "artifact":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("artifactName"));
                break;
            case "artifactPath":
                filterProvider =  new SimpleFilterProvider().addFilter(FILTER_ID, SimpleBeanPropertyFilter.filterOutAllExcept("artifactSourcePath", "artifactDestinationPath"));
                break;
        }
        try {
            if(filterProvider != null) {
                return filterProvider;
            } else {
                throw new NullPointerException("no filter for - " + dropdownName);
            }
        } catch (NullPointerException npe) {
            throw new InternalServerErrorException(npe.getLocalizedMessage(), new Object[]{npe.getCause(), npe.getMessage(), npe.getStackTrace(), npe});
        } catch (Exception e) {
            throw new MAATRuntimeException(e.getLocalizedMessage(), e.getCause());
        }
    }
}
