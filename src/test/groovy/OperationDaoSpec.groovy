import com.spring.beanFilter.dao.DAO
import com.spring.beanFilter.repository.*
import spock.lang.Shared
import spock.lang.Specification

class OperationDaoSpec extends Specification {

    @Shared
    DAO operationsDao
    @Shared
    ApplicationsRepository applicationRepository
    @Shared
    EnvironmentRepository environmentRepository
    @Shared
    InfrastructureMappingRespository infrastructureMappingRespository
    @Shared
    ServerTypeRepository serverTypeRepository
    @Shared
    TechnologyRepository technologyRepository
    @Shared
    TaskRepository taskRepository


}
