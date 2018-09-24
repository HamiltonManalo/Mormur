package brunel.mormur.Database;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IEventsRepository extends PagingAndSortingRepository<Event, Long> {

    Event save(Event event);

}
