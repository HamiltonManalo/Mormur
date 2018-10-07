package brunel.mormur.Database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "QAResponse")

public class QAResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "body")
    private String body;
    @Column(name = "messageType")
    private int messageType;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateUpdated")
    private Date dateUpdated;

    @Column(name = "dateDeleted")
    private Date dateDeleted;
    @Temporal(value = TemporalType.TIMESTAMP)
    @ElementCollection(targetClass = Date.class)
    List<Date> editDates = new ArrayList<>();

    @ManyToOne(targetEntity = User.class, cascade=CascadeType.ALL)
//    @Column(name = "owner")
    private User owner;
    @Column(name = "keyWords")
    private String[] keyWords;

    @ManyToOne(targetEntity = Event.class, cascade=CascadeType.ALL)
//    @Column(name = "event")
    private Event event;

    @ManyToOne(targetEntity = QAResponse.class, cascade=CascadeType.ALL)
//    @Column(name = "parent")
    private QAResponse parent;
    // May need to be changed to list later on (for dynamic size).
    @OneToMany(targetEntity = QAResponse.class, cascade=CascadeType.ALL)
//    @Column(name = "children")
    private List<QAResponse> children;

    @ElementCollection(targetClass=Integer.class)
    @Column(name = "userIDLikeList")
    private List<Integer> userIDLikeList;

    @Version @JsonIgnore
    private long version;

    private QAResponse() {}


    public QAResponse(String body, int messageType, Date dateCreated, Date dateUpdated, User owner, String[] keyWords, Event event, QAResponse parent, List<QAResponse> children, List<Integer> userIDLikeList, long version) {
        this.body = body;
        this.messageType = messageType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.owner = owner;
        this.keyWords = keyWords;
        this.event = event;
        this.parent = parent;
        this.children = children;
        this.userIDLikeList = userIDLikeList;
        this.version = version;
    }

    public QAResponse(String body, int messageType, Date dateCreated, Date dateUpdated, User owner, String[] keyWords, Event event, QAResponse parent, List<QAResponse> children, List<Integer> userIDLikeList) {
        this.body = body;
        this.messageType = messageType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.owner = owner;
        this.keyWords = keyWords;
        this.event = event;
        this.parent = parent;
        this.children = children;
        this.userIDLikeList = userIDLikeList;
        this.version = 1L;
    }
}
