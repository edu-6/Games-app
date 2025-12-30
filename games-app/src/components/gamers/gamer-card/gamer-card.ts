import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GamerSimple } from '../../../models/usuarios/usuario-gamer/gamer-simple';
import { UsuarioServicios } from '../../../services/usuarios/usuarios-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-gamer-card',
  imports: [RouterLink],
  templateUrl: './gamer-card.html',
  styleUrl: './gamer-card.css',
})
export class GamerCard implements OnInit {

  urlFoto !: string;
  @Input({required : true})
  gamer !: GamerSimple;


  @Output()
  gamerFueSeleccionado = new EventEmitter<GamerSimple>(); // se le pone de nombre al "evento que se lanza"

  constructor(private usuarioServicios: UsuarioServicios) {

  }

  eliminarAccion(): void {
    this.gamerFueSeleccionado.emit(this.gamer);
  }

  ngOnInit(): void {
    this.usuarioServicios.obtenerImagen(this.gamer.correo).subscribe({
      next: (datos: Blob) => {
        this.urlFoto = URL.createObjectURL(datos);
      },
      error: (error: any) => {
        console.log(error.error.mensaje);
      }
    });

  }
}
