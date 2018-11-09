package brunel.mormur.Database;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@Table(name = "Answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AnswerID")
    private long id;

    @Column(name = "AnswerText")
    private String answerText;

    @Column(name = "MediaFileAttachment")
    @ElementCollection(targetClass=String.class)
    private ArrayList<String> media;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DateCreated")
    private Date dateCreated;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DateModified")
    private Date dateModified;

    @OneToOne(targetEntity = Question.class, cascade=CascadeType.ALL)
    @Column(name = "QuestionID")
    private Question questionLink;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @Column(name = "UserID")
    private User user;

    @Version @JsonIgnore
    private long version;

    public Answer(String answerText, Date dateCreated, Date dateModified, Question questionLink, User user) {
        this.answerText = answerText;
        this.media = null;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.questionLink = questionLink;
        this.user = user;
        this.version = 0L;
    }
}
