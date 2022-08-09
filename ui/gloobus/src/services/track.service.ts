import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Track} from '../model/track.model';

@Injectable({
  providedIn: 'root'
})
export class TrackService {

  // TODO env host
  private readonly apiUrl = `http://localhost:8080/api/v1/track`;

  constructor(private http: HttpClient) {
  }

  getTrack(id: number): Observable<Track> {
    const url: string = `${this.apiUrl}/${id}`;

    return this.http.get<Track>(url);
  }

  getTracks(): Observable<Track[]> {
    const url: string = `${this.apiUrl}`;

    return this.http.get<Track[]>(url);
  }

  upload(file: File): Observable<string> {
    const url: string = `${this.apiUrl}/upload`;

    const formData = new FormData();
    formData.append("file", file, file.name);

    return this.http.post<string>(url, formData)
  }
}

