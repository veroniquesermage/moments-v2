package com.moments.modules.event.infra;

import com.moments.modules.event.domain.Event;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventPersistenceManager {

    public static EventEntity toEntity(Event event) {
        return new EventEntity(event.getId(), event.getTitle());
    }

    public static Event toDomain(EventEntity entity) {
        return new Event(entity.getId(), entity.getTitle());
    }
}
