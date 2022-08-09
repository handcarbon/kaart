package eu.vomanor.gloobus.service;

import eu.vomanor.gloobus.dto.TrackDto;
import eu.vomanor.gloobus.dto.TrackWaypointDto;
import eu.vomanor.gloobus.repository.TrackRepository;
import eu.vomanor.gloobus.repository.TrackWaypointRepository;
import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Singleton
public class TrackService {

    private final TrackRepository trackRepository;
    private final TrackWaypointRepository waypointRepository;

    public TrackService(TrackRepository trackRepository,
                        TrackWaypointRepository waypointRepository) {
        this.trackRepository = trackRepository;
        this.waypointRepository = waypointRepository;
    }

    public TrackDto getTrack(long id) {
        var track = trackRepository.findById(id).orElseThrow();

        var waypointsDto = waypointRepository.findByTrackId(id)
                .stream()
                .map(t -> new TrackWaypointDto(t.getLongitude(), t.getLatitude(), t.getElevation(), t.getTime()))
                .toList();

        return new TrackDto(track.getId(), track.getName(), waypointsDto);
    }

    public List<TrackDto> getTracks() {
        return StreamSupport.stream(trackRepository.findAll().spliterator(), false)
                .map(t -> new TrackDto(t.getId(), t.getName(), Collections.emptyList()))
                .toList();
    }
}
