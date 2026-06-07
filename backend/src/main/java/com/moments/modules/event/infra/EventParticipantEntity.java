package com.moments.modules.event.infra;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_participant")
public class EventParticipantEntity {

    @Id
    public UUID id;

    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @Column(name = "person_id", nullable = false)
    private UUID personId;

    public EventParticipantEntity(UUID id, UUID eventId, UUID personId) {
        this.id = id;
        this.eventId = eventId;
        this.personId = personId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public UUID getPersonId() {
        return personId;
    }




}
