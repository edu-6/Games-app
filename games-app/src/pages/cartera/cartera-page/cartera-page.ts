import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuarioServicios } from '../../../services/usuarios/usuarios-service';
import { SaldoTarjeta } from '../../../models/usuarios/tarjeta/saldo-tarjeta';
import { RouterLink } from '@angular/router';
import { Header } from "../../../components/header/header";

@Component({
  selector: 'app-cartera-page',
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './cartera-page.html',
  styleUrl: './cartera-page.css',
})
export class CarteraPage implements OnInit {
  saldo!: number;

  constructor(private usuariosService: UsuarioServicios) {

  }

  ngOnInit(): void {
    let correo: string | null = null;
    correo = localStorage.getItem('correo');
    if (correo != null) {
      this.usuariosService.verSaltoTarjeta(correo).subscribe({
        next: (saldoTarjeta: SaldoTarjeta) => {
          this.saldo = saldoTarjeta.saldo;
        }
      });
    }
  }
}
