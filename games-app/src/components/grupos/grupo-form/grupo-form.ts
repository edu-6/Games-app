import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { GuardiaRolesServicio } from '../../../services/seguridad/GuardiaDeRolesServicio';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { GruposService } from '../../../services/grupos-service';
import { GrupoCompleto } from '../../../models/grupos/grupo-completo';
import { Grupo } from '../../../models/grupos/grupo-request';
import { Header } from "../../header/header";
import { ParticipanteGrupoRequest } from '../../../models/grupos/participante-grupo-request';
import { ParticipanteGrupoResponse } from '../../../models/grupos/grupo-participante';

@Component({
  selector: 'app-grupo-form',
  imports: [Header, ReactiveFormsModule, RouterLink],
  templateUrl: './grupo-form.html',
  styleUrl: './grupo-form.css',
})
export class GrupoForm implements OnInit {
  fotoSeleccionada!: File;
  nuevGrupo !: Grupo;
  formulario!: FormGroup;
  urlTemporal!: String;
  mensajeError !: String;
  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;
  creacionJsonExistosa: boolean = false;
  edicionExistosa: boolean = false;
  nombreEmpresa !: string;
  esAdminSistema !: boolean;
  quiereAgregarNuevo !: boolean;
  EstaIngresandoDatos: boolean = false;
  participantes: ParticipanteGrupoResponse[] = [];

  constructor(private formBuilder: FormBuilder, private guardService: GuardiaRolesServicio,
    private gruposServicios: GruposService, private router: ActivatedRoute, private rutador: Router) {
    this.esAdminSistema = guardService.userRoleInAllowedRoles(['ADMIN_SISTEMA']);
  }

  @Input()
  estaEnEdicion: boolean = false;

  @Input()
  grupoEdicion !: GrupoCompleto;


  ngOnInit(): void {
    this.cargarParticipantes();
    this.formulario = this.formBuilder.group({
      nombre: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
      correoAdmin: [this.generarCorreoAdminCreador(), Validators.required]
    });

    if (this.estaEnEdicion) {
      this.formulario.patchValue(this.grupoEdicion);
      //this.recuperarImagen();
      this.formulario.patchValue({
        correoAdminCreador: this.generarCorreoAdminCreador() // para que no inicie sin creador
      });
    }
  }

  /**
   * Sirve para enocntrar el correo de la sesion actual
   * @returns 
   */
  generarCorreoAdminCreador(): string {
    let correoAdminEnSesion = localStorage.getItem('correo');
    if (correoAdminEnSesion != null) {
      return correoAdminEnSesion;
    }
    return "a"; // nunca pasa 
  }


  cargarParticipantes(): void {
    if (this.grupoEdicion) {
      this.gruposServicios.obtenerParticipantes(this.grupoEdicion.id).subscribe({
        next: (res) => {
          this.participantes = res;
        },
        error: (err) => {
          console.error("Error al cargar integrantes", err);
        }
      });
    }
  }

  eliminarParticipante(correo: string): void {
    if (confirm('¿Estás seguro de eliminar a este integrante?')) {
      this.gruposServicios.eliminarParticipante(this.grupoEdicion!.id, correo).subscribe({
        next: () => {
          this.cargarParticipantes();
        },
        error: (err) => {
          this.cargarParticipantes();
          this.hayError = true;
          this.mensajeError = "No se pudo eliminar al participante.";
        }
      });
    }
  }



  enviar(): void {
    if (this.estaEnEdicion) {
      this.editar();
    } else {
      this.crear();
    }
  }

  cancelarAgregacion(): void {
    this.quiereAgregarNuevo = false;
    this.EstaIngresandoDatos = false;
  }

  abrirFormularioDeParticipante(): void {
    this.quiereAgregarNuevo = true;
    this.EstaIngresandoDatos = true;
  }


  AlCargarArchivo(event: any): void {
    const archivos = event.target.files;
    if (archivos && archivos.length > 0) {
      //Seguridad para solo permitir imagenes
      if (!archivos[0].type.startsWith('image/')) {
        alert('Solo se permiten imágenes');
        event.target.value = ''; // limpiar el contenedor (si ingresó algo diferente a imagen)
        return;
      }
      this.fotoSeleccionada = archivos[0];
      this.hayArchivoCargado = true;
      this.urlTemporal = URL.createObjectURL(archivos[0]);

    }
  }

  confirmarAgregarParticipante(correo: string): void {
    this.resetearEstados();
    console.log("llego aqui");
    if (!correo || correo.trim() === '') {
      this.hayError = true;
      this.mensajeError = "Debe ingresar un correo válido";
      return;
    }
    const nuevoParticipante = new ParticipanteGrupoRequest(
      correo, this.grupoEdicion.id
    );
    this.gruposServicios.añadirParticipante(nuevoParticipante).subscribe({
      next: () => {
        this.quiereAgregarNuevo = false;
        this.hayError = false;
        this.cargarParticipantes();
      },
      error: (err: any) => {
        this.cargarParticipantes();
        this.hayError = true;
        this.mensajeError = err.error?.mensaje || "No se pudo agregar al participante";
      }
    });
  }


  resetearEstados(): void {
    this.edicionExistosa = false;
    this.intentoEnviarlo = true;
    this.hayError = false;
  }


/**
 * Para crear el grupo y luego agregar al administrador
 */
  crear(): void {
    this.resetearEstados();
    if (this.formulario.valid) {
      const nuevo = this.formulario.value as Grupo;
      this.gruposServicios.crearGrupo(nuevo).subscribe({
        next: () => {
          this.creacionExitosa = true;
          this.rutador.navigate(['/grupos']);
          },
        error: (e) => this.manejarError(e)
      });
    } else {
      console.log("formulario invalido");
    }
  }


  editar(): void {
    this.resetearEstados();
    if (this.formulario.valid) {
      const datosParaEnviar: GrupoCompleto = {
        ...this.grupoEdicion,
        ...this.formulario.value
      };
      this.gruposServicios.editarGrupo(datosParaEnviar).subscribe({
        next: () => {
          this.edicionExistosa = true;
          this.rutador.navigate(['/grupos']);
        },
        error: (e) => this.manejarError(e)
      });
    }
  }

  private manejarError(error: any) {
    this.mensajeError = error.error.mensaje;
    this.hayError = true;
  }

}
