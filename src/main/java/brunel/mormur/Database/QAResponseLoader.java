package brunel.mormur.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
    public class QAResponseLoader implements CommandLineRunner {

        private final IQuestionRepository repository;

        @Autowired
        public QAResponseLoader(IQuestionRepository repository) { this.repository = repository; }
        @Override
        public void run(String... strings) throws Exception
        {
            //To test the columns of the repository
            User u1 = new User("Frodo", "Baggins", "ring bearer");
//            SessionDetails ev = new SessionDetails(2, "Ted", "Ted Talks with Ted", new Date(), new Date(), "Vanity");

            Date d1 = new Date();

//            this.repository.save(new Question("Oh good lord, this is a lot of data", 1, new Date(), new Date(), u1, null, ev, null, null, null));
        }

}
