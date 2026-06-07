package com.moments.modules.program.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class ActivityTest {

    private static final UUID ID = UUID.randomUUID();
    private static final UUID PLANNER_ID = UUID.randomUUID();
    private static final UUID PARTICIPANT_ID = UUID.randomUUID();
    private static final TimeSlot TIMESLOT = new TimeSlot(
            LocalDateTime.of(2025, 7, 15, 8, 0),
            LocalDateTime.of(2025, 7, 15, 12, 0));

    @Test
    void shouldCreateActivityWithAllFields() {
        Set<UUID> participants = Set.of(PARTICIPANT_ID);

        Activity activity = new Activity(ID, "Hiking", "Mountain", "A hike", PLANNER_ID, participants, TIMESLOT);

        assertEquals(ID, activity.getId());
        assertEquals("Hiking", activity.getTitle());
        assertEquals("Mountain", activity.getLocation());
        assertEquals("A hike", activity.getDescription());
        assertEquals(PLANNER_ID, activity.getPlanner());
        assertEquals(participants, activity.getParticipants());
        assertEquals(TIMESLOT, activity.getTimeslot());
    }

    @Test
    void shouldInitializeEmptyParticipantsWhenNull() {
        Activity activity = new Activity(ID, "Hiking", "Mountain", "A hike", PLANNER_ID, null, TIMESLOT);

        assertNotNull(activity.getParticipants());
        assertTrue(activity.getParticipants().isEmpty());
    }

    @Test
    void shouldRenameWithValidTitle() {
        Activity activity = new Activity(ID, "Old Title", "Location", "Desc", PLANNER_ID, null, TIMESLOT);

        activity.rename("New Title");

        assertEquals("New Title", activity.getTitle());
    }

    @Test
    void shouldThrowWhenRenamingWithNullTitle() {
        Activity activity = new Activity(ID, "Title", "Location", "Desc", PLANNER_ID, null, TIMESLOT);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> activity.rename(null));
        assertTrue(ex.getMessage().contains("null or empty"));
    }

    @Test
    void shouldThrowWhenRenamingWithEmptyTitle() {
        Activity activity = new Activity(ID, "Title", "Location", "Desc", PLANNER_ID, null, TIMESLOT);

        assertThrows(IllegalArgumentException.class, () -> activity.rename(""));
    }

    @Test
    void shouldThrowWhenRenamingWithBlankTitle() {
        Activity activity = new Activity(ID, "Title", "Location", "Desc", PLANNER_ID, null, TIMESLOT);

        assertThrows(IllegalArgumentException.class, () -> activity.rename("   "));
    }
}
