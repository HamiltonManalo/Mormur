package brunel.mormur.views;

import org.springframework.data.repository.CrudRepository;
import brunel.mormur.*;
import brunel.mormur.controllers.*;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
}
