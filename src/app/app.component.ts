import { Component, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './app.component-dark.css']
})
export class AppComponent implements OnInit{
  title = 'angular';
  darkMode = false;
  token?:string;


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

    this.token = sessionStorage.getItem("token")?.split("-")[0];
    console.log(this.token);
  }


  clearToken(){
    sessionStorage.removeItem("token");
  }

  
}
