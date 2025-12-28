import { Component, OnInit } from '@angular/core';
import { JuegoDetalles } from "../../../components/juegos/juego-detalles/juego-detalles";
import { ActivatedRoute } from '@angular/router';
import { Juego } from '../../../models/juegos/juego';
import { JuegosService } from '../../../services/juegos-service';

@Component({
  selector: 'app-juego-detalles-page',
  imports: [JuegoDetalles],
  templateUrl: './juego-detalles-page.html',
  styleUrl: './juego-detalles-page.css',
})
export class JuegoDetallesPage implements OnInit {
  juego !: Juego
  nombreJuego !: string;

  constructor(private router: ActivatedRoute, private juegosServicios: JuegosService){
  }

  ngOnInit(): void {
     this.nombreJuego = this.router.snapshot.params['nombre'];
        if(!this.nombreJuego){
          console.log(" no está llegando bien");
        }
        this.juegosServicios.buscarJuegoUnico(this.nombreJuego).subscribe({
          next: (juego: Juego) => {
            this.juego  = juego;
            console.log(" se encontró el juego buscado"+ juego);
          },
          error: (error: any) => {
            console.log(error);
          }
        });
  }

}
