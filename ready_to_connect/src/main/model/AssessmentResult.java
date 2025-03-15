// AssessmentResult.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "assessment_results")
@Data
public class AssessmentResult {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(name = "student_id")
    private String studentId; // Reference to Student
    
    @Column(name = "assessment_id")
    private String assessmentId; // Reference to AssessmentGame
    
    private Double score;
    
    @ElementCollection
    @CollectionTable(name = "assessment_answers")
    private List<Answer> answers = new ArrayList<>();
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    @Column(name = "skill_level_before")
    private Integer skillLevelBefore;
    
    @Column(name = "skill_level_after")
    private Integer skillLevelAfter;
    
    @Column(name = "points_earned")
    private Integer pointsEarned;
    
    @ElementCollection
    @Column(name = "badge_id")
    private List<String> badgesEarned = new ArrayList<>(); // References to Badge IDs
    
    private String feedback;
    
    @Embeddable
    @Data
    public static class Answer {
        @Column(name = "question_id")
        private String questionId;
        private String answer;
        @Column(name = "is_correct")
        private Boolean isCorrect;
        @Column(name = "points_earned")
        private Integer pointsEarned;
    }
}