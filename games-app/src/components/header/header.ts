import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { GuardiaRolesServicio } from '../../services/seguridad/GuardiaDeRolesServicio';
@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {

  adminSistema !: boolean
  adminEmpresa !: boolean;
  gamer !: boolean;



  constructor(private router: Router, private guardiaServicio: GuardiaRolesServicio) {
    this.adminSistema = guardiaServicio.userRoleInAllowedRoles(['ADMIN_SISTEMA']);
    this.adminEmpresa = guardiaServicio.userRoleInAllowedRoles(['ADMIN_EMPRESA']);
    this.gamer = guardiaServicio.userRoleInAllowedRoles(['GAMER']);
  }

  public logout(): void {
    localStorage.removeItem("correo");
    localStorage.removeItem("rol");
    this.router.navigate(["/"]);
  }
}



