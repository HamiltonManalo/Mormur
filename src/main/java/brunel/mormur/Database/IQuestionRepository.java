package brunel.mormur.Database;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IQuestionRepository extends PagingAndSortingRepository<Question, Long> {

}
