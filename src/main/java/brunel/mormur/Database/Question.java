package brunel.mormur.Database;

import brunel.DTO.QuestionDTO;
import com.fasterxml.jackson.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Questions")
@Data
@ToString(exclude = "session")
public class Question {

    @Id
    @Column(name = "QuestionID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "LinkedQuestionID")
    @OneToOne(targetEntity = Question.class)
    private Long linkedQuestionIds;

    @OneToOne(targetEntity = User.class)
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

    @ManyToOne(targetEntity = SessionDetails.class)
    @JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
        this.linkedQuestionIds = null;
        this.participant = participant;
        this.questionText = questionText;
        this.hashTags = null;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.session = session;
        this.userIDLikeList = null;
        this.version = 0L;
    }

    public Question(QuestionDTO dto, SessionDetails session, User user) {
        this.participant = user;
        this.session = session;
        this.hashTags = dto.getHashTags();
        this.questionText = dto.getQuestionText();
        this.dateCreated = new Date();
    }


}
