import { Component, OnInit } from '@angular/core';
import { VideogiochiService } from './videogiochi.service';
import { Videogioco } from 'src/app/models/Videogioco';
import { ListarecensioniComponent } from '../listarecensioni/listarecensioni.component';
import { Router, RouterModule } from '@angular/router';
import { EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

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
  isDeleting: boolean = false;
  deletingId: number = -1;

  isModifying: boolean = false;
  modifyingId: number = -1;
  formModifica! : FormGroup;

  isInserting: boolean = false;
  formInsert! : FormGroup;
  @Output() filterRecensioniEvent = new EventEmitter<number>();

  constructor(private videogiochiService: VideogiochiService, private router : Router, private http : HttpClient) { }

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

  elimina(id: number){
    this.isModifying = false;
    this.isInserting = false;
    this.isDeleting = true;
    this.deletingId = id;
  }

  confermaElimina(id: number){
    var token = sessionStorage.getItem("token");
    if(token == null){
      token = "";
    }

    const headers = new HttpHeaders(
      {
        'Content-type' : 'application/json',
        'token': token as string
      }
    );

    const params = new HttpParams().set("id", id);

    this.http.get("http://localhost:8080/api/Videogioco/delete", {headers, params}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Eliminazione avvenuta con successo")
        // window.location.href = "/areadirigenti"
        var indice = this.videogiochi?.findIndex(x => x.id === id)
        console.log("indice", indice)
        if(indice! > -1){
          this.videogiochi?.splice(indice!, 1);
        }
      }

    })

    this.isDeleting = false;
    this.deletingId = -1;
  }
  
  annullaElimina(){
    this.isDeleting = false;
    this.deletingId = -1;
  }

}
