CREATE TABLE venue
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    address        VARCHAR(255) NOT NULL,
    total_capacity BIGINT       NOT NULL
);

CREATE TABLE events
(
    id             BIGSERIAL PRIMARY KEY,
    venue_id       BIGINT       NOT NULL,
    name           VARCHAR(255) NOT NULL,
    total_capacity BIGINT       NOT NULL,
    left_capacity  BIGINT       NOT NULL,
    CONSTRAINT fk_event_venue FOREIGN KEY (venue_id) REFERENCES venue (id) ON DELETE CASCADE
);