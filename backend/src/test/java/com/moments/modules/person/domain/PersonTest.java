package com.moments.modules.person.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    void shouldCreatePersonWithAllFields() {
        UUID id = UUID.randomUUID();

        Person person = new Person(id, "Doe", "John");

        assertEquals(id, person.getId());
        assertEquals("Doe", person.getLastName());
        assertEquals("John", person.getFirstName());
    }
}
