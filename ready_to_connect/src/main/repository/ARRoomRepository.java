// ARRoomRepository.java
package com.ready_to_connect.ready_to_connect.repository;

import com.ready_to_connect.ready_to_connect.model.ARRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ARRoomRepository extends JpaRepository<ARRoom, UUID> {
    List<ARRoom> findByType(ARRoom.RoomType type);
    List<ARRoom> findByAvailableToLevelsContaining(Integer skillLevel);
}