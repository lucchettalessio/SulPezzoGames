import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginformComponent } from './loginform/loginform.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ListavideogiochiComponent } from './listavideogiochi/listavideogiochi.component';
import { ListarecensioniComponent } from './listarecensioni/listarecensioni.component';
import { ListanotizieComponent } from './listanotizie/listanotizie.component';
import { Routes } from '@angular/router';

const routes : Routes = [
  {
    //http://localhost:4200/loginpage
    path: 'loginpage',
    component: LoginformComponent
  },
  {
    //http://localhost:4200/listanotizie
    path: 'listanotizie',
    component: ListanotizieComponent
  },
  {
    //http://localhost:4200/listarecensioni
    path: 'listarecensioni',
    component: ListarecensioniComponent
  },
  {
    //http://localhost:4200/listavideogiochi
    path: 'listavideogiochi',
    component: ListavideogiochiComponent
  },
]


@NgModule({
  declarations: [
    AppComponent,
    LoginformComponent,
    HomepageComponent,
    ListavideogiochiComponent,
    ListarecensioniComponent,
    ListanotizieComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
