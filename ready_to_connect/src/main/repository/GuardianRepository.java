// GuardianRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.example.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GuardianRepository extends JpaRepository<Guardian, String> {
    List<Guardian> findByStudentsContaining(String studentId);
}