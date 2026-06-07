package com.moments.modules.event.infra.inboundAdapter;

import java.util.List;
import java.util.UUID;

import com.moments.modules.event.application.EventParticipantReader;
import com.moments.modules.event.domain.EventParticipant;
import com.moments.modules.event.infra.EventParticipantEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class EventParticipantAdapter implements EventParticipantReader {

    private final EntityManager entityManager;

    public EventParticipantAdapter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EventParticipant> getParticipantsByEventId(UUID eventId) {

        List<EventParticipantEntity> entities = entityManager
                .createQuery("SELECT e FROM EventParticipantEntity e WHERE e.eventId = :eventId",
                        EventParticipantEntity.class)
                .setParameter("eventId", eventId)
                .getResultList();

        return entities.stream()
                .map(EventParticipantAdapter::toDomain)
                .toList();
    }

    private static EventParticipant toDomain(EventParticipantEntity entity) {
        // Convert EventParticipantEntity to EventParticipant
        return new EventParticipant(entity.getPersonId());
    }
}
