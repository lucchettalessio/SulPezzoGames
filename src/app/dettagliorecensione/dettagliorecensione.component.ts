import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParameterCodec } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recensione } from '../models/Recensione';

@Component({
  selector: 'app-dettagliorecensione',
  templateUrl: './dettagliorecensione.component.html',
  styleUrls: ['./dettagliorecensione.component.css']
})
export class DettagliorecensioneComponent implements OnInit {

  recensione?:Recensione;
  tipo_utente?:string;

  constructor(private route: ActivatedRoute, private http : HttpClient){
    this.http = http;
    this.getRecensione;
  }

  ngOnInit(): void {
    this.getRecensione();
    var token = sessionStorage.getItem("token");
    this.tipo_utente = token?.split("-")[0];
  }
  
  getRecensione(){
    const headers = new HttpHeaders({'Content-Type' : 'application/json',});
      
      const idRecensione = this.route.snapshot.paramMap.get('id');
      
      this.http.get("http://localhost:8080/api/recensione/" + idRecensione, {headers}).subscribe(risposta =>{
        this.recensione = risposta as Recensione;
      })
      
    }

}
