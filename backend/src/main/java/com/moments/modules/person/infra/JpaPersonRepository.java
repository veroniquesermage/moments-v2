package com.moments.modules.person.infra;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.moments.modules.person.application.PersonRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class JpaPersonRepository implements PersonRepository {

      public final EntityManager entityManager;

    public JpaPersonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public PersonEntity findById(UUID personId) {
        return entityManager.find(PersonEntity.class, personId);
    }

    @Override
    public Set<PersonEntity> findByIds(Set<UUID> personIds) {
        return entityManager.createQuery("SELECT p FROM PersonEntity p WHERE p.id IN :ids", PersonEntity.class)
            .setParameter("ids", personIds)
            .getResultList()
            .stream()
            .collect(Collectors.toSet());
    }



}
