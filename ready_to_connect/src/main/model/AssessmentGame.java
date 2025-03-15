// AssessmentGame.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "assessment_games")
@Data
public class AssessmentGame {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    private String title;
    
    private String description;
    
    @Column(name = "skill_category")
    private String skillCategory;
    
    private Integer difficulty; // 1-5
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();
    
    @Column(name = "time_limit")
    private Integer timeLimit; // in minutes
    
    @Column(name = "points_available")
    private Integer pointsAvailable;
    
    @Column(name = "completion_badge_id")
    private String completionBadgeId; // Reference to Badge
}