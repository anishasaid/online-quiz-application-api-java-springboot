package com.example.quiz.dto;

import com.example.quiz.entity.QuestionType;

import java.util.List;

public record QuestionResponse(Long id, String text, QuestionType type, List<OptionResponse> options) {
}
