// InternshipController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.Internship;
import com.ready_to_connect.ready_to_connect.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {
    
    private final InternshipService internshipService;
    
    @Autowired
    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }
    
    @PostMapping
    public ResponseEntity<Internship> createInternship(@RequestBody Internship internship) {
        return ResponseEntity.ok(internshipService.createInternship(internship));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Internship> getInternship(@PathVariable UUID id) {
        return internshipService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Internship>> getAllInternships() {
        return ResponseEntity.ok(internshipService.findAll());
    }
    
    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<Internship>> getByBusinessId(@PathVariable String businessId) {
        return ResponseEntity.ok(internshipService.findByBusinessId(businessId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Internship>> getByStatus(@PathVariable Internship.Status status) {
        return ResponseEntity.ok(internshipService.findByStatus(status));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Internship> updateInternship(
            @PathVariable UUID id, 
            @RequestBody Internship internship) {
        return ResponseEntity.ok(internshipService.updateInternship(id, internship));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable UUID id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.ok().build();
    }
}