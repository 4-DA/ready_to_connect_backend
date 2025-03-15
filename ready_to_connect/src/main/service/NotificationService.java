// NotificationService.java
package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.Notification;
import com.ready_to_connect.ready_to_connect.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {
    
    private final NotificationRepository notificationRepository;
    
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    
    @Transactional
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
    
    @Transactional(readOnly = true)
    public Optional<Notification> findById(UUID id) {
        return notificationRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Notification> findByUserId(String userId) {
        return notificationRepository.findByUserId(userId);
    }
    
    @Transactional(readOnly = true)
    public List<Notification> findUnreadByUserId(String userId) {
        return notificationRepository.findByUserIdAndRead(userId, false);
    }
    
    @Transactional(readOnly = true)
    public long countUnreadByUserId(String userId) {
        return notificationRepository.countByUserIdAndRead(userId, false);
    }
    
    @Transactional
    public Notification markAsRead(UUID id) {
        return notificationRepository.findById(id)
            .map(notification -> {
                notification.setRead(true);
                return notificationRepository.save(notification);
            })
            .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
    }
    
    @Transactional
    public void deleteNotification(UUID id) {
        if (!notificationRepository.existsById(id)) {
            throw new IllegalArgumentException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }
    
    @Transactional
    public void markAllAsRead(String userId) {
        List<Notification> unreadNotifications = notificationRepository.findByUserIdAndRead(userId, false);
        unreadNotifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(unreadNotifications);
    }
}