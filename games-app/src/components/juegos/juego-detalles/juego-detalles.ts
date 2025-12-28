import { Component, Input } from '@angular/core';
import { Juego } from '../../../models/juegos/juego';

@Component({
  selector: 'app-juego-detalles',
  imports: [],
  templateUrl: './juego-detalles.html',
  styleUrl: './juego-detalles.css',
})
export class JuegoDetalles {
  urlTemporal: string = "asñldfk";
  hayArchivoCargado: boolean= false;
  @Input({required : true})
  juego !: Juego;
}
