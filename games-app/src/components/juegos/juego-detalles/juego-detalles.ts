import { Component, Input, OnInit } from '@angular/core';
import { Juego } from '../../../models/juegos/juego';
import { JuegosService } from '../../../services/juegos-service';
import { RouterLink } from '@angular/router';
import { ComprasService } from '../../../services/compras-service';
import { CompraJuego } from '../../../models/compras/compra-juego';
import { Header } from "../../header/header";
import { CompraExistencia } from '../../../models/compras/CompraExistencia';
import { CompraExistenciaResponse } from '../../../models/compras/existencia-response';
import { ComentariosComponent } from "../../comentarios/comentarios-component/comentarios-component";
import { ComentariosService } from '../../../services/comentarios-service';

@Component({
  selector: 'app-juego-detalles',
  imports: [RouterLink, Header, ComentariosComponent],
  templateUrl: './juego-detalles.html',
  styleUrl: './juego-detalles.css',
})
export class JuegoDetalles implements OnInit {
  urlTemporal: string = "asñldfk";
  hayArchivoCargado: boolean = false;
  yaEstaComprado !: boolean;
  hayJuego: boolean = false;
  comentariosHabilitados : boolean = false;
  @Input({ required: true })
  juego !: Juego;

  constructor(private juegosService: JuegosService, private comprasService: ComprasService,
    private comentariosService: ComentariosService
  ) {

  }
  ngOnInit(): void {
    this.chequearPermisos();
    console.log(this.juego.nombre + "nombre del juego en detalles");
    this.hayJuego = (this.juego != null);
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


  averiguarSiYaLoCompro(): void {
    console.log(" se está averiguando");
    let correo: string | null = localStorage.getItem('correo');
    if (correo != null) {
      this.comprasService.buscarCompraAnterior(new CompraExistencia(this.juego.nombre, correo)).subscribe({
        next: (anterior: CompraExistenciaResponse) => {
          console.log("resultados");
          this.yaEstaComprado = anterior.existe; // si ya tiene compra anterior
        }
      });
    } else {
      console.log("correo nullo");
    }
  }

  chequearPermisos(): void {
    this.comentariosService.verificarPermisoComentarios(this.juego.nombre).subscribe({
      next: (res) => {
        this.comentariosHabilitados = res.permitido;
      },
      error: () => this.comentariosHabilitados = false
    });
  }
}
