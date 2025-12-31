import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-comentarios-component',
  imports: [],
  templateUrl: './comentarios-component.html',
  styleUrl: './comentarios-component.css',
})
export class ComentariosComponent implements OnInit {
  formulario !: FormGroup;
  @Input({ required: true })
  nombreJuego !: string;

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    // Inicialización del Form Group
    this.formulario = this.formBuilder.group({
      usuario: ['Usuario Invitado'], // Opcional
      texto: ['', [Validators.required]],
      fecha: [new Date()] // Guarda el objeto Date completo (Fecha, hora, segundos)
    });
  }


}
