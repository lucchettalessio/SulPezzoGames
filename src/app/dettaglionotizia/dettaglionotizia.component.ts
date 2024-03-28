import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from '../models/News';

@Component({
  selector: 'app-dettaglionotizia',
  templateUrl: './dettaglionotizia.component.html',
  styleUrls: ['./dettaglionotizia.component.css']
})
export class DettaglionotiziaComponent implements OnInit {
  news: News | undefined;
  private apiUrl = 'http://localhost:8080/api/news/get-all';

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.caricaDettaglioNotizia();
  }

  caricaDettaglioNotizia(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) {
      this.router.navigate(['/notizie']);
      return;
    }

    this.getNotiziaById(+id).subscribe(
      news => {
        this.news = news;
      },
      error => {
        console.error('Errore durante il recupero della notizia:', error);
      }
    );
  }

  getNotiziaById(id: number): Observable<News> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<News>(url);
  }
}


