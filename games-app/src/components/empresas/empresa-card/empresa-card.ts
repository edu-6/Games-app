import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Empresa } from '../../../models/empresa/empresa';
import { EmpresasService } from '../../../services/empresas-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-empresa-card',
  imports: [RouterLink],
  templateUrl: './empresa-card.html',
  styleUrl: './empresa-card.css',
})
export class EmpresaCard implements OnInit {
  urlFoto !: string;
 
  @Input({ required: true })
  empresa!: Empresa;
  

  @Output()
  empresaFueSeleccionada = new EventEmitter<Empresa>(); // se le pone de nombre al "evento que se lanza"

  constructor(private empresasService: EmpresasService) {

  }

  eliminarAccion(): void {
    this.empresaFueSeleccionada.emit(this.empresa);
  }

  ngOnInit(): void {
    this.empresasService.obtenerImagen(this.empresa.nombre).subscribe({
      next: (datos: Blob) => {
        this.urlFoto = URL.createObjectURL(datos);
      },
      error: (error: any) => {
        console.log(error.error.mensaje);
      }
    });

  }

}
