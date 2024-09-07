package com.backend.server.controllers;

import com.backend.server.entities.Announcement;
import com.backend.server.helper.AppConstants;
import com.backend.server.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/announcements")
public class AnnouncementsController {

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping()
    public String viewAppointments(@RequestParam(value="page",defaultValue="0") int page,@RequestParam(value="size",defaultValue = AppConstants.PAGE_SIZE+"") int size,@RequestParam(value="sortBy",defaultValue = "createdAt") String sortBy, @RequestParam(value="direction",defaultValue = "desc") String direction,Model model, Authentication authentication){
        Page<Announcement> announcementPage = announcementService.getAll(page,size,sortBy,direction);
        model.addAttribute("announcementPage", announcementPage);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        return "/user/announcements";
    }
    
     // REST API for Android
    @GetMapping("/all")
    public ResponseEntity<Page<Announcement>> getAnnouncements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<Announcement> announcementPage = announcementService.getAll(page, size, sortBy, direction);
        if (announcementPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(announcementPage);
    }
    
}

