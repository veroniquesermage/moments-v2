package com.moments.modules.program.application;

import java.util.List;
import java.util.UUID;


public interface ActivityParticipantRepository {

    List<UUID> findParticipantIdsByActivityId(UUID activityId);

}
