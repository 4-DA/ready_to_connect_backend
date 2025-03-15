// Skill.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "skills")
@Data
public class Skill {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    private String category;
    
    private String description;
    
    @Column(nullable = false)
    private Integer level; // 1-5
    
    private Integer points;
    
    @Column(name = "last_assessed")
    private LocalDateTime lastAssessed;
    
    @PrePersist
    @PreUpdate
    private void validateLevel() {
        if (level != null && (level < 1 || level > 5)) {
            throw new IllegalArgumentException("Level must be between 1 and 5");
        }
    }
}