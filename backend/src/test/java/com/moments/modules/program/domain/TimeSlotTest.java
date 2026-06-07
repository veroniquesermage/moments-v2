package com.moments.modules.program.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TimeSlotTest {

    @Test
    void shouldCreateValidTimeSlot() {
        LocalDateTime start = LocalDateTime.of(2025, 7, 15, 8, 0);
        LocalDateTime end = LocalDateTime.of(2025, 7, 15, 12, 0);

        TimeSlot timeSlot = new TimeSlot(start, end);

        assertEquals(start, timeSlot.start());
        assertEquals(end, timeSlot.end());
    }

    @Test
    void shouldThrowWhenEndIsBeforeStart() {
        LocalDateTime start = LocalDateTime.of(2025, 7, 15, 12, 0);
        LocalDateTime end = LocalDateTime.of(2025, 7, 15, 8, 0);

        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(start, end));
    }

    @Test
    void shouldThrowWhenEndEqualsStart() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 7, 15, 8, 0);

        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(dateTime, dateTime));
    }

    @Test
    void shouldThrowWhenStartIsNull() {
        LocalDateTime end = LocalDateTime.of(2025, 7, 15, 12, 0);

        assertThrows(NullPointerException.class, () -> new TimeSlot(null, end));
    }

    @Test
    void shouldThrowWhenEndIsNull() {
        LocalDateTime start = LocalDateTime.of(2025, 7, 15, 8, 0);

        assertThrows(NullPointerException.class, () -> new TimeSlot(start, null));
    }
}
