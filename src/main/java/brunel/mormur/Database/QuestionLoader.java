package brunel.mormur.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Date;

    public class QuestionLoader implements CommandLineRunner {

        private final IQuestionRepository repository;

        @Autowired
        public QuestionLoader(IQuestionRepository repository) { this.repository = repository; }

        @Override
        public void run(String... strings) throws Exception
        {
            //To test the columns of the repository
            User u1 = new User("Frodo", "Baggins", "ring bearer");
            SessionDetails ev = new SessionDetails("Ted Talks with Ted");
            Date d1 = new Date();
            Date d2 = new Date();
            Question q = new Question(u1, "Testttt", d1, d2, ev);
            this.repository.save(q);
        }

}
