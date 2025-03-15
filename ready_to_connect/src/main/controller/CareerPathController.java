// CareerPathController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.CareerPath;
import com.ready_to_connect.ready_to_connect.service.CareerPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/career-paths")
public class CareerPathController {
    
    private final CareerPathService careerPathService;
    
    @Autowired
    public CareerPathController(CareerPathService careerPathService) {
        this.careerPathService = careerPathService;
    }
    
    @PostMapping
    public ResponseEntity<CareerPath> createCareerPath(@RequestBody CareerPath careerPath) {
        return ResponseEntity.ok(careerPathService.createCareerPath(careerPath));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CareerPath> getCareerPath(@PathVariable UUID id) {
        return careerPathService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<CareerPath>> getAllCareerPaths() {
        return ResponseEntity.ok(careerPathService.findAll());
    }
    
    @GetMapping("/search/industry")
    public ResponseEntity<List<CareerPath>> getByIndustry(@RequestParam String industry) {
        return ResponseEntity.ok(careerPathService.findByIndustry(industry));
    }
    
    @GetMapping("/search/title")
    public ResponseEntity<List<CareerPath>> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(careerPathService.findByTitle(title));
    }
    
    @GetMapping("/popular")
    public ResponseEntity<List<CareerPath>> getPopularPaths(@RequestParam Double minScore) {
        return ResponseEntity.ok(careerPathService.findPopularPaths(minScore));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CareerPath> updateCareerPath(
            @PathVariable UUID id, 
            @RequestBody CareerPath careerPath) {
        return ResponseEntity.ok(careerPathService.updateCareerPath(id, careerPath));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareerPath(@PathVariable UUID id) {
        careerPathService.deleteCareerPath(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/internships")
    public ResponseEntity<CareerPath> addInternshipMatch(
            @PathVariable UUID id, 
            @RequestParam String internshipId) {
        return ResponseEntity.ok(careerPathService.addInternshipMatch(id, internshipId));
    }
}