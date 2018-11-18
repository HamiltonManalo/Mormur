package brunel.mormur.Database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class SessionLoader implements CommandLineRunner {

    private final ISessionRepository sessionRepository;
    private final IQuestionRepository questionRepository;
    private final IUserRepository userRepository;
    @Autowired
    public SessionLoader(ISessionRepository sessionRepository, IQuestionRepository questionRepository, IUserRepository userRepository) {

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
        SessionDetails s1 = new SessionDetails("Ted Talks with Ted", user1, new Date(), new Date());
        SessionDetails s2 = new SessionDetails("Chewing with Grace, with Larry", user2, new Date(), new Date());
        s1 = sessionRepository.save(s1);
        s2 = sessionRepository.save(s2);
        Question q1 = new Question(user1, "Oh say can you see", new Date(), new Date(), s1);
        Question q2 = new Question(user2, "by dawns early light", new Date(), new Date(), s1);
        q1 = questionRepository.save(q1);
        q2 = questionRepository.save(q2);
        s1.setQuestions(new ArrayList<>());
        s1.getQuestions().add(q1);
        s1.getQuestions().add(q2);
        sessionRepository.save(s1);


    }
}
