import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListanotizieComponent } from './listanotizie/listanotizie.component';
import { ListarecensioniComponent } from './listarecensioni/listarecensioni.component';
import { ListavideogiochiComponent } from './listavideogiochi/listavideogiochi.component';
import { LoginformComponent } from './loginform/loginform.component';
import { HomepageComponent } from './homepage/homepage.component';
import { DettaglionotiziaComponent } from './dettaglionotizia/dettaglionotizia.component';
import { DettagliorecensioneComponent } from './dettagliorecensione/dettagliorecensione.component';

const routes: Routes = [{
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
  path: 'listarecensioni/:id',
  component: ListarecensioniComponent
},
{
  //http://localhost:4200/listavideogiochi
  path: 'listavideogiochi',
  component: ListavideogiochiComponent
},
{
  //http://localhost:4200/homepage
  path: 'homepage',
  component: HomepageComponent
},
{
  //http://localhost:4200/dettaglionotizia/:id
  path: 'dettaglionotizia/:id',
  component: DettaglionotiziaComponent
},
{
  //http://localhost:4200/dettagliorecensione/:id
  path: 'dettagliorecensione/:id',
  component: DettagliorecensioneComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
