// AIInteractionController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.AIInteraction;
import com.ready_to_connect.ready_to_connect.service.AIInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ai-interactions")
public class AIInteractionController {
    
    private final AIInteractionService aiInteractionService;
    
    @Autowired
    public AIInteractionController(AIInteractionService aiInteractionService) {
        this.aiInteractionService = aiInteractionService;
    }
    
    @PostMapping
    public ResponseEntity<AIInteraction> createInteraction(@RequestBody AIInteraction interaction) {
        AIInteraction created = aiInteractionService.createInteraction(interaction);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AIInteraction> getInteraction(@PathVariable UUID id) {
        return aiInteractionService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<AIInteraction>> getAllInteractions() {
        return ResponseEntity.ok(aiInteractionService.findAll());
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AIInteraction>> getByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(aiInteractionService.findByStudentId(studentId));
    }
    
    @GetMapping("/student/{studentId}/ordered")
    public ResponseEntity<List<AIInteraction>> getByStudentIdOrdered(@PathVariable String studentId) {
        return ResponseEntity.ok(aiInteractionService.findByStudentIdOrdered(studentId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AIInteraction> updateInteraction(
            @PathVariable UUID id,
            @RequestBody AIInteraction interaction) {
        try {
            AIInteraction updated = aiInteractionService.updateInteraction(id, interaction);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteraction(@PathVariable UUID id) {
        try {
            aiInteractionService.deleteInteraction(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}