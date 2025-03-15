// BusinessService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Business;
import com.ready_to_connect.ready_to_connect.model.Internship;
import com.ready_to_connect.ready_to_connect.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessService {
    
    private final BusinessRepository businessRepository;
    
    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }
    
    @Transactional
    public Business createBusiness(Business business) {
        validateSkillLevels(business.getTargetSkillLevels());
        return businessRepository.save(business);
    }
    
    @Transactional(readOnly = true)
    public Optional<Business> findById(UUID userId) {
        return businessRepository.findById(userId);
    }
    
    @Transactional(readOnly = true)
    public List<Business> findAll() {
        return businessRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Business> findByIndustry(String industry) {
        return businessRepository.findByIndustry(industry);
    }
    
    @Transactional(readOnly = true)
    public List<Business> findByVerificationStatus(Business.VerificationStatus status) {
        return businessRepository.findByVerificationStatus(status);
    }
    
    @Transactional
    public Business updateBusiness(UUID userId, Business updatedBusiness) {
        validateSkillLevels(updatedBusiness.getTargetSkillLevels());
        return businessRepository.findById(userId)
            .map(business -> {
                business.setCompanyName(updatedBusiness.getCompanyName());
                business.setIndustry(updatedBusiness.getIndustry());
                business.setDescription(updatedBusiness.getDescription());
                business.setWebsiteUrl(updatedBusiness.getWebsiteUrl());
                business.setLogoUrl(updatedBusiness.getLogoUrl());
                business.setVerificationStatus(updatedBusiness.getVerificationStatus());
                business.setContactEmail(updatedBusiness.getContactEmail());
                business.setContactPhone(updatedBusiness.getContactPhone());
                business.setInternshipsOffered(updatedBusiness.getInternshipsOffered());
                business.setTargetSkillLevels(updatedBusiness.getTargetSkillLevels());
                // Update User fields
                business.setEmail(updatedBusiness.getEmail());
                business.setPassword(updatedBusiness.getPassword());
                business.setFullName(updatedBusiness.getFullName());
                business.setDateOfBirth(updatedBusiness.getDateOfBirth());
                business.setLocation(updatedBusiness.getLocation());
                business.setProfilePicture(updatedBusiness.getProfilePicture());
                return businessRepository.save(business);
            })
            .orElseThrow(() -> new IllegalArgumentException("Business not found"));
    }
    
    @Transactional
    public void deleteBusiness(UUID userId) {
        if (!businessRepository.existsById(userId)) {
            throw new IllegalArgumentException("Business not found");
        }
        businessRepository.deleteById(userId);
    }
    
    @Transactional
    public Business addInternship(UUID userId, Internship internship) {
        return businessRepository.findById(userId)
            .map(business -> {
                business.getInternshipsOffered().add(internship);
                return businessRepository.save(business);
            })
            .orElseThrow(() -> new IllegalArgumentException("Business not found"));
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