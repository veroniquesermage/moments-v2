package com.moments.modules.program.application;

import java.util.UUID;

public record PersonInfo(
    UUID id,
    String lastName,
    String firstName
) {

}
