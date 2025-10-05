package com.example.quiz.dto;

import java.util.List;

public record SubmitQuizRequest(List<AnswerSubmission> answers) {
}
