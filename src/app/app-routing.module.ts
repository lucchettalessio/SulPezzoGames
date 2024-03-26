import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListanotizieComponent } from './listanotizie/listanotizie.component';
import { ListarecensioniComponent } from './listarecensioni/listarecensioni.component';
import { ListavideogiochiComponent } from './listavideogiochi/listavideogiochi.component';
import { LoginformComponent } from './loginform/loginform.component';

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
  path: 'listarecensioni',
  component: ListarecensioniComponent
},
{
  //http://localhost:4200/listavideogiochi
  path: 'listavideogiochi',
  component: ListavideogiochiComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
