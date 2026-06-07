package com.moments.modules.program.application;

import java.util.UUID;

import com.moments.modules.program.domain.Activity;

public interface ActivityRepository {

    Activity save(Activity activity);

    Activity findById(UUID id);

}
