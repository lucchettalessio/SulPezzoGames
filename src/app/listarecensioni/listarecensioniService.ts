import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recensione } from '../models/Recensione';
import { Utente } from '../models/Utente';


@Injectable({
providedIn: 'root'
})
export class ListaRecensioniService {

constructor(private http: HttpClient) {
        this.http=http;
}

getRecensione(): Observable<Recensione[]> {
    return this.http.get<Recensione[]>('http://localhost:8080/api/Recensione/get-all');
}

getRecensioneById(id: number): Observable<Recensione> {
    const url = `http://localhost:8080/api/Recensione/${id}`;
    return this.http.get<Recensione>(url);
}

getAutori(): Observable<Utente[]> {
  const url = `http://localhost:8080/api/Recensione/autori`;
  return this.http.get<Utente[]>(url);
}

getRecensioniByVideogioco(videogiocoId: number): Observable<Recensione[]> {
  const url = `http://localhost:8080/api/Recensione/get-all/videogioco/${videogiocoId}`;
  return this.http.get<Recensione[]>(url);
}
}
