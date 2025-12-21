import { Component, Input, OnInit } from '@angular/core';
import { AdminSistema } from '../../../models/admins-sistema/admin-creacion';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminsSistemaService } from '../../../services/admin-sistema-service';
import { RouterLink } from '@angular/router';
import { Header } from '../../header/header';

@Component({
  selector: 'app-admin-sistema-form',
  imports: [FormsModule, ReactiveFormsModule, RouterLink, Header],
  templateUrl: './admin-sistema-form.html',
  styleUrl: './admin-sistema-form.css',
})
export class AdminSistemaForm implements OnInit {

  fotoSeleccionada!: File;
  nuevoAdmin !: AdminSistema;
  formulario!: FormGroup;
  urlTemporal: String = "url";
  mensajeError !: String;

  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;
  creacionJsonExistosa: boolean = false;
  edicionExistosa: boolean = false;




  constructor(private formBuilder: FormBuilder, private adminSistemaServicios: AdminsSistemaService) {
  }


  @Input()
  estaEnEdicion: boolean = false;

  @Input()
  adminEdicion: AdminSistema | null = null;


  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        nombre: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        correo: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        contraseña: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]]
      }
    );

    if (this.estaEnEdicion) {
      this.formulario.reset(this.adminEdicion);
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
    if (this.adminEdicion) {
      this.adminSistemaServicios.obtenerImagen(this.adminEdicion.correo).subscribe({
        next:(imagen: Blob) =>{
          this.urlTemporal = URL.createObjectURL(imagen); // se recupera la imagen
        },
        error:(error: any) =>{
          console.log("no tenía foto o hay error interno");
        }
      });
    }

  }


  subirImagen(correoAdmin: string): void {
    if (this.fotoSeleccionada) {
      console.log(" se va subir imagen");
      this.adminSistemaServicios.subirImagen(this.fotoSeleccionada, correoAdmin).subscribe({
        next: () => {
          console.log("se subió");
        },
        error: (error: any) => {
          console.log(error.error);
        }

      });
    }
  }

  resetearEstados(): void{
    this.intentoEnviarlo = true;
    this.hayError = false;
  }



  crear(): void {
      this.resetearEstados();
    if (this.formulario.valid) {
      this.nuevoAdmin = this.formulario.value as AdminSistema;
      console.log("formulario listo");
      this.adminSistemaServicios.crearAdmin(this.nuevoAdmin).subscribe({
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
    if(this.formulario.valid && this.adminEdicion != null){
      this.adminEdicion = this.formulario.value as AdminSistema;
      this.adminSistemaServicios.editarAdmin(this.adminEdicion).subscribe({
        next:()=>{
          this.edicionExistosa = true;
          if(this.adminEdicion){ // si existe el admin edicion
            this.subirImagen(this.adminEdicion?.correo);
          }
          
        },
        error:(error: any)=>{
          this.mensajeError = error.error.mensaje;
          this.hayError = true;
          console.log(error.error);
        }
      });
    }

  }


}
