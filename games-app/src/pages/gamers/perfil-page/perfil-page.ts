import { Component, OnInit } from '@angular/core';
import { Header } from "../../../components/header/header";
import { GamerRegistro } from '../../../models/usuarios/usuario-gamer/gamer-registro';
import { UsuarioServicios } from '../../../services/usuarios/usuarios-service';
import { GamerSimple } from '../../../models/usuarios/usuario-gamer/gamer-simple';
import { GamerCard } from "../../../components/gamers/gamer-card/gamer-card";
import { ModalGenerico } from "../../../shared/modal-generico/modal-generico";
import { Router } from '@angular/router';

@Component({
  selector: 'app-perfil-page',
  imports: [Header, GamerCard, ModalGenerico],
  templateUrl: './perfil-page.html',
  styleUrl: './perfil-page.css',
})
export class PerfilPage implements OnInit {
  gamer!: GamerSimple;
  mensajeEliminacion: string = "Desea elminar su cuenta?";
  gamerSeleccionado !: GamerSimple;
  mensajeError : string = "error al eliminar";
  constructor(private gamerServicio: UsuarioServicios, private router: Router) {

  }

  ngOnInit(): void {
    let correo: string | null = localStorage.getItem("correo");
    if (correo != null) {
      this.gamerServicio.obtenerGamerSimple(correo).subscribe({
        next: (gamer: GamerSimple) => {
          this.gamer = gamer;
        },
        error:(error: any) =>{ 
          console.log(error.error.mensaje);
        }
      });
    }
  }
  
    eliminarRegistro(): void {
    this.gamerServicio.eliminarGamer(this.gamerSeleccionado.nombre).subscribe({
      next: () => {
        this.router.navigate(['/']);
        localStorage.clear();
      },
      error: (error: any) => {
        this.mensajeError = error.error.mensaje;
      }
    });

  }

  guardarGamerSeleccionado(gamer: GamerSimple): void {
    this.gamerSeleccionado = gamer;
  }
}
