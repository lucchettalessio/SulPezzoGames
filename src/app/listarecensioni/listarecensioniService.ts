import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recensione } from '../models/Recensione';
import { Utente } from '../models/Utente';


@Injectable({
providedIn: 'root'
})
export class ListaRecensioniService {
private apiUrl = 'http://localhost:8080/api/Recensione/get-all';

constructor(private http: HttpClient) {
        this.http=http;
}

getRecensione(): Observable<Recensione[]> {
    return this.http.get<Recensione[]>(this.apiUrl);
}

getRecensioneById(id: number): Observable<Recensione> {
    const url = `http://localhost:8080/api/Recensione/${id}`;
    return this.http.get<Recensione>(url);
}

getAutori(): Observable<Utente[]> {
  const url = `http://localhost:8080/api/Recensione/autori`;
  return this.http.get<Utente[]>(url);
}
}
