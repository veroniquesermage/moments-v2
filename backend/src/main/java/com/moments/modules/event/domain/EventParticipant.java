package com.moments.modules.event.domain;

import java.util.UUID;


public class EventParticipant {

    private UUID personId;

    public EventParticipant(UUID personId) {
        this.personId = personId;
    }

    public UUID getPersonId() {
        return personId;
    }
}
