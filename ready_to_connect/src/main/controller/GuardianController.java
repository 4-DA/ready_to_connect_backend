// GuardianController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.Guardian;
import com.ready_to_connect.ready_to_connect.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guardians")
public class GuardianController {
    
    private final GuardianService guardianService;
    
    @Autowired
    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }
    
    @PostMapping
    public ResponseEntity<Guardian> createGuardian(@RequestBody Guardian guardian) {
        Guardian createdGuardian = guardianService.createGuardian(guardian);
        return new ResponseEntity<>(createdGuardian, HttpStatus.CREATED);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<Guardian> getGuardian(@PathVariable String userId) {
        return guardianService.findById(userId)
            .map(guardian -> new ResponseEntity<>(guardian, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping
    public ResponseEntity<List<Guardian>> getAllGuardians() {
        List<Guardian> guardians = guardianService.findAll();
        return new ResponseEntity<>(guardians, HttpStatus.OK);
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Guardian>> getGuardiansByStudentId(@PathVariable String studentId) {
        List<Guardian> guardians = guardianService.findByStudentId(studentId);
        return new ResponseEntity<>(guardians, HttpStatus.OK);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<Guardian> updateGuardian(@PathVariable String userId, @RequestBody Guardian guardian) {
        try {
            Guardian updatedGuardian = guardianService.updateGuardian(userId, guardian);
            return new ResponseEntity<>(updatedGuardian, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteGuardian(@PathVariable String userId) {
        try {
            guardianService.deleteGuardian(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/{userId}/students/{studentId}")
    public ResponseEntity<Guardian> addStudent(@PathVariable String userId, @PathVariable String studentId) {
        try {
            Guardian updatedGuardian = guardianService.addStudent(userId, studentId);
            return new ResponseEntity<>(updatedGuardian, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/{userId}/companies/{companyId}")
    public ResponseEntity<Guardian> addApprovedCompany(@PathVariable String userId, @PathVariable String companyId) {
        try {
            Guardian updatedGuardian = guardianService.addApprovedCompany(userId, companyId);
            return new ResponseEntity<>(updatedGuardian, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{userId}/notifications")
    public ResponseEntity<Guardian> updateNotificationPreferences(
            @PathVariable String userId, 
            @RequestBody Guardian.NotificationPreferences preferences) {
        try {
            Guardian updatedGuardian = guardianService.updateNotificationPreferences(userId, preferences);
            return new ResponseEntity<>(updatedGuardian, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}