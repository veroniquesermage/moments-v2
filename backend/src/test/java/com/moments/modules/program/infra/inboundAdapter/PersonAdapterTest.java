package com.moments.modules.program.infra.inboundAdapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.moments.modules.program.presentation.response.ParticipantResponse;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class PersonAdapterTest {

    @Inject
    PersonAdapter personAdapter;

    private static final UUID PERSON_1_ID = UUID.fromString("10000000-0000-0000-0000-000000000001");
    private static final UUID PERSON_2_ID = UUID.fromString("10000000-0000-0000-0000-000000000002");

    @Test
    void shouldGetPersonById() {
        ParticipantResponse response = personAdapter.getPersonById(PERSON_1_ID);

        assertNotNull(response);
        assertEquals(PERSON_1_ID, response.getId());
        assertEquals("Doe", response.getLastName());
        assertEquals("John", response.getFirstName());
    }

    @Test
    void shouldGetPersonsByIds() {
        Map<UUID, ParticipantResponse> persons = personAdapter.getPersonsByIds(Set.of(PERSON_1_ID, PERSON_2_ID));

        assertNotNull(persons);
        assertEquals(2, persons.size());
        assertTrue(persons.containsKey(PERSON_1_ID));
        assertTrue(persons.containsKey(PERSON_2_ID));

        assertEquals("Doe", persons.get(PERSON_1_ID).getLastName());
        assertEquals("Smith", persons.get(PERSON_2_ID).getLastName());
    }
}
