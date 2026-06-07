package com.moments.modules.person.domain;

import java.util.UUID;

public class Person {

    private UUID id;
    private String lastName;
    private String firstName;

    public Person(UUID id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public UUID getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
