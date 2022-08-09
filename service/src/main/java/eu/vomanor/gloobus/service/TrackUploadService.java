package eu.vomanor.gloobus.service;

import eu.vomanor.gloobus.domain.Device;
import eu.vomanor.gloobus.domain.TrackWaypoint;
import eu.vomanor.gloobus.repository.DeviceRepository;
import eu.vomanor.gloobus.repository.TrackRepository;
import eu.vomanor.gloobus.repository.TrackWaypointRepository;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Length;
import io.jenetics.jpx.Track;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Singleton
public class TrackUploadService {

    private final DeviceRepository deviceRepository;
    private final TrackRepository trackRepository;
    private final TrackWaypointRepository waypointRepository;

    public TrackUploadService(DeviceRepository deviceRepository,
                              TrackRepository trackRepository,
                              TrackWaypointRepository waypointRepository) {
        this.deviceRepository = deviceRepository;
        this.trackRepository = trackRepository;
        this.waypointRepository = waypointRepository;
    }

    public void create(String name) {
        var group = new Device();
        group.setName(name);
        group.setExternalId(UUID.randomUUID().toString());
        deviceRepository.save(group);
    }

    public void upload(CompletedFileUpload file) throws IOException {
        var bytes = file.getBytes();
        var xml = new String(bytes, StandardCharsets.UTF_8);

        var gpx = GPX.Reader.DEFAULT.fromString(xml);

        gpx.tracks().forEach(this::createTrack);
    }

    private void createTrack(Track track) {
        var t = new eu.vomanor.gloobus.domain.Track();
        track.getName().ifPresent(t::setName);
        t.setCreatedAt(Instant.now());
        t.setUpdatedAt(Instant.now());

        trackRepository.save(t);

        var waypoints = track.getSegments().stream()
                .flatMap(s -> s.getPoints().stream())
                .map(p -> {
                    var tp = new TrackWaypoint();
                    tp.setTrackId(t.getId());
                    tp.setLatitude(p.getLatitude().doubleValue());
                    tp.setLongitude(p.getLongitude().doubleValue());
                    tp.setCreatedAt(Instant.now());
                    tp.setUpdatedAt(Instant.now());

                    p.getElevation().ifPresent(l -> tp.setElevation(l.to(Length.Unit.METER)));
                    p.getTime().ifPresent(tp::setTime);

                    return tp;
                })
                .toList();

        waypointRepository.saveAll(waypoints);
    }
}
