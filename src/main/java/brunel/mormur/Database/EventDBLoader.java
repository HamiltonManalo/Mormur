package brunel.mormur.Database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        this.repoistory.save(new Event(2, "Ted", "Ted Talks with Ted", new Date(), new Date(), "Vanity"));
        this.repoistory.save(new Event(1,"Larry", "Chewing with Grace, with Larry", new Date(), new Date(), "Hunger"));
    }
}
