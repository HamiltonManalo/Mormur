package brunel.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class QuestionDTO {

    private QuestionDTO() {}

        private Long participantId;

        private String questionText;

        private ArrayList<String> hashTags;

        private Long sessionId;

    }
