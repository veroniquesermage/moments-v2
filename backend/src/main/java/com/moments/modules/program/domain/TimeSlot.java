package com.moments.modules.program.domain;

import java.time.LocalDateTime;

public record TimeSlot(
    LocalDateTime start,
    LocalDateTime end
) {
    public TimeSlot {
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException();
        }
    }
}
