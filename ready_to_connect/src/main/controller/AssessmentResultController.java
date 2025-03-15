// AssessmentResultController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.AssessmentResult;
import com.ready_to_connect.ready_to_connect.service.AssessmentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assessment-results")
public class AssessmentResultController {
    
    private final AssessmentResultService resultService;
    
    @Autowired
    public AssessmentResultController(AssessmentResultService resultService) {
        this.resultService = resultService;
    }
    
    @PostMapping
    public ResponseEntity<AssessmentResult> createResult(@RequestBody AssessmentResult result) {
        AssessmentResult createdResult = resultService.createResult(result);
        return ResponseEntity.ok(createdResult);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AssessmentResult> getResultById(@PathVariable UUID id) {
        return resultService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<AssessmentResult>> getAllResults() {
        return ResponseEntity.ok(resultService.findAll());
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssessmentResult>> getResultsByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(resultService.findByStudentId(studentId));
    }
    
    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<AssessmentResult>> getResultsByAssessment(@PathVariable String assessmentId) {
        return ResponseEntity.ok(resultService.findByAssessmentId(assessmentId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AssessmentResult> updateResult(@PathVariable UUID id, 
                                                       @RequestBody AssessmentResult result) {
        try {
            AssessmentResult updatedResult = resultService.updateResult(id, result);
            return ResponseEntity.ok(updatedResult);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable UUID id) {
        try {
            resultService.deleteResult(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}