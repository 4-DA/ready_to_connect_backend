// StudentService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.*;
import com.ready_to_connect.ready_to_connect.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    @Transactional
    public Student createStudent(Student student) {
        validateSkillLevel(student.getSkillLevel());
        return studentRepository.save(student);
    }
    
    @Transactional(readOnly = true)
    public Optional<Student> findById(String userId) {
        return studentRepository.findById(userId);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Student> findByGuardianId(String guardianId) {
        return studentRepository.findByGuardianId(guardianId);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findByMentorId(String mentorId) {
        return studentRepository.findByMentorId(mentorId);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findBySkillLevel(Integer skillLevel) {
        validateSkillLevel(skillLevel);
        return studentRepository.findBySkillLevel(skillLevel);
    }
    
    @Transactional
    public Student updateStudent(String userId, Student updatedStudent) {
        validateSkillLevel(updatedStudent.getSkillLevel());
        return studentRepository.findById(userId)
            .map(student -> {
                student.setSkillLevel(updatedStudent.getSkillLevel());
                student.setTotalPoints(updatedStudent.getTotalPoints());
                student.setInterests(updatedStudent.getInterests());
                student.setSkills(updatedStudent.getSkills());
                student.setBadges(updatedStudent.getBadges());
                student.setEducationLevel(updatedStudent.getEducationLevel());
                student.setGuardianId(updatedStudent.getGuardianId());
                student.setCompletedAssessments(updatedStudent.getCompletedAssessments());
                student.setRecommendedPaths(updatedStudent.getRecommendedPaths());
                student.setAppliedInternships(updatedStudent.getAppliedInternships());
                student.setMentorId(updatedStudent.getMentorId());
                student.setAiConversationHistory(updatedStudent.getAiConversationHistory());
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }
    
    @Transactional
    public void deleteStudent(String userId) {
        if (!studentRepository.existsById(userId)) {
            throw new IllegalArgumentException("Student not found");
        }
        studentRepository.deleteById(userId);
    }
    
    @Transactional
    public Student addBadge(String userId, Badge badge) {
        return studentRepository.findById(userId)
            .map(student -> {
                student.getBadges().add(badge);
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }
    
    @Transactional
    public Student addInternshipApplication(String userId, Internship internship) {
        return studentRepository.findById(userId)
            .map(student -> {
                student.getAppliedInternships().add(internship);
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }
    
    private void validateSkillLevel(Integer skillLevel) {
        if (skillLevel != null && (skillLevel < 1 || skillLevel > 5)) {
            throw new IllegalArgumentException("Skill level must be between 1 and 5");
        }
    }
}