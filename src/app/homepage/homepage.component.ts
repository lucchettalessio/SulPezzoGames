import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Utente } from '../models/Utente';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit{

  loggato? : boolean
  utente? : Utente

  constructor(private http : HttpClient){
    this.http = http;
  }

  ngOnInit(): void {
    sessionStorage.setItem("token", "admin-login-1")
    this.log()
  }


  log(){
    var token = sessionStorage.getItem("token")

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

  checkLogin(){

    console.log(this.loggato)

  }

}
