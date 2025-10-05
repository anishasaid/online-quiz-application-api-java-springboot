package com.example.quiz.service;

import com.example.quiz.dto.AnswerSubmission;
import com.example.quiz.dto.SubmitQuizRequest;
import com.example.quiz.dto.SubmitQuizResponse;
import com.example.quiz.entity.Option;
import com.example.quiz.entity.Question;
import com.example.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoringService {

    private  final QuestionRepository questionRepo;

    public ScoringService(QuestionRepository questionRepo) {
        this.questionRepo = questionRepo;
    }

    public SubmitQuizResponse scoreQuiz(SubmitQuizRequest req){
        int score = 0;
        int total = req.answers().size();

        for (AnswerSubmission submission : req.answers()) {
            Question q = questionRepo.findById(submission.questionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            switch (q.getType()) {
                case SINGLE_CHOICE, MULTIPLE_CHOICE -> {
                    List<Long> correctIds = q.getOptions().stream()
                            .filter(Option::isCorrect)
                            .map(Option::getId)
                            .toList();

                    List<Long> selectedIds = submission.selectedOptionIds() != null
                            ? submission.selectedOptionIds()
                            : List.of(); // avoid null

                    if (correctIds.size() == selectedIds.size()
                            && correctIds.containsAll(selectedIds)) {
                        score++;
                    }
                }
                case TEXT -> {
                    String expected = q.getExpectedAnswer();
                    String given = submission.textAnswer();

                    if (expected != null && given != null &&
                            expected.equalsIgnoreCase(given.trim())) {
                        score++;
                    }
                }
            }
        }

        return new SubmitQuizResponse(score, total);

    }
}
