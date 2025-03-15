// CareerPathService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.CareerPath;
import com.ready_to_connect.ready_to_connect.repository.CareerPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CareerPathService {
    
    private final CareerPathRepository careerPathRepository;
    
    @Autowired
    public CareerPathService(CareerPathRepository careerPathRepository) {
        this.careerPathRepository = careerPathRepository;
    }
    
    @Transactional
    public CareerPath createCareerPath(CareerPath careerPath) {
        return careerPathRepository.save(careerPath);
    }
    
    @Transactional(readOnly = true)
    public Optional<CareerPath> findById(UUID id) {
        return careerPathRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<CareerPath> findAll() {
        return careerPathRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<CareerPath> findByIndustry(String industry) {
        return careerPathRepository.findByIndustriesContaining(industry);
    }
    
    @Transactional(readOnly = true)
    public List<CareerPath> findByTitle(String title) {
        return careerPathRepository.findByTitleContainingIgnoreCase(title);
    }
    
    @Transactional(readOnly = true)
    public List<CareerPath> findPopularPaths(Double minScore) {
        return careerPathRepository.findByPopularityScoreGreaterThan(minScore);
    }
    
    @Transactional
    public CareerPath updateCareerPath(UUID id, CareerPath updatedCareerPath) {
        return careerPathRepository.findById(id)
            .map(careerPath -> {
                careerPath.setTitle(updatedCareerPath.getTitle());
                careerPath.setDescription(updatedCareerPath.getDescription());
                careerPath.setIndustries(updatedCareerPath.getIndustries());
                careerPath.setRequiredSkills(updatedCareerPath.getRequiredSkills());
                careerPath.setRecommendedCourses(updatedCareerPath.getRecommendedCourses());
                careerPath.setPotentialJobs(updatedCareerPath.getPotentialJobs());
                careerPath.setAverageSalary(updatedCareerPath.getAverageSalary());
                careerPath.setGrowthOutlook(updatedCareerPath.getGrowthOutlook());
                careerPath.setPopularityScore(updatedCareerPath.getPopularityScore());
                careerPath.setMatchingInternships(updatedCareerPath.getMatchingInternships());
                return careerPathRepository.save(careerPath);
            })
            .orElseThrow(() -> new IllegalArgumentException("Career path not found"));
    }
    
    @Transactional
    public void deleteCareerPath(UUID id) {
        if (!careerPathRepository.existsById(id)) {
            throw new IllegalArgumentException("Career path not found");
        }
        careerPathRepository.deleteById(id);
    }
    
    @Transactional
    public CareerPath addInternshipMatch(UUID id, String internshipId) {
        return careerPathRepository.findById(id)
            .map(careerPath -> {
                careerPath.getMatchingInternships().add(internshipId);
                return careerPathRepository.save(careerPath);
            })
            .orElseThrow(() -> new IllegalArgumentException("Career path not found"));
    }
}