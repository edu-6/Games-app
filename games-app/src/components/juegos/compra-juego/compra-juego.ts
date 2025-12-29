import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ComprasService } from '../../../services/compras-service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CompraJuego } from '../../../models/compras/compra-juego';
import { SaldoTarjeta } from '../../../models/usuarios/tarjeta/saldo-tarjeta';
import { UsuarioServicios } from '../../../services/usuarios/usuarios-service';
import { JuegosService } from '../../../services/juegos-service';
import { Juego } from '../../../models/juegos/juego';
import { Header } from "../../header/header";

@Component({
  selector: 'app-compra-juego',
  imports: [ReactiveFormsModule, RouterLink, Header],
  templateUrl: './compra-juego.html',
  styleUrl: './compra-juego.css',
})
export class CompraDeJuego implements OnInit {
  formulario!: FormGroup;
  mensajeError !: String;
  hayArchivoCargado: boolean = false;
  intentoEnviarlo: boolean = false;
  hayError: boolean = false;
  creacionExitosa: boolean = false;
  creacionJsonExistosa: boolean = false;
  nombreJuego !: string;
  saldo !: number;
  juego !: Juego;
  yaLoCompro : boolean = false;


  constructor(private formBuilder: FormBuilder,
    private compraServicios: ComprasService,
    private usuariosService: UsuarioServicios,
    private juegosServicios: JuegosService, private router: ActivatedRoute, private router2: Router) {
  }

  ngOnInit(): void {
    this.nombreJuego = this.router.snapshot.params['nombre'];
    this.formulario = this.formBuilder.group(
      {
        fechaCompra: [new Date().toISOString().substring(0, 10), Validators.required],
      }
    );
    // obtener el saldo de la tarjeta
    let correo: string | null = null;
    correo = localStorage.getItem('correo');
    if (correo != null) {
      this.usuariosService.verSaltoTarjeta(correo).subscribe({
        next: (saldoTarjeta: SaldoTarjeta) => {
          this.saldo = saldoTarjeta.saldo;
        }
      });
    }
    // obtener toda la información del juego
    this.juegosServicios.buscarJuegoUnico(this.nombreJuego).subscribe({
      next: (juego: Juego) => {
        this.juego = juego;
      },
      error: (error: any) => {
        console.log(error);
      }
    });

  }

  enviar(): void {
    this.hayError = false;
    let fechaSeleccionada = this.formulario.get('fechaCompra')?.value;
    let correoUsuario: string | null = localStorage.getItem('correo');
    if (correoUsuario != null && fechaSeleccionada != null) {
      let compra: CompraJuego = new CompraJuego(fechaSeleccionada, this.nombreJuego, correoUsuario);
      this.compraServicios.realizarCompra(compra).subscribe({
        next: () => {
          this.router2.navigate(['/juegos']);
        },
        error: (error: any) => {
          this.hayError = true;
          this.mensajeError = error.error.mensaje;
          console.log(this.mensajeError);
        }
      });
    }
  }

}
