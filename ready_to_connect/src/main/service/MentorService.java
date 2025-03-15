// MentorService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Mentor;
import com.ready_to_connect.ready_to_connect.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MentorService {
    
    private final MentorRepository mentorRepository;
    
    @Autowired
    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }
    
    @Transactional
    public Mentor createMentor(Mentor mentor) {
        validateSkillLevels(mentor.getAvailableToSkillLevels());
        return mentorRepository.save(mentor);
    }
    
    @Transactional(readOnly = true)
    public Optional<Mentor> findById(UUID id) {
        return mentorRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Mentor> findAll() {
        return mentorRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Mentor> findByIndustry(String industry) {
        return mentorRepository.findByIndustry(industry);
    }
    
    @Transactional(readOnly = true)
    public List<Mentor> findByIsAI(boolean isAI) {
        return mentorRepository.findByIsAI(isAI);
    }
    
    @Transactional(readOnly = true)
    public List<Mentor> findBySpecialization(String specialization) {
        return mentorRepository.findBySpecializationContaining(specialization);
    }
    
    @Transactional
    public Mentor updateMentor(UUID id, Mentor updatedMentor) {
        validateSkillLevels(updatedMentor.getAvailableToSkillLevels());
        return mentorRepository.findById(id)
            .map(mentor -> {
                mentor.setName(updatedMentor.getName());
                mentor.setSpecialization(updatedMentor.getSpecialization());
                mentor.setDescription(updatedMentor.getDescription());
                mentor.setAvatarUrl(updatedMentor.getAvatarUrl());
                mentor.setIsAI(updatedMentor.getIsAI());
                mentor.setIndustry(updatedMentor.getIndustry());
                mentor.setStudentCount(updatedMentor.getStudentCount());
                mentor.setWelcomeMessage(updatedMentor.getWelcomeMessage());
                mentor.setAvailableToSkillLevels(updatedMentor.getAvailableToSkillLevels());
                return mentorRepository.save(mentor);
            })
            .orElseThrow(() -> new IllegalArgumentException("Mentor not found"));
    }
    
    @Transactional
    public void deleteMentor(UUID id) {
        if (!mentorRepository.existsById(id)) {
            throw new IllegalArgumentException("Mentor not found");
        }
        mentorRepository.deleteById(id);
    }
    
    @Transactional
    public Mentor incrementStudentCount(UUID id) {
        return mentorRepository.findById(id)
            .map(mentor -> {
                mentor.setStudentCount(mentor.getStudentCount() + 1);
                return mentorRepository.save(mentor);
            })
            .orElseThrow(() -> new IllegalArgumentException("Mentor not found"));
    }
    
    private void validateSkillLevels(List<Integer> skillLevels) {
        if (skillLevels != null) {
            for (Integer level : skillLevels) {
                if (level < 1 || level > 5) {
                    throw new IllegalArgumentException("Skill levels must be between 1 and 5");
                }
            }
        }
    }
}