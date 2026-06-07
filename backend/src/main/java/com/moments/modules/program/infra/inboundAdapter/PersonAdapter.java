package com.moments.modules.program.infra.inboundAdapter;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import jakarta.enterprise.context.ApplicationScoped;

import com.moments.modules.person.application.PersonReader;
import com.moments.modules.program.application.PersonInfo;
import com.moments.modules.program.application.PersonProvider;
import com.moments.modules.program.presentation.response.ParticipantResponse;

@ApplicationScoped
public class PersonAdapter implements PersonProvider {

    public final PersonReader personReader;

    public PersonAdapter(PersonReader personReader) {
        this.personReader = personReader;
    }

    @Override
    public ParticipantResponse getPersonById(UUID personId) {

        PersonInfo person = personReader.getById(personId);
        return new ParticipantResponse(
                person.id(),
                person.lastName(),
                person.firstName()
        );
    }

    @Override
    public Map<UUID, ParticipantResponse> getPersonsByIds(Set<UUID> personIds) {
        Set<PersonInfo> persons = personReader.getByIds(personIds);
        return persons.stream()
                .collect(Collectors.toMap(PersonInfo::id, person -> new ParticipantResponse(
                        person.id(),
                        person.lastName(),
                        person.firstName()
                )));
    }
}
