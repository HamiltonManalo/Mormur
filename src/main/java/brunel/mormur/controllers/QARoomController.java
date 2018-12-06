package brunel.mormur.controllers;

import brunel.DTO.QuestionDTO;
import brunel.mormur.Database.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class QARoomController {

    private final IQuestionRepository questionRepository;
    private final IQARoomRepository roomRepository;
    private final IUserRepository userRepository;

    public QARoomController(IQuestionRepository questionRepository, IUserRepository userRepository, IQARoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/api/qARooms/questions")
    public QuestionDTO addNewQuestion(@RequestBody QuestionDTO question)  {

        //Query user and QARoom to link the SQL relationships. Optional is a Java data type (wrapper) that can hold a value or null which
        //Requires you to use the .get() or "OrElse" method to get the object.
        Optional<User> user = userRepository.findById(question.getParticipantId());
        Optional<QARoom> room = roomRepository.findById(question.getQaRoomId());

        //Validate data was received and print a message if it wasn't
        if(!user.isPresent()) {
            System.out.println("User for new question in Room " + question.getQaRoomId() + " with userID " + question.getParticipantId() + " does not exist");
        }
        if(!room.isPresent()) {
            System.out.println("Room " + question.getQaRoomId() + " Does not exist for question submitted by userId " + question.getParticipantId());
        }

        //Custom constructor was made to make this easier. Make new question and create relationships
        Question newQuestion = new Question(question, room.get(), user.get());
        //Save question to DB, the .save method returns a copy of the value that was saved.
        newQuestion = questionRepository.save(newQuestion);
        //Add the method to the room question list, then save the QARoom.
        room.get().getQuestions().add(newQuestion);
        roomRepository.save(room.get());

        return question;
    }
}
