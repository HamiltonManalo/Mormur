package brunel.mormur.views;

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
    @GeneratedValue
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String emailAddress;

    @Column(name = "version")
    private @Version @JsonIgnore Long version;

    private User() {}

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

}
