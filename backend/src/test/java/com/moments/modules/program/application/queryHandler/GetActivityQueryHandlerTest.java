package com.moments.modules.program.application.queryHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moments.core.exception.ResourceNotFoundException;
import com.moments.modules.program.application.ActivityParticipantRepository;
import com.moments.modules.program.application.ActivityRepository;
import com.moments.modules.program.application.PersonProvider;
import com.moments.modules.program.domain.Activity;
import com.moments.modules.program.domain.TimeSlot;
import com.moments.modules.program.presentation.response.ActivityResponse;
import com.moments.modules.program.presentation.response.ParticipantResponse;

@ExtendWith(MockitoExtension.class)
class GetActivityQueryHandlerTest {

    @Mock
    private ActivityRepository activityRepository;
    @Mock
    private ActivityParticipantRepository activityParticipantRepository;
    @Mock
    private PersonProvider personProvider;

    private GetActivityQueryHandler handler;

    private static final UUID ACTIVITY_ID = UUID.randomUUID();
    private static final UUID PLANNER_ID = UUID.randomUUID();
    private static final UUID PARTICIPANT_1_ID = UUID.randomUUID();
    private static final UUID PARTICIPANT_2_ID = UUID.randomUUID();
    private static final TimeSlot TIMESLOT = new TimeSlot(
            LocalDateTime.of(2025, 7, 15, 8, 0),
            LocalDateTime.of(2025, 7, 15, 12, 0));

    @BeforeEach
    void setUp() {
        handler = new GetActivityQueryHandler(activityRepository, activityParticipantRepository, personProvider);
    }

    @Test
    void shouldReturnCompleteActivityResponse() {
        Activity activity = new Activity(ACTIVITY_ID, "Hiking", "Mountain", "A hike", PLANNER_ID, Set.of(), TIMESLOT);
        ParticipantResponse plannerResponse = new ParticipantResponse(PLANNER_ID, "Doe", "John");
        ParticipantResponse participant1Response = new ParticipantResponse(PARTICIPANT_1_ID, "Smith", "Jane");

        when(activityRepository.findById(ACTIVITY_ID)).thenReturn(activity);
        when(activityParticipantRepository.findParticipantIdsByActivityId(ACTIVITY_ID))
                .thenReturn(List.of(PARTICIPANT_1_ID));
        when(personProvider.getPersonsByIds(Set.of(PLANNER_ID, PARTICIPANT_1_ID)))
                .thenReturn(Map.of(
                        PLANNER_ID, plannerResponse,
                        PARTICIPANT_1_ID, participant1Response));

        ActivityResponse response = handler.getActivityById(ACTIVITY_ID);

        assertNotNull(response);
        verify(activityRepository).findById(ACTIVITY_ID);
        verify(activityParticipantRepository).findParticipantIdsByActivityId(ACTIVITY_ID);
        verify(personProvider).getPersonsByIds(Set.of(PLANNER_ID, PARTICIPANT_1_ID));
    }

    @Test
    void shouldPropagateResourceNotFoundExceptionWhenActivityNotFound() {
        when(activityRepository.findById(ACTIVITY_ID))
                .thenThrow(new ResourceNotFoundException("Activity", ACTIVITY_ID));

        assertThrows(ResourceNotFoundException.class, () -> handler.getActivityById(ACTIVITY_ID));
    }

    @Test
    void shouldHandleActivityWithNoParticipants() {
        Activity activity = new Activity(ACTIVITY_ID, "Solo Hike", "Mountain", "Solo", PLANNER_ID, Set.of(), TIMESLOT);
        ParticipantResponse plannerResponse = new ParticipantResponse(PLANNER_ID, "Doe", "John");

        when(activityRepository.findById(ACTIVITY_ID)).thenReturn(activity);
        when(activityParticipantRepository.findParticipantIdsByActivityId(ACTIVITY_ID))
                .thenReturn(List.of());
        when(personProvider.getPersonsByIds(Set.of(PLANNER_ID)))
                .thenReturn(Map.of(PLANNER_ID, plannerResponse));

        ActivityResponse response = handler.getActivityById(ACTIVITY_ID);

        assertNotNull(response);
        verify(personProvider).getPersonsByIds(Set.of(PLANNER_ID));
    }

    @Test
    void shouldIncludePlannerInPersonIdsLookup() {
        Activity activity = new Activity(ACTIVITY_ID, "Hiking", "Mountain", "Desc", PLANNER_ID,
                Set.of(PARTICIPANT_1_ID, PARTICIPANT_2_ID), TIMESLOT);
        ParticipantResponse plannerResponse = new ParticipantResponse(PLANNER_ID, "Doe", "John");
        ParticipantResponse p1Response = new ParticipantResponse(PARTICIPANT_1_ID, "Smith", "Jane");
        ParticipantResponse p2Response = new ParticipantResponse(PARTICIPANT_2_ID, "Brown", "Bob");

        when(activityRepository.findById(ACTIVITY_ID)).thenReturn(activity);
        when(activityParticipantRepository.findParticipantIdsByActivityId(ACTIVITY_ID))
                .thenReturn(List.of(PARTICIPANT_1_ID, PARTICIPANT_2_ID));
        when(personProvider.getPersonsByIds(Set.of(PLANNER_ID, PARTICIPANT_1_ID, PARTICIPANT_2_ID)))
                .thenReturn(Map.of(
                        PLANNER_ID, plannerResponse,
                        PARTICIPANT_1_ID, p1Response,
                        PARTICIPANT_2_ID, p2Response));

        handler.getActivityById(ACTIVITY_ID);

        verify(personProvider).getPersonsByIds(Set.of(PLANNER_ID, PARTICIPANT_1_ID, PARTICIPANT_2_ID));
    }

    @Test
    void shouldNotDuplicatePlannerWhenPlannerIsAlsoParticipant() {
        Activity activity = new Activity(ACTIVITY_ID, "Hiking", "Mountain", "Desc", PLANNER_ID, Set.of(), TIMESLOT);
        ParticipantResponse plannerResponse = new ParticipantResponse(PLANNER_ID, "Doe", "John");

        when(activityRepository.findById(ACTIVITY_ID)).thenReturn(activity);
        when(activityParticipantRepository.findParticipantIdsByActivityId(ACTIVITY_ID))
                .thenReturn(List.of(PLANNER_ID));
        when(personProvider.getPersonsByIds(Set.of(PLANNER_ID)))
                .thenReturn(Map.of(PLANNER_ID, plannerResponse));

        ActivityResponse response = handler.getActivityById(ACTIVITY_ID);

        assertNotNull(response);
        // Planner ID added to set that already contains it -> only one lookup
        verify(personProvider).getPersonsByIds(Set.of(PLANNER_ID));
    }
}
