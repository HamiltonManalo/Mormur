package brunel.mormur.Database;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "Events")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hostId")
    private long hostId;

    @Column(name = "hostName")
    private String hostName;

    @Column(name = "title")
    private String title;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @Column(name = "topic")
    private String topic;


    @OneToMany(targetEntity = QAResponse.class)
    List<QAResponse> replies;

    @Version @JsonIgnore
    private long version = 1;

    public Event() {}

    public Event(long hostId, String hostName, String title, Date startTime, Date endTime, String topic, List<QAResponse> replies) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.topic = topic;
        this.replies = replies;
    }
    public Event(long hostId, String hostName, String title, Date startTime, Date endTime, String topic) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.topic = topic;
    }
}
