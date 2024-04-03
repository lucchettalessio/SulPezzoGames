import { Component, OnInit } from '@angular/core';
import { News } from 'src/app/models/News';
import { ListanotizieService } from './listanotizie.service';
import { Utente } from '../models/Utente';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-listanotizie',
  templateUrl: './listanotizie.component.html',
  styleUrls: ['./listanotizie.component.css']
})
export class ListanotizieComponent implements OnInit {
  notizie: News[] = [];
  autori: Utente[] = [];
  
  tipo_utente?: string;
  rowsPerPage: number = 4; 
  currentPage: number = 1;
  totalPages: number | undefined;

  ruolo: string = ""

  isDeleting: boolean = false;
  deletingId: number = -1;

  isModifying: boolean = false;
  modifyingId: number = -1;
  formModifica! : FormGroup;

  isInserting: boolean = false;
  formInsert! : FormGroup;

  constructor(private http: HttpClient, private listanotizieService: ListanotizieService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.caricaNotizie();
    this.caricaAutori();
    let tokenRuolo = sessionStorage.getItem("token")?.split("-")[0];
    if(tokenRuolo != null){
      this.ruolo = tokenRuolo
    }
  }

  caricaNotizie(): void {
    this.listanotizieService.getNotizieOrdered()
      .subscribe(notizie => {
        this.notizie = notizie;
        this.totalPages = Math.ceil(this.notizie.length / this.rowsPerPage);
      });
  }

  caricaAutori(): void {
    this.listanotizieService.getAutori()
    .subscribe(autori => {
      this.autori = autori;
    })
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

    this.http.get("http://localhost:8080/api/news/delete", {headers, params}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Eliminazione avvenuta con successo")
        // window.location.href = "/areadirigenti"
        var indice = this.notizie?.findIndex(x => x.id === id)
        console.log("indice", indice)
        if(indice! > -1){
          this.notizie?.splice(indice!, 1);
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
    this.isModifying = false;
    this.isInserting = true;
    this.formInsert = this.formBuilder.group(
      {
        id : "",
        titolo : "",
        categoria : "",
        data : "",
        immagine: "",
        immagine2: "",
        testo: "",
        autore : "",
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
    console.log(body)

    this.http.post("http://localhost:8080/api/news/add", body, {headers}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Inserimento avvenuta con successo")
        /* window.location.href = "/listarecensioni" */
        var rew : News = JSON.parse(body) as News;

        var index = this.notizie.findIndex(x => x.id == rew.id)
        this.notizie.splice(index!, 1, rew);

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


