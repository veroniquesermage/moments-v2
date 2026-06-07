package com.moments.modules.program.application.useCase;

import com.moments.modules.program.application.ActivityRepository;
import com.moments.modules.program.application.EventProvider;
import com.moments.modules.program.application.PersonProvider;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateActivityUseCase {

    private final ActivityRepository activityRepository;
    private final EventProvider eventProvider;
    private final PersonProvider personProvider;

    public CreateActivityUseCase(ActivityRepository activityRepository, EventProvider eventProvider,
            PersonProvider personProvider) {
        this.activityRepository = activityRepository;
        this.eventProvider = eventProvider;
        this.personProvider = personProvider;
    }

}
