import { Component, OnInit } from '@angular/core';
import { AdminSistema } from '../../../../models/admins-sistema/admin-creacion';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AdminsSistemaService } from '../../../../services/admin-sistema-service';
import { AdminSistemaForm } from '../../../../components/admin-sistema/admin-sistema-form/admin-sistema-form';
import { Header } from "../../../../components/header/header";

@Component({
  selector: 'app-admins-sistema-editar-page',
  imports: [AdminSistemaForm],
  templateUrl: './admins-sistema-editar-page.html',
  styleUrl: './admins-sistema-editar-page.css',
})
export class AdminsSistemaEditarPage implements OnInit {
  correoAdmin !: string;
  adminEdicion !: AdminSistema;

  constructor(private adminServicios: AdminsSistemaService, private router: ActivatedRoute){

  }

  ngOnInit(): void {
    this.correoAdmin = this.router.snapshot.params['correo'];
    this.adminServicios.buscarAdminUnico(this.correoAdmin).subscribe({
      next: (admin: AdminSistema) => {
        this.adminEdicion  = admin;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
