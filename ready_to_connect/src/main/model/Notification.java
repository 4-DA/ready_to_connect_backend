// Notification.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
public class Notification {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(name = "user_id", nullable = false)
    private String userId; // Reference to User
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String message;
    
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    
    private boolean read;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "action_link")
    private String actionLink;
    
    @Column(name = "related_id")
    private String relatedId;
    
    public enum NotificationType {
        ALERT, ACHIEVEMENT, APPLICATION, MESSAGE, RECOMMENDATION
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        read = false; // Default to unread
    }
}