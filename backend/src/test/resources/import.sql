-- Test data
INSERT INTO
    account (id)
VALUES (
        '00000000-0000-0000-0000-000000000001'
    );

INSERT INTO
    person (
        id,
        account_id,
        first_name,
        last_name
    )
VALUES (
        '10000000-0000-0000-0000-000000000001',
        '00000000-0000-0000-0000-000000000001',
        'John',
        'Doe'
    );

INSERT INTO
    person (
        id,
        account_id,
        first_name,
        last_name
    )
VALUES (
        '10000000-0000-0000-0000-000000000002',
        '00000000-0000-0000-0000-000000000001',
        'Jane',
        'Smith'
    );

INSERT INTO
    event (id, title)
VALUES (
        '20000000-0000-0000-0000-000000000001',
        'Summer Camp'
    );

INSERT INTO
    event_participant (id, event_id, person_id)
VALUES (
        '30000000-0000-0000-0000-000000000001',
        '20000000-0000-0000-0000-000000000001',
        '10000000-0000-0000-0000-000000000001'
    );

INSERT INTO
    event_participant (id, event_id, person_id)
VALUES (
        '30000000-0000-0000-0000-000000000002',
        '20000000-0000-0000-0000-000000000001',
        '10000000-0000-0000-0000-000000000002'
    );

INSERT INTO
    program_activity (
        id,
        event_id,
        planner_id,
        title,
        location,
        description,
        start_at,
        end_at
    )
VALUES (
        '40000000-0000-0000-0000-000000000001',
        '20000000-0000-0000-0000-000000000001',
        '10000000-0000-0000-0000-000000000001',
        'Morning Hike',
        'Mountain Trail',
        'A beautiful morning hike',
        '2025-07-15 08:00:00',
        '2025-07-15 12:00:00'
    );

INSERT INTO
    activity_participant (
        id,
        activityId,
        participantId,
        status
    )
VALUES (
        '50000000-0000-0000-0000-000000000001',
        '40000000-0000-0000-0000-000000000001',
        '10000000-0000-0000-0000-000000000002',
        'CONFIRMED'
    );
