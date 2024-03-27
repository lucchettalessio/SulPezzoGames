import { Component, OnInit } from '@angular/core';
import { Recensione } from '../models/Recensione';
import { ListaRecensioniService } from './listarecensioniService';

@Component({
  selector: 'app-listarecensioni',
  templateUrl: './listarecensioni.component.html',
  styleUrls: ['./listarecensioni.component.css']
})
export class ListarecensioniComponent implements OnInit {
  recensioni: Recensione[] = [];
  itemsPerPage: number = 4;
  currentPage: number = 1;
  totalPages: number | undefined;

  constructor(private listaRecensioniService: ListaRecensioniService) { }

  ngOnInit(): void {
    this.caricaRecensioni();
  }

  caricaRecensioni(): void {
    this.listaRecensioniService.getRecensione()
      .subscribe(recensioni => {
        this.recensioni = recensioni;
        this.totalPages = Math.ceil(this.recensioni.length / this.itemsPerPage);
      });
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages!) {
      this.currentPage++;
    }
  }
}
