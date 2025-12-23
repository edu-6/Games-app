import { Component, OnInit } from '@angular/core';
import { Empresa } from '../../../models/empresa/empresa';
import { EmpresasService } from '../../../services/empresas-service';
import { ActivatedRoute } from '@angular/router';
import { EmpresaForm } from "../../../components/empresas/empresa-form/empresa-form";
import { EmpresaCard } from '../../../components/empresas/empresa-card/empresa-card';

@Component({
  selector: 'app-empresas-editar-page',
  imports: [EmpresaForm],
  templateUrl: './empresas-editar-page.html',
  styleUrl: './empresas-editar-page.css',
})
export class EmpresasEditarPage implements OnInit {
  nombreEmpresa !: string;
  empresaEdicion !: Empresa;

  constructor(private  empresaServicios: EmpresasService, private router: ActivatedRoute){

  }

  ngOnInit(): void {
    this.nombreEmpresa = this.router.snapshot.params['nombre'];
    console.log(this.nombreEmpresa);
    this.empresaServicios.buscarEmpresaUnica(this.nombreEmpresa).subscribe({
      next: (empresa: Empresa) => {
        this.empresaEdicion  = empresa;
        console.log(this.empresaEdicion);
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
