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
    @Column(name = "SessionId")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "host")
    private Long hostId;

    @Column(name = "title")
    private String title;

    @OneToMany(targetEntity = Question.class)
    List<Question> replies;

    @Version @JsonIgnore
    private Long version;


    public SessionDetails() {

    }

    public SessionDetails(String title) {
        this.title = title;
        this.version = 1L;
    }

    public SessionDetails(String title, Long hostId, Long version) {
        this.title = title;
        this.hostId = hostId;
        this.version = version;
    }
}
