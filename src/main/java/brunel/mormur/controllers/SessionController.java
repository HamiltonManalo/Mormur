package brunel.mormur.controllers;


import brunel.DTO.QuestionDTO;
import brunel.mormur.Database.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SessionController {

    private final IQuestionRepository questionRepository;
    private final ISessionRepository sessionRepository;
    private final IUserRepository userRepository;

    public SessionController(IQuestionRepository questionRepository, IUserRepository userRepository, ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/api/session/questions")
    public QuestionDTO addNewQuestion(@RequestBody QuestionDTO question)  {

        //Query user and session to link the SQL relationships. Optional is a Java data type (wrapper) that can hold a value or null which
        //Requires you to use the .get() or "OrElse" method to get the object.
        Optional<User> user = userRepository.findById(question.getParticipantId());
        Optional<SessionDetails> session = sessionRepository.findById(question.getSessionId());

        //Validate data was received and print a message if it wasn't
        if(!user.isPresent()) {
            System.out.println("User for new question in Session " + question.getSessionId() + " with userID " + question.getParticipantId() + " does not exist");
        }
        if(!session.isPresent()) {
            System.out.println("Session " + question.getSessionId() + " Does not exist for question submitted by userId " + question.getParticipantId());
        }

        //Custom constructor was made to make this easier. Make new question and create relationships
        Question newQuestion = new Question(question, session.get(), user.get());
        //Save question to DB, the .save method returns a copy of the value that was saved.
        newQuestion = questionRepository.save(newQuestion);
        //Add the method to the sessions question list, then save the session.
        session.get().getQuestions().add(newQuestion);
        sessionRepository.save(session.get());

        return question;
    }
}
