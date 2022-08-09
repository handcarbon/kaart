import {Component, OnInit} from '@angular/core';

import * as L from 'leaflet';
import {TrackService} from "../services/track.service";
import {Track} from "../model/track.model";
import {LayerGroup} from "leaflet";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'gloobus';

  map!: L.Map;
  layerGroup!: LayerGroup;

  tracks: Track[] = [];

  constructor(private trackService: TrackService) {
  }

  ngOnInit(): void {
    this.loadTracks();
    this.loadMap();
  }

  private loadTracks() {
    this.trackService.getTracks().subscribe((data: Track[]) => {
      this.tracks = data;
    });
  }

  public renderTrack(id: number) {
    this.layerGroup.clearLayers();

    this.trackService.getTrack(id).subscribe((data: Track) => {
      const pointList = [];

      for (const p of data.waypoints) {
        pointList.push(new L.LatLng(p.latitude, p.longitude))
      }

      const firstpolyline = new L.Polyline(pointList, {
        color: 'red',
        weight: 3,
        opacity: 0.5,
        smoothFactor: 1
      });
      firstpolyline.addTo(this.layerGroup);

      const startFlag = L.divIcon({
        html: '<i class="fa fa-map-marker fa-2x"></i>',
        iconSize: [20, 20],
        className: 'icon-blue'
      });

      const startPoint = pointList[0];
      this.map.setView([startPoint.lat, startPoint.lng], 13);

      L.marker([startPoint.lat, startPoint.lng], {icon: startFlag}).addTo(this.layerGroup);

      const endFlag = L.divIcon({
        html: '<i class="fa fa-map-marker fa-2x"></i>',
        iconSize: [20, 20],
        className: 'icon-red'
      });

      const lastPoint = pointList[pointList.length - 1];
      L.marker([lastPoint.lat, lastPoint.lng], {icon: endFlag}).addTo(this.layerGroup);
    });
  }

  private loadMap() {
    this.map = L.map('map').setView([59.405, 24.109], 9);

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 15,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(this.map);

    this.layerGroup = L.layerGroup().addTo(this.map);
  }

  reload() {
    this.loadTracks();
  }
}
