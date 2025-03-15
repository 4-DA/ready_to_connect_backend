// ARRoomService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.ARRoom;
import com.ready_to_connect.ready_to_connect.repository.ARRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ARRoomService {
    
    private final ARRoomRepository arRoomRepository;
    
    @Autowired
    public ARRoomService(ARRoomRepository arRoomRepository) {
        this.arRoomRepository = arRoomRepository;
    }
    
    @Transactional
    public ARRoom createRoom(ARRoom room) {
        return arRoomRepository.save(room);
    }
    
    @Transactional(readOnly = true)
    public Optional<ARRoom> findById(UUID id) {
        return arRoomRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<ARRoom> findAll() {
        return arRoomRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<ARRoom> findByType(ARRoom.RoomType type) {
        return arRoomRepository.findByType(type);
    }
    
    @Transactional(readOnly = true)
    public List<ARRoom> findBySkillLevel(Integer skillLevel) {
        return arRoomRepository.findByAvailableToLevelsContaining(skillLevel);
    }
    
    @Transactional
    public ARRoom updateRoom(UUID id, ARRoom updatedRoom) {
        return arRoomRepository.findById(id)
            .map(room -> {
                room.setName(updatedRoom.getName());
                room.setDescription(updatedRoom.getDescription());
                room.setType(updatedRoom.getType());
                room.setObjectModels(updatedRoom.getObjectModels());
                room.setBackgroundUrl(updatedRoom.getBackgroundUrl());
                room.setAvailableToLevels(updatedRoom.getAvailableToLevels());
                room.setInteractionPoints(updatedRoom.getInteractionPoints());
                return arRoomRepository.save(room);
            })
            .orElseThrow(() -> new IllegalArgumentException("AR Room not found"));
    }
    
    @Transactional
    public void deleteRoom(UUID id) {
        if (!arRoomRepository.existsById(id)) {
            throw new IllegalArgumentException("AR Room not found");
        }
        arRoomRepository.deleteById(id);
    }
    
    @Transactional
    public ARRoom addObjectModel(UUID id, ObjectModel objectModel) {
        return arRoomRepository.findById(id)
            .map(room -> {
                room.getObjectModels().add(objectModel);
                return arRoomRepository.save(room);
            })
            .orElseThrow(() -> new IllegalArgumentException("AR Room not found"));
    }
}