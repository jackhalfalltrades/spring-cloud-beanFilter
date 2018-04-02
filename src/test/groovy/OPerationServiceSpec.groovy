import com.fasterxml.jackson.databind.ser.BeanPropertyFilter
import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.spring.beanFilter.dao.DAO
import com.spring.beanFilter.model.Dropdown
import com.spring.beanFilter.model.Payload
import com.spring.beanFilter.model.ResponseFilter
import com.spring.beanFilter.service.OperationsService
import org.springframework.http.converter.json.MappingJacksonValue
import spock.lang.Shared
import spock.lang.Specification

class OPerationServiceSpec extends Specification {

    @Shared
    OperationsService operationsService
    @Shared
    DAO operationsDaoMock
    @Shared
    Payload payload
    @Shared
    List<Dropdown> dropdownList
    @Shared
    Dropdown dropdown
    @Shared
    List<String> list

    def setup() {
        operationsDaoMock = Mock(DAO)
        operationsService = new OperationsService(operationsDaoMock)
    }

    def 'test sucessful fetch of dropdowns'() {
        setup:
        list = new ArrayList<>()
        dropdownList = new ArrayList<>()
        FilterProvider filterProvider = new SimpleFilterProvider()
        filterProvider.addFilter("dropdown", SimpleBeanPropertyFilter.filterOutAllExcept("envName", "envId") as BeanPropertyFilter)
        dropdownList.add(Dropdown.builder().envId("1").envName("Development").build())
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dropdownList)
        mappingJacksonValue.setFilters(filterProvider)
        list.add("2")
        payload = new Payload("environment", ResponseFilter.builder().application(list).build())
        operationsDaoMock.getDropdown(payload.getDropdownName(), payload.getResponseFilter()) >> dropdownList

        when:
        def response = operationsService.getDropdown(payload)

        then:
        response.value == mappingJacksonValue.value
        _ * operationsDaoMock.getDropdown(_, _) >> dropdownList
    }
}
