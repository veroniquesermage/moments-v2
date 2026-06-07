package com.moments.modules.program.presentation.response;

import java.util.Set;
import java.util.UUID;

import com.moments.modules.program.domain.Activity;
import com.moments.modules.program.domain.TimeSlot;

public class ActivityResponse {

    private UUID id;
    private String title;
    private String location;
    private String description;
    private ParticipantResponse planner;
    private Set<ParticipantResponse> participants;
    private TimeSlot timeslot;


    public static ActivityResponse from(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.id = activity.getId();
        response.title = activity.getTitle();
        response.location = activity.getLocation();
        response.description = activity.getDescription();
        response.timeslot = activity.getTimeslot();
        return response;
    }

    public void setPlanner(ParticipantResponse planner) {
        this.planner = planner;
    }

    public void setParticipants(Set<ParticipantResponse> participants) {
        this.participants = participants;
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

    public ParticipantResponse getPlanner() {
        return planner;
    }

    public Set<ParticipantResponse> getParticipants() {
        return participants;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

}
