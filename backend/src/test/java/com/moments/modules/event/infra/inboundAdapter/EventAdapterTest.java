package com.moments.modules.event.infra.inboundAdapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.moments.modules.event.domain.Event;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class EventAdapterTest {

    @Inject
    EventAdapter eventAdapter;

    private static final UUID EXISTING_EVENT_ID = UUID.fromString("20000000-0000-0000-0000-000000000001");
    private static final UUID UNKNOWN_EVENT_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");

    @Test
    void shouldReturnEventById() {
        Event event = eventAdapter.getById(EXISTING_EVENT_ID);

        assertNotNull(event);
        assertEquals(EXISTING_EVENT_ID, event.getId());
        assertEquals("Summer Camp", event.getTitle());
    }

    @Test
    void shouldThrowWhenEventNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> eventAdapter.getById(UNKNOWN_EVENT_ID));
    }
}
