package com.moments.modules.program.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Activity {

    private UUID id;
    private String title;
    private String location;
    private String description;
    private UUID planner;
    private Set<UUID> participants;
    private TimeSlot timeslot;

    public Activity(UUID id, String title, String location, String description, UUID planner,
            Set<UUID> participants, TimeSlot timeslot) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.planner = planner;
        this.participants = participants == null ? new HashSet<>() : participants;
        this.timeslot = timeslot;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public UUID getPlanner() {
        return planner;
    }

    public Set<UUID> getParticipants() {
        return participants;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void rename(String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Activity title cannot be null or empty.");
        }
        this.title = newTitle;
    }

}
