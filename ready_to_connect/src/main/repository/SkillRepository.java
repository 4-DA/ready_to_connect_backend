// SkillRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connect.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
    List<Skill> findByCategory(String category);
    List<Skill> findByLevel(Integer level);
    List<Skill> findByNameContainingIgnoreCase(String name);
}