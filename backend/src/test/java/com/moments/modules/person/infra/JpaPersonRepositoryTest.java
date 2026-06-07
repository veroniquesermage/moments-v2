package com.moments.modules.person.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class JpaPersonRepositoryTest {

    @Inject
    JpaPersonRepository repository;

    private static final UUID PERSON_1_ID = UUID.fromString("10000000-0000-0000-0000-000000000001");
    private static final UUID PERSON_2_ID = UUID.fromString("10000000-0000-0000-0000-000000000002");
    private static final UUID UNKNOWN_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");

    @Test
    void shouldFindPersonById() {
        PersonEntity person = repository.findById(PERSON_1_ID);

        assertNotNull(person);
        assertEquals(PERSON_1_ID, person.getId());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
    }

    @Test
    void shouldReturnNullForUnknownPerson() {
        PersonEntity person = repository.findById(UNKNOWN_ID);

        assertNull(person);
    }

    @Test
    void shouldFindPersonsByIds() {
        Set<PersonEntity> persons = repository.findByIds(Set.of(PERSON_1_ID, PERSON_2_ID));

        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    void shouldReturnEmptySetForNoMatchingIds() {
        Set<PersonEntity> persons = repository.findByIds(Set.of(UNKNOWN_ID));

        assertNotNull(persons);
        assertTrue(persons.isEmpty());
    }
}
