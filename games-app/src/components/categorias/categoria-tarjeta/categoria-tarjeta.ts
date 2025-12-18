import { Component, Input } from '@angular/core';
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
  categoria!: Categoria;

  /**
  @Output()
  eventSelected = new EventEmitter<Event>();

  isAdmin: boolean;

  constructor(private roleGuardService: RoleGuardService) {
    this.isAdmin = roleGuardService.userRoleInAllowedRoles(['ADMIN']);
  }

  deleteAction(): void {
    this.eventSelected.emit(this.selectedEvent);
  }
  */
}
