package com.moments.modules.program.infra.inboundAdapter;

import java.util.UUID;

import com.moments.modules.event.application.EventReader;
import com.moments.modules.program.application.EventProvider;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventAdapter implements EventProvider {

    private final EventReader eventReader;

    public EventAdapter(EventReader eventReader) {
        this.eventReader = eventReader;
    }


}
