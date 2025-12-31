import { Component, OnInit } from '@angular/core';
import { Header } from "../../../components/header/header";
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { GruposService } from '../../../services/grupos-service';
import { GrupoCompleto } from '../../../models/grupos/grupo-completo';
import { ModalGenerico } from "../../../shared/modal-generico/modal-generico";
import { RouterLink } from '@angular/router';
import { GrupoCard } from "../../../components/grupos/grupo-card/grupo-card";

@Component({
  selector: 'app-grupos-page',
  standalone: true,
  imports: [Header, FormsModule, ReactiveFormsModule, ModalGenerico, RouterLink, GrupoCard],
  templateUrl: './grupos-page.html',
})
export class GruposPage implements OnInit {
  barraBusqueda!: FormGroup;
  grupos: GrupoCompleto[] = [];
  grupoEncontrado: GrupoCompleto | null = null;

  hayBusqueda: boolean = false;
  mostrarTodos: boolean = false;
  hayError: boolean = false;
  mensajeError: string = "";
  grupoSeleccionado!: GrupoCompleto;
  mensajeEliminacion: string = "";

  constructor(private gruposService: GruposService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.barraBusqueda = this.fb.group({
      grupoBuscado: ["", [Validators.required]]
    });
  }

  cargarTodos(): void {
    this.hayBusqueda = false;
    this.mostrarTodos = true;
    const correoAdmin = localStorage.getItem('correo');
    if (correoAdmin) {
      this.hayBusqueda = false;
      this.mostrarTodos = true;
      this.gruposService.buscarGruposDelAdmin(correoAdmin).subscribe({
        next: (gruposRecibidos: GrupoCompleto[]) => {
          this.grupos = gruposRecibidos;
          if (this.grupos.length === 0) {
            console.log("Este administrador no tiene grupos.");
          }
        },
        error: (e) => {
          this.hayError = true;
          this.mensajeError = "No se pudieron cargar los grupos.";
          console.error(e);
        }
      });
    } else {
      this.hayError = true;
      this.mensajeError = "No se encontró un usuario en sesión.";
    }
  }

  buscarGrupo(): void {
    if (this.barraBusqueda.valid) {
      this.hayBusqueda = true;
      const nombre = this.barraBusqueda.value.grupoBuscado;

      this.gruposService.buscarGrupo(nombre).subscribe({
        next: (grupo) => {
          this.grupoEncontrado = grupo;
          this.mostrarTodos = false;
        },
        error: (err) => {
          this.hayError = true;
          this.mensajeError = err.error.mensaje;
        }
      });
    }
  }

  prepararEliminacion(grupo: GrupoCompleto): void {
    this.grupoSeleccionado = grupo;
    this.mensajeEliminacion = `¿Desea eliminar el grupo ${grupo.nombre}?`;
  }

  eliminarRegistro(): void {
    this.gruposService.eliminarGrupo(this.grupoSeleccionado.id.toString()).subscribe({
      next: () => {
        this.cargarTodos();
      },
      error: (err) => {
        this.hayError = true;
        this.mensajeError = err.error.mensaje;
      }
    });
  }
}