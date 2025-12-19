import { Component, DOCUMENT, OnInit } from '@angular/core';
import { Header } from '../../../../components/header/header';
import { RouterLink } from '@angular/router';
import { Categoria } from '../../../../models/categorias/categoria';
import { CategoriasService } from '../../../../services/categorias-service';
import { CategoriaTarjeta } from '../../../../components/categorias/categoria-tarjeta/categoria-tarjeta';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModalGenerico } from '../../../../shared/modal-generico/modal-generico';
@Component({
  selector: 'app-categorias-page',
  imports: [Header, RouterLink, CategoriaTarjeta, FormsModule, ReactiveFormsModule, ModalGenerico],
  templateUrl: './categorias-page.html',
  styleUrl: './categorias-page.css',
})
export class CategoriasPage implements OnInit {
  hayError: boolean = false;
  mostrandoTodas: boolean = false;
  categoriaBuscada!: string;
  mensajeError !: string;
  categoriaEncontrada: Categoria | null = null;
  barraBusqueda!: FormGroup;
  categoriaSeleccionada!: Categoria;

  huboEliminacion: boolean = false;
  mensajeEliminacion!: string;

  protected categorias: Categoria[] = [];

  constructor(private categoriaServicios: CategoriasService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(

      {
        categoria: ["", [Validators.required]]
      }
    );
    //this.cargarDatos();
  }


  cargarDatos(): void {
    this.categoriaServicios.obtenerCategorias().subscribe({
      next: (categoriasServidor: Categoria[]) => {
        this.categorias = categoriasServidor;
        this.mostrandoTodas = true;
      },
      error: (error: any) => {

        console.log(error.error);
      }

    });
  }


  guardarCategoriaSelecionada(categoria: Categoria): void {
    this.categoriaSeleccionada = categoria;
    this.mensajeEliminacion = " Se eliminaran todos los registros relacionados, desea elminar la categoria: " + categoria.categoria + "??";
    this.huboEliminacion = false;
  }


  eliminarRegistro(): void {
    this.categoriaServicios.eliminarCategoria(this.categoriaSeleccionada).subscribe({
      next: () => {
        this.huboEliminacion = true;
        this.cargarDatos();
      },
      error: (error: any)=>{
        this.hayError = true;
        this.mensajeError = error.error.mensaje;
        console.log(this.mensajeError);
        this.cargarDatos();
      }
    });

    
  }


  buscarCategoria(): void {
    this.categoriaEncontrada = null;
    console.log("Estado del formulario:", this.barraBusqueda.valid);
    console.log("Valores actual:", this.barraBusqueda.value);
    if (this.barraBusqueda.valid) {
      this.categoriaBuscada = this.barraBusqueda.value['categoria'];
      this.categoriaServicios.buscarCategoria(this.categoriaBuscada).subscribe({
        next: (categoria: Categoria) => {
          this.mostrandoTodas = false;
          this.categoriaEncontrada = categoria;
        },
        error: (error: any) => {
          this.hayError = true;
          this.mensajeError = error.error.mensaje;
          console.log(error.error);
        }
      });
    }

    this.mostrandoTodas = false;
  }
}
