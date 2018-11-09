package brunel.mormur.Database;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IEventsRepository extends PagingAndSortingRepository<SessionDetails, Long> {

    SessionDetails save(SessionDetails sessionDetails);

}
