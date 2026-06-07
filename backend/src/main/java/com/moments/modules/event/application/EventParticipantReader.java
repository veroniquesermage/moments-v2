package com.moments.modules.event.application;

import java.util.List;
import java.util.UUID;

import com.moments.modules.event.domain.EventParticipant;

public interface EventParticipantReader {

    List<EventParticipant> getParticipantsByEventId(UUID eventId);
}
