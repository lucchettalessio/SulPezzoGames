import { Component, OnInit } from '@angular/core';
import { VideogiochiService } from './videogiochi.service';
import { Videogioco } from 'src/app/models/Videogioco';
import { ListarecensioniComponent } from '../listarecensioni/listarecensioni.component';
import { Router, RouterModule } from '@angular/router';
import { EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-listavideogiochi',
  templateUrl: './listavideogiochi.component.html',
  styleUrls: ['./listavideogiochi.component.css']
})
export class ListavideogiochiComponent implements OnInit {
  videogiochi: Videogioco[] = [];
  tipo_utente?: string;
  rowsPerPage: number = 4; 
  currentPage: number = 1;
  totalPages: number | undefined;
  @Output() filterRecensioniEvent = new EventEmitter<number>();

  constructor(private videogiochiService: VideogiochiService, private router : Router) { }

  ngOnInit(): void {
    this.getVideogiochi();
    var token = sessionStorage.getItem("token");
    this.tipo_utente = token?.split("-")[0];
  }

  getVideogiochi(): void {
    this.videogiochiService.getVideogiochi()
      .subscribe(videogiochi => {
        this.videogiochi = videogiochi;
        this.totalPages = Math.ceil(this.videogiochi.length / this.rowsPerPage);
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

  openRecensioni(idVideogioco: number) {
    this.filterRecensioniEvent.emit(idVideogioco);
  }

}
