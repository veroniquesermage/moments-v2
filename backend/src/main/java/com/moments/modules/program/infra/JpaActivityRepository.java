package com.moments.modules.program.infra;

import java.util.Optional;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import com.moments.core.exception.ResourceNotFoundException;
import com.moments.modules.program.application.ActivityRepository;
import com.moments.modules.program.domain.Activity;

@ApplicationScoped
public class JpaActivityRepository implements ActivityRepository {

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public Activity save(Activity activity) {
        return null;
    }

    @Override
    @Transactional
    public Activity findById(UUID id) {
        ActivityEntity activityEntity = Optional
            .ofNullable(entityManager.find(ActivityEntity.class, id))
            .orElseThrow(() -> new ResourceNotFoundException("Activity", id));

        return ActivityPersistenceMapper.toDomain(activityEntity);
    }
}
