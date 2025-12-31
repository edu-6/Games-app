import { Component, EventEmitter, Input, Output } from '@angular/core';
import { GrupoCompleto } from '../../../models/grupos/grupo-completo';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-grupo-card',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './grupo-card.html',
})
export class GrupoCard {
  @Input({ required: true })
  grupo !: GrupoCompleto;

  @Output()
  grupoFueSeleccionado = new EventEmitter<GrupoCompleto>();

  constructor() { }

  eliminarAccion(): void {
    this.grupoFueSeleccionado.emit(this.grupo);
  }
}