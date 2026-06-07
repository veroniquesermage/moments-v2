package com.moments.modules.event.domain;

import java.util.List;
import java.util.UUID;

public class Event {

    private UUID id;
    private String title;
    private List<EventParticipant> participants;

    public Event(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
