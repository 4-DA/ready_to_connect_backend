// Internship.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "internships")
@Data
public class Internship {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(name = "business_id")
    private String businessId; // Reference to Business
    
    private String title;
    
    private String description;
    
    @ElementCollection
    private List<String> requirements = new ArrayList<>();
    
    private String location;
    
    @Column(name = "is_remote")
    private boolean isRemote;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;
    
    @Column(name = "skill_level_required")
    private Integer skillLevelRequired; // 1-5
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToMany
    @JoinTable(
        name = "internship_applicants",
        joinColumns = @JoinColumn(name = "internship_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> applicants = new ArrayList<>();
    
    @ElementCollection
    @Column(name = "skill_category")
    private List<String> skillCategories = new ArrayList<>();
    
    private Double stipend; // Optional
    
    @Column(name = "hours_per_week")
    private Integer hoursPerWeek;
    
    @Column(name = "application_process")
    private String applicationProcess;
    
    public enum Status {
        DRAFT, ACTIVE, FILLED, CLOSED
    }
}