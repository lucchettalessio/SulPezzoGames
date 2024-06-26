import { Component, OnInit } from '@angular/core';
import { Recensione } from '../models/Recensione';
import { ListaRecensioniService } from './listarecensioniService';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Utente } from '../models/Utente';
import { ActivatedRoute, Route } from '@angular/router';
import { Videogioco } from '../models/Videogioco';
import { VideogiochiService } from '../listavideogiochi/videogiochi.service';

@Component({
  selector: 'app-listarecensioni',
  templateUrl: './listarecensioni.component.html',
  styleUrls: ['./listarecensioni.component.css']
})
export class ListarecensioniComponent implements OnInit {
  recensioni: Recensione[] = [];
  autori: Utente[] = [];
  videogiochi: Videogioco[] = [];
  idVideogioco: any;

  rowsPerPage: number = 4; 
  currentPage: number = 1;
  totalPages: number | undefined;

  ruolo: string = ""

  isDeleting: boolean = false;
  deletingId: number = -1;

  isInserting: boolean = false;
  formInsert! : FormGroup;

  constructor(private http: HttpClient, private listaRecensioniService: ListaRecensioniService, private videogiochiService: VideogiochiService, private formBuilder: FormBuilder, private route : ActivatedRoute) {}

  ngOnInit(): void {
    this.caricaRecensioni();
    this.caricaAutori();
    this.caricaVideogiochi();
    let tokenRuolo = sessionStorage.getItem("token")?.split("-")[0];
    if(tokenRuolo != null){
      this.ruolo = tokenRuolo
    }
    setTimeout(() => {
      this.filterRecensioni();

    }, 100)
    
  }
  
  caricaRecensioni(): void {
    this.listaRecensioniService.getRecensioneOrdered()
      .subscribe(recensioni => {
        this.recensioni = recensioni;
        this.totalPages = Math.ceil(this.recensioni.length / this.rowsPerPage);
      });

    // Recupera l'id del videogioco dai parametri dell'URL
    this.idVideogioco = this.route.snapshot.paramMap.get('id');
    
  }


  caricaAutori(): void {
    this.listaRecensioniService.getAutori()
    .subscribe(autori => {
      this.autori = autori;
    })
  }

  caricaVideogiochi(){
    this.videogiochiService.getVideogiochi()
      .subscribe(videogiochi => {
        this.videogiochi = videogiochi;
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

    this.http.get("http://localhost:8080/api/Recensione/delete", {headers, params}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Eliminazione avvenuta con successo")
        var indice = this.recensioni?.findIndex(x => x.id === id)
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


  insert(){
    this.isDeleting = false;
    this.isInserting = true;
    this.formInsert = this.formBuilder.group(
      {
        id : "",
        titolo : "",
        data : "",
        punteggio: "",
        immagine: "",
        immagine2: "",
        testo: "",
        autore : "",
        videogioco : ""
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

    this.http.post("http://localhost:8080/api/Recensione/add", body, {headers}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Inserimento avvenuta con successo")
        window.location.href = "/listarecensioni"
        /* var rew : Recensione = JSON.parse(body) as Recensione;

        var index = this.recensioni.findIndex(x => x.id == rew.id)
        this.recensioni.splice(index!, 1, rew); */

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

  filterRecensioni(): void {
    if (this.idVideogioco !== null) {
      this.recensioni = this.recensioni.filter(recensione => recensione.videogioco.id == this.idVideogioco);
    }
  
  }
}


