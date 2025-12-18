import { Component, Input, OnInit } from '@angular/core';
import { Header } from "../../header/header";
import { Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { KeyValuePipe, NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Categoria } from '../../../models/categorias/categoria';
import { CategoriasService } from '../../../services/categorias-service';
import { EdicionCategoria } from '../../../models/categorias/edicion-categoria';

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
  creacionExitosa: boolean = false;
  edicionExistosa: boolean = false;
  hayError: boolean = false;
  yaFueEditado: boolean = false;
  operationMessage: string | null = null;


  @Input()
  estaEnEdicion: boolean = false;
  @Input()
  categoriaAEditar!: Categoria;



  constructor(private formBuilder: FormBuilder, private categoriaServicios: CategoriasService) {
  }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        categoria: ['', [Validators.required],]
      }
    );

    if (this.estaEnEdicion) {
      this.formulario.reset(this.categoriaAEditar);
    }
  }


  enviar(): void {
    if (this.estaEnEdicion) {
      this.editarCategoria();
    } else {
      this.crearCategoria();
    }
  }


  crearCategoria(): void {
    this.resetearEstados();
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
          console.log('Eror encontrado:', error.error);
        }
      });
    } else {
      console.log("form invalido");
    }

  }

  editarCategoria(): void {
    this.resetearEstados();
    if (this.formulario.valid && this.categoriaAEditar != null) { // si el formulario es valido y la anterior existe
      this.nuevaCategoria = this.formulario.value as Categoria;

      console.log("formulario listo");
      this.categoriaServicios.editarCategoria(this.generarModelo()).subscribe({
        next: () => {
          this.edicionExistosa = true;
          this.yaFueEditado = true;
          this.categoriaAEditar = this.formulario.value as Categoria;
          console.log(this.nuevaCategoria);
        },
        error: (error: any) => {
          this.mensajeError = error.error.mensaje;
          this.hayError = true;
          console.log('Eror encontrado:', error.error);
        }
      });
    } else {
      console.log("form invalido");
    }
  }


  generarModelo(): EdicionCategoria {
    let edicion: EdicionCategoria = {
      nombreAnterior: this.categoriaAEditar.categoria,
      nuevoNombre: this.formulario.value.categoria
    };
    return edicion;
  }

  resetearEstados(): void {
    this.creacionExitosa = false;
    this.edicionExistosa = false;
    this.intentoEnviarlo = true;
    this.hayError = false;
  }
}
