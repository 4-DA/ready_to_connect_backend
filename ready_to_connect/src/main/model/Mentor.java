// Mentor.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mentors")
@Data
public class Mentor {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @ElementCollection
    private List<String> specialization = new ArrayList<>();
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "avatar_url")
    private String avatarUrl;
    
    @Column(name = "is_ai")
    private boolean isAI;
    
    @Column(name = "industry")
    private String industry;
    
    @Column(name = "student_count")
    private int studentCount;
    
    @Column(name = "welcome_message")
    private String welcomeMessage;
    
    @ElementCollection
    @Column(name = "skill_level")
    private List<Integer> availableToSkillLevels = new ArrayList<>();
}