package com.moments.modules.program.application;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.moments.modules.program.presentation.response.ParticipantResponse;

public interface PersonProvider {

    ParticipantResponse getPersonById(UUID personId);

    Map<UUID, ParticipantResponse> getPersonsByIds(Set<UUID> personIds);

}
