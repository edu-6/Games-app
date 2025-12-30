import { Component, OnInit } from '@angular/core';
import { Header } from '../../../components/header/header';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ClasificacionEdad } from '../../../models/juegos/clasificaicon-edad';
import { KeyValuePipe, NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { GuardiaRolesServicio } from '../../../services/seguridad/GuardiaDeRolesServicio';
import { Empresa } from '../../../models/empresa/empresa';
import { EmpresasService } from '../../../services/empresas-service';
import { BusquedaJuego } from '../../../models/juegos/busqueda-juego';
import { Juego } from '../../../models/juegos/juego';
import { JuegosService } from '../../../services/juegos-service';
import { JuegoCard } from "../../../components/juegos/juego-card/juego-card";
import { ModalGenerico } from "../../../shared/modal-generico/modal-generico";

@Component({
  selector: 'app-juegos-page',
  imports: [Header, ReactiveFormsModule, RouterLink, FormsModule, ReactiveFormsModule, KeyValuePipe, NgFor, JuegoCard,ModalGenerico],
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
  juegos!: Juego[];
  juegoSeleccionado !: Juego;
  mensajeEliminacion !: string;
  huboEliminacion !: boolean;
  hayError !: boolean;
  mensajeError !: string;


  constructor(private formBuilder: FormBuilder,
    private empresasServicio: EmpresasService,
    private juegosService: JuegosService, private guardiaServicio: GuardiaRolesServicio) {
    this.esAdminEmpresa = guardiaServicio.userRoleInAllowedRoles(['ADMIN_EMPRESA']);

    // inicializar los tipos de busqueda segun el rol del usuario
    if (this.esAdminEmpresa) {
      this.tiposBusqueda = ['nombre'];
    } else {
      this.tiposBusqueda = ['nombre', 'empresa'];
      this.obtenerEmpresas();
    }
  }


  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(
      {
        nombre: ["", [Validators.required]],
        precioMin: ["", [Validators.required, Validators.min(1)]],
        precioMax: ["", [Validators.required], Validators.min(1)],
        clasificacion: ["", [Validators.required]],
        empresa: ["", [Validators.required]]
      }
    );
    // buscar juegos
    this.buscarJuego();
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
    const nombre = this.barraBusqueda.get('nombre')?.value || null;
    const pMin = this.barraBusqueda.get('precioMin')?.value || 0;
    const pMax = this.barraBusqueda.get('precioMax')?.value || 0;
    const categoriaSeleccionada = null; // aquí se agrega la categoría (despues)
    const empresa = this.barraBusqueda.get('empresa')?.value || null;
    let correoAdmin: string | null = null;

    if (this.esAdminEmpresa) {
      correoAdmin = localStorage.getItem('correo');
    } else {
      correoAdmin = empresa; // se usa el nombre de la empresas para encontrar id
    }
    const busqueda = new BusquedaJuego(
      nombre,
      pMax,
      pMin,
      categoriaSeleccionada,
      correoAdmin
    );

    this.juegosService.buscarJuegos(busqueda).subscribe({
      next: (juegos: Juego[]) => {
        this.juegos = juegos;
        console.log(juegos);
      },
      error: (error: any) => {
        console.log(error.error.mensajeError);
      }
    });
  }

  guardarEntidadSeleccionada(juego: any){
    this.juegoSeleccionado = juego;
    this.mensajeEliminacion = " Se eliminará a " + juego.nombre + "desea continuar?";
    this.huboEliminacion = false;
  }


  eliminarRegistro(): void {
    console.log(this.juegoSeleccionado.nombre);
    this.juegosService.eliminarJuego(this.juegoSeleccionado.nombre).subscribe({
      next: () => {
        this.huboEliminacion = true;
        this.buscarJuego(); // para cargar los juegos segun situación etcc
      },
      error: (error: any) => {
        this.hayError= true;
        this.mensajeError = error.error.mensaje;
        console.log(this.mensajeError);
       this.buscarJuego();
      }
    });

  }


  obtenerEmpresas(): void{
    this.empresasServicio.obtenerEmpresas().subscribe({
      next:(empresas: Empresa[])=>{
        this.empresas = empresas;
      }
    });
  }
}
