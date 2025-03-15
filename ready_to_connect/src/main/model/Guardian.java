// Guardian.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guardians")
@Data
public class Guardian {
    
    @Id
    private String userId; // Reference to User
    
    @ElementCollection
    @Column(name = "student_id")
    private List<String> students = new ArrayList<>();
    
    @Embedded
    private NotificationPreferences notificationPreferences;
    
    @ElementCollection
    @Column(name = "company_id")
    private List<String> approvedCompanies = new ArrayList<>();
    
    @Embeddable
    @Data
    public static class NotificationPreferences {
        private boolean email;
        private boolean application;
        @Column(name = "weekly_updates")
        private boolean weeklyUpdates;
    }
}