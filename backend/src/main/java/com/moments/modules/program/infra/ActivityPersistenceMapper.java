package com.moments.modules.program.infra;

import java.util.HashSet;

import com.moments.modules.program.domain.Activity;
import com.moments.modules.program.domain.TimeSlot;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActivityPersistenceMapper {


    public static Activity toDomain(ActivityEntity entity) {
        return new Activity(
                entity.id,
                entity.title,
                entity.location,
                entity.description,
                entity.plannerId,
                new HashSet<>(),
                new TimeSlot(entity.startAt, entity.endAt));
    }

}
