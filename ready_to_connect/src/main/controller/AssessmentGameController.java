// AssessmentGameController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.AssessmentGame;
import com.ready_to_connect.ready_to_connect.model.Question;
import com.ready_to_connect.ready_to_connect.service.AssessmentGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assessment-games")
public class AssessmentGameController {
    
    private final AssessmentGameService gameService;
    
    @Autowired
    public AssessmentGameController(AssessmentGameService gameService) {
        this.gameService = gameService;
    }
    
    @PostMapping
    public ResponseEntity<AssessmentGame> createGame(@RequestBody AssessmentGame game) {
        return ResponseEntity.ok(gameService.createGame(game));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AssessmentGame> getGame(@PathVariable UUID id) {
        return gameService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<AssessmentGame>> getAllGames() {
        return ResponseEntity.ok(gameService.findAll());
    }
    
    @GetMapping("/category/{skillCategory}")
    public ResponseEntity<List<AssessmentGame>> getBySkillCategory(@PathVariable String skillCategory) {
        return ResponseEntity.ok(gameService.findBySkillCategory(skillCategory));
    }
    
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<AssessmentGame>> getByDifficulty(@PathVariable Integer difficulty) {
        return ResponseEntity.ok(gameService.findByDifficulty(difficulty));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AssessmentGame> updateGame(@PathVariable UUID id, 
                                                   @RequestBody AssessmentGame game) {
        return ResponseEntity.ok(gameService.updateGame(id, game));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/questions")
    public ResponseEntity<AssessmentGame> addQuestion(@PathVariable UUID id, 
                                                    @RequestBody Question question) {
        return ResponseEntity.ok(gameService.addQuestion(id, question));
    }
}