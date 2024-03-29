import { Component, OnInit } from '@angular/core';
import { Recensione } from '../models/Recensione';
import { ListaRecensioniService } from './listarecensioniService';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-listarecensioni',
  templateUrl: './listarecensioni.component.html',
  styleUrls: ['./listarecensioni.component.css']
})
export class ListarecensioniComponent implements OnInit {
  recensioni: Recensione[] = [];
  rowsPerPage: number = 4; 
  currentPage: number = 1;
  totalPages: number | undefined;

  ruolo: string = ""

  isDeleting: boolean = false;
  deletingId: number = -1;

  isModifying: boolean = false;
  modifyingId: number = -1;
  formModifica! : FormGroup;

  constructor(private http: HttpClient, private listaRecensioniService: ListaRecensioniService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.caricaRecensioni();
    let tokenRuolo = sessionStorage.getItem("token")?.split("-")[0];
    if(tokenRuolo != null){
      this.ruolo = tokenRuolo
    }
  }
  
  caricaRecensioni(): void {
    this.listaRecensioniService.getRecensione()
      .subscribe(recensioni => {
        this.recensioni = recensioni;
        this.totalPages = Math.ceil(this.recensioni.length / this.rowsPerPage);
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
  elimina(id: number){
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

    this.http.get("http://localhost:8080/api/Recensione/delete", {headers, params}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Eliminazione avvenuta con successo")
        // window.location.href = "/areadirigenti"
        var indice = this.recensioni?.findIndex(x => x.id === id)
        console.log("indice", indice)
        if(indice! > -1){
          this.recensioni?.splice(indice!, 1);
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

  modifica(id: number){
    this.isModifying = true;
    this.modifyingId = id;
    const index = this.recensioni.findIndex((recensione) => recensione.id === id);
    this.formModifica = this.formBuilder.group(
      {
        id : this.recensioni[index].id,
        titolo : this.recensioni[index].titolo,
        data : this.recensioni[index].data,
        punteggio: this.recensioni[index].punteggio,
        immagine: this.recensioni[index].immagine,
        testo: this.recensioni[index].testo,
        autore : this.recensioni[index].autore,
        videogioco : this.recensioni[index].videogioco
      })
  }

  submitModifica(id: number){
    var token = sessionStorage.getItem("token")
    if(token == null){
      token = "";
    }

    const formValues = this.formModifica.value;
    const headers = new HttpHeaders(
      {
        'Content-Type' : 'application/json',
        'token': token as string
      }
    );

    const body = JSON.stringify(formValues);

    this.http.post("http://localhost:8080/api/Recensione/update", body, {headers}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Modifica avvenuta con successo")
        // window.location.href = "/areadirigenti"
        var rew : Recensione = JSON.parse(body) as Recensione;

        var index = this.recensioni.findIndex(x => x.id == rew.id)
        this.recensioni.splice(index!, 1, rew);

      }
    })

    this.isModifying = false;
    this.modifyingId = -1;
  }

  annullaModifica(){
    this.isModifying = false;
    this.modifyingId = -1;
  }
}


