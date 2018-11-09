package brunel.mormur.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
    public class QuestionLoader implements CommandLineRunner {

        private final IQuestionRepository repository;

        @Autowired
        public QuestionLoader(IQuestionRepository repository) { this.repository = repository; }
        @Override
        public void run(String... strings) throws Exception
        {
            //To test the columns of the repository
            User u1 = new User("Frodo", "Baggins", "ring bearer");
            SessionDetails ev = new SessionDetails("Ted Talks with Ted", 123L);
            Date d1 = new Date();

            this.repository.save(new Question(u1,
                    "Oh good lord, this is a lot of data", new Date(), new Date(), ev));
        }

}
