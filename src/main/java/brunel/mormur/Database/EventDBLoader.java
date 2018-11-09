package brunel.mormur.Database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EventDBLoader implements CommandLineRunner {

    private final IEventsRepository repoistory;

    @Autowired
    public EventDBLoader(IEventsRepository repository) {
        this.repoistory = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        User user1 = new User("Ted", "Teddleson", "Ted@TedsBeds.com");
        User user2 = new User("Larry", "Charleston", "Larry@charlestonschews.com");
        this.repoistory.save(new SessionDetails("Ted Talks with Ted", 123L));
        this.repoistory.save(new SessionDetails("Chewing with Grace, with Larry", 1234L));
    }
}
