import { Component, OnInit } from '@angular/core';
import { AdminEmpresa } from '../../../models/admin-empresa/admin-empresa';
import { AdminEmpresaService } from '../../../services/admin-empresa-service';
import { ActivatedRoute } from '@angular/router';
import { AmdinEmpresaCard } from "../../../components/admin-empresa/amdin-empresa-card/amdin-empresa-card";
import { AdminSistemaForm } from "../../../components/admin-sistema/admin-sistema-form/admin-sistema-form";
import { AmdinEmpresaForm } from "../../../components/admin-empresa/amdin-empresa-form/amdin-empresa-form";

@Component({
  selector: 'app-amdin-empresa-editar-page',
  imports: [ AmdinEmpresaForm],
  templateUrl: './amdin-empresa-editar-page.html',
  styleUrl: './amdin-empresa-editar-page.css',
})
export class AmdinEmpresaEditarPage implements OnInit{
  correoAdmin !: string;
  adminEdicion !: AdminEmpresa;

  constructor(private adminServicios: AdminEmpresaService, private router: ActivatedRoute){

  }

  ngOnInit(): void {
    this.correoAdmin = this.router.snapshot.params['correo'];
    if(!this.correoAdmin){
      console.log(" noe stá llegando bien ");
    }
    console.log(this.correoAdmin+ "si pues");
    this.adminServicios.buscarAdminUnico(this.correoAdmin).subscribe({
      next: (admin: AdminEmpresa) => {
        this.adminEdicion  = admin;
      },
      error: (error: any) => {
        //console.log(error);
      }
    });
  }


}
