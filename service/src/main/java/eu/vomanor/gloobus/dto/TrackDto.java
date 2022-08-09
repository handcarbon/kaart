package eu.vomanor.gloobus.dto;

import java.util.List;

public record TrackDto(long trackId, String name, List<TrackWaypointDto> waypoints) {
}
