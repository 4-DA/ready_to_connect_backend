// BusinessRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connect.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    List<Business> findByIndustry(String industry);
    List<Business> findByVerificationStatus(Business.VerificationStatus status);
}