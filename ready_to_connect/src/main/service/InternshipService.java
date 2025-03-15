// InternshipService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Internship;
import com.ready_to_connect.ready_to_connect.model.Student;
import com.ready_to_connect.ready_to_connect.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InternshipService {
    
    private final InternshipRepository internshipRepository;
    
    @Autowired
    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }
    
    @Transactional
    public Internship createInternship(Internship internship) {
        validateInternship(internship);
        return internshipRepository.save(internship);
    }
    
    @Transactional(readOnly = true)
    public Optional<Internship> findById(UUID id) {
        return internshipRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Internship> findAll() {
        return internshipRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Internship> findByBusinessId(String businessId) {
        return internshipRepository.findByBusinessId(businessId);
    }
    
    @Transactional(readOnly = true)
    public List<Internship> findByStatus(Internship.Status status) {
        return internshipRepository.findByStatus(status);
    }
    
    @Transactional
    public Internship updateInternship(UUID id, Internship updatedInternship) {
        validateInternship(updatedInternship);
        return internshipRepository.findById(id)
            .map(internship -> {
                internship.setBusinessId(updatedInternship.getBusinessId());
                internship.setTitle(updatedInternship.getTitle());
                internship.setDescription(updatedInternship.getDescription());
                internship.setRequirements(updatedInternship.getRequirements());
                internship.setLocation(updatedInternship.getLocation());
                internship.setRemote(updatedInternship.isRemote());
                internship.setStartDate(updatedInternship.getStartDate());
                internship.setEndDate(updatedInternship.getEndDate());
                internship.setApplicationDeadline(updatedInternship.getApplicationDeadline());
                internship.setSkillLevelRequired(updatedInternship.getSkillLevelRequired());
                internship.setStatus(updatedInternship.getStatus());
                internship.setSkillCategories(updatedInternship.getSkillCategories());
                internship.setStipend(updatedInternship.getStipend());
                internship.setHoursPerWeek(updatedInternship.getHoursPerWeek());
                internship.setApplicationProcess(updatedInternship.getApplicationProcess());
                return internshipRepository.save(internship);
            })
            .orElseThrow(() -> new IllegalArgumentException("Internship not found"));
    }
    
    @Transactional
    public Internship addApplicant(UUID internshipId, Student student) {
        return internshipRepository.findById(internshipId)
            .map(internship -> {
                internship.getApplicants().add(student);
                return internshipRepository.save(internship);
            })
            .orElseThrow(() -> new IllegalArgumentException("Internship not found"));
    }
    
    @Transactional
    public void deleteInternship(UUID id) {
        if (!internshipRepository.existsById(id)) {
            throw new IllegalArgumentException("Internship not found");
        }
        internshipRepository.deleteById(id);
    }
    
    private void validateInternship(Internship internship) {
        if (internship.getSkillLevelRequired() != null && 
            (internship.getSkillLevelRequired() < 1 || internship.getSkillLevelRequired() > 5)) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }
        if (internship.getStartDate() != null && internship.getEndDate() != null && 
            internship.getStartDate().isAfter(internship.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }
}