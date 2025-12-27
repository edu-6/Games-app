import { Component, OnInit } from '@angular/core';
import { Header } from '../../../components/header/header';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ClasificacionEdad } from '../../../models/juegos/clasificaicon-edad';
import { KeyValuePipe, NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { GuardiaRolesServicio } from '../../../services/seguridad/GuardiaDeRolesServicio';
import { Empresa } from '../../../models/empresa/empresa';
import { EmpresasService } from '../../../services/empresas-service';

@Component({
  selector: 'app-juegos-page',
  imports: [Header, ReactiveFormsModule, RouterLink, FormsModule, ReactiveFormsModule, KeyValuePipe, NgFor],
  templateUrl: './juegos-page.html',
  styleUrl: './juegos-page.css',
})
export class JuegosPage implements OnInit {
  barraBusqueda!: FormGroup;
  intentoEnviarlo: boolean = false;
  clasificacionesEnum = ClasificacionEdad;
  esAdminEmpresa !: boolean;
  empresas  !: Empresa[];
  tiposBusqueda!: string[];
  tipoBusquedaActual: string = "nombre";

  constructor(private formBuilder: FormBuilder,
    private empresasServicio: EmpresasService, private guardiaServicio: GuardiaRolesServicio) {
    this.esAdminEmpresa = guardiaServicio.userRoleInAllowedRoles(['ADMIN_EMPRESA']);

    // inicializar los tipos de busqueda segun el rol del usuario
    if (this.esAdminEmpresa) {
      this.tiposBusqueda = ['nombre'];
    } else {
      this.tiposBusqueda = ['nombre', 'empresa'];
    }
  }


  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(
      {
        nombre: ["", [Validators.required]],
        precioMin: ["", [Validators.required, Validators.min(1)]],
        precioMax: ["", [Validators.required], Validators.min(1)],
        clasificacion: ["", [Validators.required]]
      }
    );
    // obtener las empresas
    this.empresasServicio.obtenerEmpresas().subscribe({
      next: (empresas: Empresa[]) => {
        this.empresas = empresas;
      },
    }
    );
  }

  cambiarTipoDeBusqueda(evento: any) {
    let tipo = evento.target.value;
    if (tipo == 0) {
      this.tipoBusquedaActual = "nombre";
    } else if (tipo == 1) {
      this.tipoBusquedaActual = "empresa";
    }
  }

  buscarJuego() {
    switch (this.tipoBusquedaActual) {
      case "nombre":
        console.log(" busqueda por nombre");
        break;
      case "precio":
        console.log(" busqueda por precio");
        break;
      case "empresa":
        console.log(" busqueda por empresa");
        break;
    }
  }
}
