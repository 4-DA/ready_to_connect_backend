// AssessmentGameService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.AssessmentGame;
import com.ready_to_connect.ready_to_connect.repository.AssessmentGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssessmentGameService {
    
    private final AssessmentGameRepository gameRepository;
    
    @Autowired
    public AssessmentGameService(AssessmentGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    
    @Transactional
    public AssessmentGame createGame(AssessmentGame game) {
        validateDifficulty(game.getDifficulty());
        return gameRepository.save(game);
    }
    
    @Transactional(readOnly = true)
    public Optional<AssessmentGame> findById(UUID id) {
        return gameRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<AssessmentGame> findAll() {
        return gameRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<AssessmentGame> findBySkillCategory(String skillCategory) {
        return gameRepository.findBySkillCategory(skillCategory);
    }
    
    @Transactional(readOnly = true)
    public List<AssessmentGame> findByDifficulty(Integer difficulty) {
        validateDifficulty(difficulty);
        return gameRepository.findByDifficulty(difficulty);
    }
    
    @Transactional
    public AssessmentGame updateGame(UUID id, AssessmentGame updatedGame) {
        validateDifficulty(updatedGame.getDifficulty());
        return gameRepository.findById(id)
            .map(game -> {
                game.setTitle(updatedGame.getTitle());
                game.setDescription(updatedGame.getDescription());
                game.setSkillCategory(updatedGame.getSkillCategory());
                game.setDifficulty(updatedGame.getDifficulty());
                game.setQuestions(updatedGame.getQuestions());
                game.setTimeLimit(updatedGame.getTimeLimit());
                game.setPointsAvailable(updatedGame.getPointsAvailable());
                game.setCompletionBadgeId(updatedGame.getCompletionBadgeId());
                return gameRepository.save(game);
            })
            .orElseThrow(() -> new IllegalArgumentException("Assessment game not found"));
    }
    
    @Transactional
    public void deleteGame(UUID id) {
        if (!gameRepository.existsById(id)) {
            throw new IllegalArgumentException("Assessment game not found");
        }
        gameRepository.deleteById(id);
    }
    
    @Transactional
    public AssessmentGame addQuestion(UUID id, Question question) {
        return gameRepository.findById(id)
            .map(game -> {
                game.getQuestions().add(question);
                return gameRepository.save(game);
            })
            .orElseThrow(() -> new IllegalArgumentException("Assessment game not found"));
    }
    
    private void validateDifficulty(Integer difficulty) {
        if (difficulty != null && (difficulty < 1 || difficulty > 5)) {
            throw new IllegalArgumentException("Difficulty must be between 1 and 5");
        }
    }
}