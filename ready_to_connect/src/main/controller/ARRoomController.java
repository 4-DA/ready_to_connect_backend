// ARRoomController.java
package com.ready_to_connect.ready_to_connect.controller;

import com.ready_to_connect.ready_to_connect.model.ARRoom;
import com.ready_to_connect.ready_to_connect.model.ObjectModel;
import com.ready_to_connect.ready_to_connect.service.ARRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ar-rooms")
public class ARRoomController {
    
    private final ARRoomService arRoomService;
    
    @Autowired
    public ARRoomController(ARRoomService arRoomService) {
        this.arRoomService = arRoomService;
    }
    
    @PostMapping
    public ResponseEntity<ARRoom> createRoom(@RequestBody ARRoom room) {
        return ResponseEntity.ok(arRoomService.createRoom(room));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ARRoom> getRoom(@PathVariable UUID id) {
        return arRoomService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<ARRoom>> getAllRooms() {
        return ResponseEntity.ok(arRoomService.findAll());
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<ARRoom>> getRoomsByType(@PathVariable ARRoom.RoomType type) {
        return ResponseEntity.ok(arRoomService.findByType(type));
    }
    
    @GetMapping("/skill-level/{level}")
    public ResponseEntity<List<ARRoom>> getRoomsBySkillLevel(@PathVariable Integer level) {
        return ResponseEntity.ok(arRoomService.findBySkillLevel(level));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ARRoom> updateRoom(@PathVariable UUID id, @RequestBody ARRoom room) {
        return ResponseEntity.ok(arRoomService.updateRoom(id, room));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID id) {
        arRoomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/objects")
    public ResponseEntity<ARRoom> addObjectModel(@PathVariable UUID id, @RequestBody ObjectModel objectModel) {
        return ResponseEntity.ok(arRoomService.addObjectModel(id, objectModel));
    }
}