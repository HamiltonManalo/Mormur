package brunel.mormur;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String shortBio;

    private User() {}

    public User(String firstName, String lastName, String shortBio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortBio = shortBio;
    }

}
