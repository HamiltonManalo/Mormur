package brunel.mormur.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final IUserRepository repository;

    @Autowired
    public DatabaseLoader(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new User("Frodo", "Baggins", "ring bearer",1L));
        this.repository.save(new User("Frodo1", "Baggin1s", "ring bearer",1L));

    }
}