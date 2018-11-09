package brunel.mormur.Database;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "Events")
@Entity
public class SessionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "host")
    private long hostId;
    @Column(name = "title")
    private String title;

    @OneToMany(targetEntity = Question.class)
    List<Question> replies;

    @Version @JsonIgnore
    private long version;

    public SessionDetails() {}

    public SessionDetails(String title, long hostId) {
        this.title = title;
        this.hostId = hostId;
        this.version = 1L;

    }

    public SessionDetails(String title, long hostId, long version) {
        this.title = title;
        this.hostId = hostId;
        this.version = version;
    }
}
