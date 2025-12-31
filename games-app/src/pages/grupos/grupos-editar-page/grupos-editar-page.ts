import { Component, OnInit } from '@angular/core';
import { GrupoCompleto } from '../../../models/grupos/grupo-completo';
import { GruposService } from '../../../services/grupos-service';
import { ActivatedRoute } from '@angular/router';
import { GrupoForm } from "../../../components/grupos/grupo-form/grupo-form";

@Component({
  selector: 'app-grupos-editar-page',
  standalone: true,
  imports: [GrupoForm],
  templateUrl: './grupos-editar-page.html',
  styleUrl: './grupos-editar-page.css',
})
export class GruposEditarPage implements OnInit {
  idGrupo !: string;
  grupoEdicion !: GrupoCompleto;

  constructor(private gruposService: GruposService, private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.idGrupo = this.router.snapshot.params['id'];
    
    this.gruposService.buscarGrupo(this.idGrupo).subscribe({
      next: (grupo: GrupoCompleto) => {
        this.grupoEdicion = grupo;
      },
      error: (error: any) => {
        console.log("Error al recuperar el grupo:", error);
      }
    });
  }
}