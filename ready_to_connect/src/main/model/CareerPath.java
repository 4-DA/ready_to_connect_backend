// CareerPath.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "career_paths")
@Data
public class CareerPath {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    private String title;
    
    private String description;
    
    @ElementCollection
    private List<String> industries = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> requiredSkills = new ArrayList<>();
    
    @ElementCollection
    @Column(name = "course")
    private List<String> recommendedCourses = new ArrayList<>();
    
    @ElementCollection
    @Column(name = "job")
    private List<String> potentialJobs = new ArrayList<>();
    
    @Column(name = "average_salary")
    private Double averageSalary;
    
    @Column(name = "growth_outlook")
    private String growthOutlook;
    
    @Column(name = "popularity_score")
    private Double popularityScore;
    
    @ElementCollection
    @Column(name = "internship_id")
    private List<String> matchingInternships = new ArrayList<>(); // References to Internship IDs
}