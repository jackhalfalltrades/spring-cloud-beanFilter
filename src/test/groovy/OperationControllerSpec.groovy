import com.fasterxml.jackson.databind.ser.BeanPropertyFilter
import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.maat.bestbuy.integration.exception.ResourceNotFoundException
import com.maat.bestbuy.integration.model.Dropdown
import com.maat.bestbuy.integration.model.Payload
import com.maat.bestbuy.integration.model.ResponseFilter
import com.maat.bestbuy.integration.service.OperationsService
import com.maat.bestbuy.integration.web.controller.OperationsController
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.request.async.DeferredResult
import rx.Observable
import spock.lang.Shared
import spock.lang.Specification

class OperationControllerSpec extends  Specification{

    @Shared
    OperationsController operationsController

    @Shared
    OperationsService operationsServiceMock

    @Shared
    MockMvc mockMvc

    def setup() {
        operationsServiceMock = Mock(OperationsService)
        operationsController = new OperationsController(operationsServiceMock)
        mockMvc = MockMvcBuilders.standaloneSetup(operationsController).build()
    }

    def 'test load drop downs successful' () {
        setup:
        List<Dropdown> dropdownList = new ArrayList()
        dropdownList.add(Dropdown.builder().appId("1").appMerlinCode("REIM").appName("REIM STACK").build())
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dropdownList)
        FilterProvider filterProvider = new SimpleFilterProvider()
        filterProvider.addFilter("dropdown", SimpleBeanPropertyFilter.filterOutAllExcept("appName", "appId", "appMerlinCode") as BeanPropertyFilter)
        mappingJacksonValue.setFilters(filterProvider)
        DeferredResult<MappingJacksonValue> expectedResult = new DeferredResult<>()
        expectedResult.setResult(mappingJacksonValue)
        expectedResult.setErrorResult(mappingJacksonValue)
        String payload = "{\"dropdownName\": \"applications\", \"responseFilter\": {}}"
        operationsServiceMock.getDropdown(_ as Payload) >> mappingJacksonValue

        when:
        def mockMvcResponse = mockMvc.perform(MockMvcRequestBuilders.post("/load/operations/dropdown").content(payload).contentType("application/json")).andReturn()
        def response = mockMvcResponse.getResponse()

        then:
        1 * operationsServiceMock.getDropdown(_ as Payload) >> mappingJacksonValue
        response?.status == 200
    }

    def 'test bad request exception' () {
        setup:
        String payload = "{\"dropdownName\": \"\", \"responseFilter\": {}}"

        when:
        def mockMvcResponse = mockMvc.perform(MockMvcRequestBuilders.post("/load/operations/dropdown").content(payload).contentType("application/json")).andReturn()
        def response = mockMvcResponse.getResponse()

        then:
        response?.status == 400
    }

    def 'test resource not found exception' () {
        setup:
        Payload payload = new Payload()
        payload.setDropdownName("environment")
        List<String> applicationList = new ArrayList<>()
        applicationList.add("2")
        ResponseFilter responseFilter = new ResponseFilter()
        responseFilter.setApplication(applicationList)
        payload.setResponseFilter(responseFilter)
        operationsServiceMock.getDropdown(payload) >> {throw new ResourceNotFoundException("No environment values found for the filter: " + responseFilter)}
        String payload1 = "{\"dropdownName\": \"environment\", \"responseFilter\": {\"application\" : [\"2\"] }}"

        when:
        def mockMvcResponse = mockMvc.perform(MockMvcRequestBuilders.post("/load/operations/dropdown").content(payload1).contentType("application/json")).andReturn()
        def response = mockMvcResponse.getResponse()

        then:
        response?.status == 404
    }
}