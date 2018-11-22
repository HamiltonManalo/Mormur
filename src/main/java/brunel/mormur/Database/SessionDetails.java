package brunel.mormur.Database;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "Sessions")
@Entity
public class SessionDetails {

    @Id
    @Column(name = "SessionId")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JsonBackReference
    private User host;

    @Column(name = "title")
    private String title;

    @Column(name = "startTime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "endTime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToMany(targetEntity = Question.class, fetch = FetchType.LAZY)
    @JsonBackReference
    List<Question> questions;

    @Version @JsonIgnore
    private Long version;

    public SessionDetails() {    }

    public SessionDetails(String title) {
        this.title = title;
        this.version = 1L;
    }

    public SessionDetails(String title, User host, Date start, Date end) {
        this.title = title;
        this.host = host;
        this.startTime = start;
        this.endTime = end;
        this.version = 1L;
    }
}