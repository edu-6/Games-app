import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Categoria } from '../../../models/categorias/categoria';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-categoria-tarjeta',
  imports: [RouterLink],
  templateUrl: './categoria-tarjeta.html',
  styleUrl: './categoria-tarjeta.css',
})
export class CategoriaTarjeta {
@Input({ required: true })
  categoriaSeleccionada!: Categoria;

  @Output()
  categoriaFueSeleccionada = new EventEmitter<Categoria>(); // se le pone de nombre al "evento que se lanza" categoría fue seleccionada 


  eliminarAccion(): void {
    this.categoriaFueSeleccionada.emit(this.categoriaSeleccionada);
  }
  
}
