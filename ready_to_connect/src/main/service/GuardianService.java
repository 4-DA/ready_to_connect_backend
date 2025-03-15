// GuardianService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Guardian;
import com.ready_to_connect.ready_to_connect.repository.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GuardianService {
    
    private final GuardianRepository guardianRepository;
    
    @Autowired
    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }
    
    @Transactional
    public Guardian createGuardian(Guardian guardian) {
        return guardianRepository.save(guardian);
    }
    
    @Transactional(readOnly = true)
    public Optional<Guardian> findById(String userId) {
        return guardianRepository.findById(userId);
    }
    
    @Transactional(readOnly = true)
    public List<Guardian> findAll() {
        return guardianRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Guardian> findByStudentId(String studentId) {
        return guardianRepository.findByStudentsContaining(studentId);
    }
    
    @Transactional
    public Guardian updateGuardian(String userId, Guardian updatedGuardian) {
        return guardianRepository.findById(userId)
            .map(guardian -> {
                guardian.setStudents(updatedGuardian.getStudents());
                guardian.setNotificationPreferences(updatedGuardian.getNotificationPreferences());
                guardian.setApprovedCompanies(updatedGuardian.getApprovedCompanies());
                return guardianRepository.save(guardian);
            })
            .orElseThrow(() -> new IllegalArgumentException("Guardian not found"));
    }
    
    @Transactional
    public void deleteGuardian(String userId) {
        if (!guardianRepository.existsById(userId)) {
            throw new IllegalArgumentException("Guardian not found");
        }
        guardianRepository.deleteById(userId);
    }
    
    @Transactional
    public Guardian addStudent(String userId, String studentId) {
        return guardianRepository.findById(userId)
            .map(guardian -> {
                if (!guardian.getStudents().contains(studentId)) {
                    guardian.getStudents().add(studentId);
                }
                return guardianRepository.save(guardian);
            })
            .orElseThrow(() -> new IllegalArgumentException("Guardian not found"));
    }
    
    @Transactional
    public Guardian addApprovedCompany(String userId, String companyId) {
        return guardianRepository.findById(userId)
            .map(guardian -> {
                if (!guardian.getApprovedCompanies().contains(companyId)) {
                    guardian.getApprovedCompanies().add(companyId);
                }
                return guardianRepository.save(guardian);
            })
            .orElseThrow(() -> new IllegalArgumentException("Guardian not found"));
    }
    
    @Transactional
    public Guardian updateNotificationPreferences(String userId, Guardian.NotificationPreferences preferences) {
        return guardianRepository.findById(userId)
            .map(guardian -> {
                guardian.setNotificationPreferences(preferences);
                return guardianRepository.save(guardian);
            })
            .orElseThrow(() -> new IllegalArgumentException("Guardian not found"));
    }
}