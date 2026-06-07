package com.moments.modules.event.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.moments.modules.event.domain.Event;

class EventPersistenceManagerTest {

    @Test
    void shouldMapEntityToDomain() {
        UUID id = UUID.randomUUID();
        EventEntity entity = new EventEntity(id, "Summer Camp");

        Event event = EventPersistenceManager.toDomain(entity);

        assertEquals(id, event.getId());
        assertEquals("Summer Camp", event.getTitle());
    }

    @Test
    void shouldMapDomainToEntity() {
        UUID id = UUID.randomUUID();
        Event event = new Event(id, "Summer Camp");

        EventEntity entity = EventPersistenceManager.toEntity(event);

        assertEquals(id, entity.getId());
        assertEquals("Summer Camp", entity.getTitle());
    }

    @Test
    void shouldPreserveIdDuringRoundTrip() {
        UUID id = UUID.randomUUID();
        Event original = new Event(id, "Beach Party");

        EventEntity entity = EventPersistenceManager.toEntity(original);
        Event roundTripped = EventPersistenceManager.toDomain(entity);

        assertEquals(original.getId(), roundTripped.getId());
        assertEquals(original.getTitle(), roundTripped.getTitle());
    }
}
