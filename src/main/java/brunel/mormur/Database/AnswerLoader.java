package brunel.mormur.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Date;

public class AnswerLoader implements CommandLineRunner {

    private final IAnswerRepository repository;

    @Autowired
    public AnswerLoader(IAnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        User user1 = new User("Ted", "Teddleson", "Ted@TedsBeds.com");
        User user2 = new User("Larry", "Charleston", "Larry@charlestonschews.com");

        QARoom ev = new QARoom("Ted Talks with Ted");
        Question q1 = new Question(user1,"Oh good lord, this is a lot of data", new Date(), new Date(), ev);
        Question q2 = new Question(user2,"Oh good lord, this is a lot of data", new Date(), new Date(), ev);
        this.repository.save(new Answer("Try counting on your fingers", new Date(), new Date(), q1, user2 ));
        this.repository.save(new Answer("Chewing with Grace, with Larry", new Date(), new Date(), q2, user1));
    }

}
