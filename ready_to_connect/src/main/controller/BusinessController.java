// BusinessController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.example.model.Business;
import com.example.model.Internship;
import com.example.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {
    
    private final BusinessService businessService;
    
    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }
    
    @PostMapping
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) {
        return ResponseEntity.ok(businessService.createBusiness(business));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusiness(@PathVariable UUID id) {
        return businessService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Business>> getAllBusinesses() {
        return ResponseEntity.ok(businessService.findAll());
    }
    
    @GetMapping("/industry/{industry}")
    public ResponseEntity<List<Business>> getByIndustry(@PathVariable String industry) {
        return ResponseEntity.ok(businessService.findByIndustry(industry));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Business>> getByVerificationStatus(
            @PathVariable Business.VerificationStatus status) {
        return ResponseEntity.ok(businessService.findByVerificationStatus(status));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Business> updateBusiness(
            @PathVariable UUID id, 
            @RequestBody Business business) {
        return ResponseEntity.ok(businessService.updateBusiness(id, business));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable UUID id) {
        businessService.deleteBusiness(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/internships")
    public ResponseEntity<Business> addInternship(
            @PathVariable UUID id, 
            @RequestBody Internship internship) {
        return ResponseEntity.ok(businessService.addInternship(id, internship));
    }
}