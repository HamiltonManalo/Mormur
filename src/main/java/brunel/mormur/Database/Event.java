package brunel.mormur.Database;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Events")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "host")
    private long hostId;
    @Column(name = "title")
    private String title;
    private @Version @JsonIgnore

    Long version;
    public Event(String title, long hostId) {
        this.title = title;
        this.hostId = hostId;
    }

    public Event(String title, long hostId, long version) {
        this.title = title;
        this.hostId = hostId;
        this.version = version;
    }
}
