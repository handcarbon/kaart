package eu.vomanor.gloobus.repository;

import eu.vomanor.gloobus.domain.Device;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface DeviceRepository extends PageableRepository<Device, Long> {

}
