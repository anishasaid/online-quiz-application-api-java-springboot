package com.example.quiz.dto;

import java.util.List;

public record AnswerSubmission(Long questionId, List<Long> selectedOptionIds, String textAnswer) {
}
