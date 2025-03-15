package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connect.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByGuardianId(String guardianId);
    List<Student> findByMentorId(String mentorId);
    List<Student> findBySkillLevel(Integer skillLevel);
}
