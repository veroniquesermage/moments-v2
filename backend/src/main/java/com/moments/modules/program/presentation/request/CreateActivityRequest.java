package com.moments.modules.program.presentation.request;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateActivityRequest {

    private String title;
    private String location;
    private String description;
    private UUID plannerId;
    private LocalDateTime start;
    private LocalDateTime end;
    private UUID eventId;

    public String getTitle() {
        return title;
    }
    public String getLocation() {
        return location;
    }
    public String getDescription() {
        return description;
    }
    public UUID getPlannerId() {
        return plannerId;
    }
    public LocalDateTime getStart() {
        return start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    public UUID getEventId() {
        return eventId;
    }


}
