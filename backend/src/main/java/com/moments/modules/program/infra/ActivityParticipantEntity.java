package com.moments.modules.program.infra;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "activity_participant")
public class ActivityParticipantEntity {

    @Id
    @Column(nullable = false)
    public UUID id;

    @Column(name = "activityId", nullable = false)
    private UUID activityId;

    @Column(name = "participantId", nullable = false)
    private UUID participantId;

    @Column(name = "status", nullable = false)
    public String status;

    public UUID getId() {
        return id;
    }

    public UUID getActivityId() {
        return activityId;
    }

    public UUID getParticipantId() {
        return participantId;
    }

    public String getStatus() {
        return status;
    }





}
