// InternshipRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connect.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface InternshipRepository extends JpaRepository<Internship, UUID> {
    List<Internship> findByBusinessId(String businessId);
    List<Internship> findByStatus(Internship.Status status);
    List<Internship> findBySkillLevelRequired(Integer skillLevel);
}