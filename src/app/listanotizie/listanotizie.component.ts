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
  tipo_utente?: string;
  rowsPerPage: number = 4; 
  currentPage: number = 1;
  totalPages: number | undefined;

  constructor(private listanotizieService: ListanotizieService) { }

  ngOnInit(): void {
    this.caricaNotizie();
    var token = sessionStorage.getItem("token");
    this.tipo_utente = token?.split("-")[0];
  }

  caricaNotizie(): void {
    this.listanotizieService.getNotizieOrdered()
      .subscribe(notizie => {
        this.notizie = notizie;
        this.totalPages = Math.ceil(this.notizie.length / this.rowsPerPage);
      });
  }
  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages!) {
      this.currentPage++;
    }
    else {
      this.currentPage = 1; 
    }

  }
}


