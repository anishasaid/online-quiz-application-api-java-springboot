package com.example.quiz.service;

import com.example.quiz.dto.CreateQuizRequest;
import com.example.quiz.dto.QuizResponse;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepo;

    public QuizService(QuizRepository quizRepo) {
        this.quizRepo = quizRepo;
    }

    //Create Quiz
    /*
    public QuizResponse create(CreateQuizRequest req){
        Quiz q = new Quiz(req.title());
        q = quizRepo.save(q);
        return new QuizResponse(q.getId(), q.getTitle());
    }
    */
    public QuizResponse create(CreateQuizRequest req){
        System.out.println("Inside create service with title: " + req.title());
        Quiz q = new Quiz(req.title());
        q = quizRepo.save(q);
        System.out.println("Saved quiz with id: " + q.getId());
        return new QuizResponse(q.getId(), q.getTitle());
    }




    // Get All Quiz
    public List<QuizResponse> listAll(){
        return quizRepo.findAll().stream()
                .map(q -> new QuizResponse(q.getId(), q.getTitle()))
                .collect(Collectors.toList());
    }
}
