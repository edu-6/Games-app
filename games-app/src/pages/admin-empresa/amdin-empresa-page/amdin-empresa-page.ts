import { Component } from '@angular/core';
import { AdminEmpresaSimple } from '../../../models/admin-empresa/admin-empresa-simple';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminEmpresaService } from '../../../services/admin-empresa-service';
import { Header } from "../../../components/header/header";
import { RouterLink } from '@angular/router';
import { AmdinEmpresaCard } from "../../../components/admin-empresa/amdin-empresa-card/amdin-empresa-card";
import { ModalGenerico } from '../../../shared/modal-generico/modal-generico';

@Component({
  selector: 'app-amdin-empresa-page',
  imports: [Header, ReactiveFormsModule, RouterLink, AmdinEmpresaCard, ModalGenerico],
  templateUrl: './amdin-empresa-page.html',
  styleUrl: './amdin-empresa-page.css',
})
export class AmdinEmpresaPage {
  hayError: boolean = false;
  mostrarTodos: boolean = false;
  adminBuscado !: string;
  mensajeError !: string;
  adminEncontrado: AdminEmpresaSimple | null = null;
  barraBusqueda!: FormGroup;
  adminSeleccionado!: AdminEmpresaSimple;
  hayBusqueda: boolean = false;

  huboEliminacion: boolean = false;
  mensajeEliminacion!: string;

  protected admins: AdminEmpresaSimple[] = [];
  protected adminsEncontrados: AdminEmpresaSimple[] = [];

  constructor(private adminsServicios: AdminEmpresaService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(
      {
        adminBuscado: ["", [Validators.required]]
      }
    );
    //this.cargarDatos();
  }


  cargarTodosLosAdmins(): void {
    const correo: string = localStorage.getItem('correo') ?? 'evitar_nulo';
    this.hayBusqueda = false;
    this.adminsServicios.obtenerAdmins(correo).subscribe({
      next: (adminsServidor: AdminEmpresaSimple[]) => {
        this.admins = adminsServidor;
        console.log(this.admins);
        this.mostrarTodos = true;
      },
      error: (error: any) => {
        console.log(error.error);
      }
    });
  }

  cargarDatosSegunEstado() {  // si se eliminó o editó algo se cargan los todos os datos o las buqeduas 
    if (this.hayBusqueda) {
      this.buscarAdmin();
    } else {
      this.cargarTodosLosAdmins();
    }
  }


  guardarAdminSeleccionado(admin: AdminEmpresaSimple): void {
    this.adminSeleccionado = admin;
    this.mensajeEliminacion = " Se eliminará a " + admin.nombre + " como admin-sistema desea eliminarlo??";
    this.huboEliminacion = false;
  }


  eliminarRegistro(): void {
    this.adminsServicios.eliminarAdmin(this.adminSeleccionado.correo).subscribe({
      next: () => {
        this.huboEliminacion = true;
        this.cargarDatosSegunEstado();

      },
      error: (error: any) => {
        this.hayError = true;
        this.mensajeError = error.error.mensaje;
        console.log(this.mensajeError);
        this.cargarDatosSegunEstado();
      }
    });


  }


  buscarAdmin(): void {
    this.hayBusqueda = true;
    this.adminEncontrado = null;
    console.log("Estado del formulario:", this.barraBusqueda.valid);
    console.log("Valores actual:", this.barraBusqueda.value);
    if (this.barraBusqueda.valid) {
      this.adminBuscado = this.barraBusqueda.value['adminBuscado'];
      this.adminsServicios.buscarAdmins(this.adminBuscado).subscribe({
        next: (admins: AdminEmpresaSimple[]) => {
          this.mostrarTodos = false;
          this.adminsEncontrados = admins;
        },
        error: (error: any) => {
          this.hayError = true;
          this.mensajeError = error.error.mensaje;
          console.log(error.error);
        }
      });
    }

    this.mostrarTodos = false;
  }

}
