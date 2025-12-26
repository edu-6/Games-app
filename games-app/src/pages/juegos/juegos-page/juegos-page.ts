import { Component, OnInit } from '@angular/core';
import { Header } from '../../../components/header/header';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ClasificacionEdad } from '../../../models/juegos/clasificaicon-edad';
import { KeyValuePipe, NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-juegos-page',
  imports: [Header, ReactiveFormsModule,RouterLink,FormsModule, ReactiveFormsModule, KeyValuePipe, NgFor],
  templateUrl: './juegos-page.html',
  styleUrl: './juegos-page.css',
})
export class JuegosPage  implements OnInit{
  barraBusqueda!: FormGroup;
  intentoEnviarlo: boolean = false;
  clasificacionesEnum = ClasificacionEdad;


  constructor( private formBuilder: FormBuilder) { }


  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(
      {
        nombre : ["", [Validators.required]],
        precioMin : ["", [Validators.required, Validators.min(1)]],
        precioMax : ["", [Validators.required], Validators.min(1)],
        clasificacion : ["", [Validators.required]]
      }
    );
    //this.cargarDatos();
  }

  buscarJuego(){

  }
}
