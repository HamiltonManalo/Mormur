package brunel.mormur.Database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import brunel.mormur.*;
import brunel.mormur.controllers.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
//    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String emailAddress;

//    @Column(name = "version")
    private @Version @JsonIgnore Long version;

    private User() {}

    public User(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }
    public User(String firstName, String lastName, String emailAddress, long version) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.version = version;
    }

}
