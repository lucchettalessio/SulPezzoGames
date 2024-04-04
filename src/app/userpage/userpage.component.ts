import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Utente } from '../models/Utente';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit{


  ruolo: string = "";

  utente! : Utente


  constructor(private route: ActivatedRoute, private http : HttpClient){
    this.http = http;
  }

  ngOnInit(): void {
    this.getUtente();
    let tokenRuolo = sessionStorage.getItem("token")?.split("-")[0];
    if(tokenRuolo != null){
      this.ruolo = tokenRuolo
    }
  }

  getUtente(){
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

}
