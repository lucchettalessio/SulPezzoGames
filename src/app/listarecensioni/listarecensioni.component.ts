import { Component } from '@angular/core';
import { Recensione } from '../models/Recensione';
import { ListaRecensioniService } from './listarRcensioniService';

@Component({
  selector: 'app-listarecensioni',
  templateUrl: './listarecensioni.component.html',
  styleUrls: ['./listarecensioni.component.css']
})
export class ListarecensioniComponent {
  recensioni: Recensione[] = [];

  constructor(private listaRecensioniService: ListaRecensioniService) { }

  ngOnInit(): void {
    this.caricaRecensioni();
  }

  caricaRecensioni(): void {
    this.listaRecensioniService.getRecensione()
      .subscribe(recensioni => {
        this.recensioni = recensioni;
      });
  }
}
