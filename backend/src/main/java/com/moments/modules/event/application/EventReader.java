package com.moments.modules.event.application;

import java.util.UUID;

import com.moments.modules.event.domain.Event;

public interface EventReader {

    Event getById(UUID eventId);

}
