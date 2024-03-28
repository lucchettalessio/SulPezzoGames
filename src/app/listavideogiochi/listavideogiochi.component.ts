import { Component, OnInit } from '@angular/core';
import { VideogiochiService } from './videogiochi.service';
import { Videogioco } from 'src/app/models/Videogioco';

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

  constructor(private videogiochiService: VideogiochiService) { }

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
}
