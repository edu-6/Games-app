import { Component, Input, OnInit } from '@angular/core';
import { ComentarioResponse } from '../../../models/comentarios/comentario-response';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SubcomentarioRequest } from '../../../models/comentarios/subcomentario-request';
import { ComentariosService } from '../../../services/comentarios-service';
import { SubcomentarioResponse } from '../../../models/comentarios/subcomentario-response';
import { SubcomentarioVista } from "../subcomentario-vista/subcomentario-vista";

@Component({
  selector: 'app-comentario-vista',
  imports: [SubcomentarioVista, ReactiveFormsModule],
  templateUrl: './comentario-vista.html',
  styleUrl: './comentario-vista.css',
})
export class ComentarioVista implements OnInit {
  formulario!: FormGroup;
  quiereComentar: boolean = false;
  listaSubcomentarios: SubcomentarioResponse[] = [];
  @Input({ required: true })
  comentario!: ComentarioResponse;

  @Input({ required: true })
  yaEstaComprado!: boolean;

  constructor(private formBuilder: FormBuilder, private comentariosService: ComentariosService) {

  }
  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      idComentarioPadre: [this.comentario.idComentario, Validators.required],
      correoUsuario: [localStorage.getItem('correo') || '', Validators.required],
      texto: [null, [Validators.required, Validators.maxLength(500)]],
      fecha: [new Date()]
    });
    this.cargarRespuestas();

  }

  cancelarComentario(): void {
    this.quiereComentar = false;
  }


  habilitarCampoRespuesta(): void {
    this.quiereComentar = true;
  }



  enviarComentario(): void {

    if (this.formulario.valid) {

      this.formulario.patchValue({
        fecha: new Date()
      });
      const datosEnvio: SubcomentarioRequest = this.formulario.value;

      this.comentariosService.crearSubcomentario(datosEnvio).subscribe({
        next: () => {
          console.log("Respuesta enviada con éxito");
          this.formulario.get('texto')?.reset();
          this.cargarRespuestas(); // Refrescamos la lista de respuestas
        },
        error: (err) => {
          console.error("Error al enviar respuesta", err);
          alert("No se pudo enviar la respuesta. Intenta de nuevo.");
        }
      });
    }
  }

  cargarRespuestas(): void {
    this.comentariosService.obtenerSubcomentarios(this.comentario.idComentario).subscribe({
      next: (res) => {
        this.listaSubcomentarios = res;
      },
      error: (err) => console.error("Error al cargar subcomentarios", err)
    });
  }

}
