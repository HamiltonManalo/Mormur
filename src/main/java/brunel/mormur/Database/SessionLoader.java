package brunel.mormur.Database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SessionLoader implements CommandLineRunner {

    private final ISessionRepository repoistory;

    @Autowired
    public SessionLoader(ISessionRepository repository) {
        this.repoistory = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        User user1 = new User("Ted", "Teddleson", "Ted@TedsBeds.com");
        User user2 = new User("Larry", "Charleston", "Larry@charlestonschews.com");
        this.repoistory.save(new SessionDetails("Ted Talks with Ted"));
        this.repoistory.save(new SessionDetails("Chewing with Grace, with Larry"));
    }
}
