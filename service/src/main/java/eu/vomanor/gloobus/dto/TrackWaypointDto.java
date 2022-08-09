package eu.vomanor.gloobus.dto;

import java.time.Instant;

public record TrackWaypointDto(double longitude, double latitude, double elevation, Instant time) {
}
