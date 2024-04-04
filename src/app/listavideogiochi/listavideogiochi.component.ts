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

  isInserting: boolean = false;
  formInsert! : FormGroup;
  @Output() filterRecensioniEvent = new EventEmitter<number>();

  constructor(private videogiochiService: VideogiochiService, private router : Router, private http : HttpClient, private formBuilder: FormBuilder) { }

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


  insert(){
    this.isDeleting = false;
    this.isInserting = true;
    this.formInsert = this.formBuilder.group(
      {
        id : "",
        titolo : "",
        data : "",
        genere: "",
        produzione: "",
        immagine: "",
      })
  }

  submitInsert(){
    var token = sessionStorage.getItem("token")
    if(token == null){
      token = "";
    }

    const formValues = this.formInsert.value;
    const headers = new HttpHeaders(
      {
        'Content-Type' : 'application/json',
        'token': token as string
      }
    );

    const body = JSON.stringify(formValues);

    this.http.post("http://localhost:8080/api/Videogioco/add", body, {headers}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Inserimento avvenuta con successo")
        /* window.location.href = "/listarecensioni" */
        var rew : Videogioco = JSON.parse(body) as Videogioco;

        var index = this.videogiochi.findIndex(x => x.id == rew.id)
        this.videogiochi.splice(index!, 1, rew);

      }
      if(!check){
        console.log("ammazzati")
      }
    })

    this.isInserting = false;
  }

  annullaInsert(){
    this.isInserting = false;
  }

}
