import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Comentario } from '../../../models/comentarios/comentario-request';
import { ComentarioResponse } from '../../../models/comentarios/comentario-response';
import { ComentariosService } from '../../../services/comentarios-service';
import { ComentarioVista } from "../comentario-vista/comentario-vista";
import { Juego } from '../../../models/juegos/juego';

@Component({
  selector: 'app-comentarios-component',
  standalone: true,
  imports: [ReactiveFormsModule, ComentarioVista],
  templateUrl: './comentarios-component.html',
  styleUrl: './comentarios-component.css',
})
export class ComentariosComponent implements OnInit {

  formulario!: FormGroup;
  listaComentarios: ComentarioResponse[] = [];

  @Input({ required: true })
  juego!: Juego;

  @Input({ required: true })
  yaEstaComprado!: boolean;

  constructor(
    private formBuilder: FormBuilder, private comentariosService: ComentariosService) {

  }

  ngOnInit(): void {
    console.log("estamos iniciando el formulario");
    console.log(this.juego);
    this.formulario = this.formBuilder.group({
      correoUsuario: [this.generarCorreo(), Validators.required],
      texto: ['', [Validators.required, Validators.maxLength(500)]],
      fecha: [new Date()],
      nombreJuego: [this.juego.nombre]
    });

    this.cargarComentarios();
  }

  cargarComentarios(): void {
    this.comentariosService.obtenerComentariosPorJuego(this.juego.nombre).subscribe({
      next: (res) => {
        this.listaComentarios = res;
      },
      error: (err: any) => console.error("Error al cargar comentarios", err)
    });
  }

  enviarComentario(): void {
    if (this.formulario.valid) {
      this.formulario.patchValue({ fecha: new Date() });
      const request = this.formulario.value as Comentario;
      console.log(" enviando comententario + cuerpo comentario es: ");
      console.log(request);

      this.comentariosService.crearComentario(request).subscribe({
        next: () => {
          this.formulario.get('texto')?.reset();
          this.cargarComentarios();
        }
      });
    }
  }

  generarCorreo(): string {
    return localStorage.getItem("correo") || "a";
  }
}