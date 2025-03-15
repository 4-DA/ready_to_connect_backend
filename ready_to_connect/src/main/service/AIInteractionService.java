// AIInteractionService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.AIInteraction;
import com.ready_to_connect.ready_to_connect.repository.AIInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AIInteractionService {
    
    private final AIInteractionRepository aiInteractionRepository;
    
    @Autowired
    public AIInteractionService(AIInteractionRepository aiInteractionRepository) {
        this.aiInteractionRepository = aiInteractionRepository;
    }
    
    @Transactional
    public AIInteraction createInteraction(AIInteraction interaction) {
        return aiInteractionRepository.save(interaction);
    }
    
    @Transactional(readOnly = true)
    public Optional<AIInteraction> findById(UUID id) {
        return aiInteractionRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<AIInteraction> findAll() {
        return aiInteractionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<AIInteraction> findByStudentId(String studentId) {
        return aiInteractionRepository.findByStudentId(studentId);
    }
    
    @Transactional(readOnly = true)
    public List<AIInteraction> findByStudentIdOrdered(String studentId) {
        return aiInteractionRepository.findByStudentIdOrderByTimestampDesc(studentId);
    }
    
    @Transactional
    public AIInteraction updateInteraction(UUID id, AIInteraction updatedInteraction) {
        return aiInteractionRepository.findById(id)
            .map(interaction -> {
                interaction.setStudentId(updatedInteraction.getStudentId());
                interaction.setTimestamp(updatedInteraction.getTimestamp());
                interaction.setUserQuery(updatedInteraction.getUserQuery());
                interaction.setAiResponse(updatedInteraction.getAiResponse());
                interaction.setRelatedSkillCategories(updatedInteraction.getRelatedSkillCategories());
                interaction.setPointsAwarded(updatedInteraction.getPointsAwarded());
                interaction.setResourcesGenerated(updatedInteraction.getResourcesGenerated());
                interaction.setSentimentScore(updatedInteraction.getSentimentScore());
                return aiInteractionRepository.save(interaction);
            })
            .orElseThrow(() -> new IllegalArgumentException("AI Interaction not found"));
    }
    
    @Transactional
    public void deleteInteraction(UUID id) {
        if (!aiInteractionRepository.existsById(id)) {
            throw new IllegalArgumentException("AI Interaction not found");
        }
        aiInteractionRepository.deleteById(id);
    }
}