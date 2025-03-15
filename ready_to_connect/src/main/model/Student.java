package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
public class Student extends User{
    
    @Id
    private String userId; // Reference to User
    
    @Column(name = "skill_level")
    private Integer skillLevel; // 1-5
    
    @Column(name = "total_points")
    private Integer totalPoints;
    
    @ElementCollection
    private List<String> interests = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Badge> badges = new ArrayList<>();
    
    @Column(name = "education_level")
    private String educationLevel;
    
    @Column(name = "guardian_id")
    private String guardianId; // Reference to Guardian
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssessmentResult> completedAssessments = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CareerPath> recommendedPaths = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Internship> appliedInternships = new ArrayList<>();
    
    @Column(name = "mentor_id")
    private String mentorId; // Reference to Mentor
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AIInteraction> aiConversationHistory = new ArrayList<>();
}
