import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Videogioco } from 'src/app/models/Videogioco';

@Injectable({
  providedIn: 'root'
})
export class VideogiochiService {
  private apiUrl = 'http://localhost:8080/api/Videogioco/get-all';

  constructor(private http: HttpClient) { }

  getVideogiochi(): Observable<Videogioco[]> {
    return this.http.get<Videogioco[]>(this.apiUrl);
  }
}