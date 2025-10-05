package com.example.quiz.controller;

import com.example.quiz.dto.*;
import com.example.quiz.service.QuestionService;
import com.example.quiz.service.QuizService;
import com.example.quiz.service.ScoringService;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final ScoringService scoringService;

    public QuizController(QuizService quizService, QuestionService questionService, ScoringService scoringService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.scoringService = scoringService;
    }

    /************ Quiz *******************/
    //create quiz
    @PostMapping
    public QuizResponse createQuiz(@RequestBody CreateQuizRequest req){
        return quizService.create
                (req);
    }

    // Get all quizes
    @GetMapping
    public List<QuizResponse> listQuizzes(){
        return quizService.listAll();
    }

    /************** Question **********************/

    //Add Question
    @PostMapping("/{quizId}/questions")
    public QuestionResponse addQuestion(@PathVariable Long quizId, @RequestBody AddQuestionRequest req){
        return questionService.addQuestion(quizId, req);
    }

    // Get all Questions
    @GetMapping("/{quizId}/questions")
    public List<QuestionResponse> getQuestions(@PathVariable Long quizId){
        return questionService.getQuestionsForQuiz(quizId);
    }

    /************** Scoring ********************/
    @PostMapping("/{quizId}/submissions")
    public SubmitQuizResponse submit(@PathVariable Long quizId, @RequestBody SubmitQuizRequest req){
        return scoringService.scoreQuiz(req);
    }

}


