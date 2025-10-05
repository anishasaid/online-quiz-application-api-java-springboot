package com.example.quiz.dto;

import com.example.quiz.entity.QuestionType;

import java.util.List;

public record AddQuestionRequest(String text, QuestionType type, String exceptedAnswer, List<OptionRequest> options) {
}
