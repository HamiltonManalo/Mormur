package brunel.mormur.Database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SessionLoader implements CommandLineRunner {

    private final ISessionRepository repository;

    @Autowired
    public SessionLoader(ISessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        User user1 = new User("Ted", "Teddleson", "Ted@TedsBeds.com");
        User user2 = new User("Larry", "Charleston", "Larry@charlestonschews.com");
        this.repository.save(new SessionDetails("Ted Talks with Ted"));
        this.repository.save(new SessionDetails("Chewing with Grace, with Larry"));

    }
}
