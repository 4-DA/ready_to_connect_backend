// BadgeService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Badge;
import com.ready_to_connect.ready_to_connect.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BadgeService {
    
    private final BadgeRepository badgeRepository;
    
    @Autowired
    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }
    
    @Transactional
    public Badge createBadge(Badge badge) {
        return badgeRepository.save(badge);
    }
    
    @Transactional(readOnly = true)
    public Optional<Badge> findById(UUID id) {
        return badgeRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Badge> findAll() {
        return badgeRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Badge> findByCategory(String category) {
        return badgeRepository.findByCategory(category);
    }
    
    @Transactional(readOnly = true)
    public List<Badge> findByPointsRequired(Integer points) {
        return badgeRepository.findByPointsRequiredLessThanEqual(points);
    }
    
    @Transactional
    public Badge updateBadge(UUID id, Badge updatedBadge) {
        return badgeRepository.findById(id)
            .map(badge -> {
                badge.setName(updatedBadge.getName());
                badge.setDescription(updatedBadge.getDescription());
                badge.setImageUrl(updatedBadge.getImageUrl());
                badge.setCategory(updatedBadge.getCategory());
                badge.setPointsRequired(updatedBadge.getPointsRequired());
                badge.setRequirements(updatedBadge.getRequirements());
                return badgeRepository.save(badge);
            })
            .orElseThrow(() -> new IllegalArgumentException("Badge not found"));
    }
    
    @Transactional
    public Badge unlockBadge(UUID id) {
        return badgeRepository.findById(id)
            .map(badge -> {
                badge.setUnlockedAt(LocalDateTime.now());
                return badgeRepository.save(badge);
            })
            .orElseThrow(() -> new IllegalArgumentException("Badge not found"));
    }
    
    @Transactional
    public void deleteBadge(UUID id) {
        if (!badgeRepository.existsById(id)) {
            throw new IllegalArgumentException("Badge not found");
        }
        badgeRepository.deleteById(id);
    }
}