package eu.vomanor.gloobus.repository;


import eu.vomanor.gloobus.domain.TrackWaypoint;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TrackWaypointRepository extends PageableRepository<TrackWaypoint, Long> {

    List<TrackWaypoint> findByTrackId(long trackId);
}
