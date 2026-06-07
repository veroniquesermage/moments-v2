package com.moments.modules.program.presentation.response;

import java.util.UUID;

public class ParticipantResponse {

    private UUID id;
    private String lastName;
    private String firstName;
    private String status;

    public ParticipantResponse(UUID id, String lastName, String firstName) {
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

    public String getStatus() {
        return status;
    }
}
