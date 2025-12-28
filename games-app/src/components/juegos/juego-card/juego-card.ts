import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Juego } from '../../../models/juegos/juego';
import { JuegosService } from '../../../services/juegos-service';
import { GuardiaRolesServicio } from '../../../services/seguridad/GuardiaDeRolesServicio';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-juego-card',
  imports: [RouterLink],
  templateUrl: './juego-card.html',
  styleUrl: './juego-card.css',
})
export class JuegoCard implements OnInit {
  esAdminEmpresa!: boolean;
  urlFoto !: string;
  @Input({ required: true })
  juego !: Juego;

  @Output()
  entidadSeleccionada = new EventEmitter<Juego>(); // se le pone de nombre al "evento que se lanza"

  constructor(private juegosService: JuegosService, private guardiaServicio: GuardiaRolesServicio) {
    this.esAdminEmpresa = guardiaServicio.userRoleInAllowedRoles(['ADMIN_EMPRESA']);
  }

  eliminarAccion(): void {
    this.entidadSeleccionada.emit(this.juego);
  }

  ngOnInit(): void {
    // obtener imagen
    this.juegosService.obtenerImagen(this.juego.nombre).subscribe({
      next: (datos: Blob) => {
        this.urlFoto = URL.createObjectURL(datos);
      },
      error: (error: any) => {
        console.log(error.error.mensaje);
      }
    });

  }
}
