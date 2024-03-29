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

  }

  ngOnInit(): void {
    this.token =  sessionStorage.getItem("token")?.split("-")[0];
    console.log(this.token);
  }

  clearToken(){
    sessionStorage.removeItem("token");
  }

}
