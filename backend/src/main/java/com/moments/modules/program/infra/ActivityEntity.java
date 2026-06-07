package com.moments.modules.program.infra;

import java.time.LocalDateTime;
import java.util.UUID;

import com.moments.modules.event.infra.EventEntity;
import com.moments.modules.event.infra.EventParticipantEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "program_activity")
public class ActivityEntity {

    @Id
    @Column(nullable = false)
    public UUID id;

    @Column(name = "event_id", nullable = false)
    public UUID eventId;

    @Column(name = "planner_id", nullable = false)
    public UUID plannerId;

    @Column(nullable = false)
    public String title;

    public String location;

    public String description;

    @Column(name = "start_at", nullable = false)
    public LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    public LocalDateTime endAt;

    public ActivityEntity(UUID id, EventEntity event, EventParticipantEntity planner, String title, String location,
            String description, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.eventId = event.getId();
        this.plannerId = planner.getId();
        this.title = title;
        this.location = location;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
