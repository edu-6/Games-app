import { KeyValuePipe, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PaisEnum } from '../../models/usuarios/usuario-gamer/pais-enum';
import { GamerRegistro } from '../../models/usuarios/usuario-gamer/gamer-registro';
import { UsuarioServicios } from '../../services/usuarios/usuarios-service';
@Component({
  selector: 'app-registro-form',
  templateUrl: './registro-form.html',
  imports: [FormsModule, ReactiveFormsModule, KeyValuePipe, NgFor],
  styleUrl: './registro-form.css',
})

export class RegistroForm implements OnInit {
  fotoSeleccionada: File | null = null;
  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  urlTemporal: String = "url";

  nuevoUsuario !: GamerRegistro;
  formulario!: FormGroup;
  paisesEnums = PaisEnum;

  constructor(private formBuilder: FormBuilder, private usuariosServicios: UsuarioServicios) {

  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        telefono: [null, [Validators.required, Validators.maxLength(10)]],
        correo: [null, [Validators.required, Validators.maxLength(100)]],
        nickname: [null, Validators.required],
        nombre: [null, Validators.required],
        pais: [null, Validators.required],
        contraseña: [null, Validators.required],
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
    if (this.formulario.valid) {
      this.nuevoUsuario = this.formulario.value as GamerRegistro;
      console.log("formulario listo");
      this.usuariosServicios.crearNuevoGamer(this.nuevoUsuario).subscribe({
        next: () => {
          console.log(this.nuevoUsuario);
        },
        error: (error: any) => {
          console.log(error);
        }
      });
    } else {
      console.log("form invalido");
    }
  }
}

