import { Component, OnInit } from '@angular/core';
import { News } from 'src/app/models/News';
import { ListanotizieService } from './listanotizie.service';

@Component({
  selector: 'app-listanotizie',
  templateUrl: './listanotizie.component.html',
  styleUrls: ['./listanotizie.component.css']
})
export class ListanotizieComponent implements OnInit {
  notizie: News[] = [];

  constructor(private listanotizieService: ListanotizieService) { }

  ngOnInit(): void {
    this.caricaNotizie();
  }

  caricaNotizie(): void {
    this.listanotizieService.getNotizie()
      .subscribe(notizie => {
        this.notizie = notizie;
      });
  }
}


