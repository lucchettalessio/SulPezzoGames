import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginStatus } from 'src/app/models/LoginStatus';

@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.css']
})
export class LoginformComponent {
  formLogin: FormGroup;
  formRegister: FormGroup;
  isLogin?=true;

  constructor(private http: HttpClient, private formBuilder: FormBuilder, private router: Router){
    this.http = http;
    this.formLogin = formBuilder.group({
      username: "",
      password: ""
    })
    this.formRegister = formBuilder.group({
      email:"",
      usernameR:"",
      passwordR:"",
      nome:"",
      cognome:"",
      tipo_utente:""
    })
  }


  submitForm(){
    const formValues = this.formLogin.value;
    const headers = {'Content-Type' : 'application/json'}
    const body = JSON.stringify(formValues);
    this.http.post("http://localhost:8080/api/login", body, {'headers' : headers}).subscribe(risposta => {
      var loginStatus : LoginStatus = risposta as LoginStatus;
      
      sessionStorage.setItem("token", loginStatus.token)

        if(loginStatus.tipo_utente != null){
          this.router.navigate(['/homepage'])
        }
        else{
          alert("ERRORE LOGIN");
          this.formLogin.patchValue(
            {
              username : "",
              password : ""
            }
          )
      }
    })
  }

  submitRegistration(){
    const formValues = this.formRegister.value;
    const headers = {'Content-Type' : 'application/json'}
    const body = JSON.stringify(formValues);
    this.http.post("http://localhost:8080/api/register", body, { 'headers': headers }).subscribe(() => {

      console.log("Registrazione effettuata");

      sessionStorage.setItem("registrationSuccess", "true");
      this.router.navigate(['/loginpage']);
      this.toggleRegister();

  }, (error) => {
      console.error("Errore registrazione:", error);
      alert("Si è verificato un errore durante la registrazione.");
  });
}

  toggleRegister(){
    if(this.isLogin == false)
      this.isLogin=true;
    else
      this.isLogin=false;
  }


}

