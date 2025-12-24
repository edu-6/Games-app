import { Component, Input, OnInit } from '@angular/core';
import { AdminEmpresa } from '../../../models/admin-empresa/admin-empresa';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioServicios } from '../../../services/usuarios/usuarios-service';
import { AdminEmpresaService } from '../../../services/admin-empresa-service';
import { AdminSistema } from '../../../models/admins-sistema/admin-creacion';
import { Header } from '../../header/header';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-amdin-empresa-form',
  imports: [ReactiveFormsModule, FormsModule, Header, RouterLink],
  templateUrl: './amdin-empresa-form.html',
  styleUrl: './amdin-empresa-form.css',
})
export class AmdinEmpresaForm implements OnInit {
  fotoSeleccionada!: File;
  nuevoAdmin !: AdminEmpresa;
  formulario!: FormGroup;
  urlTemporal!: String;
  mensajeError !: String;
  tipoInput: string = "password";
  estado: string = "mostrar";
  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;
  creacionJsonExistosa: boolean = false;
  edicionExistosa: boolean = false;
  nombreEmpresa !: string;

  constructor(private formBuilder: FormBuilder, private adminEmpresaServicios: AdminEmpresaService, private router: ActivatedRoute) {
  }


  @Input()
  estaEnEdicion: boolean = false;

  @Input()
  adminEdicion: AdminEmpresa | null = null;


  ngOnInit(): void {
    this.nombreEmpresa  = this.router.snapshot.params['nombre']; // se recojge el parametro si es que se envió
    this.formulario = this.formBuilder.group(
      {
        nombre: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        correo: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        contrasena: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        fechaNacimiento: [new Date().toISOString().substring(0, 10), Validators.required],
        correoAdminCreador: [this.generarCorreoAdminCreador(), Validators.required]
      }
    );

    if (this.estaEnEdicion) {
      this.formulario.reset(this.adminEdicion);
      this.recuperarImagen(); // recuperar imagen desde el servidor
    }
  }

  /**
   * Sirve para generar el correo o el nombre de la empresa a la cual se asocia el admin empresa
   * @returns 
   */
  generarCorreoAdminCreador(): string {
    if(!this.nombreEmpresa){ // si no hay  nombre empresa se genera en base al usuario en login
      let correoAdminEnSesion = localStorage.getItem('correo');
       if(correoAdminEnSesion){
        return correoAdminEnSesion;
       }
    }
    return this.nombreEmpresa;
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
    if (this.adminEdicion) {
      this.adminEmpresaServicios.obtenerImagen(this.adminEdicion.correo).subscribe({
        next: (imagen: Blob) => {
          this.urlTemporal = URL.createObjectURL(imagen); // se recupera la imagen
        },
        error: (error: any) => {
          console.log("no tenía foto o hay error interno");
        }
      });
    }

  }


  subirImagen(correoAdmin: string): void {
    if (this.fotoSeleccionada) {
      console.log(" se va subir imagen");
      this.adminEmpresaServicios.subirImagen(this.fotoSeleccionada, correoAdmin).subscribe({
        next: () => {
          console.log("se subió");
        },
        error: (error: any) => {
          console.log(error.error);
        }

      });
    }
  }

  resetearEstados(): void {
    this.intentoEnviarlo = true;
    this.hayError = false;
  }



  crear(): void {
    this.resetearEstados();
    if (this.formulario.valid) {
      this.nuevoAdmin = this.formulario.value as AdminEmpresa;
      console.log("formulario listo");
      this.adminEmpresaServicios.crearAdmin(this.nuevoAdmin).subscribe({
        next: () => {
          this.creacionExitosa = true;
          console.log(this.nuevoAdmin);

          // se sube la imagen después del usuario
          this.subirImagen(this.nuevoAdmin.correo);
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
    if (this.formulario.valid && this.adminEdicion != null) {
      this.adminEdicion = this.formulario.value as AdminEmpresa;
      this.adminEmpresaServicios.editarAdmin(this.adminEdicion).subscribe({
        next: () => {
          this.edicionExistosa = true;
          if (this.adminEdicion) { // si existe el admin edicion
            this.subirImagen(this.adminEdicion?.correo);
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
}
