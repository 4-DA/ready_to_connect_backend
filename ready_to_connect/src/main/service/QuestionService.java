// QuestionService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Question;
import com.ready_to_connect.ready_to_connect.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {
    
    private final QuestionRepository questionRepository;
    
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    
    @Transactional
    public Question createQuestion(Question question) {
        validateQuestion(question);
        return questionRepository.save(question);
    }
    
    @Transactional(readOnly = true)
    public Optional<Question> findById(UUID id) {
        return questionRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Question> findAll() {
        return questionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Question> findByAssessmentId(String assessmentId) {
        return questionRepository.findByAssessmentId(assessmentId);
    }
    
    @Transactional(readOnly = true)
    public List<Question> findByType(Question.QuestionType type) {
        return questionRepository.findByType(type);
    }
    
    @Transactional
    public Question updateQuestion(UUID id, Question updatedQuestion) {
        validateQuestion(updatedQuestion);
        return questionRepository.findById(id)
            .map(question -> {
                question.setAssessmentId(updatedQuestion.getAssessmentId());
                question.setText(updatedQuestion.getText());
                question.setType(updatedQuestion.getType());
                question.setOptions(updatedQuestion.getOptions());
                question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
                question.setPoints(updatedQuestion.getPoints());
                question.setAiPrompt(updatedQuestion.getAiPrompt());
                return questionRepository.save(question);
            })
            .orElseThrow(() -> new IllegalArgumentException("Question not found"));
    }
    
    @Transactional
    public void deleteQuestion(UUID id) {
        if (!questionRepository.existsById(id)) {
            throw new IllegalArgumentException("Question not found");
        }
        questionRepository.deleteById(id);
    }
    
    private void validateQuestion(Question question) {
        if (question.getType() == Question.QuestionType.MULTIPLE_CHOICE) {
            if (question.getOptions() == null || question.getOptions().isEmpty()) {
                throw new IllegalArgumentException("Multiple choice questions must have options");
            }
            if (question.getCorrectAnswer() == null) {
                throw new IllegalArgumentException("Multiple choice questions must have a correct answer");
            }
        }
        if (question.getType() == Question.QuestionType.OPEN_ENDED && 
            question.getAiPrompt() == null) {
            throw new IllegalArgumentException("Open-ended questions must have an AI prompt");
        }
        if (question.getPoints() != null && question.getPoints() < 0) {
            throw new IllegalArgumentException("Points cannot be negative");
        }
    }
}