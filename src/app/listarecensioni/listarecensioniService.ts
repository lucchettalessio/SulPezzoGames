import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recensione } from '../models/Recensione';


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
}
