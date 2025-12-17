import { KeyValuePipe, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PaisEnum } from '../../models/usuarios/usuario-gamer/pais-enum';
import { GamerRegistro } from '../../models/usuarios/usuario-gamer/gamer-registro';
import { UsuarioServicios } from '../../services/usuarios/usuarios-service';
import { ErrorResponse } from '../../services/ErrorResponse';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-registro-form',
  templateUrl: './registro-form.html',
  imports: [RouterLink,FormsModule, ReactiveFormsModule, KeyValuePipe, NgFor],
  styleUrl: './registro-form.css',
})

export class RegistroForm implements OnInit {
  fotoSeleccionada!: File;
  nuevoUsuario !: GamerRegistro;
  formulario!: FormGroup;
  paisesEnums = PaisEnum;
  urlTemporal: String = "url";
  mensajeError !: String;

  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;
  creacionJsonExistosa: boolean = false;




  constructor(private formBuilder: FormBuilder, private usuariosServicios: UsuarioServicios) {
  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        telefono: ['', [Validators.required, Validators.pattern(/^[0-9]{8}$/)]],
        correo: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        nickname: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
        nombre: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        nombrePais: [null, Validators.required],
        contrasena: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        fechaNacimiento: [new Date().toISOString().substring(0, 10), Validators.required],
      }
    );
  }

  paisesOpciones = Object.keys(PaisEnum).filter(val => isNaN(Number(val))).map(key => ({
    key: key,
    value: PaisEnum[key as keyof typeof PaisEnum]
  }));


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


  enviar(): void {
    this.intentoEnviarlo = true;
    this.hayError = false;
    if (this.formulario.valid) {
      this.nuevoUsuario = this.formulario.value as GamerRegistro;
      console.log("formulario listo");
      this.usuariosServicios.crearNuevoGamer(this.nuevoUsuario).subscribe({
        next: () => {
          this.creacionExitosa = true;
          this.creacionJsonExistosa = true;
          console.log(this.nuevoUsuario);

          // se sube la imagen después del usuario
          if (this.fotoSeleccionada) {
            console.log(" se va subir imagen");
            this.usuariosServicios.subirImagen(this.fotoSeleccionada, this.nuevoUsuario.correo).subscribe({
              next: () => {
                console.log("supuestamente se subió");
              },
              error: (error: any) => {
                console.log(error);
              }

            });
          }

        },
        error: (error: any) => {
          this.mensajeError = error.error.mensaje;
          this.hayError = true;
          console.log(error);
        }
      });
    } else {
      console.log("form invalido");
    }
  }
}

