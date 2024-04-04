import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParameterCodec } from '@angular/common/http';
import { Observable, interval } from 'rxjs';
import { Recensione } from '../models/Recensione';
import { ListaRecensioniService } from '../listarecensioni/listarecensioniService';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Utente } from '../models/Utente';

@Component({
  selector: 'app-dettagliorecensione',
  templateUrl: './dettagliorecensione.component.html',
  styleUrls: ['./dettagliorecensione.component.css']
})
export class DettagliorecensioneComponent implements OnInit {

  recensione!:Recensione;
  autori: Utente[] = [];
  immagine = "";
  isDisabled = false;

  tipo_utente: string = "";

  isModifying: boolean = false;
  formModifica! : FormGroup;

  currentIndex = 0;

  constructor(private route: ActivatedRoute, private http: HttpClient, private listaRecensioniService: ListaRecensioniService, private formBuilder: FormBuilder){
    this.http = http;
    this.getRecensione;
  }

  ngOnInit(): void {
    this.getRecensione();
    this.caricaAutori();
    let tokenRuolo = sessionStorage.getItem("token")?.split("-")[0];
    if(tokenRuolo != null){
      this.tipo_utente = tokenRuolo
    }

    let cont = 0;
    setInterval(()=>{
      if(cont%2 == 0){
        this.immagine = this.recensione.immagine;
      }
      else{
        this.immagine = this.recensione.immagine2;
      }
      cont++;
    },2000)


  }

  
  getRecensione(){
    const headers = new HttpHeaders({'Content-Type' : 'application/json',});
      
      const idRecensione = this.route.snapshot.paramMap.get('id');
      
      this.http.get("http://localhost:8080/api/Recensione/" + idRecensione, {headers}).subscribe(risposta =>{
        this.recensione = risposta as Recensione;
        
      })
      
    }

  caricaAutori(): void {
    this.listaRecensioniService.getAutori()
    .subscribe(autori => {
      this.autori = autori;
    })
  }

  modifica(id: number){
    console.log(this.recensione);
    this.isModifying = true;
    this.formModifica = this.formBuilder.group(
      {
        id : this.recensione.id,
        titolo : this.recensione.titolo,
        data : this.recensione.data,
        punteggio: this.recensione.punteggio,
        immagine: this.recensione.immagine,
        testo: this.recensione.testo,
        autore : this.recensione.autore.id,
        videogioco : this.recensione.videogioco.id
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
        window.location.reload();

        this.isModifying = false;
      }
    })
  }


  annullaModifica(){
    this.isModifying = false;
  }

}
