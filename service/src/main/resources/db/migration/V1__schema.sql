CREATE TABLE device
(
    id          SERIAL PRIMARY KEY       NOT NULL,
    name        VARCHAR(255)             NOT NULL,
    external_id VARCHAR(255)             NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE track
(
    id         SERIAL PRIMARY KEY       NOT NULL,
    name       VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE track_waypoint
(
    id         SERIAL PRIMARY KEY           NOT NULL,
    track_id   BIGINT REFERENCES track (id) NOT NULL,
    elevation  DOUBLE PRECISION,
    longitude  DOUBLE precision             NOT NULL,
    latitude   DOUBLE PRECISION             NOT NULL,
    time       TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE     NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE     NOT NULL DEFAULT NOW()
);

CREATE INDEX ON track_waypoint (track_id);