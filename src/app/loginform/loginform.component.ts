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

  constructor(private http: HttpClient, private formBuilder: FormBuilder, private router: Router){
    this.http = http;
    this.formLogin = formBuilder.group({
      username: "",
      password: ""
    })
  }

  submitForm(){
    const formValues = this.formLogin.value;
    const headers = {'Content-Type' : 'application/json'}
    const body = JSON.stringify(formValues);
    this.http.post("http://localhost:8080/api/login/", body, {'headers' : headers}).subscribe(risposta => {
      var loginStatus : LoginStatus = risposta as LoginStatus;
      
      sessionStorage.setItem("token", loginStatus.token)

        if(loginStatus.tipo_utente != null)
          this.router.navigate(['/homepage'])

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


}
