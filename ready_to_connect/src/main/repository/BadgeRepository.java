// BadgeRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connect.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BadgeRepository extends JpaRepository<Badge, UUID> {
    List<Badge> findByCategory(String category);
    List<Badge> findByPointsRequiredLessThanEqual(Integer points);
}