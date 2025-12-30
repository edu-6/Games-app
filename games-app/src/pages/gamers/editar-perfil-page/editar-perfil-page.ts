import { Component, OnInit } from '@angular/core';
import { UsuarioServicios } from '../../../services/usuarios/usuarios-service';
import { ActivatedRoute } from '@angular/router';
import { GamerRegistro } from '../../../models/usuarios/usuario-gamer/gamer-registro';
import { RegistroForm } from "../../../components/registro-form/registro-form";

@Component({
  selector: 'app-editar-perfil-page',
  imports: [RegistroForm],
  templateUrl: './editar-perfil-page.html',
  styleUrl: './editar-perfil-page.css',
})
export class EditarPerfilPage  implements OnInit {
  correo !: string;
  gamerEdicion!: GamerRegistro;
  constructor( private usuarioService: UsuarioServicios, private router: ActivatedRoute){

  }

  ngOnInit(): void {
      this.correo = this.router.snapshot.params['correo'];
      this.usuarioService.obtenerGamerPorCorreo(this.correo).subscribe({
        next: (gamer: GamerRegistro) => {
          this. gamerEdicion = gamer;
        },
        error: (error: any) => {
          console.log(error);
        }
      });
    }
}
