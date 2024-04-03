import { Component, OnInit, Renderer2 } from '@angular/core';
import { Utente } from './models/Utente';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './app.component-dark.css']
})
export class AppComponent implements OnInit{
  title = 'angular';
  darkMode = false;

  token: string | null | undefined;
  ruolo: string = "";

  utente! : Utente;

  userPopUp: boolean = false;



  constructor(private http : HttpClient){
    this.http = http;
  }

  switchDarkMode(){
    this.darkMode = !this.darkMode;
    if(this.darkMode) {
      // Salva la modalità notte nella sessione o local storage se necessario
      localStorage.setItem('darkMode', 'true');
    } else {
      // Rimuovi la modalità notte dalla sessione o local storage se necessario
      localStorage.removeItem('darkMode');
    }
  }
  ngOnInit(): void {
    // Controlla se la modalità notte è stata attivata in precedenza
    this.darkMode = localStorage.getItem('darkMode') === 'true';

    this.token = sessionStorage.getItem("token");
    if(this.token != null){
      this.ruolo = this.token.split("-")[0]
      this.getUtente();
    }
    else{
      this.token = '';
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

  showPopUp(){
    this.userPopUp = true;
  }

  hidePopUp(){
    this.userPopUp = false;
  }


  clearToken(){
    sessionStorage.removeItem("token");
  }

  
}
