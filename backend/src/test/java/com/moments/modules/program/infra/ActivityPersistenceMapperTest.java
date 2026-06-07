package com.moments.modules.program.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.moments.modules.event.infra.EventEntity;
import com.moments.modules.event.infra.EventParticipantEntity;
import com.moments.modules.program.domain.Activity;

class ActivityPersistenceMapperTest {

    @Test
    void shouldMapEntityToDomain() {
        UUID activityId = UUID.randomUUID();
        UUID eventId = UUID.randomUUID();
        UUID plannerId = UUID.randomUUID();
        LocalDateTime startAt = LocalDateTime.of(2025, 7, 15, 8, 0);
        LocalDateTime endAt = LocalDateTime.of(2025, 7, 15, 12, 0);

        EventEntity event = new EventEntity(eventId, "Summer Camp");
        EventParticipantEntity planner = new EventParticipantEntity(UUID.randomUUID(), eventId, plannerId);

        ActivityEntity entity = new ActivityEntity(activityId, event, planner, "Morning Hike",
                "Mountain Trail", "A beautiful morning hike", startAt, endAt);

        Activity activity = ActivityPersistenceMapper.toDomain(entity);

        assertEquals(activityId, activity.getId());
        assertEquals("Morning Hike", activity.getTitle());
        assertEquals("Mountain Trail", activity.getLocation());
        assertEquals("A beautiful morning hike", activity.getDescription());
        assertEquals(planner.getId(), activity.getPlanner());
        assertNotNull(activity.getParticipants());
        assertTrue(activity.getParticipants().isEmpty());
        assertEquals(startAt, activity.getTimeslot().start());
        assertEquals(endAt, activity.getTimeslot().end());
    }

    @Test
    void shouldMapEntityWithNullableFieldsToDomain() {
        UUID activityId = UUID.randomUUID();
        UUID eventId = UUID.randomUUID();
        UUID plannerId = UUID.randomUUID();
        LocalDateTime startAt = LocalDateTime.of(2025, 7, 15, 8, 0);
        LocalDateTime endAt = LocalDateTime.of(2025, 7, 15, 12, 0);

        EventEntity event = new EventEntity(eventId, "Event");
        EventParticipantEntity planner = new EventParticipantEntity(UUID.randomUUID(), eventId, plannerId);

        ActivityEntity entity = new ActivityEntity(activityId, event, planner, "Title",
                null, null, startAt, endAt);

        Activity activity = ActivityPersistenceMapper.toDomain(entity);

        assertNull(activity.getLocation());
        assertNull(activity.getDescription());
    }
}
