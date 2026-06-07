CREATE TABLE account (
    id UUID PRIMARY KEY
);

CREATE TABLE person (
    id UUID PRIMARY KEY,
    account_id UUID,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE event (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE event_participant (
    id UUID PRIMARY KEY,
    event_id UUID NOT NULL,
    person_id UUID NOT NULL,
    CONSTRAINT uk_event_participant_event_person
        UNIQUE (event_id, person_id)
);

CREATE TABLE program_activity (
    id UUID PRIMARY KEY,
    event_id UUID NOT NULL,
    planner_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    description TEXT,
    start_at TIMESTAMP NOT NULL,
    end_at TIMESTAMP NOT NULL,
    CONSTRAINT ck_program_activity_period
        CHECK (end_at > start_at)
);

CREATE TABLE activity_participant (
    id UUID PRIMARY KEY,
    activityId UUID NOT NULL,
    participantId UUID NOT NULL,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT uk_activity_participant_activity_participant
        UNIQUE (activityId, participantId)
);

CREATE INDEX idx_person_account_id
    ON person (account_id);

CREATE INDEX idx_event_participant_event_id
    ON event_participant (event_id);

CREATE INDEX idx_event_participant_person_id
    ON event_participant (person_id);

CREATE INDEX idx_program_activity_event_id
    ON program_activity (event_id);

CREATE INDEX idx_program_activity_planner_id
    ON program_activity (planner_id);

CREATE INDEX idx_activity_participant_activity_id
    ON activity_participant (activityId);

CREATE INDEX idx_activity_participant_participant_id
    ON activity_participant (participantId);
