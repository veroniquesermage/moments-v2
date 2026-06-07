package com.moments.modules.event.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class EventParticipantTest {

    @Test
    void shouldCreateEventParticipantWithPersonId() {
        UUID personId = UUID.randomUUID();

        EventParticipant participant = new EventParticipant(personId);

        assertEquals(personId, participant.getPersonId());
    }
}
