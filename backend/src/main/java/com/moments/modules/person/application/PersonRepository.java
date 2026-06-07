package com.moments.modules.person.application;

import java.util.Set;
import java.util.UUID;

import com.moments.modules.person.infra.PersonEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface PersonRepository {

    PersonEntity findById(UUID personId);

    Set<PersonEntity> findByIds(Set<UUID> personIds);

}
