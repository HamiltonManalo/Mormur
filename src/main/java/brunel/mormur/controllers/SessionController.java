package brunel.mormur.controllers;


import brunel.DTO.QuestionDTO;
import brunel.mormur.Database.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SessionController {

    private final IQuestionRepository questionRepoistory;
    private final ISessionRepository sessionRepository;
    private final IUserRepository userRepository;

    public SessionController(IQuestionRepository questionRepository, IUserRepository userRepository, ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.questionRepoistory = questionRepository;
    }

//    @GetMapping("/{")

    @PostMapping("/api/session/questions")
    public QuestionDTO addNewQuestion(@RequestBody QuestionDTO question)  {
        System.out.println(question.toString());
        Optional<User> user = userRepository.findById(question.getParticipantId());
        Optional<SessionDetails> session = sessionRepository.findById(question.getSessionId());
        if(!user.isPresent()) {
            System.out.println("User for new question in Session " + question.getSessionId() + " with userID " + question.getParticipantId() + " does not exist");
        }
        if(!session.isPresent()) {
            System.out.println("Session " + question.getSessionId() + " Does not exist for question submitted by userId " + question.getParticipantId());
        }
        Question newQuestion = new Question(question, session.get(), user.get());
        try {

        newQuestion = questionRepoistory.save(newQuestion);
        } catch (Exception E) {
            E.printStackTrace();
        }
        System.out.println("New Question " + newQuestion.toString());
        return question;
    }
//    @GetMapping("/{sessionId}/questions")
//    public List<Question> all(@PathVariable long sessionId) {
//        List<Question> questions = questionRepoistory.queryQuestionsBySession_Id(sessionId);
//
//        return questions;
//    }
}
