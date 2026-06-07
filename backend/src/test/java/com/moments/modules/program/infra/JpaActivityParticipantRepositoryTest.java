package com.moments.modules.program.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class JpaActivityParticipantRepositoryTest {

    @Inject
    JpaActivityParticipantRepository repository;

    private static final UUID EXISTING_ACTIVITY_ID = UUID.fromString("40000000-0000-0000-0000-000000000001");
    private static final UUID UNKNOWN_ACTIVITY_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");
    private static final UUID EXPECTED_PARTICIPANT_ID = UUID.fromString("10000000-0000-0000-0000-000000000002");

    @Test
    void shouldFindParticipantIdsByActivityId() {
        List<UUID> participantIds = repository.findParticipantIdsByActivityId(EXISTING_ACTIVITY_ID);

        assertNotNull(participantIds);
        assertEquals(1, participantIds.size());
        assertTrue(participantIds.contains(EXPECTED_PARTICIPANT_ID));
    }

    @Test
    void shouldReturnEmptyListForUnknownActivity() {
        List<UUID> participantIds = repository.findParticipantIdsByActivityId(UNKNOWN_ACTIVITY_ID);

        assertNotNull(participantIds);
        assertTrue(participantIds.isEmpty());
    }
}
