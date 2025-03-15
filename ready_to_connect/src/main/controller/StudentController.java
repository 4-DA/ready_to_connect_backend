// StudentController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.Student;
import com.ready_to_connect.ready_to_connect.model.Badge;
import com.ready_to_connect.ready_to_connect.model.Internship;
import com.ready_to_connect.ready_to_connect.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private final StudentService studentService;
    
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    // Create a new student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return ResponseEntity.ok(createdStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    // Get student by ID
    @GetMapping("/{userId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String userId) {
        return studentService.findById(userId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }
    
    // Get students by guardian ID
    @GetMapping("/guardian/{guardianId}")
    public ResponseEntity<List<Student>> getStudentsByGuardian(@PathVariable String guardianId) {
        List<Student> students = studentService.findByGuardianId(guardianId);
        return ResponseEntity.ok(students);
    }
    
    // Get students by mentor ID
    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<Student>> getStudentsByMentor(@PathVariable String mentorId) {
        List<Student> students = studentService.findByMentorId(mentorId);
        return ResponseEntity.ok(students);
    }
    
    // Get students by skill level
    @GetMapping("/skill-level/{skillLevel}")
    public ResponseEntity<List<Student>> getStudentsBySkillLevel(@PathVariable Integer skillLevel) {
        try {
            List<Student> students = studentService.findBySkillLevel(skillLevel);
            return ResponseEntity.ok(students);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    // Update student
    @PutMapping("/{userId}")
    public ResponseEntity<Student> updateStudent(@PathVariable String userId, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(userId, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete student
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String userId) {
        try {
            studentService.deleteStudent(userId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Add badge to student
    @PostMapping("/{userId}/badges")
    public ResponseEntity<Student> addBadge(
            @PathVariable String userId,
            @RequestBody Badge badge) {
        try {
            Student updatedStudent = studentService.addBadge(userId, badge);
            return ResponseEntity.ok(updatedStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Add internship application to student
    @PostMapping("/{userId}/internships")
    public ResponseEntity<Student> addInternshipApplication(
            @PathVariable String userId,
            @RequestBody Internship internship) {
        try {
            Student updatedStudent = studentService.addInternshipApplication(userId, internship);
            return ResponseEntity.ok(updatedStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}