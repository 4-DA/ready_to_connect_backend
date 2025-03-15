// MentorController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.Mentor;
import com.ready_to_connect.ready_to_connect.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {
    
    private final MentorService mentorService;
    
    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }
    
    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor) {
        return ResponseEntity.ok(mentorService.createMentor(mentor));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable UUID id) {
        return mentorService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Mentor>> getAllMentors() {
        return ResponseEntity.ok(mentorService.findAll());
    }
    
    @GetMapping("/industry/{industry}")
    public ResponseEntity<List<Mentor>> getMentorsByIndustry(@PathVariable String industry) {
        return ResponseEntity.ok(mentorService.findByIndustry(industry));
    }
    
    @GetMapping("/type")
    public ResponseEntity<List<Mentor>> getMentorsByType(@RequestParam boolean isAI) {
        return ResponseEntity.ok(mentorService.findByIsAI(isAI));
    }
    
    @GetMapping("/specialization")
    public ResponseEntity<List<Mentor>> getMentorsBySpecialization(@RequestParam String specialization) {
        return ResponseEntity.ok(mentorService.findBySpecialization(specialization));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable UUID id, @RequestBody Mentor mentor) {
        return ResponseEntity.ok(mentorService.updateMentor(id, mentor));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable UUID id) {
        mentorService.deleteMentor(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/increment-students")
    public ResponseEntity<Mentor> incrementStudentCount(@PathVariable UUID id) {
        return ResponseEntity.ok(mentorService.incrementStudentCount(id));
    }
}