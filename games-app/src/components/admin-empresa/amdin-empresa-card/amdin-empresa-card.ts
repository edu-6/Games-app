import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AdminEmpresaService } from '../../../services/admin-empresa-service';
import { AdminEmpresaSimple } from '../../../models/admin-empresa/admin-empresa-simple';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-amdin-empresa-card',
  imports: [RouterLink],
  templateUrl: './amdin-empresa-card.html',
  styleUrl: './amdin-empresa-card.css',
})
export class AmdinEmpresaCard implements OnInit{
urlFoto !: string;
 
  @Input({ required: true })
  admin!: AdminEmpresaSimple;
  esElMismo: boolean = false;
  

  @Output()
  adminFueSeleccionado = new EventEmitter<AdminEmpresaSimple>(); // se le pone de nombre al "evento que se lanza"

  constructor(private adminsService: AdminEmpresaService) {

  }

  eliminarAccion(): void {
    this.adminFueSeleccionado.emit(this.admin);
  }

  ngOnInit(): void {
   this.esElMismo = this.admin.correo === localStorage.getItem("correo");
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
