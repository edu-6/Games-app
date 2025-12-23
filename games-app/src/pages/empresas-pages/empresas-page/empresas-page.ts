import { Component, OnInit } from '@angular/core';
import { Header } from "../../../components/header/header";
import { Empresa } from '../../../models/empresa/empresa';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmpresasService } from '../../../services/empresas-service';
import { RouterLink } from '@angular/router';
import { EmpresaCard } from "../../../components/empresas/empresa-card/empresa-card";
import { ModalGenerico } from '../../../shared/modal-generico/modal-generico';

@Component({
  selector: 'app-empresas-page',
  imports: [Header, FormsModule, ReactiveFormsModule, RouterLink, EmpresaCard, ModalGenerico],
  templateUrl: './empresas-page.html',
  styleUrl: './empresas-page.css',
})
export class EmpresasPage implements OnInit {

  hayError: boolean = false;
  mostrarTodos: boolean = false;
  empresaBuscada !: string;
  mensajeError !: string;
  empresaEncontrada: Empresa  | null = null;
  barraBusqueda!: FormGroup;
  empresaSeleccionada!: Empresa;
  hayBusqueda: boolean = false;

  huboEliminacion: boolean = false;
  mensajeEliminacion!: string;

  protected empresas: Empresa[] = [];
  protected empresasEncontradas: Empresa[] = [];

  constructor(private empresasServicios: EmpresasService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.barraBusqueda = this.formBuilder.group(
      {
        empresaBuscada : ["", [Validators.required]]
      }
    );
    //this.cargarDatos();
  }


  cargarTodasLasEmpresas(): void {
    this.hayBusqueda = false;
    this.empresasServicios.obtenerEmpresas().subscribe({
      next: (empresas: Empresa[]) => {
        this.empresas = empresas;
        this.mostrarTodos = true;
      },
      error: (error: any) => {
        console.log(error.error);
      }
    });
  }

  cargarDatosSegunEstado() {  // si se eliminó o editó algo se cargan los todos os datos o busquedas
    if (this.hayBusqueda) {
      this.buscarEmpresa();
    } else {
      this.cargarTodasLasEmpresas();
    }
  }


  guardarEmpresaSeleccionada(empresa: Empresa): void {
    this.empresaSeleccionada = empresa;
    this.mensajeEliminacion = " Se eliminará a " + empresa.nombre + " como empresa, desea continuar?";
    this.huboEliminacion = false;
  }


  eliminarRegistro(): void {
    this.empresasServicios.eliminarEmpresa(this.empresaSeleccionada.nombre).subscribe({
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


  buscarEmpresa(): void {
    this.hayBusqueda = true;
    this.empresaEncontrada = null;
    console.log("Estado del formulario:", this.barraBusqueda.valid);
    console.log("Valores actual:", this.barraBusqueda.value);
    if (this.barraBusqueda.valid) {
      this.empresaBuscada = this.barraBusqueda.value['empresaBuscada'];
      this.empresasServicios.buscarEmpresaUnica(this.empresaBuscada).subscribe({
        next: (empresa: Empresa) => {
          this.mostrarTodos = false;
          this.empresaEncontrada = empresa;
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
