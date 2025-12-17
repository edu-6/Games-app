import { Component, OnInit } from '@angular/core';
import { Header } from "../../header/header";
import { Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { KeyValuePipe, NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Categoria } from '../../../models/categoria';
import { CategoriasService } from '../../../services/categorias-service';

@Component({
  selector: 'app-nueva-categoria-from',
  imports: [RouterLink, FormsModule, ReactiveFormsModule],
  templateUrl: './nueva-categoria-from.html',
  styleUrl: './nueva-categoria-from.css',
})
export class NuevaCategoriaFrom implements OnInit {
  nuevaCategoria !: Categoria;
  mensajeError !: string;
  formulario!: FormGroup;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;




  constructor(private formBuilder: FormBuilder, private categoriaServicios: CategoriasService) {
  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        categoria: ['', [Validators.required],]
      }
    );
  }


  enviar(): void {
    this.intentoEnviarlo = true;
    this.hayError = false;
    if (this.formulario.valid) {
      this.nuevaCategoria = this.formulario.value as Categoria;
      console.log("formulario listo");
      this.categoriaServicios.crearCategoria(this.nuevaCategoria).subscribe({
        next: () => {
          this.creacionExitosa = true;
          console.log(this.nuevaCategoria);
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
