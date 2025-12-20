import { Component, EventEmitter, Input, OnInit, Output, OutputEmitterRef } from '@angular/core';
import { AdminSistemaSimple } from '../../../models/admins-sistema/admin-simple';
import { AdminsSistemaService } from '../../../services/admin-sistema-service';

@Component({
  selector: 'app-admin-sistema-card',
  imports: [],
  templateUrl: './admin-sistema-card.html',
  styleUrl: './admin-sistema-card.css',
})
export class AdminSistemaCard implements OnInit {
  urlFoto !: string;
 
  @Input({ required: true })
  admin!: AdminSistemaSimple;
  esElMismo: boolean = false;
  

  @Output()
  adminFueSeleccionado = new EventEmitter<AdminSistemaSimple>(); // se le pone de nombre al "evento que se lanza"

  constructor(private adminsService: AdminsSistemaService) {

  }

  eliminarAccion(): void {
    this.adminFueSeleccionado.emit(this.admin);
  }

  ngOnInit(): void {
   this.esElMismo = this.admin.correo === localStorage.getItem("usuario_correo");
    this.adminsService.obtenerImagen(this.admin.correo).subscribe({
      next: (datos: Blob) => {
        this.urlFoto = URL.createObjectURL(datos);
      },
      error: (error: any) => {
        console.log(error.error.mensaje);
      }
    });

  }
}
