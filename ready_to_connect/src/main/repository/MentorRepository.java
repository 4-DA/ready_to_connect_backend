// MentorRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connect.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MentorRepository extends JpaRepository<Mentor, UUID> {
    List<Mentor> findByIndustry(String industry);
    List<Mentor> findByIsAI(boolean isAI);
    List<Mentor> findBySpecializationContaining(String specialization);
}