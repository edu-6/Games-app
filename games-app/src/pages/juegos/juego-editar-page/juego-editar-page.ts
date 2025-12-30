import { Component, OnInit } from '@angular/core';
import { JuegosService } from '../../../services/juegos-service';
import { ActivatedRoute } from '@angular/router';
import { Juego } from '../../../models/juegos/juego';
import { JuegoCard } from "../../../components/juegos/juego-card/juego-card";
import { JuegoForm } from "../../../components/juegos/juego-form/juego-form";

@Component({
  selector: 'app-juego-editar-page',
  imports: [ JuegoForm],
  templateUrl: './juego-editar-page.html',
  styleUrl: './juego-editar-page.css',
})
export class JuegoEditarPage implements OnInit {
  nombreJuego !: string;
  juegoEnEdicion !: Juego;

  constructor(private juegosService: JuegosService, private router: ActivatedRoute){

  }
  ngOnInit(): void {
      this.nombreJuego = this.router.snapshot.params['nombre'];
      this.juegosService.buscarJuegoUnico(this.nombreJuego).subscribe({
        next: (juego: Juego) => {
          this.juegoEnEdicion  = juego;
        },
        error: (error: any) => {
          console.log(error);
        }
      });
    }
}
