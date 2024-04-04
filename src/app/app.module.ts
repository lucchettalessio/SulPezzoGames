import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginformComponent } from './loginform/loginform.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ListavideogiochiComponent } from './listavideogiochi/listavideogiochi.component';
import { ListarecensioniComponent } from './listarecensioni/listarecensioni.component';
import { ListanotizieComponent } from './listanotizie/listanotizie.component';

import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DettaglionotiziaComponent } from './dettaglionotizia/dettaglionotizia.component';
import { DettagliorecensioneComponent } from './dettagliorecensione/dettagliorecensione.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginformComponent,
    HomepageComponent,
    ListavideogiochiComponent,
    ListarecensioniComponent,
    ListanotizieComponent,
    DettaglionotiziaComponent,
    DettagliorecensioneComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
