import { Component, Input, OnInit } from '@angular/core';
import { Empresa } from '../../../models/empresa/empresa';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmpresasService } from '../../../services/empresas-service';
import { Header } from "../../header/header";
import { Router, RouterLink } from '@angular/router';
import { EmpresaCard } from '../empresa-card/empresa-card';
import { EmpresaEdicion } from '../../../models/empresa/empresa-edicion';

@Component({
  selector: 'app-empresa-form',
  imports: [Header, ReactiveFormsModule, RouterLink],
  templateUrl: './empresa-form.html',
  styleUrl: './empresa-form.css',
})
export class EmpresaForm implements OnInit {
  fotoSeleccionada!: File;
  nuevaEmpresa !: Empresa;
  formulario!: FormGroup;
  urlTemporal!: String;
  mensajeError !: String;
  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;
  //creacionJsonExistosa: boolean = false;
  edicionExistosa: boolean = false;

  mensajeEdicion: String = "Edicion empresa";
  mensajeCreacion: String = "Creacion empresa";


  empresaEditada !: EmpresaEdicion;

  constructor(private formBuilder: FormBuilder, private empresasServicio: EmpresasService, private router: Router) {
  }


  @Input()
  estaEnEdicion: boolean = false;

  @Input()
  empresaEdicion!: Empresa;


  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        nombre: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(40)]],
        aceptaComentarios: [false, [Validators.required]],
      }
    );

    if (this.estaEnEdicion) {
      this.formulario.reset(this.empresaEdicion);
      this.recuperarImagen(); // recuperar imagen desde el servidor
    }
  }


  enviar(): void {
    if (this.estaEnEdicion) {
      this.editar();
    } else {
      this.crear();
    }
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


  recuperarImagen(): void {
    if (this.empresaEdicion) {
      this.empresasServicio.obtenerImagen(this.empresaEdicion.nombre).subscribe({
        next: (imagen: Blob) => {
          this.urlTemporal = URL.createObjectURL(imagen); // se recupera la imagen
        },
        error: (error: any) => {
          console.log("no tenía foto o hay error interno");
        }
      });
    }
  }


  subirImagen(nombreEmpresa: string): void {
    if (this.fotoSeleccionada) {
      console.log(" se va subir imagen");
      this.empresasServicio.subirImagen(this.fotoSeleccionada, nombreEmpresa).subscribe({
        next: () => {
          console.log("se subió");
        },
        error: (error: any) => {
          console.log(error.error.mensaje);
        }
      });
    }
  }

  resetearEstados(): void {
    this.edicionExistosa = false;
    this.creacionExitosa = false;
    this.intentoEnviarlo = true;
    this.hayError = false;
  }



  crear(): void {
    this.resetearEstados();
    if (this.formulario.valid) {
      this.nuevaEmpresa = this.formulario.value as Empresa;
      console.log("formulario listo");
      this.empresasServicio.crearEmpresa(this.nuevaEmpresa).subscribe({
        next: () => {
          this.creacionExitosa = true;
          console.log(this.nuevaEmpresa);

          // se sube la imagen después del usuario
          this.subirImagen(this.nuevaEmpresa.nombre);

          // moverse a la pestaña de crear admin despues
          this.router.navigate([`/admins-empresa/form/${this.nuevaEmpresa.nombre}`]);
        },
        error: (error: any) => {
          this.mensajeError = error.error.mensaje;
          this.hayError = true;
          console.log(error.error);
        }
      });
    } else {
      console.log("form invalido");
    }
  }


  editar(): void {
    
    this.resetearEstados();
    if (this.formulario.valid && this.empresaEdicion != null) {
      this.empresaEditada = this.generarModelo();
      this.empresasServicio.editarEmpresa(this.empresaEditada).subscribe({
        next: () => {
          this.edicionExistosa = true;
          if (this.empresaEdicion) { // si existe la empresa edicion
            this.subirImagen(this.empresaEditada.nuevoNombre);
            this.empresaEdicion = this.formulario.value as Empresa; /// se actualiza a  la nueva edición
          }
        },
        error: (error: any) => {
          this.mensajeError = error.error.mensaje;
          this.hayError = true;
          console.log(error.error);
        }
      });
    }

  }

  generarModelo(): EmpresaEdicion {
    let edicion: EmpresaEdicion = {
      nombre: this.empresaEdicion.nombre,
      nuevoNombre:  this.formulario.value.nombre,
      aceptaComentarios: this.formulario.value.aceptaComentarios
    };
    return edicion;
  }
}
