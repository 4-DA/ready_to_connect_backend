// Question.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Data
public class Question {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(name = "assessment_id")
    private String assessmentId; // Reference to AssessmentGame
    
    @Column(nullable = false)
    private String text;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;
    
    @ElementCollection
    private List<String> options = new ArrayList<>(); // For multiple choice
    
    @Column(name = "correct_answer")
    private String correctAnswer; // For multiple choice
    
    private Integer points;
    
    @Column(name = "ai_prompt")
    private String aiPrompt; // For AI evaluation
    
    public enum QuestionType {
        MULTIPLE_CHOICE, OPEN_ENDED, SCENARIO
    }
}