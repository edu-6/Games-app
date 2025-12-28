import { Component } from '@angular/core';
import { JuegoDetalles } from "../../../components/juegos/juego-detalles/juego-detalles";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-juego-detalles-page',
  imports: [JuegoDetalles],
  templateUrl: './juego-detalles-page.html',
  styleUrl: './juego-detalles-page.css',
})
export class JuegoDetallesPage {

  constructor(private router: ActivatedRoute){

  }

}
