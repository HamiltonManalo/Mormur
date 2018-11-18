package brunel.controllers;

import brunel.mormur.Database.*;
import brunel.mormur.mormurMain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {mormurMain.class})
public class SessionControllerTests {

    @Autowired
    private TestEntityManager entityManager;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IQuestionRepository repository;


    @Before
    public void start() throws Exception
    {
//        User u1 = new User("Frodo", "Baggins", "ring bearer");
//        SessionDetails ev = new SessionDetails("Ted Talks with Ted");
//        Date d1 = new Date();
//        Date d2 = new Date();
//        Question q = new Question(u1, "Testttt", d1, d2, ev);
//        entityManager.persist(q);
    }
    @Test
    public void findQuestionsBySession_Test() {
        User u1 = new User("Frodo", "Baggins", "ring bearer");
        SessionDetails ev = new SessionDetails("Ted Talks with Ted");
        Date d1 = new Date();
        Date d2 = new Date();
        List<Question> q = new ArrayList<Question>();
        q.add(new Question(u1, "Oh say can you see", d1, d2, ev));
        q.add(new Question(u1, "by dawns early light", d1, d2, ev));
        q.add(new Question(u1, "something something", d1, d2, ev));
        q.add(new Question(u1, "america", d1, d2, ev));
        q.forEach(x -> entityManager.persist(x));
        entityManager.flush();

        long sessionIdToFindby = 3;
        System.out.println("HEERRREE ");
        q = null;
        q = repository.queryQuestionsBySession_Id(sessionIdToFindby);
        for(Question x : q) {
            System.out.println("Printing questions found " + x.getQuestionText());
        }


    }
}
