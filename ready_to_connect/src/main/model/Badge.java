// Badge.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "badges")
@Data
public class Badge {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    private String category;
    
    @Column(name = "points_required")
    private Integer pointsRequired;
    
    @Column(name = "unlocked_at")
    private LocalDateTime unlockedAt;
    
    @ElementCollection
    private List<String> requirements = new ArrayList<>();
}