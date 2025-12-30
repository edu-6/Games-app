import { Component, Input, OnInit } from '@angular/core';
import { Juego } from '../../../models/juegos/juego';
import { JuegosService } from '../../../services/juegos-service';
import { RouterLink } from '@angular/router';
import { ComprasService } from '../../../services/compras-service';
import { CompraJuego } from '../../../models/compras/compra-juego';
import { Header } from "../../header/header";
import { CompraExistencia } from '../../../models/compras/CompraExistencia';
import { CompraExistenciaResponse } from '../../../models/compras/existencia-response';

@Component({
  selector: 'app-juego-detalles',
  imports: [RouterLink, Header],
  templateUrl: './juego-detalles.html',
  styleUrl: './juego-detalles.css',
})
export class JuegoDetalles implements OnInit {
  urlTemporal: string = "asñldfk";
  hayArchivoCargado: boolean = false;
  yaEstaComprado !: boolean;
  @Input({ required: true })
  juego !: Juego;

  constructor(private juegosService: JuegosService, private comprasService: ComprasService) {
    
  }
  ngOnInit(): void {
    this.averiguarSiYaLoCompro();
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


  averiguarSiYaLoCompro(): void{
    console.log(" se está averiguando");
    let correo: string | null = localStorage.getItem('correo');
    if (correo != null) {
      this.comprasService.buscarCompraAnterior(new CompraExistencia(this.juego.nombre, correo)).subscribe({
        next:(anterior: CompraExistenciaResponse)=>{
          console.log("resultados");
          this.yaEstaComprado = anterior.existe; // si ya tiene compra anterior
        }
      });
    }else{
      console.log("correo nullo");
    }
  }
}
