package com.moments.modules.event.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void shouldCreateEventWithIdAndTitle() {
        UUID id = UUID.randomUUID();

        Event event = new Event(id, "Summer Camp");

        assertEquals(id, event.getId());
        assertEquals("Summer Camp", event.getTitle());
    }

    @Test
    void shouldAllowNullTitle() {
        UUID id = UUID.randomUUID();

        Event event = new Event(id, null);

        assertNull(event.getTitle());
    }
}
