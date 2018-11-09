package brunel.mormur.Database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Question")
@Data
public class Question {

    @Id
    @Column(name = "QuestionID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "LinkedQuestionID")
    @OneToOne(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Long linkedQuestionIds;

    @OneToOne(targetEntity = User.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "ParticipantID")
    private User participant;

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

    @OneToOne(targetEntity = SessionDetails.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "SessionDetails")
    private SessionDetails session;

    @ElementCollection(targetClass=Integer.class)
    @Column(name = "userIDLikeList")
    private List<Integer> userIDLikeList;

    @Version @JsonIgnore
    private Long version;

    private Question() {}

    public Question(Long linkedQuestionIds, User participant, String questionText, ArrayList<String> hashTags, Date dateCreated, Date dateUpdated, SessionDetails session, List<Integer> userIDLikeList) {
        this.linkedQuestionIds = linkedQuestionIds;
        this.participant = participant;
        this.questionText = questionText;
        this.hashTags = hashTags;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.session = session;
        this.userIDLikeList = userIDLikeList;
        this.version = 0L;
        }
    public Question(User participant, String questionText, Date dateCreated, Date dateUpdated, SessionDetails session) {
        this.linkedQuestionIds = 0L;
        this.participant = participant;
        this.questionText = questionText;
        this.hashTags = null;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.session = session;
        this.userIDLikeList = null;
        this.version = 0L;
    }


}
