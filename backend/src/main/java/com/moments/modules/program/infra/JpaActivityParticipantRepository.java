package com.moments.modules.program.infra;

import java.util.List;
import java.util.UUID;

import com.moments.modules.program.application.ActivityParticipantRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class JpaActivityParticipantRepository implements ActivityParticipantRepository {

    public final EntityManager entityManager;

    public JpaActivityParticipantRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UUID> findParticipantIdsByActivityId(UUID activityId) {
        return entityManager
            .createQuery("""
                SELECT a.participantId
                FROM ActivityParticipantEntity a
                WHERE a.activityId = :activityId
            """, UUID.class)
            .setParameter("activityId", activityId)
            .getResultList();
    }


}
