package brunel.mormur.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {

      List<Question> queryQuestionsByRoom_Id(long sessionId);

}
