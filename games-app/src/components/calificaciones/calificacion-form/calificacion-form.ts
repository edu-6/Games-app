import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-calificacion-form',
  imports: [ReactiveFormsModule],
  templateUrl: './calificacion-form.html',
  styleUrl: './calificacion-form.css',
})
export class CalificacionForm implements OnInit {
  formulario !: FormGroup;
  intentoEnviarlo : boolean = false;
  @Input({required : true})
  nombreJuego !: string;
  constructor(private formBuilder: FormBuilder){
  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        calificacion: [null, Validators.required, Validators.min(1), Validators.max(5)]
      }
    );
  }


  enviar(): void {
    
  }

}
