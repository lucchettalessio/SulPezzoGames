import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParameterCodec } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from '../models/News';
import { ListanotizieService } from '../listanotizie/listanotizie.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Utente } from '../models/Utente';

@Component({
  selector: 'app-dettaglionotizia',
  templateUrl: './dettaglionotizia.component.html',
  styleUrls: ['./dettaglionotizia.component.css']
})
export class DettaglionotiziaComponent implements OnInit {
  
  news!:News;
  autori: Utente[] = [];

  ruolo: string = "";

  isModifying: boolean = false;
  formModifica! : FormGroup;
  
  constructor(private route: ActivatedRoute, private http : HttpClient, private listanotizieService: ListanotizieService, private formBuilder: FormBuilder){
    this.http = http;
    this.getNews;
  }
  
  
  ngOnInit(): void {
    this.getNews();
    this.caricaAutori();
    let tokenRuolo = sessionStorage.getItem("token")?.split("-")[0];
    if(tokenRuolo != null){
      this.ruolo = tokenRuolo
    }
  }
  
  getNews(){
    const headers = new HttpHeaders({'Content-Type' : 'application/json',});
      
      const idNews = this.route.snapshot.paramMap.get('id');
      
      this.http.get("http://localhost:8080/api/news/" + idNews, {headers}).subscribe(risposta =>{
        this.news = risposta as News;
      })
      
    }

  caricaAutori(): void {
    this.listanotizieService.getAutori()
    .subscribe(autori => {
      this.autori = autori;
    })
  }
    
  modifica(id: number){
    console.log(this.news);
    this.isModifying = true;
    this.formModifica = this.formBuilder.group(
      {
        id : this.news.id,
        titolo : this.news.titolo,
        categoria : this.news.categoria,
        data : this.news.data,
        immagine: this.news.immagine,
        testo: this.news.testo,
        autore : this.news.autore.id,
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

    this.http.post("http://localhost:8080/api/news/update", body, {headers}).subscribe(risposta =>{
      var check = risposta as boolean;
      if(check){
        alert("Modifica avvenuta con successo")
        window.location.reload();

        this.isModifying = false;
      }
    })
  }

  annullaModifica(){
    this.isModifying = false;
  }

}
