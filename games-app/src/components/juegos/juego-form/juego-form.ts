import { Component, OnInit } from '@angular/core';
import { Juego } from '../../../models/juegos/juego';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ClasificacionEdad } from '../../../models/juegos/clasificaicon-edad';
import { JuegosService } from '../../../services/juegos-service';
import { KeyValuePipe } from '@angular/common';

@Component({
  selector: 'app-juego-form',
  imports: [ReactiveFormsModule, KeyValuePipe],
  templateUrl: './juego-form.html',
  styleUrl: './juego-form.css',
})
export class JuegoForm implements OnInit {

  fotoSeleccionada!: File;
  nuevoJuego !: Juego;
  formulario!: FormGroup;
  clasificaciones = ClasificacionEdad;
  urlTemporal !: String;
  mensajeError !: String;

  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;
  creacionJsonExistosa: boolean = false;




  constructor(private formBuilder: FormBuilder, private juegoServicios: JuegosService) {
  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {

        nombre: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        descripcion: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(200)]],
        clasificacionEdad: [null, [Validators.required]],
        permiteComentarios: [true, Validators.required],
        activo: [true, Validators.required],
        precio: [null, [Validators.required, Validators.min(1)]],
        requerimientos: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(200)]],
        fechaLanzamiento: [new Date().toISOString().substring(0, 10), Validators.required],
      }
    );
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


  enviar(): void {
    this.intentoEnviarlo = true;
    this.hayError = false;
    if (this.formulario.valid) {
      this.nuevoJuego = this.formulario.value as Juego;
      console.log("formulario listo");
      this.juegoServicios.crearJuego(this.nuevoJuego).subscribe({
        next: () => {
          this.creacionExitosa = true;
          this.creacionJsonExistosa = true;
          console.log(this.nuevoJuego);

          // se sube la imagen después del usuario
          if (this.fotoSeleccionada) {
            console.log(" se va subir imagen");
            this.juegoServicios.subirImagen(this.fotoSeleccionada, this.nuevoJuego.nombre).subscribe({
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
