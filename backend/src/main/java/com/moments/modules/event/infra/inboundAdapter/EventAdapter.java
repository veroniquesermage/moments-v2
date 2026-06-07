package com.moments.modules.event.infra.inboundAdapter;

import java.util.UUID;

import com.moments.modules.event.application.EventReader;
import com.moments.modules.event.domain.Event;
import com.moments.modules.event.infra.EventEntity;
import com.moments.modules.event.infra.EventPersistenceManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class EventAdapter implements EventReader{


    private final EntityManager entityManager;

    public EventAdapter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Event getById(UUID eventId) {
        EventEntity entity = entityManager.find(EventEntity.class, eventId);
        if (entity == null) {
            throw new IllegalArgumentException("Event not found.");
        }

        return EventPersistenceManager.toDomain(entity);

    }

}
