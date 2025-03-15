// AssessmentResultService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.AssessmentResult;
import com.ready_to_connect.ready_to_connect.repository.AssessmentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssessmentResultService {
    
    private final AssessmentResultRepository resultRepository;
    
    @Autowired
    public AssessmentResultService(AssessmentResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }
    
    @Transactional
    public AssessmentResult createResult(AssessmentResult result) {
        validateSkillLevels(result);
        result.setCompletedAt(LocalDateTime.now());
        return resultRepository.save(result);
    }
    
    @Transactional(readOnly = true)
    public Optional<AssessmentResult> findById(UUID id) {
        return resultRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<AssessmentResult> findAll() {
        return resultRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<AssessmentResult> findByStudentId(String studentId) {
        return resultRepository.findByStudentId(studentId);
    }
    
    @Transactional(readOnly = true)
    public List<AssessmentResult> findByAssessmentId(String assessmentId) {
        return resultRepository.findByAssessmentId(assessmentId);
    }
    
    @Transactional
    public AssessmentResult updateResult(UUID id, AssessmentResult updatedResult) {
        validateSkillLevels(updatedResult);
        return resultRepository.findById(id)
            .map(result -> {
                result.setStudentId(updatedResult.getStudentId());
                result.setAssessmentId(updatedResult.getAssessmentId());
                result.setScore(updatedResult.getScore());
                result.setAnswers(updatedResult.getAnswers());
                result.setSkillLevelBefore(updatedResult.getSkillLevelBefore());
                result.setSkillLevelAfter(updatedResult.getSkillLevelAfter());
                result.setPointsEarned(updatedResult.getPointsEarned());
                result.setBadgesEarned(updatedResult.getBadgesEarned());
                result.setFeedback(updatedResult.getFeedback());
                return resultRepository.save(result);
            })
            .orElseThrow(() -> new IllegalArgumentException("Assessment result not found"));
    }
    
    @Transactional
    public void deleteResult(UUID id) {
        if (!resultRepository.existsById(id)) {
            throw new IllegalArgumentException("Assessment result not found");
        }
        resultRepository.deleteById(id);
    }
    
    private void validateSkillLevels(AssessmentResult result) {
        if (result.getSkillLevelBefore() != null && (result.getSkillLevelBefore() < 1 || result.getSkillLevelBefore() > 5)) {
            throw new IllegalArgumentException("Skill level before must be between 1 and 5");
        }
        if (result.getSkillLevelAfter() != null && (result.getSkillLevelAfter() < 1 || result.getSkillLevelAfter() > 5)) {
            throw new IllegalArgumentException("Skill level after must be between 1 and 5");
        }
    }
}