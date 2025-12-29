import { Component, Input, OnInit } from '@angular/core';
import { Juego } from '../../../models/juegos/juego';
import { JuegosService } from '../../../services/juegos-service';

@Component({
  selector: 'app-juego-detalles',
  imports: [],
  templateUrl: './juego-detalles.html',
  styleUrl: './juego-detalles.css',
})
export class JuegoDetalles implements OnInit {
  urlTemporal: string = "asñldfk";
  hayArchivoCargado: boolean= false;
  @Input({required : true})
  juego !: Juego;

  constructor(private juegosService:JuegosService){

  }
  ngOnInit(): void {
    this.juegosService.obtenerImagen(this.juego.nombre).subscribe({
      next: (datos: Blob) => {
        this.urlTemporal = URL.createObjectURL(datos);
        this.hayArchivoCargado = true;
      },
      error: (error: any) => {
        console.log(error.error.mensaje);
      }
    });
  }
}
