import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from 'src/app/models/News';

@Injectable({
  providedIn: 'root'
})
export class ListanotizieService {
  private apiUrl = 'http://localhost:8080/api/news/get-all';

  constructor(private http: HttpClient) {
        this.http=http;
   }

  getNotizie(): Observable<News[]> {
    return this.http.get<News[]>(this.apiUrl);
  }

  getNotiziaById(id: number): Observable<News> {
    const url = `http://localhost:8080/api/news/${id}`;
    return this.http.get<News>(url);
  }
}
