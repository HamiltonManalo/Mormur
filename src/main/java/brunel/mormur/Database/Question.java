package brunel.mormur.Database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "Question")

public class Question {

    @Id
    @Column(name = "QuestionID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "LinkedQuestionID")
    @OneToOne(targetEntity = Question.class, cascade = CascadeType.ALL)
    private long linkedQuestionIds;

    @ManyToOne(targetEntity = User.class, cascade=CascadeType.ALL)
    @Column(name = "ParticipantID")
    private User participantId;

    @Column(name = "QuestionText")
    private String questionText;

    @Column(name = "HashTags")
    private ArrayList<String> hashTags;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DateCreated")
    private Date dateCreated;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DateModified")
    private Date dateUpdated;




    @ManyToOne(targetEntity = SessionDetails.class, cascade=CascadeType.ALL)
    @Column(name = "SessionDetails")
    private SessionDetails session;




    @ElementCollection(targetClass=Integer.class)
    @Column(name = "userIDLikeList")
    private List<Integer> userIDLikeList;

    @Version @JsonIgnore
    private long version;

    private Question() {}


    public Question(String body, int messageType, Date dateCreated, Date dateUpdated, User participantId, String[] keyWords, SessionDetails sessionDetails, Question parent, List<Question> children, List<Integer> userIDLikeList, long version) {
        this.body = body;
        this.messageType = messageType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.participantId = participantId;
        this.keyWords = keyWords;
        this.event = sessionDetails;
        this.parent = parent;
        this.children = children;
        this.userIDLikeList = userIDLikeList;
        this.version = version;
    }

    public Question(String body, int messageType, Date dateCreated, Date dateUpdated, User participantId, String[] keyWords, SessionDetails sessionDetails, Question parent, List<Question> children, List<Integer> userIDLikeList) {
        this.body = body;
        this.messageType = messageType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.participantId = participantId;
        this.keyWords = keyWords;
        this.event = sessionDetails;
        this.parent = parent;
        this.children = children;
        this.userIDLikeList = userIDLikeList;
        this.version = 1L;
    }
}
