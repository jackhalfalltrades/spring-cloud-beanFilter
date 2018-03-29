import com.maat.bestbuy.integration.dao.OperationsDao
import com.maat.bestbuy.integration.repository.ApplicationsRepository
import com.maat.bestbuy.integration.repository.EnvironmentRepository
import com.maat.bestbuy.integration.repository.InfrastructureMappingRespository
import com.maat.bestbuy.integration.repository.ServerTypeRepository
import com.maat.bestbuy.integration.repository.TaskRepository
import com.maat.bestbuy.integration.repository.TechnologyRepository
import spock.lang.Shared
import spock.lang.Specification

class OperationDaoSpec extends Specification{

    @Shared
    OperationsDao operationsDao
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
