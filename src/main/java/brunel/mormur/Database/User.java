package brunel.mormur.Database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import brunel.mormur.*;
import brunel.mormur.controllers.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "Password")
    @JsonIgnore
    private String password;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "Joined")
    private LocalDateTime joined;

    @Column(name = "ImageURL")
    private String imageURL;

    @Column(name = "UserBadge")
    private String userBadge;

    @Column(name = "ModerationFlag")
    private Boolean moderationFlag;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "LastLogin")
    private LocalDateTime lastLogin;

//    @Column(name = "isExpert")
//
//    @Column(name = "IsActive")
//
//    @Column(name = "IsSuperUser")

    @Column(name = "version")
    @Version @JsonIgnore
    private Long version;

    private User() {}

    public User(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = "";
        this.joined = LocalDateTime.now();
        this.imageURL = "";
        this.userBadge = "";
        this.moderationFlag = false;
        this.lastLogin = LocalDateTime.now();
        this.version = 0L;
    }

    public User(String firstName, String lastName, String emailAddress, String password, LocalDateTime joined, String imageURL, String userBadge, boolean moderationFlag, LocalDateTime lastLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.joined = joined;
        this.imageURL = imageURL;
        this.userBadge = userBadge;
        this.moderationFlag = moderationFlag;
        this.lastLogin = lastLogin;
        this.version = 0L;
    }
}


