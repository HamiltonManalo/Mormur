package brunel.mormur.Database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "QAResponse")

public class QAResponse {


//    @Id
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    @Column(name = "body")
    private String body;
    @Column(name = "messageType")
    private int messageType;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateUpdated")
    private Date dateUpdated;
    @Column(name = "owner")
    private User owner;
    @Column(name = "keyWords")
    private String[] keyWords;
    @Column(name = "event")
    private Event event;
    @Column(name = "parent")
    private QAResponse parent;
    // May need to be changed to list later on (for dynamic size).
    @Column(name = "children")
    private QAResponse[] children;
    @Column(name = "userIDLikeList")
    private List<Integer> userIDLikeList;

    private @Version @JsonIgnore
    Long version;

    private QAResponse() {}


    public QAResponse(String body, int messageType, Date dateCreated, Date dateUpdated, User owner, String[] keyWords, Event event, QAResponse parent, QAResponse[] children, List<Integer> userIDLikeList, Long version) {
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

    public QAResponse(String body, int messageType, Date dateCreated, Date dateUpdated, User owner, String[] keyWords, Event event, QAResponse parent, QAResponse[] children, List<Integer> userIDLikeList) {
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
    }
}
