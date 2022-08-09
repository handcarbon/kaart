package eu.vomanor.gloobus.repository;

import eu.vomanor.gloobus.domain.Track;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TrackRepository extends PageableRepository<Track, Long> {

}
