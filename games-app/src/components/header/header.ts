import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {

  constructor(private router: Router){

  }
  
  public logout(): void{
    localStorage.removeItem("correo");
    localStorage.removeItem("rol");
    this.router.navigate(["/"]);
  }
}



