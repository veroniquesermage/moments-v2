package com.moments.modules.program.application.queryHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.moments.modules.program.application.ActivityParticipantRepository;
import com.moments.modules.program.application.ActivityRepository;
import com.moments.modules.program.application.PersonProvider;
import com.moments.modules.program.domain.Activity;
import com.moments.modules.program.presentation.response.ActivityResponse;
import com.moments.modules.program.presentation.response.ParticipantResponse;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetActivityQueryHandler {

    private final ActivityRepository activityRepository;
    private final ActivityParticipantRepository activityParticipantRepository;
    private final PersonProvider personProvider;

    public GetActivityQueryHandler(ActivityRepository activityRepository,
        ActivityParticipantRepository activityParticipantRepository,
        PersonProvider personProvider) {
        this.activityRepository = activityRepository;
        this.activityParticipantRepository = activityParticipantRepository;
        this.personProvider = personProvider;
    }

    public ActivityResponse getActivityById(UUID activityId) {
        Activity activity = activityRepository.findById(activityId);
        List<UUID> participantIds = activityParticipantRepository.findParticipantIdsByActivityId(activityId);

        Set<UUID> personIds = new HashSet<>(participantIds);
        personIds.add(activity.getPlanner());

        Map<UUID, ParticipantResponse> persons = personProvider.getPersonsByIds(personIds);
        ParticipantResponse planner = persons.get(activity.getPlanner());
        Set<ParticipantResponse> participants = participantIds.stream()
            .map(persons::get)
            .collect(Collectors.toSet());

        ActivityResponse activityResponse = ActivityResponse.from(activity);
        activityResponse.setPlanner(planner);
        activityResponse.setParticipants(participants);
        return activityResponse;
    }
}
