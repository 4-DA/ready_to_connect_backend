// AIInteraction.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ai_interactions")
@Data
public class AIInteraction {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(name = "student_id")
    private String studentId; // Reference to Student
    
    private LocalDateTime timestamp;
    
    @Column(name = "user_query")
    private String userQuery;
    
    @Column(name = "ai_response")
    private String aiResponse;
    
    @ElementCollection
    @Column(name = "skill_category")
    private List<String> relatedSkillCategories = new ArrayList<>();
    
    @Column(name = "points_awarded")
    private Integer pointsAwarded;
    
    @ElementCollection
    @Column(name = "resource")
    private List<String> resourcesGenerated = new ArrayList<>();
    
    @Column(name = "sentiment_score")
    private Double sentimentScore;
    
    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }
}