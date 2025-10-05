package com.example.quiz.service;

import com.example.quiz.dto.*;
import com.example.quiz.entity.Option;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.QuestionType;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuizRepository quizRepo;
    private final QuestionRepository questionRepo;

    public QuestionService(QuizRepository quizRepo, QuestionRepository questionRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
    }

    public QuestionResponse addQuestion(Long quizId, AddQuestionRequest req){

        Quiz quiz = quizRepo.findById(quizId).orElseThrow(()-> new RuntimeException("Quiz not found"));

        if(req.type() == QuestionType.SINGLE_CHOICE){
            long c = req.options().stream().filter(OptionRequest::correct).count();
            if (c != 1){
                throw new RuntimeException("SINGLE_CHOICE question should contain exact 1 correct option");
            }
        }

        if(req.type() == QuestionType.TEXT && req.options() != null && !req.options().isEmpty()){
            throw new RuntimeException("TEXT questions can not have options");
        }

        Question q = new Question();
        q.setText(req.text());
        q.setType(req.type());
        q.setExpectedAnswer(req.exceptedAnswer());
        q.setQuiz(quiz);
        if(req.options() != null){
            for(OptionRequest oreq : req.options()){

                Option o = new Option();
                o.setText(oreq.text());
                o.setCorrect(oreq.correct());
                o.setQuestion(q);
                q.getOptions().add(o);
            }
        }

        q = questionRepo.save(q);

        // map to response (do not include correct flag)
        List<OptionResponse> opts = q.getOptions().stream()
                .map(opt -> new OptionResponse(opt.getId(), opt.getText()))
                .collect(Collectors.toList());
        return new QuestionResponse(q.getId(), q.getText(), q.getType(), opts);
    }

    public List<QuestionResponse> getQuestionsForQuiz(Long quizId){
        List<Question> qs = questionRepo.findByQuizId(quizId);
        return qs.stream().map(q-> {

            List<OptionResponse> opts = q.getOptions().stream()
                    .map(opt -> new OptionResponse(opt.getId(), opt.getText()))
                    .collect(Collectors.toList());
            return new QuestionResponse(q.getId(), q.getText(), q.getType(), opts);

        }).collect(Collectors.toList());
    }

}
