// ARRoom.java
package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ar_rooms")
@Data
public class ARRoom {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    private String name;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private RoomType type;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjectModel> objectModels = new ArrayList<>();
    
    @Column(name = "background_url")
    private String backgroundUrl;
    
    @ElementCollection
    @Column(name = "skill_level")
    private List<Integer> availableToLevels = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InteractionPoint> interactionPoints = new ArrayList<>();
    
    public enum RoomType {
        INTERVIEW, NETWORKING, SKILL_DEMO
    }
}

// ObjectModel.java
@Entity
@Data
public class ObjectModel {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "object_url")
    private String objectUrl;
    
    @Embedded
    private Vector3D position;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "x", column = @Column(name = "scale_x")),
        @AttributeOverride(name = "y", column = @Column(name = "scale_y")),
        @AttributeOverride(name = "z", column = @Column(name = "scale_z"))
    })
    private Vector3D scale;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "x", column = @Column(name = "rotation_x")),
        @AttributeOverride(name = "y", column = @Column(name = "rotation_y")),
        @AttributeOverride(name = "z", column = @Column(name = "rotation_z"))
    })
    private Vector3D rotation;
}

// InteractionPoint.java
@Entity
@Data
public class InteractionPoint {
    @Id
    @GeneratedValue
    private Long id;
    
    @Embedded
    private Vector3D position;
    
    private String action;
    
    @Column(name = "trigger_event")
    private String triggerEvent;
}

// Vector3D.java (embeddable component)
@Embeddable
@Data
public class Vector3D {
    private Float x;
    private Float y;
    private Float z;
}