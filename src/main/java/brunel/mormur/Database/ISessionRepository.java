package brunel.mormur.Database;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISessionRepository extends PagingAndSortingRepository<SessionDetails, Long> {

}
