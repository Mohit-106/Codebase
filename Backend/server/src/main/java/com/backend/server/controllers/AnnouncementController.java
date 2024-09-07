package com.backend.server.controllers;
import com.backend.server.entities.Announcement;
import com.backend.server.forms.AnnouncementForm;
import com.backend.server.helper.Message;
import com.backend.server.helper.MessageType;
import com.backend.server.services.AnnouncementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("user/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping()
    
    public String pushAnnouncement(Model model) {
        model.addAttribute("announcementForm", new AnnouncementForm());  // Add empty form object to the model
        return "user/announcement";  
    }

    @PostMapping("/add")
    public String addAnnouncement(@Valid AnnouncementForm announcementForm, BindingResult bindingResult, Model model,HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("announcementForm", announcementForm);
            return "user/announcement";
        }
        Announcement announcement = new Announcement();
        announcement.setContent(announcementForm.getContent());
        announcementService.save(announcement);

        Message message = Message.builder().content("Announcement has been posted successfully✅").type(MessageType.green).build();
        session.setAttribute("message", message);
        
        return "redirect:/user/announcement";
    }

    // Delete Announcement
    @RequestMapping("/delete/{id}")
    public String deleteAnnouncement(@PathVariable("id") String id, HttpSession session){
        announcementService.delete(id);
        return "redirect:/user/announcements";
    }


}
