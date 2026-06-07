package com.moments.modules.event.infra.inboundAdapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.moments.modules.event.domain.EventParticipant;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class EventParticipantAdapterTest {

    @Inject
    EventParticipantAdapter eventParticipantAdapter;

    private static final UUID EXISTING_EVENT_ID = UUID.fromString("20000000-0000-0000-0000-000000000001");
    private static final UUID UNKNOWN_EVENT_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");

    @Test
    void shouldReturnParticipantsForEvent() {
        List<EventParticipant> participants = eventParticipantAdapter.getParticipantsByEventId(EXISTING_EVENT_ID);

        assertNotNull(participants);
        assertEquals(2, participants.size());
    }

    @Test
    void shouldReturnEmptyListForUnknownEvent() {
        List<EventParticipant> participants = eventParticipantAdapter.getParticipantsByEventId(UNKNOWN_EVENT_ID);

        assertNotNull(participants);
        assertTrue(participants.isEmpty());
    }

    @Test
    void shouldMapEntityToDomainCorrectly() {
        List<EventParticipant> participants = eventParticipantAdapter.getParticipantsByEventId(EXISTING_EVENT_ID);

        participants.forEach(p -> assertNotNull(p.getPersonId()));
    }
}
