package com.backend.server.services.impl;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backend.server.entities.Announcement;
import com.backend.server.helper.ResourceNotFoundException;
import com.backend.server.repositories.AnnouncementRepo;
import com.backend.server.services.AnnouncementService;


@Service
public class AnnouncementServiceImpl implements AnnouncementService{

    @Autowired
    private AnnouncementRepo announcementRepository;

    @Override
    public Announcement save(Announcement announcement) {
         String appointmentId = UUID.randomUUID().toString();
            announcement.setId(appointmentId);
            announcement.setCreatedAt(LocalDateTime.now());
        return announcementRepository.save(announcement);
    }

    @Override
    public Page<Announcement> getAll(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return announcementRepository.findAll(pageable);
    }    

    @Override
    public void delete(String id) {
        var announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));
        announcementRepository.delete(announcement);
    }
   
}
