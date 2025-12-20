import { Component, OnInit } from '@angular/core';
import { Header } from '../../../../components/header/header';
import { AdminSistemaSimple } from '../../../../models/admins-sistema/admin-simple';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminsSistemaService } from '../../../../services/admin-sistema-service';
import { RouterLink } from '@angular/router';
import { ModalGenerico } from '../../../../shared/modal-generico/modal-generico';
import { AdminSistemaCard } from "../../../../components/admin-sistema/admin-sistema-card/admin-sistema-card";

@Component({
  selector: 'app-admins-sistema-page',
  imports: [Header, FormsModule, ReactiveFormsModule, RouterLink, ModalGenerico, AdminSistemaCard],
  templateUrl: './admins-sistema-page.html',
  styleUrl: './admins-sistema-page.css',
})
export class AdminsSistemaPage implements OnInit {

  hayError: boolean = false;
  mostrarTodos: boolean = false;
  adminBuscado !: string;
  mensajeError !: string;
  adminEncontrado: AdminSistemaSimple | null = null;
  barraBusqueda!: FormGroup;
  adminSeleccionado!: AdminSistemaSimple;
  hayBusqueda: boolean = false;

  huboEliminacion: boolean = false;
  mensajeEliminacion!: string;

  protected admins: AdminSistemaSimple[] = [];
  protected adminsEncontrados: AdminSistemaSimple[] = [];

  constructor(private adminsServicios: AdminsSistemaService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(

      {
        adminBuscado: ["", [Validators.required]]
      }
    );
    //this.cargarDatos();
  }


  cargarTodosLosAdmins(): void {
    this.hayBusqueda = false;
    this.adminsServicios.obtenerAdmins().subscribe({
      next: (adminsServidor: AdminSistemaSimple[]) => {
        this.admins = adminsServidor;
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


  guardarAdminSeleccionado(admin: AdminSistemaSimple): void {
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
        next: (admins: AdminSistemaSimple[]) => {
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
