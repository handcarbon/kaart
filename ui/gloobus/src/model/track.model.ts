export interface Track {
  trackId: number;
  name: string;
  waypoints: TrackWaypoint[];
}

export interface TrackWaypoint {
  latitude: number;
  longitude: number;
  elevation: number;
  time: Date;
}
