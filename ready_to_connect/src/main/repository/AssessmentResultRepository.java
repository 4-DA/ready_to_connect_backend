// AssessmentResultRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connecte.model.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, UUID> {
    List<AssessmentResult> findByStudentId(String studentId);
    List<AssessmentResult> findByAssessmentId(String assessmentId);
}