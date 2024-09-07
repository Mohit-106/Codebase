package com.backend.server.services;
import java.util.List;

import org.springframework.data.domain.Page;

import com.backend.server.entities.Announcement;

public interface AnnouncementService {
     Announcement save(Announcement announcement);
     Page<Announcement> getAll(int page, int size, String sortBy, String direction);
     void delete(String id);
}
