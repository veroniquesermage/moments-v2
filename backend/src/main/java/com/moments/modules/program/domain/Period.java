package com.moments.modules.program.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Period {

    public UUID id;
    public LocalDate startDate;
    public LocalDate endDate;

    public Period(UUID id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


}
