package com.backend.server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.server.entities.Announcement;

public interface AnnouncementRepo extends JpaRepository<Announcement,String>{
     Page<Announcement> findAll(Pageable pageable);
}
