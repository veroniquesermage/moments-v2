package com.moments.modules.person.application;

import java.util.Set;
import java.util.UUID;

import com.moments.modules.program.application.PersonInfo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface PersonReader {

    PersonInfo getById(UUID personId);

    Set<PersonInfo> getByIds(Set<UUID> personIds);

}
