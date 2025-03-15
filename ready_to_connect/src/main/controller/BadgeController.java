// BadgeController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.Badge;
import com.ready_to_connect.ready_to_connect.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {
    
    private final BadgeService badgeService;
    
    @Autowired
    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }
    
    @PostMapping
    public ResponseEntity<Badge> createBadge(@RequestBody Badge badge) {
        Badge createdBadge = badgeService.createBadge(badge);
        return new ResponseEntity<>(createdBadge, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Badge> getBadgeById(@PathVariable UUID id) {
        return badgeService.findById(id)
            .map(badge -> new ResponseEntity<>(badge, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeService.findAll();
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Badge>> getBadgesByCategory(@PathVariable String category) {
        List<Badge> badges = badgeService.findByCategory(category);
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }
    
    @GetMapping("/points/{points}")
    public ResponseEntity<List<Badge>> getBadgesByPoints(@PathVariable Integer points) {
        List<Badge> badges = badgeService.findByPointsRequired(points);
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Badge> updateBadge(@PathVariable UUID id, @RequestBody Badge badge) {
        try {
            Badge updatedBadge = badgeService.updateBadge(id, badge);
            return new ResponseEntity<>(updatedBadge, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}/unlock")
    public ResponseEntity<Badge> unlockBadge(@PathVariable UUID id) {
        try {
            Badge unlockedBadge = badgeService.unlockBadge(id);
            return new ResponseEntity<>(unlockedBadge, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadge(@PathVariable UUID id) {
        try {
            badgeService.deleteBadge(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}