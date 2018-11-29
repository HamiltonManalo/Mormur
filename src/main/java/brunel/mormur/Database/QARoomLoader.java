package brunel.mormur.Database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class QARoomLoader implements CommandLineRunner {

    private final IQARoomRepository sessionRepository;
    private final IQuestionRepository questionRepository;
    private final IUserRepository userRepository;
    @Autowired
    public QARoomLoader(IQARoomRepository sessionRepository, IQuestionRepository questionRepository, IUserRepository userRepository) {

        this.sessionRepository = sessionRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        User user1 = new User("Ted", "Teddleson", "Ted@TedsBeds.com");
        User user2 = new User("Larry", "Charleston", "Larry@charlestonschews.com");
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        QARoom r1 = new QARoom("Ted Talks with Ted", user1, new Date(), new Date());
        QARoom r2 = new QARoom("Chewing with Grace, with Larry", user2, new Date(), new Date());
        r1 = sessionRepository.save(r1);
        r2 = sessionRepository.save(r2);
        Question q1 = new Question(user1, "Why is programming hard?", new Date(), new Date(), r1);
        Question q2 = new Question(user2, "Theres so many frameworks and programming languages, why cant?", new Date(), new Date(), r1);
        q1 = questionRepository.save(q1);
        q2 = questionRepository.save(q2);
        r1.setQuestions(new ArrayList<>());
        r1.getQuestions().add(q1);
        r1.getQuestions().add(q2);
        sessionRepository.save(r1);
    }
}
