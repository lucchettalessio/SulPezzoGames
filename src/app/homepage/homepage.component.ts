import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Utente } from '../models/Utente';
import { News } from '../models/News';
import { ListanotizieService } from '../listanotizie/listanotizie.service';
import { ListaRecensioniService } from '../listarecensioni/listarecensioniService';
import { Recensione } from '../models/Recensione';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit{



  loggato? : boolean
  tipo_utente?:string;
  utente? : Utente
  notizie: News[] = [];
  recensioni: Recensione[] = []

  cliccatoNews : boolean = true
  cliccatoRec : boolean = true

  constructor(private http : HttpClient, private listaNotizieService: ListanotizieService, private listaRecensioniService: ListaRecensioniService){
    this.http = http;
  }

  ngOnInit(): void {
    var token = sessionStorage.getItem("token")
    this.log();
    this.caricaNotizie();
    this.caricaRecensioni();
    this.tipo_utente = token?.split("-")[0];
  }


  log(){
    var token = sessionStorage.getItem("token")
    console.log("token: " + token)

    if(token == null){
      token = "";
      this.loggato = false
    }
    else{
      this.tornaUtente();
      this.loggato = true
    }
  }


  tornaUtente(){
    var token = sessionStorage.getItem("token")

    if(token == null){
      token = "";
    }

    const headers = new HttpHeaders(
      {
        'Content-Type' : 'application/json',
        'token': token as string
      }
    );
    const params = new HttpParams().set("idUtente", token.split("-")[2])

    this.http.get("http://localhost:8080/api/utente/utente-byId", {headers, params}).subscribe(risposta => {
      this.utente = risposta as Utente;
    })
  }

  logout(){
    sessionStorage.clear()
    window.location.href = "/homepage"
  }

  caricaNotizie(): void {
    this.listaNotizieService.getNotizie()
      .subscribe(notizie => {
        this.notizie = notizie;
      });
  }

  caricaRecensioni(): void {
    this.listaRecensioniService.getRecensione()
      .subscribe(recensioni => {
        this.recensioni = recensioni.slice(0,5);
      });
  }

  onlyNews(){
    this.cliccatoNews = true;
    this.cliccatoRec = false;
  }

  onlyRec(){
    this.cliccatoRec = true;
    this.cliccatoNews = false;
  }
}
