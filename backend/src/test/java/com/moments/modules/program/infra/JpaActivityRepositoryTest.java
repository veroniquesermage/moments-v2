package com.moments.modules.program.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.moments.core.exception.ResourceNotFoundException;
import com.moments.modules.program.domain.Activity;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class JpaActivityRepositoryTest {

    @Inject
    JpaActivityRepository repository;

    private static final UUID EXISTING_ACTIVITY_ID = UUID.fromString("40000000-0000-0000-0000-000000000001");
    private static final UUID NON_EXISTING_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");

    @Test
    void shouldFindActivityById() {
        Activity activity = repository.findById(EXISTING_ACTIVITY_ID);

        assertNotNull(activity);
        assertEquals(EXISTING_ACTIVITY_ID, activity.getId());
        assertEquals("Morning Hike", activity.getTitle());
        assertEquals("Mountain Trail", activity.getLocation());
        assertEquals("A beautiful morning hike", activity.getDescription());
        assertNotNull(activity.getTimeslot());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionForUnknownId() {
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> repository.findById(NON_EXISTING_ID));

        assertEquals("Activity", ex.getResource());
        assertEquals(NON_EXISTING_ID, ex.getId());
    }
}
