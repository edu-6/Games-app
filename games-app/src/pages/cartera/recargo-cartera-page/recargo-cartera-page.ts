import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Header } from "../../../components/header/header";
import { UsuarioServicios } from '../../../services/usuarios/usuarios-service';
import { RecargoTarjeta } from '../../../models/usuarios/tarjeta/recargo-tarjeta';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-recargo-cartera-page',
  imports: [ReactiveFormsModule, Header,RouterLink],
  templateUrl: './recargo-cartera-page.html',
  styleUrl: './recargo-cartera-page.css',
})
export class RecargoCarteraPage implements OnInit {
  formulario!: FormGroup;
  hayError: boolean = false;
  intentoEnviarlo: boolean = false;
  mensajeError: string = "alskdjf";
  recargo !: RecargoTarjeta

  constructor(private formBuilder: FormBuilder, private usuariosService: UsuarioServicios, private router: Router) {

  }
  ngOnInit(): void {
    this.formulario = this.formBuilder.group(
      {
        deposito: [null, [Validators.required, Validators.min(1)]],
        correoGamer:[this.generarCorreoDelGamer()]
      }
    );
  }

  generarCorreoDelGamer(): string | null {
    let correo: string | null = null;
    correo = localStorage.getItem('correo');
    if (correo != null) {
      return correo;
    }
    return null;
  }

  recargarTarjeta() {
    this.intentoEnviarlo = true;
    if (this.formulario.valid) {
      this.recargo = this.formulario.value as RecargoTarjeta;
      this.usuariosService.recargarTarjeta(this.recargo).subscribe({
        next: () => {
          this.router.navigate(["/cartera"]);
        },
        error: (error: any) =>{
          this.mensajeError = error.error.mensajeError;
        }
      });
    } else {
      console.log(" formulario invalido");
    }

  }
}
