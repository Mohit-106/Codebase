package com.backend.server.forms;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementForm {

    @NotBlank(message = "Content is required")
    private String content;

    private String id;
}
