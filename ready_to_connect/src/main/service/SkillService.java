// SkillService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Skill;
import com.ready_to_connect.ready_to_connect.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {
    
    private final SkillRepository skillRepository;
    
    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }
    
    @Transactional
    public Skill createSkill(Skill skill) {
        skill.setLastAssessed(LocalDateTime.now());
        return skillRepository.save(skill);
    }
    
    @Transactional(readOnly = true)
    public Optional<Skill> findById(UUID id) {
        return skillRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Skill> findByCategory(String category) {
        return skillRepository.findByCategory(category);
    }
    
    @Transactional(readOnly = true)
    public List<Skill> findByLevel(Integer level) {
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Level must be between 1 and 5");
        }
        return skillRepository.findByLevel(level);
    }
    
    @Transactional(readOnly = true)
    public List<Skill> findByName(String name) {
        return skillRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Transactional
    public Skill updateSkill(UUID id, Skill updatedSkill) {
        return skillRepository.findById(id)
            .map(skill -> {
                skill.setName(updatedSkill.getName());
                skill.setCategory(updatedSkill.getCategory());
                skill.setDescription(updatedSkill.getDescription());
                skill.setLevel(updatedSkill.getLevel());
                skill.setPoints(updatedSkill.getPoints());
                skill.setLastAssessed(LocalDateTime.now());
                return skillRepository.save(skill);
            })
            .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
    }
    
    @Transactional
    public void deleteSkill(UUID id) {
        if (!skillRepository.existsById(id)) {
            throw new IllegalArgumentException("Skill not found");
        }
        skillRepository.deleteById(id);
    }
}