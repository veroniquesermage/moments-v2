package com.moments.modules.person.infra.outboundAdapter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.moments.modules.person.application.PersonReader;
import com.moments.modules.person.application.PersonRepository;
import com.moments.modules.person.infra.PersonEntity;
import com.moments.modules.program.application.PersonInfo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonAdapter implements PersonReader {

    private final PersonRepository personRepository;

    public PersonAdapter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonInfo getById(UUID personId) {
        // Implement the logic to fetch the person by ID
        PersonEntity personEntity = personRepository.findById(personId);

        return new PersonInfo(
            personEntity.getId(),
            personEntity.getLastName(),
            personEntity.getFirstName()
        );
    }

    @Override
    public Set<PersonInfo> getByIds(Set<UUID> personIds) {
        // Implement the logic to fetch multiple persons by their IDs
        Set<PersonEntity> personEntities = personRepository.findByIds(personIds);

        return personEntities.stream()
            .map(personEntity -> new PersonInfo(
                personEntity.getId(),
                personEntity.getLastName(),
                personEntity.getFirstName()
            ))
            .collect(Collectors.toSet());
    }



}
