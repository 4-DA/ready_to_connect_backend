package com.ready_to_connect.ready_to_connect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "businesses")
@Data
@PrimaryKeyJoinColumn(name = "user_id")
public class Business extends User {
    
    @Column(name = "company_name")
    private String companyName;
    
    private String industry;
    
    private String description;
    
    @Column(name = "website_url")private String websiteUrl;
    
    @Column(name = "logo_url")
    private String logoUrl;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    private VerificationStatus verificationStatus;
    
    @Column(name = "contact_email")
    private String contactEmail;
    
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Internship> internshipsOffered = new ArrayList<>();
    
    @ElementCollection
    @Column(name = "skill_level")
    private List<Integer> targetSkillLevels = new ArrayList<>();
    
    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }}